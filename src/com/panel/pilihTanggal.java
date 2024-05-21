package com.panel;


import com.mysql.cj.jdbc.Blob;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import koneksi.konek;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Brilian
 */
public class pilihTanggal extends javax.swing.JFrame {

private Keuntungan keuntunganPanel;

    public pilihTanggal(Keuntungan aThis) {
        initComponents();
         this.keuntunganPanel = aThis;
   
    }

    
    public void minuman(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Minuman");
        tbl.addColumn("Harga");
        tbl.addColumn("Stok");
        DataMenu.minuman.setModel(tbl);
        DataMenu.minuman.getTableHeader().setBackground(new Color(115,206,191));
        DataMenu.minuman.getTableHeader().setForeground(new Color(255,255,255));
        try {
            Statement st = konek.GetConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT nama_menu, harga, stok FROM menu WHERE kode_menu LIKE '%MI%'");
            while(rs.next()){
                tbl.addRow(new Object[]{
                    rs.getString("nama_menu"),
                    rs.getString("harga"),
                    rs.getString("stok")
                });
                DataMenu.minuman.setModel(tbl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
        public void makanan(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Makanan");
        tbl.addColumn("Harga");
        tbl.addColumn("Stok");
        DataMenu.makanan.setModel(tbl);
        DataMenu.makanan.getTableHeader().setBackground(new Color(115,206,191));
        DataMenu.makanan.getTableHeader().setForeground(new Color(255,255,255));
        try {
            Statement st = konek.GetConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT nama_menu, harga, stok FROM menu WHERE kode_menu LIKE '%MA%'");
            while(rs.next()){
                tbl.addRow(new Object[]{
                    rs.getString("nama_menu"),
                    rs.getString("harga"),
                    rs.getString("stok")
                });
                DataMenu.makanan.setModel(tbl);
            }
        } catch (Exception e) {
        }
    }
        public double modal()throws ParseException {
    double nilaiModal = 0.0;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String tanggalAwal = date1.getText();
    String tanggalAkhir = date2.getText();
    try {
        Date dateSatu = dateFormat.parse(tanggalAwal);
        Date dateDua = dateFormat.parse(tanggalAkhir);
        String query = "SELECT SUM(belanja.total) AS total_belanja FROM belanja WHERE tanggal BETWEEN ? AND ?";
        PreparedStatement preparedStatement = konek.GetConnection().prepareStatement(query);

        preparedStatement.setString(1, tanggalAwal);
        preparedStatement.setString(2, tanggalAkhir);

        ResultSet res = preparedStatement.executeQuery();

        while (res.next()) {
            nilaiModal = res.getDouble("total_belanja");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return nilaiModal;
}

public double transaksi() throws ParseException {
    double nilaiTotal = 0.0;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String tanggalAwal = date1.getText();
    String tanggalAkhir = date2.getText();
    try {
        Date dateSatu = dateFormat.parse(tanggalAwal);
        Date dateDua = dateFormat.parse(tanggalAkhir);
        String query = "SELECT SUM(transaksi.total) AS total_transaksi FROM transaksi WHERE tgl_transaksi BETWEEN ? AND ?";
        PreparedStatement preparedStatement = konek.GetConnection().prepareStatement(query);

        preparedStatement.setString(1, tanggalAwal);
        preparedStatement.setString(2, tanggalAkhir);

        ResultSet res = preparedStatement.executeQuery();

        while (res.next()) {
            nilaiTotal = res.getDouble("total_transaksi");
            
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return nilaiTotal;
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        shape2 = new com.swing.Shape();
        background1 = new com.swing.background();
        title = new javax.swing.JLabel();
        body = new javax.swing.JLabel();
        hitungbt = new com.button.Fbutton();
        shape1 = new com.swing.Shape();
        date1 = new javax.swing.JTextField();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        shape3 = new com.swing.Shape();
        date2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        batal = new com.button.Fbutton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        background1.setBackground(new java.awt.Color(122, 178, 178));
        background1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        title.setForeground(new java.awt.Color(0, 153, 153));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Pilih Tanggal");
        title.setToolTipText("");
        background1.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 6, -1, -1));

        body.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        body.setForeground(new java.awt.Color(0, 153, 153));
        body.setText("Untuk Menghitung Keuntungan yang Telah Didapat!");
        background1.add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 60, -1, -1));

        hitungbt.setText("Hitung");
        hitungbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungbtActionPerformed(evt);
            }
        });
        background1.add(hitungbt, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 100, 37));

        shape1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        date1.setBackground(new java.awt.Color(0, 167, 157));
        date1.setForeground(new java.awt.Color(255, 255, 255));
        date1.setBorder(null);
        shape1.add(date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 220, 30));

