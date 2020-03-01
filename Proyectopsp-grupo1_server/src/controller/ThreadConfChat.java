package controller;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Este hilo se encarga de generar un puerto y una dirección para
 * el chat que usará el cliente y el administrador.
 */
public class ThreadConfChat extends Thread{

    private Server s;
    private boolean running;

    private String address;
    private int port;
    private Socket client;

    private ObjectOutputStream clientOutput;
    private ObjectOutputStream adminOutput;



    public ThreadConfChat(Socket cliente) {
        s = Server.getServer();
        this.client = cliente;
    }


    @Override
    /**
     * Configura el socket multicast y lo envía al cliente y
     * administrador que van a comunicarse a través del chat
     */
    public void run() {
         try {

            clientOutput = new ObjectOutputStream(client.getOutputStream());
            Socket admin = s.getAdminForChat();
            adminOutput = new ObjectOutputStream(admin.getOutputStream());

            address = s.getDireccion();
            port = s.getPuerto();

            clientOutput.writeObject(address);
            clientOutput.writeObject(port);

            adminOutput.writeObject(address);
            adminOutput.writeObject(port);
             //ENVIAR AL ADMIN

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
