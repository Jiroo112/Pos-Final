/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.panel;

import java.awt.Color;
import com.main.Admin;
import com.main.popupregist;
import com.panel.Registrasi;
import com.swing.ScrollBar;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.konek;

/**
 *
 * @author liber
 */
public class DataKaryawan extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private Registrasi regist;
    popupregist popup = new popupregist();
    
    public DataKaryawan() {
        initComponents();
        font();
        regist = new Registrasi();
        Scroll.setVerticalScrollBar(new ScrollBar());
        setBackground(new Color(0,0,0));
                tabelkaryawan();
    }
        public void showForm(Component com){
        Admin.mainPanel.removeAll();
        Admin.mainPanel.add(com);
        Admin.mainPanel.repaint();
        Admin.mainPanel.revalidate();
    }
        
        public void font(){
        try {
            File fontStyle  = new File("src/com/font/Louis George Cafe Bold Italic.ttf");
            File fontStyle2  = new File("src/com/font/Louis George Cafe Italic.ttf");
            File fontStyle3  = new File("src/com/font/Louis George Cafe.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(36f);
            Font font2 = Font.createFont(Font.TRUETYPE_FONT, fontStyle2).deriveFont(12f);
            Font font3 = Font.createFont(Font.TRUETYPE_FONT, fontStyle3).deriveFont(12f);
            title.setFont(font);
            body.setFont(font2);
            fbutton1.setFont(font3);
            hapus_button.setFont(font3);
            fbutton3.setFont(font3);
        } catch (Exception e) {
            e.getMessage();
        }
    }
        
    public void tabelkaryawan(){
    DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Username");
        tbl.addColumn("Password");
        tbl.addColumn("Email");
        tbl.addColumn("Nama");
        tbl.addColumn("Gender");
        tbl.addColumn("No Telepon");
        tbl.addColumn("Jabatan");
        tbl.addColumn("Alamat");
        tbl.addColumn("ID Number");
        karyawan.setModel(tbl);
        
        karyawan.getTableHeader().setBackground(new Color(115,206,191));
        karyawan.getTableHeader().setForeground(new Color(255,255,255));
        try {
            Statement st = konek.GetConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM data_karyawan");
            while(rs.next ()){
                tbl.addRow(new Object[] {
                rs.getString("username"),
                rs.getString("password"), 
                rs.getString("email"),
                rs.getString("nama_karyawan"),
                rs.getString("jenis_kelamin"),
                rs.getString("no_hp"),
                rs.getString("jabatan"),
                rs.getString("alamat"),
                rs.getString("barcodenumber")
                });
                karyawan.setModel(tbl);
            } 
            st.close();
        } catch (Exception e) {
        }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        panelShadow1 = new Shape.PanelShadow();
        Scroll = new javax.swing.JScrollPane();
        karyawan = new javax.swing.JTable();
        body = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        fbutton1 = new com.button.Fbutton();
        hapus_button = new com.button.Fbutton();
        fbutton3 = new com.button.Fbutton();

        jRadioButton1.setText("jRadioButton1");

        setMinimumSize(new java.awt.Dimension(1020, 520));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1020, 520));

        panelShadow1.setBackground(new java.awt.Color(255, 255, 255));

        karyawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                karyawanMouseClicked(evt);
            }
        });
        Scroll.setViewportView(karyawan);

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(Scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        body.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        body.setForeground(new java.awt.Color(0, 153, 153));
        body.setText("Daftar karyawan Warung Lalapan 8");

        title.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        title.setForeground(new java.awt.Color(0, 153, 153));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Daftar Karyawan");
        title.setToolTipText("");

        fbutton1.setText("Edit");
        fbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fbutton1ActionPerformed(evt);
            }
        });

        hapus_button.setText("Hapus");
        hapus_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_buttonActionPerformed(evt);
            }
        });

        fbutton3.setText("Tambah");
        fbutton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fbutton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(fbutton3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hapus_button, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(body)
                            .addComponent(title)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapus_button, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fbutton3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(48, 48, 48))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fbutton1ActionPerformed
        // TODO add your handling code here:
        int index = karyawan.getSelectedRow();
        editk k = new editk();
        
        DefaultTableModel model = (DefaultTableModel)karyawan.getModel();  

        
         String Username = model.getValueAt(index, 0).toString();
         String Password = model.getValueAt(index, 1).toString();
         String Email = model.getValueAt(index, 2).toString();
         String Nama = model.getValueAt(index, 3).toString();
         String Gender = model.getValueAt(index, 4).toString();
         String NoTelp = model.getValueAt(index, 5).toString();
         String Jabatan = model.getValueAt(index, 6).toString();
         String Alamat = model.getValueAt(index, 7).toString();
         String IDNumber = model.getValueAt(index, 8).toString();


         k.setVisible(true);
         k.pack();
         
         k.txt_username.setText(Username);
         k.pass.setText(Password);
         k.txt_email.setText(Email);
         k.txt_nama.setText(Nama);
         k.jComb_gender.setSelectedItem(Gender);
         k.txt_telp.setText(NoTelp);
         k.jComb_jabatan.setSelectedItem(Jabatan);
         k.txt_alamat.setText(Alamat);
         k.txt_barcode.setText(IDNumber);
    }//GEN-LAST:event_fbutton1ActionPerformed

    private void hapus_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_buttonActionPerformed
    int index = karyawan.getSelectedRow();
    
    DefaultTableModel model = (DefaultTableModel)karyawan.getModel();      
    String Username = model.getValueAt(index, 0).toString();
    
        try {
            Statement st = konek.GetConnection().createStatement();
            ResultSet rs = st.executeQuery("DELETE FROM data_karyawan WHERE username = '"+Username+"';");
        } catch (Exception e) {
            e.getMessage();
        }
    }//GEN-LAST:event_hapus_buttonActionPerformed

    private void karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_karyawanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_karyawanMouseClicked

    private void fbutton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fbutton3ActionPerformed
        popup.setVisible(true);
    }//GEN-LAST:event_fbutton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Scroll;
    private javax.swing.JLabel body;
    private com.button.Fbutton fbutton1;
    private com.button.Fbutton fbutton3;
    private com.button.Fbutton hapus_button;
    private javax.swing.JRadioButton jRadioButton1;
    public javax.swing.JTable karyawan;
    private Shape.PanelShadow panelShadow1;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
