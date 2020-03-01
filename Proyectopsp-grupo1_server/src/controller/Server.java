package controller;


import model.Model;
import model.VO.Incidence;
import model.VO.IncidenceAdmin;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

/**
 * @author David and Alexis
 */
public class Server extends JFrame {

    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextArea logArea;

    private static Server s = null;
    private Stack<String> emailsAtendidos = new Stack<String>();

    private ArrayList<Socket> admins;
    private ArrayList<Integer> puertos;
    private ArrayList<String> direcciones;

    private Model model;

    private Server() {
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Server");
        initComponents();
        chargeLayout();
        model = new Model();
        puertos = new ArrayList<>();
        direcciones = new ArrayList<>();
    }


    private void initComponents() {
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        logArea = new JTextArea();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        logArea.setEditable(false);
        logArea.setColumns(20);
        logArea.setRows(5);
        scrollPane1.setViewportView(logArea);
    }

    public void chargeLayout() {
        GroupLayout panel1Layout = new GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }



    public static void main(String args[]) throws InterruptedException, IOException {
        s = new Server();
        s.admins = new ArrayList<>();

        s.setVisible(true);
        s.getLogArea().append(s.getHour() + " - Server online. \n");
        Thread.sleep(1000);
        s.getLogArea().append(s.getHour() + " - Waiting response... \n");

        //SE CREA EL SOCKET PARA RECIVIR LAS INCIDENCIAS
        ServerSocket socket = new ServerSocket(13300);

        while (true) {
            System.out.println("Lee");
            Socket recieve = socket.accept();
            DataInputStream read = new DataInputStream(recieve.getInputStream());

            //EL PRIMER DATO QUE LLEGARÁ A TRAVÉS DEL STREAM SERÁ UN BYTE QUE
            //DETERMINE EL ROL DE QUIEN COMIENZA LA CONEXIÓN, Y EN CASO DE LOS
            //INCIDENCE ADMIN, SI ES UNA CONEXIÓN, O CIERRE DE CONEXIÓN PORQUE
            // HAY QUE QUITARLOS DEL ARRAYLIST ADMIN.

            int rol = read.readInt();
            System.out.println("Lee "+rol);

            switch (rol) {

                //Client connects
                case 31:
                    s.getLogArea().append(s.getHour() + " - Connecting client...\n");
                    ThreadCliente ThreadCliente = new ThreadCliente(recieve);
                    ThreadCliente.start();
                    break;


                //Client opens chat
                case 35:
                    s.getLogArea().append(" - Configurating chat...\n");
                    ThreadConfChat tcc = new ThreadConfChat(recieve);
                    tcc.start();
                    break;

                //Entra IncidenceAdmin
                case 21:
                    s.getLogArea().append(s.getHour() + " - Incidence admin connected.\n");
                    ThreadIncidenceAdmin threadIncidenceAdmin = new ThreadIncidenceAdmin(recieve);
                    threadIncidenceAdmin.start();
                    break;

                //Entra SysAdmin
                case 11:
                    s.admins.add(recieve);
                    s.getLogArea().append(s.getHour() + " - System admin connected.\n");
                    ThreadSystemAdmin threadSystemAdmin = new ThreadSystemAdmin(recieve);
                    threadSystemAdmin.start();
                    break;

                case 10:
                    s.getLogArea().append(s.getHour() + " - Connecting administrator...\n");
                    ThreadAdminLogin threadAdminLogin = new ThreadAdminLogin(recieve);
                    threadAdminLogin.start();
                    break;

                default:
                    System.out.println(" - Sale");
                    break;
            }
        }
    }


    public JTextArea getLogArea() {
        return logArea;
    }


    public synchronized void addIncidenceAdminToList(Socket admin){
        s.admins.add(admin);
        notifyAll();
    }

    public synchronized ArrayList<Incidence> get(String mail) {
        // CONSULTA DE LA BASE DE DATOS PARA LLENAR LA LISTA DE INCIDENCIAS
        return s.model.getClientIncidences(mail);
    }

    public synchronized Incidence put(Incidence i) {
        // INSERTA CONSULTA EN BASE DE DATOS  devuelve la incidencia insertada
        return s.model.saveIncidence(i);
    }

    public synchronized Incidence updateIncidence(Incidence i) {
        return s.model.updateIncidence(i);
    }

    public synchronized String getLogin(ArrayList<String> credentials) {
        return s.model.getLogin(credentials);
    }

    public synchronized ArrayList<Incidence> getAdminIncidences(String adminName) {
        return s.model.getAdminIncidences(adminName);
    }

    public synchronized boolean saveIncidenceAdmin(IncidenceAdmin incidenceAdmin){
        return s.model.saveIncidenceAdmin(incidenceAdmin);
    }

    public synchronized void removeIncidenceAdmin(IncidenceAdmin incidenceAdmin){
        s.model.removeIncidenceAdmin(incidenceAdmin);
    }


    public static Server getServer() {
        return s;
    }

    public Stack getCola() {
        return emailsAtendidos;
    }

    public synchronized void removeAdmin(InetAddress inetAddress) {
        for (Socket i : admins) {
            if (i.getInetAddress().equals(inetAddress)) {
                admins.remove(i);
            }
        }
    }

    public synchronized int getPuerto() {
        boolean flag = true;
        int puerto = 0;
        while (flag) {
            puerto = (int) (Math.random() * 65000);
            if (!s.puertos.contains(puerto)) {
                puertos.add(puerto);
                flag = false;
            }
        }
        return puerto;
    }

    public synchronized String getDireccion() {
        String direccion = "0";
        boolean flag = true;
        while (flag) {
            String c1 = String.valueOf((int) (Math.random() * 15) + 224);
            String c2 = String.valueOf((int) (Math.random() * 255));
            String c3 = String.valueOf((int) (Math.random() * 255));
            String c4 = String.valueOf((int) (Math.random() * 255));

            direccion = c1 + "." + c2 + "." + c3 + "." + c4;

            if (!direcciones.contains(direccion)) {
                direcciones.add(direccion);
                flag = false;
            }
        }

        return direccion;
    }

    public synchronized Socket getAdminForChat() {

        while (admins.size()<=0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        int adminSelected = (int) (Math.random() * (admins.size() - 1));
        Socket administratorSocket = admins.get(adminSelected);
        admins.remove(adminSelected);
        return administratorSocket;
    }




    public static String getHour() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String hora = dateFormat.format(date);
        return hora;
    }


    public void writeCloseClient() {
        s.getLogArea().append(getHour() + " - Client disconnected\n");
    }

    public void writeCloseIncidenceAdmin() {
        s.getLogArea().append(getHour() + " - Incidence admin disconnected\n");
    }
    public void writeCloseSystemAdmin() {
        s.getLogArea().append(getHour() + " - System admin disconnected\n");
    }

    public ArrayList<IncidenceAdmin>getIncidenceAdmins(){
        return s.model.getIncidenceAdmins();
    }
}
