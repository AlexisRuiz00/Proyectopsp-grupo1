package View;

import controller.MainCliente;
import model.VO.Incidence;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Alexis
 */
public class ViewClient extends JFrame {

    private JButton btnChat;
    private JButton btnReply;
    private JButton btnClose;
    private JTextArea incidencesDetail;
    private JButton btnSendIncidence;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JList<Incidence> jList1;
    private JMenu jMenu1;
    private JMenuItem btnNewIncidence;
    private JMenuItem btnOpenChat;
    private JMenuItem btnCloseChat;
    private JPanel jPanel1;
    private JSeparator jSeparator1;
    private JTextArea jTextArea1;
    private JTextArea textReply;
    private JScrollPane listIncidences;
    private JMenuBar menuBar;
    private JTextArea txtChat;
    private DefaultListModel<Incidence>  listModel;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;


    /**
     * Creates new form ClientView
     */
    public ViewClient(ArrayList<Incidence> incidences) {
        this.setTitle("ATCS - Client");
        initComponents(incidences);
}


    private void initComponents(ArrayList<Incidence> incidences) {

        jPanel1 = new JPanel();
        listIncidences = new JScrollPane();
        jList1 = new JList<Incidence>();
        incidencesDetail = new JTextArea();
        btnSendIncidence = new JButton();
        btnChat = new JButton();
        btnReply = new JButton();
        btnClose = new JButton();
        jSeparator1 = new JSeparator();
        txtChat = new JTextArea();
        jTextArea1 = new JTextArea();
        textReply = new JTextArea();
        menuBar = new JMenuBar();
        jMenu1 = new JMenu();
        btnNewIncidence = new JMenuItem();
        btnOpenChat = new JMenuItem();
        btnCloseChat = new JMenuItem();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jScrollPane2 = new JScrollPane();


        chargeLayout(incidences);

        this.addWindowListener(MainCliente.getClientController());

        //Configure Button actions


        //CHAT BUTTONS
        btnChat.setActionCommand("sendChat");
        btnChat.addActionListener(MainCliente.getClientController());

        btnClose.setActionCommand("closeChat");
        btnClose.addActionListener(MainCliente.getClientController());

        btnOpenChat.setActionCommand("openChat");
        btnOpenChat.addActionListener(MainCliente.getClientController());


        //INCIDENCE BUTTONS
        btnReply.setActionCommand("replyIncidence");
        btnReply.addActionListener(MainCliente.getClientController());

        btnNewIncidence.setActionCommand("newIncidence");
        btnNewIncidence.addActionListener(MainCliente.getClientController());



        jList1.addListSelectionListener(MainCliente.getClientController());

    }

    public void chargeLayout(ArrayList<Incidence> incidences) {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);


        listModel = new DefaultListModel<Incidence>();
        for(Incidence i : incidences){
            listModel.addElement(i);
        }
        jList1.setModel(listModel);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listIncidences.setViewportView(jList1);


        incidencesDetail.setEditable(false);


        btnReply.setText("Send");
        btnChat.setText("Send");

        jSeparator1.setOrientation(SwingConstants.VERTICAL);
        jScrollPane1.setViewportView(incidencesDetail);
        jScrollPane2.setViewportView(textReply);

        txtChat.setEditable(false);
        txtChat.setColumns(20);
        txtChat.setRows(5);
        txtChat.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextArea1.setColumns(15);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        textReply.setColumns(20);
        textReply.setLineWrap(true);
        textReply.setRows(5);
        textReply.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        textReply.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        jLabel1.setText("Incidences");

        jLabel2.setText("Detail");

        btnClose.setText("Close");


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLabel1)
                                                .addGap(284, 284, 284)
                                                .addComponent(jLabel2)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(listIncidences, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(btnReply))
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(btnClose)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnChat))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jTextArea1)
                                                                        .addComponent(txtChat))))
                                                .addGap(12, 12, 12))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(listIncidences, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane1))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane2))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtChat, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnChat)
                                                .addComponent(btnClose))
                                        .addComponent(btnReply, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(1, 1, 1))
        );

        jMenu1.setText("File");

        btnNewIncidence.setText("New Incidence");
        jMenu1.add(btnNewIncidence);

        btnOpenChat.setText("Chat Support");
        jMenu1.add(btnOpenChat);

        btnCloseChat.setText("Exit");
        jMenu1.add(btnCloseChat);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public int getSelectedIncidenceListId(){
        if (jList1.getSelectedValue() != null) {
            return jList1.getSelectedIndex();
        }else
            return -1;
    }

    public String getTextReplyToString(){
        return this.textReply.getText();
    }
    public void setIncidencesDetail(String detail){
        this.incidencesDetail.setText(detail);
    }
    public void addToList(Incidence i){
        listModel.addElement(i);
    }
    public void updateElement(Incidence i, int id){
        listModel.add(id,i);
        listModel.remove(id);
    }


    public void writeInChat(String string){
        this.txtChat.append(string);
    }
    public void cleanChat(){
        this.txtChat.setText("");
    }

    public String getChatMessage(){
        return this.jTextArea1.getText();
    }

    public void openChat(){
        this.resize(760,getHeight());
    }
    public void closeChat(){
        this.resize(516,getHeight());
    }
}