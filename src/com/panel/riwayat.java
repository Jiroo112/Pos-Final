
package com.panel;

import com.main.tanggal_riwayat;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import koneksi.konek;

/**
 *
 * @author ASUS
 */
public class riwayat extends javax.swing.JPanel {
    
    private tanggal_riwayat tanggal;
    
    private Component rootPane;

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getDate1() {
        return date1;
    }

    public String getDate2() {
        return date2;
    }

        private String date1;
        private String date2;
        
    public void totalBiaya(){
        int jumlahBaris = tabel_riwayat.getRowCount();
        int totalBiaya = 0;
        int total;
        for(int i = 0; i < jumlahBaris; i++){
            total =Integer.parseInt(tabel_riwayat.getValueAt(i, 2).toString());
            totalBiaya = totalBiaya + (total);
        }
        txt_pemasukan.setText("Rp " +totalBiaya +",00");
    }
    
    public riwayat() {
        initComponents();
        tanggal = new tanggal_riwayat();
        data_table();
        JTableHeader header = tabel_riwayat.getTableHeader();
        // Membuat renderer kustom
        header.setDefaultRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setOpaque(true);
                label.setBackground(new Color(122, 178, 178)); // Set your desired background color
                label.setForeground(Color.BLACK); // Set your desired foreground color
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(40,40));
                label.setFont(new Font("Segoe UI",Font.BOLD, 14));
                
