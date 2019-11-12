package View;

import Model.Incidence;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

/**
 * @author David and Alexis
 */
public class Server extends JFrame implements Runnable {

    String tipoConsulta;
    Incidence i;
    Stack<String> emailsAtendidos = new Stack<String>();


    public Server(String tipoConsulta, Incidence i) {
        this.tipoConsulta = tipoConsulta;
        this.i = i;
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Server");
        initComponents();
        chargeLayout();
    }


    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        scrollPane1 = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logArea.setEditable(false);
        logArea.setColumns(20);
        logArea.setRows(5);
        scrollPane1.setViewportView(logArea);
    }

    public void chargeLayout() {
        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException {

        String tipoConsulta = "";
        Incidence i = null;

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String hora = dateFormat.format(date);

        Server s = new Server(tipoConsulta, i);
        s.setVisible(true);

        s.getLogArea().append(hora + " - Servidor iniciado. \n");
        Thread.sleep(1000);
        s.getLogArea().append(hora + " - Esperando respuesta... \n");

        Thread thread = new Thread(s);
        thread.start();

        // A TRAVES DEL SOCKET, LLEGA UN OBJETO "INCIDENCIA", EL HILO PETICION TRAMITA LA INCIDENCIA QUE ES LA QUE SE ENVIA A LA BASE DE DATOS
        // A TRAVES DEL SOCKET, LLEGA UNA PETICION DE CONSULTA, EL HILO PETICION MANDA LA INFORMACION AL SERVIDOR Y ESTE DEVUELVE (GET) LA INCIDENCIA DE LA BASE DE DATOS.

    }

    @Override
    public void run() {


        if(emailsAtendidos.contains(i.getMail())) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {

            // Añadimos el email a la pila
            emailsAtendidos.add(i.getMail());

            if(tipoConsulta.equals("Consulta")) {
                get(); // Consulta a la base de datos
            } else if(tipoConsulta.equals("Nuevo")) {
                put(i); // Da de alta la consulta que recibe en la base de datos
            }
        }

    }

    public synchronized void get() {

        // CONSULTA DE LA BASE DE DATOS

        // ELIMINAN EL EMAIL DE LA PILA
        emailsAtendidos.remove(i.getMail());
        notify();

    }

    public synchronized void put(Incidence i) {

        // INSERTA CONSULTA EN BASE DE DATOS

        // ELIMINA EL EMAIL DE LA PILA
        emailsAtendidos.remove(i.getMail());
        notify();

    }

    public JTextArea getLogArea() {
        return logArea;
    }

    public void setLogArea(JTextArea logArea) {
        this.logArea = logArea;
    }

    private javax.swing.JPanel panel1;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JTextArea logArea;

}
