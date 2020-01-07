package Controller;

import Model.Incidence;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadCliente extends Thread{
    private ObjectInputStream entradaIncidencia;
    private ObjectOutputStream salidaIncidencia;
    private Server s;
    private Incidence i;
    private Socket socket;
    private boolean running;

    public ThreadCliente(Socket socket) {
        this.socket = socket;
        s = Server.getServer();


        try {
            //FLUJO DE entrada para leer los mensajes
            entradaIncidencia = new ObjectInputStream(socket.getInputStream());
            salidaIncidencia = new ObjectOutputStream(socket.getOutputStream());
            running = true;
            run();

        } catch (IOException e) {
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
        }



    }

    @Override
    public void run() {

        while (running) {

            try {
                        if (i.getType().equals("-1")) {
                            socket.close();
                            running = false;

                        } else {
                            if (s.getCola().contains(i.getMail())) {
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else {

                                // Añadimos el email a la cola
                                s.getCola().add(i.getMail());

                                //SI LA INCIDENCIA SE MARCA COMO SONSULTA
                                if (i.getType().equals("Consulta")) {
                                    //SE GENERA UN ARRAYLIST QUE SE LLENARÁ CON UNA LISTA
                                    //DEVUELTA POR LA BASE DE DATOS
                                    ArrayList<Incidence> incidences = new ArrayList<>();
                                    incidences = get(); // Consulta a la base de datos

                                    //CUADO SE GENERE, SE ENVÍA ESTE ARRAYLIST A TRAVÉS
                                    //DE UN OBJECTOUPUTSTREAM AL CLIENTE DE ESTE HILO
                                    try {
                                        salidaIncidencia.writeObject(incidences);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                //SI LA INCIDENCIA SE MARCA COMO NUEVA
                                } else if (i.getType().equals("Nuevo")) {
                                    put(i); // Da de alta la consulta que recibe en la base de datos
                                }
                                notify();
                            }
                        }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }//endWhile
    }

    private synchronized ArrayList<Incidence> get() {
        ArrayList<Incidence> incidences = new ArrayList<>();

        // CONSULTA DE LA BASE DE DATOS PARA LLENAR LA LISTA DE INCIDENCIAS

        // ELIMINAN EL EMAIL DE LA PILA
        s.getCola().remove(i.getMail());

        return incidences;
    }
    private synchronized void put(Incidence i) {

        // INSERTA CONSULTA EN BASE DE DATOS

        // ELIMINA EL EMAIL DE LA PILA
        s.getCola().remove(i.getMail());
    }


}
