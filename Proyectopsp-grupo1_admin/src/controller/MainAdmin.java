package controller;

import model.VO.Incidence;
import view.ViewAdminLogin;
import view.ViewIncidenceAdmin;
import view.ViewNewIncidenceAdmin;
import view.ViewSystemAdmin;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que arranca y controla la aplicación
 */
public class MainAdmin implements ActionListener, WindowListener, ListSelectionListener {

    private static MainAdmin controller;
    private ViewAdminLogin viewAdminLogin;
    private ViewSystemAdmin viewSystemAdmin;
    private ViewIncidenceAdmin viewIncidenceAdmin;
    private ArrayList<Incidence> incidences;
    private static Socket s = null;
    private ObjectOutputStream oop;
    private ObjectInputStream finput;
    private DataOutputStream foutput;
    private MainAdmin() {

    }

    public static void main(String[] args) {
        getAdminController();
        controller.viewAdminLogin = new ViewAdminLogin();
        controller.viewAdminLogin.chargeLayout();
        controller.viewAdminLogin.setVisible(true);

    }

    public static MainAdmin getAdminController() {

        if (controller == null) {
            controller = new MainAdmin();
        }
        return controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (actionEvent.getActionCommand()) {

            case "Login":
                startApp();
                break;
            case "Chat":
                viewIncidenceAdmin.openChat();
                break;
            case "Exit":
                viewIncidenceAdmin.dispose();
                break;
            case "Accept":
                this.replyIncidence();
                break;

        }
    }

    private void startApp() {

        int puerto = 13300;

        try {

            s = new Socket("localhost", puerto);


            String role;
            foutput = new DataOutputStream(s.getOutputStream());
            foutput.writeInt(10);


            ArrayList<String> credentials;
            credentials = viewAdminLogin.getCredentials();

            oop = new ObjectOutputStream(s.getOutputStream());
            oop.writeObject(credentials);


            finput = new ObjectInputStream(s.getInputStream());

            try {


                try {
                    role = (String) finput.readObject();

                    switch (role) {
                        case "IncidenceAdmin":

                            controller.incidences =incidences;
                            s = new Socket("localhost", puerto);

                            /*Send int with value 21 to advertise server that an
                            incidence admin is going to start a connection*/

                            foutput = new DataOutputStream(s.getOutputStream());
                            foutput.writeInt(21);


                            //SEND USERNAME FOR RECIEVING LINKED INCIDENCES
                            oop = new ObjectOutputStream(s.getOutputStream());
                            oop.writeObject(viewAdminLogin.getCredentials().get(1));


                            //RECIEVE INCIDENCES FROM SERVER
                            finput = new ObjectInputStream(s.getInputStream());
                            ArrayList<Incidence> adminIncidence = (ArrayList<Incidence>) finput.readObject();

                            viewIncidenceAdmin = new ViewIncidenceAdmin(adminIncidence);
                            viewAdminLogin.dispose();
                            viewIncidenceAdmin.setVisible(true);

                            break;

                        case "SystemAdmin":
                            viewSystemAdmin = new ViewSystemAdmin();
                            viewAdminLogin.dispose();
                            viewSystemAdmin.setVisible(true);
                            break;
                        case "":
                            JOptionPane.showMessageDialog(null, "Invalid username / password", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Invalid username / password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Connection refused, there has been a problem" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void replyIncidence() {

        int idx = viewIncidenceAdmin.getSelectedIncidenceListId();
        if (idx == -1){
            JOptionPane.showMessageDialog(null, "Select an incidence to reply\n",
                    "<<MENSAJE DE ERROR:5>>", JOptionPane.ERROR_MESSAGE);
        }else if(viewIncidenceAdmin.getTextReplyToString().isEmpty()){
            JOptionPane.showMessageDialog(null, "Insert a reply\n",
                    "<<MENSAJE DE ERROR:4>>", JOptionPane.ERROR_MESSAGE);
        }else {
            Incidence tmpIncidence =
                    incidences.get(idx);
            tmpIncidence.setBody(tmpIncidence.getBody()+
                    "\n<------------------ "+getHour()+" -------------------->\n"+
                    viewIncidenceAdmin.getTextReplyToString());
            try {
                incidences.set(incidences.indexOf(findById(tmpIncidence.getId())),tmpIncidence);
                viewIncidenceAdmin.updateElement(tmpIncidence,idx);
                tmpIncidence.setType("answer");

                System.out.println(tmpIncidence.getBody());
                oop.reset();
                oop.writeObject(tmpIncidence);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error sending reply\n",
                        "<<MENSAJE DE ERROR:6>>", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public Incidence findById(int id){
        return incidences.stream().filter(i -> i.getId() == id).findFirst().get();
    }

    public String getHour(){
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        Date date = new Date();
        String hora = dateFormat.format(date);
        return hora;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        viewIncidenceAdmin.setAreaDetail(((JList<Incidence>)e.getSource()).getSelectedValue().getBody());
    }
}
