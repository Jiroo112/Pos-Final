package com.main;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.text.Caret;
import koneksi.konek;


public class scanrfid extends javax.swing.JFrame {
    
    private Admin ad;
    private Karyawan kr;
    private login lg;
    private boolean login = true;
    
    public scanrfid(login login) {
        this.lg = login;
        initComponents();
        font();
        id.requestFocusInWindow();
        id.setForeground(Color.WHITE);
        id.setCaretColor(Color.white);
        ad = new Admin();
        kr = new Karyawan();
        jSeparator1.setForeground(Color.BLACK);
        close1.event(this);
    }
    
    public void font(){
        try {
            File fontStyle  = new File("src/com/font/Louis George Cafe Bold.ttf");
            File fontStyle2  = new File("src/com/font/Louis George Cafe.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(35f);
            Font font2 = Font.createFont(Font.TRUETYPE_FONT, fontStyle2).deriveFont(18f);
            title.setFont(font);
            body.setFont(font2);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void login(){
        String itemId = id.getText();
        
        try {
            Statement st = konek.GetConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT barcodenumber, jabatan FROM data_karyawan WHERE barcodenumber = '"+itemId+"'");
            if(rs.next()){
                String jabatan = rs.getString("jabatan");
                if(jabatan.equals("Admin")){
                    ad.setVisible(true);
                }
                else if(jabatan.equals("Kasir")){
                    kr.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Kartu tidak terdeteksi");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Kartu tidak terdeteksi");
                login=false;
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Outer = new com.swing.Shape();
        background21 = new com.swing.background2();
        title = new javax.swing.JLabel();
        close1 = new com.button.Close();
        body = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        title.setText("Scan RFID");
        background21.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 32, 181, -1));
        background21.add(close1, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 6, -1, -1));

        body.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        body.setText("Mohon Tap Kartu Anda!");
        background21.add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 198, -1));

        id.setForeground(new java.awt.Color(255, 255, 255));
        id.setBorder(null);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        background21.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 185, 30));
        background21.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 185, 10));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/picture/rf.gif"))); // NOI18N
        background21.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 216, 159));

        javax.swing.GroupLayout OuterLayout = new javax.swing.GroupLayout(Outer);
        Outer.setLayout(OuterLayout);
        OuterLayout.setHorizontalGroup(
            OuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OuterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        OuterLayout.setVerticalGroup(
            OuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OuterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background21, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Outer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Outer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        String itemId = id.getText();
        
        if(itemId.length()==10){
            login();
            if(login==false){
                id.setText("");
                login = true;
            }
            else{
                this.dispose();
                lg.dispose();
            }
        }
        else{
            id.setText("");
        }
    }//GEN-LAST:event_idActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(scanrfid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(scanrfid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(scanrfid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(scanrfid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                login lg = new login();
                new scanrfid(lg).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swing.Shape Outer;
    private com.swing.background2 background21;
    private javax.swing.JLabel body;
    private com.button.Close close1;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
