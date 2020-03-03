package view;


import controller.MainAdmin;
import model.VO.Incidence;
import model.VO.IncidenceAdmin;

import javax.swing.*;
import java.util.ArrayList;

public class ViewSystemAdmin extends JFrame {


    private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnNew;
    private JTextField fieldMail;
    private JTextField fieldName;
    private JTextField fieldPhone;
    private JTextField fieldSurname;
    private JTextField fieldUsername;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JMenuBar jMenuBar1;
    private JScrollPane jScrollPane1;
    private JList<IncidenceAdmin> listAdmins;
    private JMenu menu;
    private JMenuItem menuFTP;
    private DefaultListModel<IncidenceAdmin>  listModel;
    private JMenuItem menuCorreo;


    public ViewSystemAdmin(ArrayList<IncidenceAdmin> incidenceAdmins) {
        initComponents(incidenceAdmins);
    }


    private void initComponents(ArrayList<IncidenceAdmin> incidenceAdmins) {

        jScrollPane1 = new JScrollPane();
        listAdmins = new JList<>();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        fieldName = new JTextField();
        fieldSurname = new JTextField();
        fieldMail = new JTextField();
        fieldPhone = new JTextField();
        fieldUsername = new JTextField();
        btnNew = new JButton();
        btnDelete = new JButton();
        btnEdit = new JButton();
        jMenuBar1 = new JMenuBar();
        menu = new JMenu();
        menuFTP = new JMenuItem();

        fieldName.setEditable(false);
        fieldSurname.setEditable(false);
        fieldMail.setEditable(false);
        fieldPhone.setEditable(false);
        fieldUsername.setEditable(false);
        menuCorreo = new JMenuItem();

        this.setResizable(false);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<IncidenceAdmin>();
        for(IncidenceAdmin i : incidenceAdmins){
            listModel.addElement(i);
        }
        listAdmins.setModel(listModel);
        listAdmins.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listAdmins.addListSelectionListener(MainAdmin.getAdminController());
        listAdmins.setName("listSystemAdmin");

        addWindowListener(MainAdmin.getAdminController());
        jScrollPane1.setViewportView(listAdmins);

        jLabel1.setText("Name");
        jLabel2.setText("Surname");
        jLabel3.setText("Mail");
        jLabel4.setText("Phone");
        jLabel5.setText("Username");



        btnNew.setText("New");
        btnNew.setActionCommand("newIncidenceAdmin");
        btnNew.addActionListener(MainAdmin.getAdminController());

        btnDelete.setText("Delete");
        btnDelete.setActionCommand("deleteIncidenceAdmin");
        btnDelete.addActionListener(MainAdmin.getAdminController());

        btnEdit.setText("Edit");
        btnEdit.setActionCommand("editIncidenceAdmin");
        btnEdit.addActionListener(MainAdmin.getAdminController());

        jMenuBar1.add(menu);
        menu.setText("File");
        menuFTP.setText("FTP");
        menuCorreo.setText("Correo");
        menu.add(menuFTP);
        menu.add(menuCorreo);

        menuFTP.setActionCommand("FTP");
        menuFTP.addActionListener(MainAdmin.getAdminController());

        menuCorreo.setActionCommand("Correo");
        menuCorreo.addActionListener(MainAdmin.getAdminController());


        setJMenuBar(jMenuBar1);

        chargeLayout();
    }
     public void chargeLayout(){

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldUsername, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldPhone, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldMail, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldSurname, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(44, 44, 44)
                        .addComponent(fieldName, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(fieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(fieldSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(fieldMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(fieldPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(fieldUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)))
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnEdit)
                    .addComponent(btnNew))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }



    public void setAdminDetails(IncidenceAdmin incidenceAdmin){
        this.fieldName.setText(incidenceAdmin.getName());
        this.fieldSurname.setText(incidenceAdmin.getApell());
        this.fieldMail.setText(incidenceAdmin.getMail());
        this.fieldPhone.setText(incidenceAdmin.getPhone());
        this.fieldUsername.setText(incidenceAdmin.getUsername());
    }

    public void setAdminDetailsEmpty(){
        this.fieldName.setText("");
        this.fieldSurname.setText("");
        this.fieldMail.setText("");
        this.fieldPhone.setText("");
        this.fieldUsername.setText("");
    }


    public void addIncidenceAdmin(IncidenceAdmin incidenceAdmin){
        this.listModel.addElement(incidenceAdmin);
    }


    public IncidenceAdmin getSelectedAdmin(){
        return this.listAdmins.getSelectedValue();
    }


    public void removeSelectedAdmin(){
        this.listModel.removeElementAt(listAdmins.getSelectedIndex());
        this.setAdminDetailsEmpty();
    }

    public void updateSelectedAdmin(IncidenceAdmin incidenceAdmin){
        this.listModel.setElementAt(incidenceAdmin,listAdmins.getSelectedIndex()    );
    }




}
