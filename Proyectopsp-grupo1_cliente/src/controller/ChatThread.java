package controller;

import View.ViewClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

/**
 * This thread will manage the receivement of chat messages in the client's view
 */
public class ChatThread extends Thread{


    private ViewClient viewClient;
    private byte[] buf = new byte[1024];
    private DatagramPacket recibo;
    private MulticastSocket ms;
    private ObjectInputStream oip;
    private String chat = "";
    private boolean running = true;

    public ChatThread(Socket socket, ViewClient viewClient){

        try {
            this.oip = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.viewClient = viewClient;
    }




    @Override
    public void run(){

        try {
            String address = (String) oip.readObject();
            int port = (int) oip.readObject();

            ms = new MulticastSocket(port);
            ms.joinGroup(InetAddress.getByName(address));

            MainCliente.getClientController().setMs(ms);
            MainCliente.getClientController().confChatVariables(address,port);
            viewClient.writeInChat("\nConected\n");

            running = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        while (running){

            try {
                recibo = new DatagramPacket(buf, buf.length);
                ms.receive(recibo);
                chat = new String(recibo.getData(), 0, recibo.getLength());

                //PINTAR MENSAJE EN EL CHAT
                viewClient.writeInChat(chat);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
