package controller;

import model.VO.Incidence;
import model.VO.IncidenceAdmin;

import java.io.*;
import java.net.Socket;

public class ThreadSystemAdmin extends Thread {

    private ObjectInputStream objectInput;
    private ObjectOutputStream objectOutput;
    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private Server s;
    private Incidence i;
    private Socket socket;
    private boolean running;

    public ThreadSystemAdmin(Socket socket) {
        this.s = Server.getServer();
        this.socket = socket;

        try {
            objectOutput = new ObjectOutputStream(socket.getOutputStream());
            objectOutput.writeObject(s.getIncidenceAdmins());


            running = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (running) {

            try {

                //SE LEE EL PRIMER BYTE QUE DETERMINA LA ACCIÓN A REALIZAR
                dataInput = new DataInputStream(socket.getInputStream());
                int action = dataInput.readInt();
                System.out.println(action);

                switch (action) {
                    //Crete a new Incidence Admin
                    case 1:
                        try {

                            objectInput = new ObjectInputStream(socket.getInputStream());
                            IncidenceAdmin incidenceAdmin =
                                    (IncidenceAdmin) objectInput.readObject();
                            s.saveIncidenceAdmin(incidenceAdmin);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;

                    default:
                        System.out.println("SALE");
                }

            } catch (IOException  e) {
                e.printStackTrace();
            }
        }
    }



}