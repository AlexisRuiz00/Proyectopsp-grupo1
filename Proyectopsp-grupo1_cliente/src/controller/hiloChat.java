package controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
/**
 * This thread will manage the receivement of chat messages in the client's view
 */
public class hiloChat extends Thread{


    private byte[] recibidos = new byte[1024];
    private DatagramPacket recibo;
    private MulticastSocket ms;
    private boolean running = true;

    public hiloChat(MulticastSocket ms){
        this.ms = ms;
        recibo  = new DatagramPacket(recibidos,recibidos.length);
        run();
    }


    @Override
    public void run(){
        while (running){
            try {
                ms.receive(recibo);

                //PINTAR MENSAJE EN EL CHAT

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
