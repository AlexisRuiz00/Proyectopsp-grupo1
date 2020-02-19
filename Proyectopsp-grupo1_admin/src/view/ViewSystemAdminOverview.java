package view;


import controller.MainAdmin;
import model.VO.Incidence;
import model.VO.IncidenceAdmin;

import javax.swing.*;

public class ViewSystemAdminOverview extends JFrame {

    private JButton btnCancel;
    private JButton btnOk;
    private JTextField fieldMail;
    private JTextField fieldName;
    private JPasswordField fieldPassword;
    private JTextField fieldPhone;
    private JTextField fieldSurname;
    private JTextField fieldUsername;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;


    public ViewSystemAdminOverview(IncidenceAdmin incidenceAdmin) {
        initComponents(incidenceAdmin);
    }


    private void initComponents(IncidenceAdmin incidenceAdmin) {

        jLabel1 = new JLabel();
        fieldName = new JTextField();
        jLabel2 = new JLabel();
        fieldSurname = new JTextField();
        fieldMail = new JTextField();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        fieldPhone = new JTextField();
        jLabel5 = new JLabel();
        fieldUsername = new JTextField();
        fieldPassword = new JPasswordField();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        btnOk = new JButton();
        btnCancel = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name");
        jLabel2.setText("Surname");
        jLabel3.setText("Mail");
        jLabel4.setText("Phone");
        jLabel5.setText("Username");
        jLabel6.setText("Password");
        jLabel7.setText("Incidence Administrator");


        if (incidenceAdmin!=null){
            this.fieldName.setText(incidenceAdmin.getName());
            this.fieldSurname.setText(incidenceAdmin.getApell());
            this.fieldMail.setText(incidenceAdmin.getMail());
            this.fieldPhone.setText(incidenceAdmin.getPhone());
            this.fieldUsername.setText(incidenceAdmin.getUsername());
        }






        btnOk.setText("Ok");
        btnOk.setActionCommand("okOverview");
        btnOk.addActionListener(MainAdmin.getAdminController());

        btnCancel.setText("Cancel");
        btnCancel.setActionCommand("cancelOverview");
        btnCancel.addActionListener(MainAdmin.getAdminController());

        chargeLayout();
    }
     public void chargeLayout(){

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(44, 44, 44)
                                    .addComponent(fieldName, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addGap(44, 44, 44)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(fieldSurname, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fieldMail, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(44, 44, 44)
                                        .addComponent(fieldPhone, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(44, 44, 44)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fieldPassword)
                                        .addComponent(fieldUsername, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnOk)
                                .addGap(27, 27, 27)
                                .addComponent(btnCancel)
                                .addGap(12, 12, 12))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel7)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }

    public IncidenceAdmin getIncidenceAdmin(){
        IncidenceAdmin incidenceAdmin = new IncidenceAdmin(
          this.fieldName.getText(),
          this.fieldSurname.getText(),
          this.fieldMail.getText(),
          this.fieldPhone.getText(),
          this.fieldUsername.getText(),
          this.fieldPassword.getPassword().toString(),
          "IncidenceAdmin"
        );

        return incidenceAdmin;
    }
 
}
