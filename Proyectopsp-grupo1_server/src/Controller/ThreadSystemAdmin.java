package Controller;

import Model_Admin.Incidence;

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
            objectInput = new ObjectInputStream(socket.getInputStream());
            objectOutput = new ObjectOutputStream(socket.getOutputStream());
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        run();
    }

    @Override
    public void run() {
        while (running) {

            try {

                //SE LEE EL PRIMER BYTE QUE DETERMINA LA ACCIÓN A REALIZAR
                byte action = dataInput.readByte();

                switch (action) {

                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
