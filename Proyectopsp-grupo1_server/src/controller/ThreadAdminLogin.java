package Controller;

import Model.VO.Incidence;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadAdminLogin extends Thread {

    private ObjectInputStream objectInput;
    private ObjectOutputStream objectOutput;
    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private Server s;
    private Incidence i;
    private Socket socket;
    private boolean running;

    public ThreadAdminLogin(Socket socket) {
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

        try {

            System.out.println(socket.isClosed());
            ArrayList<String> credentials = (ArrayList<String>) objectInput.readObject();
            String role = s.getLogin(credentials);

            objectOutput.writeObject(role);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