        background1.add(shape1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 260, 30));

        dateChooser1.setForeground(new java.awt.Color(122, 178, 178));
        dateChooser1.setDateFormat("yyyy-MM-dd");
        dateChooser1.setTextRefernce(date1);
        background1.add(dateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        dateChooser2.setForeground(new java.awt.Color(122, 178, 178));
        dateChooser2.setDateFormat("yyyy-MM-dd");
        background1.add(dateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));

        shape3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        date2.setBackground(new java.awt.Color(0, 167, 157));
        date2.setForeground(new java.awt.Color(255, 255, 255));
        date2.setBorder(null);
        shape3.add(date2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 220, 30));

        background1.add(shape3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 260, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sampai Tanggal");
        background1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 90, 250, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Dari Tanggal");
        background1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 90, 250, -1));

        batal.setText("Batal");
        batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batalMouseClicked(evt);
            }
        });
        background1.add(batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 410, 100, 40));

        javax.swing.GroupLayout shape2Layout = new javax.swing.GroupLayout(shape2);
        shape2.setLayout(shape2Layout);
        shape2Layout.setHorizontalGroup(
            shape2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shape2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
        );
        shape2Layout.setVerticalGroup(
            shape2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shape2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shape2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shape2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
  
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       
    }//GEN-LAST:event_formWindowActivated

    private void hitungbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungbtActionPerformed
      String tanggalAwal = date1.getText();
      String tanggalAkhir = date2.getText();

    if (tanggalAwal == null || tanggalAkhir == null) {
    JOptionPane.showMessageDialog(this, "Pilih tanggal terlebih dahulu", "Peringatan", JOptionPane.WARNING_MESSAGE);
    return; // Menghentikan eksekusi lebih lanjut jika salah satu tanggal kosong
}
    // Format tanggal sesuai dengan database
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    // Mengambil data transaksi dari database
    DefaultTableModel tbl = (DefaultTableModel) keuntunganPanel.Ttabel.getModel();
    tbl.setRowCount(0); // Menghapus semua baris yang ada

    try {
        Date dateSatu = dateFormat.parse(tanggalAwal);
        Date dateDua = dateFormat.parse(tanggalAkhir);
        Statement statement = konek.GetConnection().createStatement();
        ResultSet res = statement.executeQuery(
            "SELECT transaksi.tgl_transaksi, transaksi.kode_transaksi, transaksi.total " +
            "FROM transaksi WHERE tgl_transaksi BETWEEN '" + new java.sql.Date(dateSatu.getTime()) + "' AND '" + new java.sql.Date(dateDua.getTime()) + "' " +
            "ORDER BY tgl_transaksi ASC");

        while (res.next()) {
            tbl.addRow(new Object[]{
                res.getDate("tgl_transaksi"),
                res.getString("kode_transaksi"),
                res.getInt("total")   
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ParseException ex) {
        Logger.getLogger(pilihTanggal.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Mengambil data belanja dari database
    DefaultTableModel tblB = (DefaultTableModel) keuntunganPanel.belanjatabel.getModel();
    tblB.setRowCount(0); // Menghapus semua baris yang ada

    try {
        Date dateSatu = dateFormat.parse(tanggalAwal);
        Date dateDua = dateFormat.parse(tanggalAkhir);
        Statement statement = konek.GetConnection().createStatement();
        ResultSet res = statement.executeQuery(
            "SELECT belanja.tanggal, belanja.total, belanja.keterangan " +
            "FROM belanja WHERE tanggal BETWEEN '" + new java.sql.Date(dateSatu.getTime()) + "' AND '" + new java.sql.Date(dateDua.getTime()) + "' " +
            "ORDER BY tanggal ASC");

        while (res.next()) {
            tblB.addRow(new Object[]{
                res.getDate("tanggal"),
                res.getInt("total"),
                res.getString("keterangan")
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ParseException ex) {
        Logger.getLogger(pilihTanggal.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Menghitung dan menampilkan keuntungan
    try{
    double modal = modal();
    double transaksi = transaksi();
    double keuntungan = transaksi - modal;
    keuntunganPanel.getTotaluntungField().setText(String.valueOf(keuntungan));
    
    // Menutup jendela pilihTanggal
    
    }catch(ParseException e){
        e.printStackTrace();
    }
    dispose();
    }//GEN-LAST:event_hitungbtActionPerformed

    private void batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_batalMouseClicked

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
            java.util.logging.Logger.getLogger(pilihTanggal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pilihTanggal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pilihTanggal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pilihTanggal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
           
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swing.background background1;
    private com.button.Fbutton batal;
    private javax.swing.JLabel body;
    private javax.swing.JTextField date1;
    private javax.swing.JTextField date2;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private com.button.Fbutton hitungbt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.swing.Shape shape1;
    private com.swing.Shape shape2;
    private com.swing.Shape shape3;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
