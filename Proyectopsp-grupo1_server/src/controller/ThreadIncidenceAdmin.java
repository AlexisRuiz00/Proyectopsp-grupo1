package controller;

import model.VO.Incidence;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadIncidenceAdmin extends Thread {

    private ObjectInputStream objectInput;
    private ObjectOutputStream objectOutput;
    private DataInputStream dataInput;
    private Server s;
    private Incidence i;
    private Socket socket;
    private boolean running;

    public ThreadIncidenceAdmin(Socket socket) {
        this.socket = socket;
        s = Server.getServer();

        try {
            objectOutput = new ObjectOutputStream(socket.getOutputStream());
            objectInput = new ObjectInputStream(socket.getInputStream());

            //Get Incidence Administrator name
            String username = (String) objectInput.readObject();

            //Get Incidence linked to Incidence Administrator logged.
            ArrayList<Incidence> adminIncidences = s.getAdminIncidences(username);

            // GET INCIDENCE
            //i = adminIncidences.get(0);

            //Send Incidence to Administrator app
            objectOutput.writeObject(adminIncidences);

            //Start run method
            running = true;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        while (running) {

            try {
                s.addIncidenceAdminToList(socket);


                //SE LEE EL PRIMER BYTE QUE DETERMINA LA ACCIÓN A REALIZAR
                dataInput = new DataInputStream(socket.getInputStream());
                int action = dataInput.readInt();

                switch (action) {

                    case 1:
                        // Update the incidence
                        s.updateIncidence(i);
                        break;

                    case 2:
                        s.addIncidenceAdminToList(socket);
                        break;

                    case 3:
                        running = false;
                        s.writeCloseIncidenceAdmin();

                        try {

                            s.removeIncidenceAdminFromList(socket);
                            objectOutput.close();
                            objectInput.close();
                            dataInput.close();
                            socket.close();
                        }catch (Exception e){
                        }
                        break;

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}