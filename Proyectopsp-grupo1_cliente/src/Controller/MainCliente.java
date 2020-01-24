package Controller;

import Model.Incidence;
import View.ViewClient;
import View.ViewClientLogin;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Esta clase arranca y controla la aplicación
 */
public class MainCliente implements ActionListener, ListSelectionListener, WindowListener {

    public static MainCliente controller;
    private ViewClientLogin viewClientLogin;
    private ViewClient viewClient;
    private static Socket s = null;
    ObjectOutputStream oop;

    private MainCliente(){
    }

    public static void main(String[] args) {
        getClientController();

        controller.viewClientLogin = new ViewClientLogin();
        controller.viewClientLogin.chargeLayout();
        controller.viewClientLogin.show();



    }

    public static MainCliente getClientController() {
        if (controller == null) {
            controller = new MainCliente();
        }
        return controller;
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){

            case "Enter": startApp();break;
            case "openChat": viewClient.openChat();break;
            case "closeChat": viewClient.closeChat();break;

        }

    }


    private void startApp(){

        System.out.println("Entra");
        int puerto = 13300;
        try {
            s = new Socket("localhost",puerto);
            DataOutputStream foutput = new DataOutputStream(s.getOutputStream());
            System.out.println("Entra2");

            ArrayList<Incidence> incidences;
            foutput.writeInt(1);
            System.out.println("Entra3");


            oop = new ObjectOutputStream(s.getOutputStream());
            Incidence incidence = new Incidence("p@hotmail.com","Consult");
            oop.writeObject(incidence);

            try {

                ObjectInputStream finput = new ObjectInputStream(s.getInputStream());

                try {
                    incidences = (ArrayList<Incidence>) finput.readObject();
                    if (!(incidences == null)) {

                        viewClient = new ViewClient(incidences);
                        System.out.println(incidences.get(0).getType());
                        viewClientLogin.dispose();
                        viewClient.resize(516,viewClient.getHeight());
                        viewClient.show();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "EL EMAIL INTRODUCIDO YA ESTÁ CONECTADO AL SERVIDOR\nPRUEBE A CONECTARSE MÁS TARDE\n",
                                "<<MENSAJE DE ERROR:2>>", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "EL EMAIL INTRODUCIDO YA ESTÁ CONECTADO AL SERVIDOR\nPRUEBE A CONECTARSE MÁS TARDE\n" + e.getMessage(),
                            "<<MENSAJE DE ERROR:2>>", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
                    "<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        viewClient.setIncidencesDetail(((JList<Incidence>)e.getSource()).getSelectedValue().getBody());
    }



    @Override
    public void windowClosing(WindowEvent e) {
        try {
            oop.writeObject(new Incidence("","-1"));
            ((JFrame)e.getSource()).dispose();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {
    }
    @Override
    public void windowClosed(WindowEvent e) {
    }
    @Override
    public void windowIconified(WindowEvent e) {
    }
    @Override
    public void windowDeiconified(WindowEvent e) {
    }
    @Override
    public void windowActivated(WindowEvent e) {
    }
    @Override
    public void windowDeactivated(WindowEvent e) {
    }


}