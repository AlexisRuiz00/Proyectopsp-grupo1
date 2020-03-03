package controller;

import View.ViewClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

/**
 * This thread will manage the receivement of chat messages in the client's view
 */
public class ChatThread extends Thread{


    private ViewClient viewClient;
    private byte[] buf = new byte[1024];
    private DatagramPacket recibo;
    private MulticastSocket ms;
    private ObjectInputStream oip;
    private String message = "";
    private boolean running = true;
    private boolean reading = true;

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

     while (running) {

         try {
             String address = (String) oip.readObject();
             int port = (int) oip.readObject();

             ms = new MulticastSocket(port);
             ms.joinGroup(InetAddress.getByName(address));

             MainCliente.getClientController().setMs(ms);
             MainCliente.getClientController().confChatVariables(address, port);


             String message = "\nClient connected";
             DatagramPacket paquete = new DatagramPacket(message.getBytes(),
                     message.length(), InetAddress.getByName(address), port);
             ms.send(paquete);

             running = true;
         } catch (IOException e) {
             e.printStackTrace();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }


         while (reading) {

             try {
                 recibo = new DatagramPacket(buf, buf.length);
                 ms.receive(recibo);
                 message = new String(recibo.getData(), 0, recibo.getLength());

                 if (message.equalsIgnoreCase("disconnect")) {
                     viewClient.writeInChat("\nAdmin "+message+"\n");
                     reading = false;
                 }else
                 viewClient.writeInChat(message);

             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
  }


    public void finish(){
        this.running = false;
        this.reading = false;
        try {
            ms.setSoTimeout(0);
            ms.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


}
