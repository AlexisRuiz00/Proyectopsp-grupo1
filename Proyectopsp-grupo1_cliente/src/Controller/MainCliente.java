package Controller;

import Model.Incidence;
import View.ViewClient;
import View.ViewClientLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Esta clase arranca y controla la aplicación
 */
public class MainCliente implements ActionListener {

    public static MainCliente controller;
    private ViewClientLogin viewClientLogin;
    private ViewClient viewClient;
    private static Socket s = null;

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


            ObjectOutputStream oop = new ObjectOutputStream(s.getOutputStream());
            Incidence incidence = new Incidence("p@hotmail.com","Consult");
            oop.writeObject(incidence);


            try {

                ObjectInputStream finput = new ObjectInputStream(s.getInputStream());
                incidences =  (ArrayList<Incidence>)finput.readObject();
                viewClient = new ViewClient(incidences);
                viewClientLogin.dispose();
                viewClient.show();

            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
                    "<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
        }




    }

}
