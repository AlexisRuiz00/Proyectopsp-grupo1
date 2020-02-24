package controller;

import model.VO.Incidence;
import model.VO.IncidenceAdmin;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import view.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.View;
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

    //Net objects
    private static Socket s = null;
    private ObjectOutputStream oop;
    private ObjectInputStream finput;
    private DataOutputStream foutput;

    //View objects
    private ViewAdminLogin viewAdminLogin;
    private ViewSystemAdmin viewSystemAdmin;
    private ViewIncidenceAdmin viewIncidenceAdmin;
    private ViewFtp viewFtp;

    //SystemAdmin objects
    private ViewSystemAdminOverview viewSystemAdminOverview;
    private FTPClient client;

    //IncidenceAdmin objects
    private ArrayList<Incidence> incidences;


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

            case "newIncidenceAdmin":

                viewSystemAdminOverview =
                        new ViewSystemAdminOverview(null);
                viewSystemAdminOverview.setVisible(true);
                break;

            case "editIncidenceAdmin":

                viewSystemAdminOverview =
                        new ViewSystemAdminOverview(viewSystemAdmin.getSelectedAdmin());
                viewSystemAdminOverview.setVisible(true);
                break;


            case "okOverview":

                IncidenceAdmin incidenceAdmin
                        = viewSystemAdminOverview.getIncidenceAdmin();
                try {
                    foutput.writeInt(1);
                    oop = new ObjectOutputStream(s.getOutputStream());
                    oop.writeObject(incidenceAdmin);

                    if ((boolean) finput.readObject())
                        viewSystemAdmin.addIncidenceAdmin(incidenceAdmin);
                    else
                        viewSystemAdmin.updateSelectedAdmin(incidenceAdmin);

                    viewSystemAdminOverview.dispose();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case "deleteIncidenceAdmin":

                try {
                    foutput.writeInt(2);
                    oop = new ObjectOutputStream(s.getOutputStream());
                    oop.writeObject(viewSystemAdmin.getSelectedAdmin());

                    viewSystemAdmin.removeSelectedAdmin();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            case "FTP":
                viewFtp = new ViewFtp();
                viewFtp.setVisible(true);

                break;



            case "ftpUpload":

                try {
                    boolean login = false;


                    client = new FTPClient();
                    client.enterLocalPassiveMode();
                    client.connect(viewFtp.getServer());


                    client.setFileType(FTP.BINARY_FILE_TYPE);


                    if (!viewFtp.isAnonymousModeOn())
                        login = client.login(viewFtp.getUser(), viewFtp.getPassword());
                    else
                        login= client.login("anonymous","");


                    if (login) {
                        JFileChooser chooser = new JFileChooser();
                        System.out.println();

                        chooser.setCurrentDirectory(new File(client.printWorkingDirectory()));
                        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        chooser.showOpenDialog(new JFrame("Select a file"));


                        File selectedFile = chooser.getSelectedFile();
                        if (selectedFile == null) {
                            JOptionPane.showMessageDialog(null,
                                    "No file selected",
                                    "Select a file",
                                    JOptionPane.WARNING_MESSAGE);
                        }else {

                            try {

                                BufferedInputStream in = new BufferedInputStream(new FileInputStream(selectedFile));
                                if (client.storeFile(selectedFile.getName(), in)) {

                                    JOptionPane.showMessageDialog(null,
                                            "File uploaded successfuly",
                                            "",
                                            JOptionPane.WARNING_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "Error uploading",
                                            "SERVER ERROR",
                                            JOptionPane.WARNING_MESSAGE);                                }
                                in.close();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error login into server",
                                "SERVER ERROR",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Connection to server failed",
                            "SERVER ERROR",
                            JOptionPane.WARNING_MESSAGE);                }
                break;

            case "ftpDownload":

                try {
                    boolean login = false;


                    client = new FTPClient();
                    client.enterLocalPassiveMode();
                    client.connect(viewFtp.getServer());


                    client.setFileType(FTP.BINARY_FILE_TYPE);


                    if (!viewFtp.isAnonymousModeOn())
                        login = client.login(viewFtp.getUser(), viewFtp.getPassword());
                    else
                        login= client.login("anonymous","");


                    if (login) {
                        JFileChooser chooser = new JFileChooser();

                        System.out.println();
                        chooser.setCurrentDirectory(new File(client.printWorkingDirectory()));
                        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        chooser.showOpenDialog(new JFrame("Select a file"));


                        File selectedFile = chooser.getSelectedFile();
                        if (selectedFile == null) {
                            JOptionPane.showMessageDialog(null,
                                    "No file selected",
                                    "Select a file",
                                    JOptionPane.WARNING_MESSAGE);
                        }else {

                            try {

                                BufferedOutputStream out = new BufferedOutputStream(
                                        new FileOutputStream(selectedFile));
                                if (client.retrieveFile(selectedFile.getName(),out)) {

                                    JOptionPane.showMessageDialog(null,
                                            "File downloaded successfuly",
                                            "",
                                            JOptionPane.WARNING_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "Error downloading",
                                            "SERVER ERROR",
                                            JOptionPane.WARNING_MESSAGE);                                }
                                out.close();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error login into server",
                                "SERVER ERROR",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Connection to server failed",
                            "SERVER ERROR",
                            JOptionPane.WARNING_MESSAGE);                }
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
                            incidences = adminIncidence;

                            viewIncidenceAdmin = new ViewIncidenceAdmin(adminIncidence);
                            viewAdminLogin.dispose();
                            viewIncidenceAdmin.setVisible(true);

                            break;

                        case "SystemAdmin":
                            s = new Socket("localhost", puerto);

                            /*Send int with value 11 to advertise server that a
                            system admin is going to start a connection*/

                            foutput = new DataOutputStream(s.getOutputStream());
                            foutput.writeInt(11);

                            //RECIEVE INCIDENCE ADMINISTRATORS
                            finput = new ObjectInputStream(s.getInputStream());
                            ArrayList<IncidenceAdmin> incidenceAdmins =
                                    (ArrayList<IncidenceAdmin>) finput.readObject();

                            viewSystemAdmin = new ViewSystemAdmin(incidenceAdmins);
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
    public void valueChanged(ListSelectionEvent e) {
        if (viewSystemAdmin != null && viewSystemAdmin.getSelectedAdmin()!= null)
            viewSystemAdmin.setAdminDetails(((JList<IncidenceAdmin>)e.getSource()).getSelectedValue());
        else {
            viewIncidenceAdmin.setAreaDetail(((JList<Incidence>)e.getSource()).getSelectedValue().getBody());
        }

    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        try {
            foutput.writeInt(3);
        } catch (IOException e) { }
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
