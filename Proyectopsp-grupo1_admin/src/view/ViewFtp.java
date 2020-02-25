package view;

import controller.MainAdmin;
import org.apache.commons.net.ftp.FTPFile;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ViewFtp extends javax.swing.JFrame {

    private javax.swing.JButton btnCreateFolder;
    private javax.swing.JButton btnDownloadFile;
    private javax.swing.JButton btnRemoveFile;
    private javax.swing.JButton btnRemoveFolder;
    private javax.swing.JButton btnUploadFile;
    private JList<String> fileDetail;
    private JList<String> fileList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private DefaultListModel<String> listModel;
    private DefaultListModel<String> listModelDetail;
    public ViewFtp(FTPFile[] files) {
        initComponents(files);
    }


    private void initComponents(FTPFile[] files) {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1 = new javax.swing.JScrollPane();
        fileList = new JList<String>();
        jScrollPane2 = new javax.swing.JScrollPane();
        fileDetail = new  JList<String>();

        btnRemoveFolder = new JButton();
        btnCreateFolder = new JButton();
        btnRemoveFile = new JButton();
        btnUploadFile = new JButton();
        btnDownloadFile = new JButton();


        btnUploadFile.setText("Upload File");
        btnUploadFile.setActionCommand("ftpUpload");
        btnUploadFile.addActionListener(MainAdmin.getAdminController());

        btnDownloadFile.setText("Download File");
        btnDownloadFile.setActionCommand("ftpDownload");
        btnDownloadFile.addActionListener(MainAdmin.getAdminController());

        btnRemoveFile.setText("Remove File");
        btnRemoveFile.setActionCommand("ftpRemoveFile");
        btnRemoveFile.addActionListener(MainAdmin.getAdminController());

        btnCreateFolder.setText("Create Folder");
        btnCreateFolder.setActionCommand("ftpCreateFolder");
        btnCreateFolder.addActionListener(MainAdmin.getAdminController());

        btnRemoveFolder.setText("Remove Folder");
        btnRemoveFolder.setActionCommand("ftpRemoveFolder");
        btnRemoveFolder.addActionListener(MainAdmin.getAdminController());


        listModel = new DefaultListModel<String>();
        listModelDetail = new DefaultListModel<String>();
        fileDetail.setModel(listModelDetail);


        for (FTPFile f : files) {
            String type;
            if (f.getType() == 1) {
                type = "(Dir ) ";
            }else
                type = "(File) ";

            listModel.addElement(type + f.getName());
        }

        fileList.setModel(listModel);
        fileList.setName("fileList");
        fileList.addListSelectionListener(MainAdmin.getAdminController());

        jScrollPane1.setViewportView(fileList);



	    chargeLayout();
	}

	public void chargeLayout(){

        fileDetail.setFixedCellWidth(20);
        fileDetail.setFixedCellHeight(17);
        jScrollPane2.setViewportView(fileDetail);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCreateFolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUploadFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRemoveFolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDownloadFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnRemoveFile)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateFolder)
                    .addComponent(btnRemoveFolder))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUploadFile)
                    .addComponent(btnDownloadFile)
                    .addComponent(btnRemoveFile))
                .addGap(20, 20, 20))
        );

        pack();
    }

    public void chargeDetailList(FTPFile[] files){
        listModelDetail.clear();


        for (FTPFile f : files) {
            String type;

            if (f.getType() == 1) {
                type = "(Dir ) ";
            }else {
                type = "(File) ";
            }
            listModelDetail.addElement(type + f.getName());
        }
    }

    public String getSelectedFile(){
        String file = null;

        try {

        if (fileList.getSelectedValue().substring(1,5).equals("Dir ")){
            if (fileDetail.getSelectedValue()!=null)
            file =  fileList.getSelectedValue().substring(7)+"/"+fileDetail.getSelectedValue().substring(7);

        }else
                file = fileList.getSelectedValue().substring(7);

            }catch (Exception e){
                e.printStackTrace();
            }
        return file;
    }

}
