package Controller;
import Model.Incidence;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadCliente extends Thread{
    private ObjectInputStream entradaIncidencia;
    private ObjectOutputStream salidaIncidencia;
    private Server s;
    private Incidence i;
    private Socket socket;
    private boolean running;
    boolean newThread;


    public ThreadCliente(Socket socket) {
        this.socket = socket;
        newThread = true;
        s = Server.getServer();


        try {
            //FLUJO DE entrada para leer los mensajes
            entradaIncidencia = new ObjectInputStream(socket.getInputStream());
            salidaIncidencia = new ObjectOutputStream(socket.getOutputStream());
            running = true;
        } catch (IOException e) {
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
        }



    }

    @Override
    public void run() {

        try {
            System.out.println("entra1");
            i = (Incidence) entradaIncidencia.readObject();

            if (s.getCola().contains(i.getMail())) {
                salidaIncidencia.writeObject(null);
                running = false;
            }


            else {
                // Añadimos el email a la cola
                s.getCola().add(i.getMail());

                while (running) {

                    System.out.println(i.getType());
                    System.out.println("entra2");
                    if (i.getType().equals("-1")) {
                        socket.close();
                        running = false;

                    } else {
                        //SI LA INCIDENCIA SE MARCA COMO SONSULTA
                        if (i.getType().equalsIgnoreCase("Consult")) {
                            System.out.println("ENTRA CONSULTA");
                            //SE GENERA UN ARRAYLIST QUE SE LLENARÁ CON UNA LISTA
                            //DEVUELTA POR LA BASE DE DATOS
                            ArrayList<Incidence> incidences = new ArrayList<>();
                            incidences = s.get(); // Consulta a la base de datos

                            //CUADO SE GENERE, SE ENVÍA ESTE ARRAYLIST A TRAVÉS
                            //DE UN OBJECTOUPUTSTREAM AL CLIENTE DE ESTE HILO
                            salidaIncidencia.writeObject(incidences);

                            //SI LA INCIDENCIA SE MARCA COMO NUEVA
                        } else if (i.getType().equals("Nuevo")) {
                            s.put(i); // Da de alta la consulta que recibe en la base de datos
                        }
                    }

                    i = (Incidence) entradaIncidencia.readObject();
                }//endWhile

            }//endElse
        } catch (Exception e) {e.printStackTrace();}
    }//endRun

}
