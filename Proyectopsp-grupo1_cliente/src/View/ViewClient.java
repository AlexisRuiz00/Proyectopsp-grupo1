package View;

import Model.Incidence;
import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author Alexis
 */
public class ViewClient extends JFrame {

    private JButton btnChat;
    private JButton btnReply;
    private JButton btnSendIncidence;
    private JTextField incidencesDetail;
    private JList<Incidence> jList1;
    private JMenu jMenu1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JPanel jPanel1;
    private JSeparator jSeparator1;
    private JTextArea jTextArea1;
    private JTextArea jTextArea2;
    private JScrollPane listIncidences;
    private JMenuBar menuBar;
    private JTextArea txtChat;


    /**
     * Creates new form ClientView
     */
    public ViewClient(ArrayList<Incidence> incidences) {
        initComponents(incidences);
    }




    private void initComponents(ArrayList<Incidence> incidences) {

        jPanel1 = new JPanel();
        listIncidences = new JScrollPane();
        jList1 = new JList<Incidence>();
        incidencesDetail = new JTextField();
        btnSendIncidence = new JButton();
        btnReply = new JButton();
        btnChat = new JButton();
        jSeparator1 = new JSeparator();
        txtChat = new JTextArea();
        jTextArea1 = new JTextArea();
        jTextArea2 = new JTextArea();
        menuBar = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenuItem2 = new JMenuItem();
        jMenuItem3 = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jList1.setModel(new AbstractListModel<Incidence>() {
            public int getSize() { return incidences.size(); }
            public Incidence getElementAt(int i) { return incidences.get(i); }
        });
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listIncidences.setViewportView(jList1);

        incidencesDetail.setEditable(false);


        btnSendIncidence.setText("Reply");
        btnReply.setText("Send");

        btnChat.setText("Send");

        jSeparator1.setOrientation(SwingConstants.VERTICAL);

        txtChat.setEditable(false);
        txtChat.setColumns(20);
        txtChat.setRows(5);
        txtChat.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(listIncidences, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSendIncidence)
                            .addComponent(incidencesDetail, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnReply, GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextArea2))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnChat))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextArea1)
                            .addComponent(txtChat))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(incidencesDetail)
                            .addComponent(listIncidences, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSendIncidence)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextArea2, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtChat)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextArea1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnChat, GroupLayout.Alignment.TRAILING)
                    .addComponent(btnReply, GroupLayout.Alignment.TRAILING))
                .addGap(1, 1, 1))
        );

        jMenu1.setText("File");

        jMenuItem1.setText("New Incidence");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Chat Support");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Exit");
        jMenu1.add(jMenuItem3);

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
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReplyActionPerformed(java.awt.event.ActionEvent evt) {
    }//GEN-LAST:event_btnReplyActionPerformed

}
