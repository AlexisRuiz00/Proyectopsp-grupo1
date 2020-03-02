package controller;

import view.ViewIncidenceAdmin;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

/**
 * This thread will manage the receivement of chat messages in the incidence's admin view
 */
public class ThreadAdminChat extends Thread {

    private ViewIncidenceAdmin viewIncidenceAdmin;
    private byte[] recibidos = new byte[1024];
    private DatagramPacket recibo;
    private MulticastSocket ms;
    private boolean running = true;
    private boolean reading = true;
    private Socket socket;
    private ObjectInputStream ois;
    private DataOutputStream foutput;
    private byte[] buf = new byte[1024];
    private String address;
    private int port;


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
                foutput = new DataOutputStream(socket.getOutputStream());

                address = (String) ois.readObject();
                port = (int) ois.readObject();


                ms= new MulticastSocket(port);
                ms.joinGroup(InetAddress.getByName(address));
                MainAdmin.getAdminController().setMs(ms);
                MainAdmin.getAdminController().confChatSocket(address,port);


                String message = "\nAdmin " +MainAdmin.getAdminController().getUsername()+ " Connected";
                DatagramPacket paquete = new DatagramPacket(message.getBytes(),
                        message.length(), InetAddress.getByName(address), port);
                ms.send(paquete);
                viewIncidenceAdmin.cleanChat();
                reading = true;

                while (reading) {

                    recibo = new DatagramPacket(buf, buf.length);
                    ms.receive(recibo);
                    message = new String(recibo.getData(), 0, recibo.getLength());

                    if (message.equalsIgnoreCase("disconnect")) {
                        viewIncidenceAdmin.writeInChat("\nClient "+message+"\n");
                        foutput.writeInt(2);
                        reading = false;

                    }else
                    viewIncidenceAdmin.writeInChat(message);
                }

                ms.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

            try {
                foutput.flush();
                foutput.close();
                ois.close();
                ms.setSoTimeout(0);
                ms.close();

            }catch (Exception e){
                e.printStackTrace();
            }
    }


    public void finishThread(){
        this.running = false;
        this.reading = false;
    }


}
