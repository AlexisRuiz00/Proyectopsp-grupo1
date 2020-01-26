package Controller;
import Model.VO.Incidence;

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
    private String email;

    public ThreadCliente(Socket socket) {
        this.socket = socket;
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
            i = (Incidence) entradaIncidencia.readObject();

            if (s.getCola().contains(i.getMail())) {
                salidaIncidencia.writeObject(null);
                running = false;
            }


            else {
                // Añadimos el email a la cola
                s.getCola().add(i.getMail());
                this.email = i.getMail();
                System.out.println(email);

                while (running) {

                    switch (i.getType()){

                        case "-1" :
                                try {
                                    entradaIncidencia.close();
                                    salidaIncidencia.close();
                                    socket.close();
                                } catch (IOException ex) {}
                            running = false;
                            s.getCola().remove(email);
                            s.writeCloseClient();
                            break;

                        //SI LA INCIDENCIA SE MARCA COMO SONSULTA
                        case "Consult":
                            //SE GENERA UN ARRAYLIST QUE SE LLENARÁ CON UNA LISTA
                            //DEVUELTA POR LA BASE DE DATOS
                            ArrayList<Incidence> incidences = new ArrayList<>();
                            incidences = s.get(email); // Consulta a la base de datos

                            //CUADO SE GENERE, SE ENVÍA ESTE ARRAYLIST A TRAVÉS
                            //DE UN OBJECTOUPUTSTREAM AL CLIENTE DE ESTE HILO
                                try {
                                    salidaIncidencia.writeObject(incidences);
                                } catch (IOException ex) {}
                            break;
                            //SI LA INCIDENCIA SE MARCA COMO NUEVA
                        case "new":
                            Incidence newIncidence =
                                    s.put(i); // Da de alta la consulta que recibe en la base de datos

                            salidaIncidencia.writeObject(newIncidence);
                            break;

                        case "answer":
                            s.updateIncidence(i);
                            break;
                        }//endSwitch

                    if (running)
                    i = (Incidence) entradaIncidencia.readObject();

                    }//endWhile
                }//endElse
            } catch (Exception e) {e.printStackTrace();}
    }//endRun
}
