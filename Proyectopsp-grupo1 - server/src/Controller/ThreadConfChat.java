package Controller;

import Model.Incidence;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Este hilo se encarga de generar un puerto y una dirección para
 * el chat que usará el cliente y el administrador.
 */
public class ThreadConfChat extends Thread{

    private Server s;
    private boolean running;

    private MulticastSocket socketPactado;
    private InetAddress grupo;
    private int puerto;
    private Socket cliente;
    private ObjectOutputStream salidaCliente;
    private ObjectOutputStream salidaAdmin;



    public ThreadConfChat(Socket cliente) {
        s = Server.getServer();
        this.cliente = cliente;

        try {
            salidaCliente = new ObjectOutputStream(cliente.getOutputStream());
            salidaAdmin = new ObjectOutputStream(s.getAdminForChat().getOutputStream());
            run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    /**
     * Configura el socket multicast y lo envía al cliente y
     * administrador que van a comunicarse a través del chat
     */
    public void run() {
         try {
            grupo = InetAddress.getByName(s.getDireccion());
            puerto = s.getPuerto();
            MulticastSocket ms = new MulticastSocket(puerto);
            ms.joinGroup(grupo);

            salidaAdmin.writeObject(ms);
            salidaCliente.writeObject(ms);

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
