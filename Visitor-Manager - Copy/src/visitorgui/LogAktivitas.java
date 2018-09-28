/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitorgui;
import tools.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LogAktivitas extends javax.swing.JFrame {

    /**
     * Creates new form LogAktivitas
     */
    private DefaultTableModel model;
    
    public LogAktivitas() {
        initComponents();
        model = new DefaultTableModel( );
        logTable.setModel(model);
        model.addColumn("No");
        model.addColumn("Date and Time");
        model.addColumn("Log Aktivitas");
        model.addColumn("Subjek");
        model.addColumn("Objek");
        model.addColumn("Ruangan");
        getData("All","");        
    }
    
    public void getData(String filter, String keyword){
        //menghapus isi table tblGaji
        model.getDataVector( ).removeAllElements( );
        model.fireTableDataChanged();
        String where = "1";
        if(filter == "All"){
            where = "1";
        }
        else if(filter == "Aktivitas Sistem"){
           where = "id_log != 'FALSEIN' AND id_log != 'FALSEOUT' AND id_log != 'TRUEIN' AND id_log != 'TRUEOUT'";
        }
        else if(filter == "Aktivitas Pengunjung"){
           where = "(id_log = 'FALSEIN' OR id_log = 'FALSEOUT' OR id_log = 'TRUEIN' OR id_log = 'TRUEOUT')";
        }
        else if(filter == "Pelanggaran"){
           where = "(id_log = 'FALSEIN' OR id_log = 'FALSEOUT')";
        }
        
        keyword = "('%" + keyword + "%')";
        String searches = " AND (subjek LIKE " + keyword +
                "OR objek LIKE " + keyword +
                "OR nama_ruangan LIKE " + keyword +
                "OR date_time LIKE " + keyword + ")";
        
//        where += " AND '" + keyword + "' IN(id_log, subjek, objek, nama_ruangan)";
        where += searches;
        
        try{
              //membuat statemen pemanggilan data pada table log history dari database
              where += " ORDER by date_time DESC";
              ResultSet res   = new FungsiQuery().selectData("*", "log_history JOIN daftar_log USING(id_log)", where);
              int index = 0;
               //penelusuran baris pada tabel history dari database
               while(res.next ()){
                   index++;
                   Object[ ] obj = new Object[6];
                   obj[0] = index;
                   obj[1] = res.getString("date_time");
                   obj[2] = res.getString("nama_kejadian");
                   obj[3] = res.getString("subjek");
                   obj[4] = res.getString("objek");
                   obj[5] = res.getString("nama_ruangan");
                   model.addRow(obj);
               }
           }
        catch(SQLException err){
                 JOptionPane.showMessageDialog(null, err.getMessage() );
           }
        new AutoColumn().runAuto(logTable);
        logTable.revalidate();
    }    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        filterCombo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        searchText = new javax.swing.JTextField();
        resetButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setTitle("Log Aktivitas");
        setLocation(new java.awt.Point(200, 100));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("LOG AKTIVITAS SISTEM");

        logTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(logTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Saring Daftar"));

        filterCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Aktivitas Sistem", "Aktivitas Pengunjung", "Pelanggaran" }));
        filterCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filterComboItemStateChanged(evt);
            }
        });
        filterCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterComboActionPerformed(evt);
            }
        });

        jLabel2.setText("Filter:");

        refreshButton.setText("Search");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        searchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextActionPerformed(evt);
            }
        });
        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextKeyReleased(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        jLabel9.setText("Keyword:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterCombo, 0, 179, Short.MAX_VALUE)
                    .addComponent(searchText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refreshButton)
                    .addComponent(resetButton))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(resetButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshButton)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(368, 368, 368)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(278, 278, 278)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 483, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filterComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterComboActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        System.out.println(filterCombo.getSelectedItem().toString());
        String filter = filterCombo.getSelectedItem().toString();
        getData(filter, this.searchText.getText());
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void filterComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filterComboItemStateChanged
        // TODO add your handling code here:
        System.out.println(filterCombo.getSelectedItem().toString());
        String filter = filterCombo.getSelectedItem().toString();
        getData(filter, this.searchText.getText());
    }//GEN-LAST:event_filterComboItemStateChanged

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        getData("All", "");
        this.searchText.setText("");
        this.filterCombo.setSelectedIndex(0);
    }//GEN-LAST:event_resetButtonActionPerformed

    private void searchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextActionPerformed

    private void searchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyReleased
        // TODO add your handling code here:
        System.out.println(filterCombo.getSelectedItem().toString());
        String filter = filterCombo.getSelectedItem().toString();
        getData(filter, this.searchText.getText());
    }//GEN-LAST:event_searchTextKeyReleased

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
            java.util.logging.Logger.getLogger(LogAktivitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogAktivitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogAktivitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogAktivitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogAktivitas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> filterCombo;
    private javax.swing.JComboBox<String> filterCombo1;
    private javax.swing.JComboBox<String> filterCombo2;
    private javax.swing.JComboBox<String> filterCombo3;
    private javax.swing.JComboBox<String> filterCombo4;
    private javax.swing.JComboBox<String> filterCombo5;
    private javax.swing.JComboBox<String> filterCombo6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable logTable;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton refreshButton1;
    private javax.swing.JButton refreshButton2;
    private javax.swing.JButton refreshButton3;
    private javax.swing.JButton refreshButton4;
    private javax.swing.JButton refreshButton5;
    private javax.swing.JButton refreshButton6;
    private javax.swing.JButton resetButton;
    private javax.swing.JTextField searchText;
    // End of variables declaration//GEN-END:variables
        
}



