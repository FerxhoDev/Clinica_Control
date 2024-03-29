/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package control_clinica;

import com.mysql.cj.xdevapi.Statement;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FerdyRCardonaDev
 */
public class Paciente extends javax.swing.JFrame {

    /**
     * Creates new form Paciente
     */
    public Paciente() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        this.setLocationRelativeTo(null);
        txtNombre.requestFocus();
        
        //Data to JtextFiels
        tbPaciente.addMouseListener(new MouseAdapter(){
    public void mousePressed(MouseEvent Mouse_evt){
        JTable table = (JTable) Mouse_evt.getSource();
        Point point = Mouse_evt.getPoint();
        int row = table.rowAtPoint(point);
        // variable ID
        String idp = tbPaciente.getValueAt(tbPaciente.getSelectedRow(), 0).toString();
        if(Mouse_evt.getClickCount() == 1){
            txtNom.setText(tbPaciente.getValueAt(tbPaciente.getSelectedRow(), 1).toString());
            txtDireccion.setText(tbPaciente.getValueAt(tbPaciente.getSelectedRow(), 2).toString());
            txtTelefono.setText(tbPaciente.getValueAt(tbPaciente.getSelectedRow(), 3).toString());
        }
        
    }            
        });     
    }

    
    public Connection conectar(){
    
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/control_clinica","root","Emperador5732");
        }catch (SQLException e){
            System.err.print(e.toString());
            JOptionPane.showMessageDialog(this, "Ocurrió un error con la conexion a DB");
        }
        return con;                                                                                                                                            
    }
    
    public ResultSet listartab (String consulta){
        Connection cn = null;
        Statement sql;
        ResultSet rs = null;
        PreparedStatement pst = null;
        try{
            cn = conectar();
            pst = cn.prepareStatement(consulta);
            rs = pst.executeQuery();      
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado, con la consulta :)" +e.toString());
        }
        return rs;
    }
    public void consulta (String consulta){
        Connection cn = null;
        Statement sql;
        PreparedStatement pst = null;
        try{
            cn = conectar();
            pst = cn.prepareStatement(consulta);
            pst.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado, con la consulta :)" +e.toString());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new control_clinica.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        panelRound2 = new control_clinica.PanelRound();
        jLabel4 = new javax.swing.JLabel();
        panelRound3 = new control_clinica.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPaciente = new javax.swing.JTable();
        panelRound4 = new control_clinica.PanelRound();
        txtTelefono = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelRound5 = new control_clinica.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        panelRound6 = new control_clinica.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        panelRound7 = new control_clinica.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(61, 130, 219));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BUSCAR PACIENTE");
        panelRound1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 165, 30));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 300, -1));

        jLabel3.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre");
        panelRound1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        panelRound2.setBackground(new java.awt.Color(237, 245, 255));
        panelRound2.setRoundBottomLeft(10);
        panelRound2.setRoundBottomRight(10);
        panelRound2.setRoundTopLeft(10);
        panelRound2.setRoundTopRight(10);
        panelRound2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound2MouseClicked(evt);
            }
        });
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(20, 36, 57));
        jLabel4.setText("Buscar");
        panelRound2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 90, 40));

        panelRound3.setBackground(new java.awt.Color(237, 245, 255));
        panelRound3.setToolTipText("");
        panelRound3.setRoundBottomLeft(20);
        panelRound3.setRoundBottomRight(20);
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);
        panelRound3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tbPaciente.setBackground(new java.awt.Color(255, 255, 255));
        tbPaciente.setForeground(new java.awt.Color(0, 0, 0));
        tbPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Nombre", "Dirección", "Teléfono"
            }
        ));
        tbPaciente.setGridColor(new java.awt.Color(61, 130, 219));
        jScrollPane1.setViewportView(tbPaciente);

        panelRound3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 472, 100));

        panelRound1.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 530, 220));

        panelRound4.setBackground(new java.awt.Color(237, 245, 255));
        panelRound4.setRoundBottomLeft(30);
        panelRound4.setRoundBottomRight(30);
        panelRound4.setRoundTopLeft(30);
        panelRound4.setRoundTopRight(30);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound4.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 206, 32));
        panelRound4.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 260, 32));
        panelRound4.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 206, 32));

        jLabel2.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(61, 130, 219));
        jLabel2.setText("Nombre");
        panelRound4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(61, 130, 219));
        jLabel5.setText("Dirección");
        panelRound4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(61, 130, 219));
        jLabel6.setText("Teléfono");
        panelRound4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        panelRound5.setBackground(new java.awt.Color(20, 36, 57));
        panelRound5.setRoundBottomLeft(10);
        panelRound5.setRoundBottomRight(10);
        panelRound5.setRoundTopLeft(10);
        panelRound5.setRoundTopRight(10);
        panelRound5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound5MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("+ Agregar cita");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelRound4.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 100, 30));

        panelRound6.setBackground(new java.awt.Color(20, 36, 57));
        panelRound6.setRoundBottomLeft(10);
        panelRound6.setRoundBottomRight(10);
        panelRound6.setRoundTopLeft(10);
        panelRound6.setRoundTopRight(10);
        panelRound6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound6MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Eliminar");

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelRound4.add(panelRound6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, -1, -1));

        panelRound7.setBackground(new java.awt.Color(20, 36, 57));
        panelRound7.setRoundBottomLeft(10);
        panelRound7.setRoundBottomRight(10);
        panelRound7.setRoundTopLeft(10);
        panelRound7.setRoundTopRight(10);

        jLabel9.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Actualizar");

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelRound4.add(panelRound7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 100, 30));

        panelRound1.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, 400, 390));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(20, 36, 57));
        jLabel13.setText("X");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        panelRound1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, -1, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(20, 36, 57));
        jLabel14.setText("-");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        panelRound1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, 10, 20));

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelRound2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MouseClicked
        int c = 0;
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("id");
        md.addColumn("Nombre");
        md.addColumn("Dirección");
        md.addColumn("Teléfono");
        tbPaciente.setModel(md);
        String nom = txtNombre.getText();
        ResultSet rs = listartab("select * from paciente where Nombre like '%"+nom+"%'");
        
        md.setColumnIdentifiers(new Object[]{"id", "Nombre", "Dirección", "Teléfono"});
           
        try{
            while(rs.next()){
                md.addRow(new Object[] {rs.getInt("ID_paciente"), rs.getString("Nombre"), rs.getString("Direccion"), rs.getString("Telefono")}); 
                tbPaciente.setModel(md);
                c = c+1;
            }          
            if(c<1){
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
        }
    }//GEN-LAST:event_panelRound2MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        this.setExtendedState(1);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void panelRound6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound6MouseClicked
         String id = tbPaciente.getValueAt(tbPaciente.getSelectedRow(), 0).toString();
         String [] botones = {"Si", "No"};
         
         int op = JOptionPane.showOptionDialog(this, "Quieres eliminar el registro?","",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,botones, botones[0]);
         
         if (op == 0) {
            consulta("delete from paciente where ID_paciente = "+id+"");       
            JOptionPane.showMessageDialog(null, "Paciente Eliminado");    
         }else if (op == 1){
             JOptionPane.showMessageDialog(null, "No se realizaron cambios");    
         }
                
    }//GEN-LAST:event_panelRound6MouseClicked

    private void panelRound5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound5MouseClicked
        String id = tbPaciente.getValueAt(tbPaciente.getSelectedRow(), 0).toString();
        
        
        JOptionPane.showMessageDialog(null, "Data is: "+id);
        
        citas frame = new citas(id);
        frame.setVisible(true);
    }//GEN-LAST:event_panelRound5MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        String id = tbPaciente.getValueAt(tbPaciente.getSelectedRow(), 0).toString();
        
        citas frame = new citas(id);
        frame.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

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
            java.util.logging.Logger.getLogger(Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Paciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private control_clinica.PanelRound panelRound1;
    private control_clinica.PanelRound panelRound2;
    private control_clinica.PanelRound panelRound3;
    private control_clinica.PanelRound panelRound4;
    private control_clinica.PanelRound panelRound5;
    private control_clinica.PanelRound panelRound6;
    private control_clinica.PanelRound panelRound7;
    private javax.swing.JTable tbPaciente;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
