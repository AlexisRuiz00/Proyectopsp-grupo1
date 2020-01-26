package Controller;

import Model.Incidence;
import View.ViewAdminLogin;
import View.ViewIncidenceAdmin;
import View.ViewNewIncidenceAdmin;
import View.ViewSystemAdmin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Clase que arranca y controla la aplicación
 */
public class MainAdmin implements ActionListener, WindowListener {

    private static MainAdmin controller;
    private ViewAdminLogin viewAdminLogin;
    private ViewNewIncidenceAdmin viewNewIncidenceAdmin;
    private ViewSystemAdmin viewSystemAdmin;
    private ViewIncidenceAdmin viewIncidenceAdmin;
    private static Socket s = null;
    ObjectOutputStream oop;

    private MainAdmin() {

    }

    public static void main(String[] args) {
        getAdminController();
        controller.viewAdminLogin = new ViewAdminLogin();
        controller.viewAdminLogin.chargeLayout();
        controller.viewAdminLogin.setVisible(true);

    }

    public static MainAdmin getAdminController() {

        if(controller == null) {
            controller = new MainAdmin();
        }
        return controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (actionEvent.getActionCommand()) {

            case "Login": startApp();break;
            case "Chat": viewNewIncidenceAdmin.openChat();
            case "Exit": viewNewIncidenceAdmin.dispose();

        }
    }

    private void startApp() {

        int puerto = 13300;

        try {

            s = new Socket("localhost", puerto);
            DataOutputStream foutput = new DataOutputStream(s.getOutputStream());

            ArrayList<Incidence>incidences;
            foutput.write(1);

            oop = new ObjectOutputStream(s.getOutputStream());
            Incidence incidence = new Incidence(1, "Prueba", "p@hotmail.com","Consult", "AAAAAA");
            oop.writeObject(incidence);

            try {

                ObjectInputStream finput = new ObjectInputStream(s.getInputStream());

                try {
                    incidences = (ArrayList<Incidence>) finput.readObject();

                    if(!(incidences == null)) {

                        viewIncidenceAdmin = new ViewIncidenceAdmin();
                        viewAdminLogin.dispose();
                        viewIncidenceAdmin.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario que has introducido es incorrecto, por favor comprueba los datos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NullPointerException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "El usuario que has introducido es incorrecto, por favor comprueba los datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Imposible conectarse al servidor" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        try {
            oop.writeObject(new Incidence(2, "", "", "", ""));
            ((JFrame)windowEvent.getSource()).dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
