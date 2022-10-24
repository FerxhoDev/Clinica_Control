/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package control_clinica;

import com.mysql.cj.xdevapi.Statement;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author FerdyRCardonaDev
 */
public class citas extends javax.swing.JFrame {

   String id;
   public citas(String dataId) {
        initComponents();
        setBackground(new Color(0,0,0,0));
        this.setLocationRelativeTo(null);
        this.id = dataId;
        
        //data1.getData();
        System.out.println("Data is: "+id);
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
   
   public void add(){
       
        Connection cn = null;
        Statement sql;
        PreparedStatement pst = null;
        String insert = "INSERT INTO cita (motivo, fecha, hora, paciente_id)VALUES (?,?,?,?)";
        
        Date date = jData.getDate();
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        
        String Motivo = txtMotivoC.getText();
        String Hora = txtHora.getText();
        
        int idcons = Integer.parseInt(id);
        
        try{
            cn = conectar();
            pst = cn.prepareStatement(insert);
            pst.setString(1, Motivo);
            pst.setDate(2, fecha);
            pst.setString(3, Hora);
            pst.setInt(4, idcons);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cita ingresada con Exitoso");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado, al ingresar :( 3" +e.toString());
        }
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new control_clinica.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        panelRound2 = new control_clinica.PanelRound();
        jData = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMotivoC = new javax.swing.JTextField();
        panelRound3 = new control_clinica.PanelRound();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setBackground(new java.awt.Color(20, 36, 57));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agendar Cita");
        panelRound1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 110, -1));

        panelRound2.setBackground(new java.awt.Color(237, 245, 255));
        panelRound2.setRoundBottomRight(50);
        panelRound2.setRoundTopRight(50);
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound2.add(jData, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 240, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(20, 36, 57));
        jLabel14.setText("-");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        panelRound2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 10, 20));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(20, 36, 57));
        jLabel13.setText("X");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        panelRound2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        jLabel4.setBackground(new java.awt.Color(20, 36, 57));
        jLabel4.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(20, 36, 57));
        jLabel4.setText("Hora");
        panelRound2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(20, 36, 57));
        jLabel5.setText("Fecha");
        panelRound2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        txtHora.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 130, -1));

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 290, 340));

        jLabel2.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Motivo de consulta");
        panelRound1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        txtMotivoC.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.add(txtMotivoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 260, -1));

        panelRound3.setBackground(new java.awt.Color(237, 245, 255));
        panelRound3.setRoundBottomLeft(20);
        panelRound3.setRoundBottomRight(20);
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);
        panelRound3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound3MouseClicked(evt);
            }
        });
        panelRound3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(20, 36, 57));
        jLabel3.setText("Crear");
        panelRound3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 50, 20));

        panelRound1.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 120, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelRound3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound3MouseClicked
        add();
    }//GEN-LAST:event_panelRound3MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        this.setExtendedState(1);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

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
            java.util.logging.Logger.getLogger(citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new citas("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private control_clinica.PanelRound panelRound1;
    private control_clinica.PanelRound panelRound2;
    private control_clinica.PanelRound panelRound3;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtMotivoC;
    // End of variables declaration//GEN-END:variables
}
