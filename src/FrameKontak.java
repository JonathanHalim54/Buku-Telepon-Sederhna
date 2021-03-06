/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bobby
 */
import Tabel.DataKontak;
import Tabel.TabelDataKontak;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class FrameKontak extends javax.swing.JFrame {
private Connection koneksi;
private Statement script;
private TabelDataKontak ModelDataKontak;
    /**
     * Creates new form FrameKontak
     */
    public FrameKontak() {
        initComponents();
ModelDataKontak = new TabelDataKontak();
TblKontak.setModel(ModelDataKontak);

KoneksiDb( );
tampil( );
bersih( );
    }
    private void KoneksiDb( ){ try{
Class.forName("com.mysql.jdbc.Driver");
koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kontak_db", "root", "pratesis123");
script = koneksi.createStatement( );
}
catch(SQLException ex){
System.err.print(ex);
}
catch(ClassNotFoundException ex){
System.err.print(ex);
}
}

private void tampil( ){
try{
int baris = TblKontak.getRowCount( );
for(int i=0; i<baris; i++){
ModelDataKontak.delete(0, baris);
}

String sql = "select * from tb_kontak";
ResultSet rs = script.executeQuery(sql);

while (rs.next( )){
DataKontak kontak = new DataKontak( );

kontak.setNama(rs.getString(1));
kontak.setTelepon(rs.getString(2));
kontak.setE_mail(rs.getString(3));

ModelDataKontak.add(kontak);
}
}
catch(SQLException ex){
System.err.print(ex);
}
}

private void bersih( ){
TxtNama.setText(null);
TxtTelepon.setText(null);
TxtEmail.setText(null);
TxtCari.setText(null);
BtnSimpan.setEnabled(true);
BtnUbah.setEnabled(false);
BtnHapus.setEnabled(false);
}

private void tabel( ){
TblKontak.getSelectionModel( ).addListSelectionListener(new ListSelectionListener( ) {

@Override
public void valueChanged(ListSelectionEvent e) {
int baris = TblKontak.getSelectedRow( );

if(baris != -1){
DataKontak kontak = ModelDataKontak.get(baris);
TxtNama.setText(kontak.getNama( ));
TxtTelepon.setText(kontak.getTelepon( ));
TxtEmail.setText(kontak.getE_mail( ));
}
}
});
BtnSimpan.setEnabled(false);
BtnUbah.setEnabled(true);
BtnHapus.setEnabled(true);
}

private void simpan( ){
try{
String sql = "insert into tb_kontak values ("
+"'"+TxtNama.getText( )+"',"
+"'"+TxtTelepon.getText( )+"',"
+"'"+TxtEmail.getText( )+"'"
+")";

script.executeUpdate(sql);

tampil( );
JOptionPane.showMessageDialog(null, TxtNama.getText( )+" berhasil Disimpan");
bersih( );
}
catch(SQLException ex){

JOptionPane.showMessageDialog(this, "No Telepon sudah Ada");
bersih();

}
}

private void ubah( ){
int app;

if((app = JOptionPane.showConfirmDialog(null, "Ubah kontak"
+" ?","Perhatian",JOptionPane.YES_NO_OPTION))==0){
try{
String sql = "update tb_kontak set"
+" nama = '"+TxtNama.getText()+"',"
+" e_mail = '"+TxtEmail.getText()+"' where"
+" telepon = '"+TxtTelepon.getText()+"'";

script.executeUpdate(sql);

tampil( );
JOptionPane.showMessageDialog(null, "Kontak berhasil dirubah");
bersih( );
}
catch(SQLException ex){
System.err.print(ex);
}
}
}

private void hapus( ){
int app, bantu;

if((app = JOptionPane.showConfirmDialog(null, "Hapus data"
+" ?","Perhatian",JOptionPane.YES_NO_OPTION))==0){
try{
String sql = "delete from tb_kontak where"
+" nama = '"+TxtNama.getText()+"'";

bantu = script.executeUpdate(sql);

tampil( );
JOptionPane.showMessageDialog(null, "Kontak berhasil dihapus");
bersih( );
}
catch(SQLException ex){
System.err.print(ex);
}
}
}

private void cari( ){
int baris = TblKontak.getRowCount( );
String bantu = CmbCari.getSelectedItem( ).toString( );

for(int i=0; i<baris; i++){
ModelDataKontak.delete(i, baris);
}

try{
String sql = "select * from tb_kontak where "
+bantu+" like '%"+TxtCari.getText()+"%'";

ResultSet rs = script.executeQuery(sql);

while(rs.next( )){
DataKontak kontak = new DataKontak( );

kontak.setNama(rs.getString(1));
kontak.setTelepon(rs.getString(2));
kontak.setE_mail(rs.getString(3));

ModelDataKontak.add(kontak);
}
}
catch(SQLException ex){
System.err.print(ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        Nama = new javax.swing.JLabel();
        Telepon = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        TxtNama = new javax.swing.JTextField();
        TxtTelepon = new javax.swing.JTextField();
        TxtEmail = new javax.swing.JTextField();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        BtnSimpan = new javax.swing.JButton();
        BtnUbah = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();
        Kontak = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblKontak = new javax.swing.JTable();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel5 = new javax.swing.JLabel();
        CmbCari = new javax.swing.JComboBox<>();
        TxtCari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buku Telepon Sederhana"));

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Kontak"));

        Nama.setText("Nama");

        Telepon.setText("Telepon");

        Email.setText("E-Mail");

        jLayeredPane1.setLayer(Nama, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Telepon, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Email, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(TxtNama, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(TxtTelepon, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(TxtEmail, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Telepon)
                    .addComponent(Email)
                    .addComponent(Nama))
                .addGap(36, 36, 36)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(TxtEmail)
                        .addGroup(jLayeredPane1Layout.createSequentialGroup()
                            .addComponent(TxtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 159, Short.MAX_VALUE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Nama)
                    .addComponent(TxtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Telepon)
                    .addComponent(TxtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Email)
                    .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tombol"));

        BtnSimpan.setText("Simpan");
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });

        BtnUbah.setText("Ubah");
        BtnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUbahActionPerformed(evt);
            }
        });

        BtnHapus.setText("Hapus");
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(BtnSimpan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(BtnUbah, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(BtnHapus, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(BtnSimpan)
                .addGap(18, 18, 18)
                .addComponent(BtnUbah)
                .addGap(18, 18, 18)
                .addComponent(BtnHapus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Kontak.setBorder(javax.swing.BorderFactory.createTitledBorder("Daftar Kontak"));

        TblKontak.setModel(new javax.swing.table.DefaultTableModel(
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
        TblKontak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TblKontakMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(TblKontak);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Cari Kontak"));
        jLayeredPane4.setToolTipText("");

        jLabel5.setText("Cari Berdasarkan");

        CmbCari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "nama", "telepon", "e_mail" }));

        TxtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtCariKeyReleased(evt);
            }
        });

        jLayeredPane4.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(CmbCari, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(TxtCari, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addGap(75, 75, 75)
                .addComponent(CmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(TxtCari, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        Kontak.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Kontak.setLayer(jLayeredPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout KontakLayout = new javax.swing.GroupLayout(Kontak);
        Kontak.setLayout(KontakLayout);
        KontakLayout.setHorizontalGroup(
            KontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KontakLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(KontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLayeredPane4)))
        );
        KontakLayout.setVerticalGroup(
            KontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KontakLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Kontak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane2)
                    .addComponent(jLayeredPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Kontak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUbahActionPerformed
ubah();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnUbahActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
hapus();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
simpan();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void TblKontakMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblKontakMousePressed
tabel();        // TODO add your handling code here:
    }//GEN-LAST:event_TblKontakMousePressed

    private void TxtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCariKeyReleased
cari();        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCariKeyReleased

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
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameKontak().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnSimpan;
    private javax.swing.JButton BtnUbah;
    private javax.swing.JComboBox<String> CmbCari;
    private javax.swing.JLabel Email;
    private javax.swing.JLayeredPane Kontak;
    private javax.swing.JLabel Nama;
    private javax.swing.JTable TblKontak;
    private javax.swing.JLabel Telepon;
    private javax.swing.JTextField TxtCari;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtNama;
    private javax.swing.JTextField TxtTelepon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
