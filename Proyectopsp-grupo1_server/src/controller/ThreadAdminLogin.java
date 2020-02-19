package controller;

import model.VO.Incidence;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadAdminLogin extends Thread {

    private ObjectInputStream objectInput;
    private ObjectOutputStream objectOutput;

    private Server s;
    private Incidence i;
    private Socket socket;

    public ThreadAdminLogin(Socket socket) {
        this.s = Server.getServer();
        this.socket = socket;

        try {
            objectInput = new ObjectInputStream(socket.getInputStream());
            objectOutput = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        try {
            ArrayList<String> credentials = (ArrayList<String>) objectInput.readObject();
            String role = s.getLogin(credentials);

            objectOutput.writeObject(role);
            objectInput.close();
            objectOutput.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