                return label;
            }
        });
    }
    
    public void setDate(){
        setDate1(tanggal.a);
        setDate2(tanggal.b);
        System.out.println(getDate1());
        System.out.println(getDate2());
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
            tanggalButton.setFont(font3);
            
        } catch (Exception e) {
            e.getMessage();
        }
    }

     public void data_table(){
        DefaultTableModel tbl=new DefaultTableModel();
        tbl.addColumn("Tanggal Transaksi");
        tbl.addColumn("Kode Transaksi");
        tbl.addColumn("Total");
        tbl.addColumn("Bayar");
        tbl.addColumn("Kembalian");
        tbl.addColumn("Username");
       tabel_riwayat.setModel(tbl);
        try{
            Statement statement = (Statement)konek.GetConnection().createStatement();
            ResultSet res= statement.executeQuery("select * from transaksi where kode_transaksi <(select max(kode_transaksi) from transaksi); ");
            while(res.next())
            {
                tbl.addRow(new Object[] {
                    res.getDate("tgl_transaksi"),
                    res.getString("kode_transaksi"),
                    res.getInt("total"),
                    res.getInt("bayar"),
                    res.getInt("kembalian"),
                    res.getString("username"),
                });
                tabel_riwayat.setModel(tbl);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(rootPane, "salah"); 
        } 
     }
     public static JTextField getTotalPemasukan() {
        return txt_pemasukan;
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        body = new javax.swing.JLabel();
        panelShadow1 = new Shape.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_riwayat = new javax.swing.JTable();
        background2 = new com.swing.background();
        jLabel5 = new javax.swing.JLabel();
        shape1 = new com.swing.Shape();
        tanggalButton = new javax.swing.JLabel();
        shape2 = new com.swing.Shape();
        reload = new javax.swing.JLabel();
        shape3 = new com.swing.Shape();
        txt_search = new javax.swing.JTextField();
        search = new com.button.button();
        shape4 = new com.swing.Shape();
        txt_pemasukan = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(1020, 520));
        setPreferredSize(new java.awt.Dimension(1020, 520));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(245, 247, 248));
        jPanel1.setPreferredSize(new java.awt.Dimension(1020, 520));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        title.setForeground(new java.awt.Color(0, 153, 153));
        title.setText("Riwayat Transaksi");
        jPanel1.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        body.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        body.setForeground(new java.awt.Color(0, 153, 153));
        body.setText("Pilih Riwayat Transaksi yang Anda Cari");
        jPanel1.add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        panelShadow1.setBackground(new java.awt.Color(122, 178, 178));
        panelShadow1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_riwayat.setBackground(new java.awt.Color(122, 178, 178));
        tabel_riwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal ", "Kode Transaksi", "Total", "Bayar", "Kembalian", "Username"
            }
        ));
        tabel_riwayat.setRowHeight(30);
        tabel_riwayat.setSelectionBackground(new java.awt.Color(0, 135, 133));
        tabel_riwayat.setShowGrid(false);
        jScrollPane1.setViewportView(tabel_riwayat);

        panelShadow1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 910, 210));

        jPanel1.add(panelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 950, 270));

        background2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Total pemasukan");
        background2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 510, -1, -1));

        shape1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tanggalButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tanggalButton.setForeground(new java.awt.Color(255, 255, 255));
        tanggalButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tanggalButton.setText("Tanggal");
        tanggalButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tanggalButtonMouseClicked(evt);
            }
        });
        shape1.add(tanggalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 40));

        background2.add(shape1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 130, 40));

        shape2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        reload.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/picture/reload.png"))); // NOI18N
        reload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reloadMouseClicked(evt);
            }
        });
        shape2.add(reload, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        background2.add(shape2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 40, 40));

        shape3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_search.setBackground(new java.awt.Color(0, 167, 157));
        txt_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_search.setForeground(new java.awt.Color(255, 255, 255));
        txt_search.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_search.setBorder(null);
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        shape3.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 30));

        background2.add(shape3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, 210, 30));

        search.setBackground(new java.awt.Color(0, 167, 157));
        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/picture/cari (1).png"))); // NOI18N
        search.setAutoscrolls(true);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        background2.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 160, 40, 30));

        shape4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_pemasukan.setBackground(new java.awt.Color(0, 167, 157));
        txt_pemasukan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_pemasukan.setForeground(new java.awt.Color(255, 255, 255));
        txt_pemasukan.setBorder(null);
        txt_pemasukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_pemasukanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_pemasukanMouseEntered(evt);
            }
        });
        shape4.add(txt_pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 210, 40));

        background2.add(shape4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 500, 230, 40));

        jPanel1.add(background2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 700));

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        String dicari= txt_search.getText();
        try{
            Statement statement = (Statement)konek.GetConnection().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM transaksi WHERE kode_transaksi = ('" + dicari +"');");

            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("Tanggal Transaksi");
            tbl.addColumn("Kode Transaksi");
            tbl.addColumn("Total");
            tbl.addColumn("Bayar");
            tbl.addColumn("Kembalian");
            tbl.addColumn("Username");
            tabel_riwayat.setModel(tbl);
             while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getDate("tgl_transaksi"),
                    res.getString("kode_transaksi"),
                    res.getInt("total"),
                    res.getInt("bayar"),
                    res.getInt("kembalian"),
                    res.getString("username")
                });
                tabel_riwayat.setModel(tbl);

            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(rootPane, "salah");
        
        }
        totalBiaya();
    }//GEN-LAST:event_searchActionPerformed

    private void tanggalButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tanggalButtonMouseClicked
        tanggal.setVisible(true);
    }//GEN-LAST:event_tanggalButtonMouseClicked

    private void reloadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reloadMouseClicked
        // TODO add your handling code here:
        data_table();
        totalBiaya();
    }//GEN-LAST:event_reloadMouseClicked

    private void txt_pemasukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_pemasukanMouseClicked
        // TODO add your handling code here:
        totalBiaya();
    }//GEN-LAST:event_txt_pemasukanMouseClicked

    private void txt_pemasukanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_pemasukanMouseEntered
        // TODO add your handling code here:
        totalBiaya();
    }//GEN-LAST:event_txt_pemasukanMouseEntered

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swing.background background2;
    private javax.swing.JLabel body;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Shape.PanelShadow panelShadow1;
    private javax.swing.JLabel reload;
    private com.button.button search;
    private com.swing.Shape shape1;
    private com.swing.Shape shape2;
    private com.swing.Shape shape3;
    private com.swing.Shape shape4;
    public static javax.swing.JTable tabel_riwayat;
    private javax.swing.JLabel tanggalButton;
    private javax.swing.JLabel title;
    public static javax.swing.JTextField txt_pemasukan;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
