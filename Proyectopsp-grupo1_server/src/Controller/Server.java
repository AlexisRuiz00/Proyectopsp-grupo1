package Controller;


import Model.Incidence;

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

    private static Server s = null;
    private Stack<String> emailsAtendidos = new Stack<String>();
    private ArrayList<Socket> admins;
    private ArrayList<Integer>puertos;
    private ArrayList<String>direcciones;

    private Server() {
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Server");
        initComponents();
        chargeLayout();
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

    public static Server getServer(){
        return s;
    }
    public Stack getCola(){
        return emailsAtendidos;
    }
    public synchronized void removeAdmin(InetAddress inetAddress){
        for (Socket i : admins){
            if (i.getInetAddress().equals(inetAddress)){
                admins.remove(i);
            }
        }
    }
    public synchronized int getPuerto() {
        boolean flag = true;
        int puerto = 0;
        while (flag){
            puerto = (int) (Math.random()*65000);
            if (!s.puertos.contains(puerto)) {
                puertos.add(puerto);
                flag = false;
            }
        }
        return puerto;
    }
    public synchronized String getDireccion(){
        String direccion = "0";
        boolean flag = true;
        while (flag){
           String c1 = String.valueOf((int)(Math.random()*15)+224);
           String c2 = String.valueOf((int)(Math.random()*255));
           String c3 = String.valueOf((int)(Math.random()*255));
           String c4 = String.valueOf((int)(Math.random()*255));

           direccion = c1+"."+c2+"."+c3+"."+c4;

           if (!direcciones.contains(direccion)){
               direcciones.add(direccion);
               flag=false;
           }
        }

        return direccion;
    }
    public synchronized Socket getAdminForChat(){

        int adminSelected = (int)(Math.random()*(admins.size()-1))+1;
        Socket administratorSocket = admins.get(adminSelected);
        admins.remove(adminSelected);
        return administratorSocket;
    }



    public static void main(String args[]) throws InterruptedException, IOException {
        s = new Server();
        s.admins = new ArrayList<>();


        s.setVisible(true);
        s.getLogArea().append(s.getHour() + " - Servidor iniciado. \n");
        Thread.sleep(1000);
        s.getLogArea().append(s.getHour() + " - Esperando respuesta... \n");

        //SE CREA EL SOCKET PARA RECIVIR LAS INCIDENCIAS
        ServerSocket socket = new ServerSocket(13300);

        while (true) {
            Socket recieve = socket.accept();
            DataInputStream read = new DataInputStream(recieve.getInputStream());

            //EL PRIMER DATO QUE LLEGARÁ A TRAVÉS DEL STREAM SERÁ UN BYTE QUE
            //DETERMINE EL ROL DE QUIEN COMIENZA LA CONEXIÓN, Y EN CASO DE LOS
            //INCIDENCE ADMIN, SI ES UNA CONEXIÓN, O CIERRE DE CONEXIÓN PORQUE
            // HAY QUE QUITARLOS DEL ARRAYLIST ADMIN.

            System.out.println("Entra");
            int rol = read.readInt();
            System.out.println("Entra2 "+rol);
            switch (rol){

                //Entra cliente
                case 1:
                             s.getLogArea().append(s.getHour()+" - Conectando cliente...\n");
                             ThreadCliente ThreadCliente = new ThreadCliente(recieve);
                             ThreadCliente.start();
                             break;

                //Se abre chat
                case 2:      ThreadConfChat tcc = new ThreadConfChat(recieve);
                             tcc.start();
                             s.getLogArea().append(" - Configurando chat...\n");
                             break;

                //Entra IncidenceAdmin
                case 3:      s.admins.add(recieve);
                             s.getLogArea().append(" - Conectando Administrador de Incidencias...\n");
                             ThreadIncidenceAdmin threadIncidenceAdmin = new ThreadIncidenceAdmin(recieve);
                             threadIncidenceAdmin.start();
                             break;

                //Cierra IncidenceAdmin
                case 4:      s.removeAdmin(recieve.getInetAddress());
                             s.direcciones.remove(recieve.getInetAddress().getHostName());
                             s.getLogArea().append(" - Desconectando Administrador de Incidencias...\n");
                             break;

                //Entra SysAdmin
                case 5:     s.getLogArea().append(" - Conectando Administrador de Sistema...\n");
                            ThreadSystemAdmin threadSystemAdmin = new ThreadSystemAdmin(recieve);
                            threadSystemAdmin.start();

                default:
                    System.out.println(" - Sale");  break;
            }
        }
    }





    public JTextArea getLogArea() {
        return logArea;
    }

    public void setLogArea(JTextArea logArea) {
        this.logArea = logArea;
    }

    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextArea logArea;




    public synchronized ArrayList<Incidence> get() {
        ArrayList<Incidence> incidences = new ArrayList<>();
        incidences.add(new Incidence(1,"Juan","E@hotmail.com","Consult","PRUEBA\nMASCOSAS\nES UNA GRAN PRUEABA\nSI"));

        // CONSULTA DE LA BASE DE DATOS PARA LLENAR LA LISTA DE INCIDENCIAS
        return incidences;
    }
    public synchronized Incidence put(Incidence i) {


        // INSERTA CONSULTA EN BASE DE DATOS  devuelve la incidencia insertada
        return new Incidence(6,"","","","prueba de nueva incidencia");
    }







    public String getHour(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String hora = dateFormat.format(date);
        return hora;
    }


    public void writeCloseClient(){
        s.getLogArea().append(getHour()+" - Cliente desconectado\n");
    }


}
