package com.panel;

import org.jfree.chart.ChartFactory;
import com.main.Admin;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import Shape.BarChartAnimator;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import koneksi.konek;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.util.Calendar;
import java.util.Date;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.general.Dataset;

public class GrafikBaru extends JPanel {

    private JFreeChart chart;
    private DefaultCategoryDataset dataset;
    private Date tanggalAwal;
    private Date tanggalAkhir;
        private Animator animator;
    private float animate;

    public GrafikBaru() {
        initComponents();
        setBackground(new Color(250, 250, 250));

        dataset = new DefaultCategoryDataset();
        chart = createBarChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(chartPanel, BorderLayout.CENTER);

        fillDataset();

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(null);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(245, 189, 135)); //transaksi
        renderer.setSeriesPaint(1, new Color(135, 189, 245)); // belanja
        renderer.setSeriesPaint(2, new Color(189, 135, 245)); // keuntungan

        // Implementasi animasi untuk setiap bar
        makanan();
        minuman();
        tampilkanTopSellerMakanan();
        tampilkanTopSellerMinuman();
               animateBars();
    }

    private JFreeChart createBarChart() {
        return ChartFactory.createBarChart(
                "Penjualan dan Belanja Bulanan",
                "Tanggal",
                "Jumlah",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    private void fillDataset() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -40);
        Date startDate = calendar.getTime();
        Date endDate = new Date();

        try {
            PreparedStatement psTransaksi = konek.GetConnection().prepareStatement("SELECT SUM(total) as total FROM transaksi WHERE tgl_transaksi BETWEEN ? AND ?");
            psTransaksi.setDate(1, new java.sql.Date(startDate.getTime()));
            psTransaksi.setDate(2, new java.sql.Date(endDate.getTime()));

            PreparedStatement psBelanja = konek.GetConnection().prepareStatement("SELECT SUM(total) as total FROM belanja WHERE tanggal BETWEEN ? AND ?");
            psBelanja.setDate(1, new java.sql.Date(startDate.getTime()));
            psBelanja.setDate(2, new java.sql.Date(endDate.getTime()));

            //rs
            ResultSet rsTransaksi = psTransaksi.executeQuery();
            ResultSet rsBelanja = psBelanja.executeQuery();

            ///get value
            double totalTransaksi = 0;
            double totalBelanja = 0;

            if (rsTransaksi.next()) {
                totalTransaksi = rsTransaksi.getDouble("total");
            }

            if (rsBelanja.next()) {
                totalBelanja = rsBelanja.getDouble("total");
            }

           //untung = transaksi - belanja
            double keuntungan = totalTransaksi - totalBelanja;

            dataset.addValue(totalTransaksi, "Belanja", "Bulan Lalu");
            dataset.addValue(totalBelanja, "Transaksi", "Bulan Lalu");
            dataset.addValue(keuntungan, "Keuntungan", "Bulan Lalu");

            //tutup ps rs
            rsTransaksi.close();
            rsBelanja.close();
            psTransaksi.close();
            psBelanja.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
        private void animateBars() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                animate = fraction;
                dataset.setValue(animate * dataset.getValue(0, 0).doubleValue(), dataset.getRowKey(0), dataset.getColumnKey(0));
                dataset.setValue(animate * dataset.getValue(1, 0).doubleValue(), dataset.getRowKey(1), dataset.getColumnKey(0));
                dataset.setValue(animate * dataset.getValue(2, 0).doubleValue(), dataset.getRowKey(2), dataset.getColumnKey(0));
            }
        };
        animator = new Animator(800, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
    
    
    }
        private Date getOneMonthAgoDate() {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, -40); 
                return calendar.getTime();
    }
    private void makanan() {
        DefaultTableModel tblMakanan = new DefaultTableModel();
        tblMakanan.addColumn("Makanan");
        tblMakanan.addColumn("Harga");
        tblMakanan.addColumn("Stok Terjual/Bulan");
        makanan1.setModel(tblMakanan); 
        makanan1.getTableHeader().setBackground(new Color(115, 206, 191));
        makanan1.getTableHeader().setForeground(new Color(0, 0, 0));

        try {
            Statement st = konek.GetConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT m.nama_menu, m.harga, SUM(dt.jumlah) AS total_terjual " +
                    "FROM menu m " +
                    "LEFT JOIN detail_transaksi dt ON m.kode_menu = dt.kode_menu " +
                    "JOIN transaksi t ON dt.kode_transaksi = t.kode_transaksi " +
                    "WHERE m.kode_menu LIKE '%MA%' " +
                    "AND t.tgl_transaksi >= DATE_SUB(CURDATE(), INTERVAL 40 MONTH) " +
                    "GROUP BY m.nama_menu");
            while (rs.next()) {
                String namaMenu = rs.getString("nama_menu");
                String harga = rs.getString("harga");
                String totalTerjual = rs.getString("total_terjual");
                tblMakanan.addRow(new Object[]{namaMenu, harga, totalTerjual});
                //testing
                System.out.println("Makanan: " + namaMenu + ", Harga: " + harga + ", Terjual: " + totalTerjual);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

  }
    public DefaultCategoryDataset minuman() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DefaultTableModel tblMinuman = new DefaultTableModel();
        tblMinuman.addColumn("Minuman");
        tblMinuman.addColumn("Harga");
        tblMinuman.addColumn("StokTerjual/Bulan");
        minuman1.setModel(tblMinuman);
        minuman1.getTableHeader().setBackground(new Color(115,206,191));
        minuman1.getTableHeader().setForeground(new Color(0,0,0));
        try {
            Statement st = konek.GetConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT m.nama_menu, m.harga, SUM(dt.jumlah) AS total_terjual " +
                                           "FROM menu m " +
                                           "LEFT JOIN detail_transaksi dt ON m.kode_menu = dt.kode_menu " +
                                           "JOIN transaksi t ON dt.kode_transaksi = t.kode_transaksi " +
                                           "WHERE m.kode_menu LIKE '%MI%' " +
                                           "AND t.tgl_transaksi >= DATE_SUB(CURDATE(), INTERVAL 40 MONTH) " +
                                           "GROUP BY m.nama_menu");
            while(rs.next()){
                tblMinuman.addRow(new Object[]{
                        rs.getString("nama_menu"),
                        rs.getString("harga"),
                        rs.getString("total_terjual")
                });
                dataset.addValue(rs.getDouble("total_terjual"), "Minuman", rs.getString("nama_menu"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return dataset;

        }
    private String topSellerInfo(ResultSet rs) throws SQLException {
       StringBuilder sb = new StringBuilder();
       sb.append("Top 3 Menu Terlaris:\n");
       int rank = 1;
       while (rs.next()) {
           String namaMenu = rs.getString("nama_menu");
           int totalTerjual = rs.getInt("total_terjual");
           sb.append(rank).append(". ").append(namaMenu).append(" - ").append(totalTerjual).append(" terjual\n");
           rank++;
       }
       return sb.toString();
   }

    private void tampilkanTopSellerMakanan() {
       try {
        String queryTopSellerMakanan = "SELECT m.nama_menu, SUM(dt.jumlah) AS total_terjual FROM menu m "
                + "LEFT JOIN detail_transaksi dt ON m.kode_menu = dt.kode_menu AND m.kode_menu LIKE 'MA%' "
                + "JOIN transaksi t ON dt.kode_transaksi = t.kode_transaksi WHERE t.tgl_transaksi >= DATE_SUB(CURDATE(), INTERVAL 40 MONTH) "
                + "GROUP BY m.nama_menu ORDER BY total_terjual DESC LIMIT 3;";

        PreparedStatement psTopSeller = konek.GetConnection().prepareStatement(queryTopSellerMakanan);
        ResultSet rsTopSeller = psTopSeller.executeQuery();

        String topSellerText = "";
        while (rsTopSeller.next()) {
            String namaMenu = rsTopSeller.getString("nama_menu");
            int totalTerjual = rsTopSeller.getInt("total_terjual");
            topSellerText +=  "<html>" +  namaMenu + " : " + totalTerjual + " terjual <br/>";
        }

        bestSeller3makanan1.setText(topSellerText);

        rsTopSeller.close();
        psTopSeller.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
       

    }
    private void tampilkanTopSellerMinuman() {
    try { 
        String queryTopSellerMinuman = "SELECT m.nama_menu, SUM(dt.jumlah) AS total_terjual FROM menu m LEFT "
                + "JOIN detail_transaksi dt ON m.kode_menu = dt.kode_menu AND m.kode_menu LIKE 'MI%' "
                + "JOIN transaksi t ON dt.kode_transaksi = t.kode_transaksi WHERE t.tgl_transaksi >= DATE_SUB(CURDATE(), INTERVAL 40 MONTH)"
                + "GROUP BY m.nama_menu ORDER BY total_terjual DESC LIMIT 3;";
                
        PreparedStatement psTopSellerMin = konek.GetConnection().prepareStatement(queryTopSellerMinuman);
        ResultSet rsTopSeller = psTopSellerMin.executeQuery();
        
        String topSellerMin = "";
        while (rsTopSeller.next()){
            String namaMenu = rsTopSeller.getString("nama_menu");
            int totalTerjual = rsTopSeller.getInt("total_terjual");
            topSellerMin += "<html>" + namaMenu + " : " + totalTerjual + " terjual <br/>";
        }
        
        
        bestSeller3minuman1.setText(topSellerMin);
        rsTopSeller.close();
        psTopSellerMin.close();
    }catch (SQLException e ) {
        e.printStackTrace();
        
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new com.swing.background();
        jScrollPane3 = new javax.swing.JScrollPane();
        makanan1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        minuman1 = new javax.swing.JTable();
        shape1 = new com.swing.Shape();
        bestSeller3minuman1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        shape2 = new com.swing.Shape();
        bestSeller3makanan1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        makanan1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        makanan1.setModel(new javax.swing.table.DefaultTableModel(
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
        makanan1.setRowHeight(25);
        makanan1.setShowHorizontalLines(true);
        jScrollPane3.setViewportView(makanan1);

        minuman1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        minuman1.setModel(new javax.swing.table.DefaultTableModel(
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
        minuman1.setRowHeight(25);
        minuman1.setShowHorizontalLines(true);
        jScrollPane4.setViewportView(minuman1);

        bestSeller3minuman1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        bestSeller3minuman1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TOP 3 MINUMAN");

        javax.swing.GroupLayout shape1Layout = new javax.swing.GroupLayout(shape1);
        shape1.setLayout(shape1Layout);
        shape1Layout.setHorizontalGroup(
            shape1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shape1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(bestSeller3minuman1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(shape1Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        shape1Layout.setVerticalGroup(
            shape1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shape1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(bestSeller3minuman1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        bestSeller3makanan1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        bestSeller3makanan1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TOP 3 MAKANAN");

        javax.swing.GroupLayout shape2Layout = new javax.swing.GroupLayout(shape2);
        shape2.setLayout(shape2Layout);
        shape2Layout.setHorizontalGroup(
            shape2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shape2Layout.createSequentialGroup()
                .addGroup(shape2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shape2Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel3))
                    .addGroup(shape2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(bestSeller3makanan1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        shape2Layout.setVerticalGroup(
            shape2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shape2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bestSeller3makanan1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(shape2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shape1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
            .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(background1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 15, Short.MAX_VALUE)))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                        .addComponent(shape2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(shape1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(235, Short.MAX_VALUE))
            .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(background1Layout.createSequentialGroup()
                    .addContainerGap(360, Short.MAX_VALUE)
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGap(0, 29, Short.MAX_VALUE)))
        );

        add(background1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swing.background background1;
    private javax.swing.JLabel bestSeller3makanan1;
    private javax.swing.JLabel bestSeller3minuman1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable makanan1;
    private javax.swing.JTable minuman1;
    private com.swing.Shape shape1;
    private com.swing.Shape shape2;
    // End of variables declaration//GEN-END:variables
}
