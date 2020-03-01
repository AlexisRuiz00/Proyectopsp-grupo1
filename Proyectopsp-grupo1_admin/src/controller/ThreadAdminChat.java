package controller;

import view.ViewIncidenceAdmin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

/**
 * This thread will manage the receivement of chat messages in the incidence's admin view
 */
public class ThreadAdminChat extends Thread {

    private ViewIncidenceAdmin viewIncidenceAdmin;
    private byte[] recibidos = new byte[1024];
    private DatagramPacket recibo;
    private MulticastSocket ms;
    private boolean running = true;
    private Socket socket;
    private ObjectInputStream ois;
    private byte[] buf = new byte[1024];

    public ThreadAdminChat(Socket socket,ViewIncidenceAdmin viewIncidenceAdmin){
        this.socket = socket;
        this.viewIncidenceAdmin = viewIncidenceAdmin;
        recibo  = new DatagramPacket(recibidos,recibidos.length);
    }


    @Override
    public void run(){
        while (running){
            try {
                ois =  new ObjectInputStream(socket.getInputStream());
                String address = (String) ois.readObject();
                int port = (int) ois.readObject();



                ms= new MulticastSocket(port);
                ms.joinGroup(InetAddress.getByName(address));
                MainAdmin.getAdminController().setMs(ms);
                MainAdmin.getAdminController().confChatSocket(address,port);
                //PINTAR MENSAJE EN EL CHAT


                String message = "Admin " +MainAdmin.getAdminController().getUsername()+ " Connected";
                DatagramPacket paquete = new DatagramPacket(message.getBytes(),
                        message.length(), InetAddress.getByName(address), port);
                ms.send(paquete);


                while (running) {

                    recibo = new DatagramPacket(buf, buf.length);
                    ms.receive(recibo);
                    message = new String(recibo.getData(), 0, recibo.getLength());

                    //PINTAR MENSAJE EN EL CHAT
                    viewIncidenceAdmin.writeInChat(message);
                }



            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}
