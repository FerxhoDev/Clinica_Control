/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package control_clinica;

import com.mysql.cj.xdevapi.Statement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Usuario
 */
public class Container extends javax.swing.JFrame {

    /**
     * Creates new form Container
     */
    public Container() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        this.setLocationRelativeTo(null);
        Panelpestañas.setSelectedIndex(5);
        Panelpestañas.setEnabledAt(1, false);
        Panelpestañas.setEnabledAt(2, false);
        Panelpestañas.setEnabledAt(3, false);
        Panelpestañas.setEnabledAt(4, false);
        // inicio con lista de doctor___es
        
        DefaultListModel md = new DefaultListModel();
        
        ResultSet rs = listdoc("select Nombre from doctor");
        
        try{
            while(rs.next()){
                md.addElement(rs.getString("Nombre"));
                listDoc.setModel(md);
            }           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado en el modelado");
        }
        
        cchart();
        paintDashBoard();
        
        tbcitas.addMouseListener(new MouseAdapter(){
    public void mousePressed(MouseEvent Mouse_evt){
        JTable table = (JTable) Mouse_evt.getSource();
        Point point = Mouse_evt.getPoint();
        int row = table.rowAtPoint(point);
        // variable ID
        String idp = tbcitas.getValueAt(tbcitas.getSelectedRow(), 0).toString();
        if(Mouse_evt.getClickCount() == 1){
            txtNom.setText(tbcitas.getValueAt(tbcitas.getSelectedRow(), 1).toString());
            txtFecha.setText(tbcitas.getValueAt(tbcitas.getSelectedRow(), 2).toString());
            txtHora.setText(tbcitas.getValueAt(tbcitas.getSelectedRow(), 3).toString());
        }
        
    }            
        }); 
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
    public Connection conectar(){
    
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/control_clinica","root","Emperador5732");
        }catch (SQLException e){
            System.err.print(e.toString());
            JOptionPane.showMessageDialog(this, "Ocurrió un error");
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
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado, con la consulta :)");
        }
        return rs;
    }
    
    public ResultSet listdoc (String consultaD){
        
        Connection cn = null;
        Statement sql;
        ResultSet rs = rs = null;
        PreparedStatement pst = null;
        
        try{
            cn = conectar();
            pst = cn.prepareStatement(consultaD);
            rs = pst.executeQuery();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error en listar Doctores, contacte con el Administrador :)");
        }
        return rs;
    }
    
    public ResultSet count (String consulta){
        Connection cn = null;
        Statement sql;
        ResultSet rs = rs = null;
        PreparedStatement pst = null;
        
        try{
            cn = conectar();
            pst = cn.prepareStatement(consulta);
            rs = pst.executeQuery();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error en el conte, contacte con el Administrador :)");
        }
        return rs;
    }
    public void paintDashBoard(){
        String cont = "";
        String cont2 = "";
        
        ResultSet rspaciente = count("select count(ID_paciente)from paciente ");
        ResultSet rscitas = count("select count(id)from cita ");
        //recorre Query y convierte a String el ResultSet
        try {
            while(rspaciente.next()){
                cont = rspaciente.getString("count(ID_paciente)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(rscitas.next()){
                cont2 = rscitas.getString("count(id)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblPacient.setText(cont);
        lblCita.setText(cont2);
    }
    public void cchart(){
        int paciente = 0;
        int citas = 0;
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        ResultSet rspaciente = count("select count(ID_paciente)from paciente ");
        ResultSet rscitas = count ("select count(id) from cita");
        
        try {
            while(rspaciente.next()){
                paciente = rspaciente.getInt("count(ID_paciente)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rscitas.next()){
                citas = rscitas.getInt("count(id)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        data.setValue(paciente,"Pacientes", "");
        data.setValue(citas,"Citas", "");
        JFreeChart Firstchart = ChartFactory.createBarChart3D(
            "DasBoard",
            "",
            "Cantidad",
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        
        ChartPanel panel = new ChartPanel(Firstchart);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(400,200));
        
        panChart.setLayout(new BorderLayout());
        panChart.add(panel,BorderLayout.CENTER);
        pack();
        repaint();
    }
   /* public void mostrar(String tabla){
        String sql = "select from " + tabla;
        Statement st;
        ResultSet s = null;
        conexion con = new conexion();
        Connection conexion = con.conectar();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Nombre");
        model.addColumn("Dirección");
        model.addColumn("Teléfono");
        Tpacientes.setModel(model);
        
        //Mostrar datos en el JTable 
        
        String [] data = new String [5];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.execute();
            
        }catch(SQLException e){
            
        }
    }*/
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new control_clinica.PanelRound();
        pCabecera = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Panelpestañas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tpacientes = new javax.swing.JTable();
        panelRound7 = new control_clinica.PanelRound();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        panelRound10 = new control_clinica.PanelRound();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panelRound8 = new control_clinica.PanelRound();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbcitas = new javax.swing.JTable();
        panelRound9 = new control_clinica.PanelRound();
        txtHora = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        panelRound12 = new control_clinica.PanelRound();
        jLabel22 = new javax.swing.JLabel();
        panelRound13 = new control_clinica.PanelRound();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        panelRound4 = new control_clinica.PanelRound();
        lblPacientes2 = new javax.swing.JLabel();
        panelRound5 = new control_clinica.PanelRound();
        LBL = new javax.swing.JLabel();
        lblPacient = new javax.swing.JLabel();
        panelRound6 = new control_clinica.PanelRound();
        lblPacientes4 = new javax.swing.JLabel();
        lblCita = new javax.swing.JLabel();
        panelRound3 = new control_clinica.PanelRound();
        lblPacientes5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listDoc = new javax.swing.JList<>();
        panChart = new control_clinica.PanelRound();
        panelRound2 = new control_clinica.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        lblCitas = new javax.swing.JLabel();
        lblInicio = new javax.swing.JLabel();
        lblPacientes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setBackground(new java.awt.Color(237, 245, 255));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pCabecera.setBackground(new java.awt.Color(237, 245, 255));
        pCabecera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(20, 36, 57));
        jLabel13.setText("X");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        pCabecera.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, -1, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(20, 36, 57));
        jLabel14.setText("-");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        pCabecera.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 0, 10, 20));

        panelRound1.add(pCabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1000, 30));

        Panelpestañas.setBackground(new java.awt.Color(237, 245, 255));

        jPanel1.setBackground(new java.awt.Color(237, 245, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Panelpestañas.addTab("Doctor", jPanel1);

        jPanel2.setBackground(new java.awt.Color(237, 245, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tpacientes.setBackground(new java.awt.Color(255, 255, 255));
        Tpacientes.setFont(new java.awt.Font("Dubai Medium", 0, 12)); // NOI18N
        Tpacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Dirección", "Teléfono"
            }
        ));
        Tpacientes.setToolTipText("");
        Tpacientes.setGridColor(new java.awt.Color(61, 130, 219));
        jScrollPane2.setViewportView(Tpacientes);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 920, 220));

        panelRound7.setBackground(new java.awt.Color(61, 130, 219));
        panelRound7.setRoundBottomLeft(20);
        panelRound7.setRoundBottomRight(20);
        panelRound7.setRoundTopLeft(20);
        panelRound7.setRoundTopRight(20);
        panelRound7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound7MouseClicked(evt);
            }
        });
        panelRound7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Buscar");
        panelRound7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 50, -1));

        jPanel2.add(panelRound7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 170, 60));

        jLabel17.setFont(new java.awt.Font("Dubai Medium", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(61, 130, 219));
        jLabel17.setText("Pacientes");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 130, -1));

        panelRound10.setBackground(new java.awt.Color(61, 130, 219));
        panelRound10.setRoundBottomLeft(20);
        panelRound10.setRoundBottomRight(20);
        panelRound10.setRoundTopLeft(20);
        panelRound10.setRoundTopRight(20);
        panelRound10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound10MouseClicked(evt);
            }
        });
        panelRound10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("+ Agregar paciente");
        panelRound10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, -1));

        jPanel2.add(panelRound10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, 170, 60));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Doc_100.png"))); // NOI18N
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        Panelpestañas.addTab("Paciente", jPanel2);

        jPanel3.setBackground(new java.awt.Color(237, 245, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("Cita");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1016, 574, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("HISTORIA CLINICA");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 6, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Ficha de Identificación");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 65, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 104, -1, -1));
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 103, 300, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Edad:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, -1, -1));
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 81, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Sexo:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, -1, -1));

        jCheckBox1.setText("F");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, -1, -1));

        jCheckBox2.setText("M");
        jPanel3.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ocupación;");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 134, -1, -1));
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 133, 280, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Estado Civil;");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, -1, -1));

        jCheckBox3.setText("Soltera");
        jPanel3.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, -1, -1));

        jCheckBox4.setText("Casada");
        jPanel3.add(jCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, -1, -1));

        jCheckBox5.setText("Viuda");
        jPanel3.add(jCheckBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, -1, -1));

        jCheckBox6.setText("Divorciada");
        jPanel3.add(jCheckBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Nacionalidad:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, -1, -1));
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, 150, -1));

        Panelpestañas.addTab("Historia Clinica", jPanel3);

        jPanel5.setBackground(new java.awt.Color(237, 245, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Dubai Medium", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(61, 130, 219));
        jLabel11.setText("CITAS AGENDADAS");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        panelRound8.setBackground(new java.awt.Color(61, 130, 219));
        panelRound8.setRoundBottomLeft(20);
        panelRound8.setRoundBottomRight(20);
        panelRound8.setRoundTopLeft(20);
        panelRound8.setRoundTopRight(20);
        panelRound8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound8MouseClicked(evt);
            }
        });
        panelRound8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Buscar paciente");
        panelRound8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 110, -1));

        jPanel5.add(panelRound8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 180, 70));

        tbcitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Fecha", "Hora"
            }
        ));
        jScrollPane3.setViewportView(tbcitas);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 610, 240));

        panelRound9.setBackground(new java.awt.Color(61, 130, 219));
        panelRound9.setRoundBottomLeft(30);
        panelRound9.setRoundBottomRight(30);
        panelRound9.setRoundTopLeft(30);
        panelRound9.setRoundTopRight(30);
        panelRound9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound9.add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 206, 32));
        panelRound9.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 260, 32));
        panelRound9.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 206, 32));

        jLabel10.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(237, 245, 255));
        jLabel10.setText("Nombre");
        panelRound9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel12.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(237, 245, 255));
        jLabel12.setText("Fecha");
        panelRound9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel18.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(237, 245, 255));
        jLabel18.setText("Teléfono");
        panelRound9.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        panelRound12.setBackground(new java.awt.Color(20, 36, 57));
        panelRound12.setRoundBottomLeft(10);
        panelRound12.setRoundBottomRight(10);
        panelRound12.setRoundTopLeft(10);
        panelRound12.setRoundTopRight(10);
        panelRound12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound12MouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Eliminar");

        javax.swing.GroupLayout panelRound12Layout = new javax.swing.GroupLayout(panelRound12);
        panelRound12.setLayout(panelRound12Layout);
        panelRound12Layout.setHorizontalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound12Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel22)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelRound12Layout.setVerticalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound12Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel22)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelRound9.add(panelRound12, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 287, 90, 30));

        panelRound13.setBackground(new java.awt.Color(20, 36, 57));
        panelRound13.setRoundBottomLeft(10);
        panelRound13.setRoundBottomRight(10);
        panelRound13.setRoundTopLeft(10);
        panelRound13.setRoundTopRight(10);
        panelRound13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound13MouseClicked(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Actualizar");

        javax.swing.GroupLayout panelRound13Layout = new javax.swing.GroupLayout(panelRound13);
        panelRound13.setLayout(panelRound13Layout);
        panelRound13Layout.setHorizontalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel23)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelRound13Layout.setVerticalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel23)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelRound9.add(panelRound13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 100, 30));

        jPanel5.add(panelRound9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 340, 350));

        Panelpestañas.addTab("Cita", jPanel5);

        jPanel4.setBackground(new java.awt.Color(237, 245, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setText("Doctor");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 574, -1, -1));

        Panelpestañas.addTab("Tratamiento", jPanel4);

        jPanel6.setBackground(new java.awt.Color(237, 245, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound4.setBackground(new java.awt.Color(61, 130, 219));
        panelRound4.setPreferredSize(new java.awt.Dimension(170, 70));
        panelRound4.setRoundBottomLeft(15);
        panelRound4.setRoundBottomRight(15);
        panelRound4.setRoundTopLeft(15);
        panelRound4.setRoundTopRight(15);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPacientes2.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        lblPacientes2.setForeground(new java.awt.Color(255, 255, 255));
        lblPacientes2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Pacientes_50.png"))); // NOI18N
        lblPacientes2.setText("Pacientes");
        lblPacientes2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPacientes2MouseClicked(evt);
            }
        });
        panelRound4.add(lblPacientes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 40));

        jPanel6.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 200, 110));

        panelRound5.setBackground(new java.awt.Color(61, 130, 219));
        panelRound5.setRoundBottomLeft(15);
        panelRound5.setRoundBottomRight(15);
        panelRound5.setRoundTopLeft(15);
        panelRound5.setRoundTopRight(15);
        panelRound5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LBL.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        LBL.setForeground(new java.awt.Color(255, 255, 255));
        LBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Pacientes_50.png"))); // NOI18N
        LBL.setText("Pacientes");
        LBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLMouseClicked(evt);
            }
        });
        panelRound5.add(LBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 40));

        lblPacient.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        lblPacient.setForeground(new java.awt.Color(255, 255, 255));
        lblPacient.setText("1");
        panelRound5.add(lblPacient, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        jPanel6.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 190, 110));

        panelRound6.setBackground(new java.awt.Color(61, 130, 219));
        panelRound6.setPreferredSize(new java.awt.Dimension(170, 70));
        panelRound6.setRoundBottomLeft(15);
        panelRound6.setRoundBottomRight(15);
        panelRound6.setRoundTopLeft(15);
        panelRound6.setRoundTopRight(15);
        panelRound6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPacientes4.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        lblPacientes4.setForeground(new java.awt.Color(255, 255, 255));
        lblPacientes4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Calendario-48.png"))); // NOI18N
        lblPacientes4.setText("Citas");
        lblPacientes4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPacientes4MouseClicked(evt);
            }
        });
        panelRound6.add(lblPacientes4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 110, 40));

        lblCita.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        lblCita.setForeground(new java.awt.Color(255, 255, 255));
        lblCita.setText("1");
        panelRound6.add(lblCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        jPanel6.add(panelRound6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 200, 110));

        panelRound3.setBackground(new java.awt.Color(61, 130, 219));
        panelRound3.setRoundTopLeft(10);
        panelRound3.setRoundTopRight(10);

        lblPacientes5.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        lblPacientes5.setForeground(new java.awt.Color(255, 255, 255));
        lblPacientes5.setText("Doctor");
        lblPacientes5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPacientes5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
            .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblPacientes5)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblPacientes5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel6.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, 260, 40));

        listDoc.setBackground(new java.awt.Color(255, 255, 255));
        listDoc.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        listDoc.setForeground(new java.awt.Color(0, 0, 0));
        listDoc.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Mario Borralles", "Alejando Gallo " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listDoc);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, 260, 100));

        panChart.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panChartLayout = new javax.swing.GroupLayout(panChart);
        panChart.setLayout(panChartLayout);
        panChartLayout.setHorizontalGroup(
            panChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        panChartLayout.setVerticalGroup(
            panChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        jPanel6.add(panChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 600, 350));

        Panelpestañas.addTab("tab6", jPanel6);

        panelRound1.add(Panelpestañas, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 9, 980, 630));

        panelRound2.setBackground(new java.awt.Color(61, 130, 219));
        panelRound2.setRoundBottomLeft(50);
        panelRound2.setRoundTopLeft(50);
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Dubai Medium", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Bienvenido!");
        panelRound2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 130, -1));

        lblCitas.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        lblCitas.setForeground(new java.awt.Color(255, 255, 255));
        lblCitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Calendario-48.png"))); // NOI18N
        lblCitas.setText("Citas");
        lblCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCitasMouseClicked(evt);
            }
        });
        panelRound2.add(lblCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 130, -1));

        lblInicio.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        lblInicio.setForeground(new java.awt.Color(24, 74, 127));
        lblInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Casa_48_Focus.png"))); // NOI18N
        lblInicio.setText("Inicio");
        lblInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInicioMouseClicked(evt);
            }
        });
        panelRound2.add(lblInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 130, -1));

        lblPacientes.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        lblPacientes.setForeground(new java.awt.Color(255, 255, 255));
        lblPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Pacientes_50.png"))); // NOI18N
        lblPacientes.setText("Pacientes");
        lblPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPacientesMouseClicked(evt);
            }
        });
        panelRound2.add(lblPacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 140, -1));

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 650));

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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Panelpestañas.setSelectedIndex(0);
        Panelpestañas.setEnabledAt(0, true);
        Panelpestañas.setEnabledAt(4, false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Panelpestañas.setSelectedIndex(2);
        Panelpestañas.setEnabledAt(3, true);
        Panelpestañas.setEnabledAt(2, false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed
    
    private ImageIcon CargarImg(String rut){
        String ruta = rut;
        ImageIcon icon = new ImageIcon (ruta);
        return icon;
    }
    private void lblCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitasMouseClicked
        Panelpestañas.setSelectedIndex(3);
        lblPacientes.setForeground(Color.white);
        lblInicio.setForeground(Color.white);
        lblCitas.setForeground(new Color(24,74,127));
        String inicio = "src/Media/Casa_48.png";     
        String Paciente = "src/Media/Pacientes_50.png";
        String Cita_F = "src/Media/Calendario-48_Focus.png";
        lblInicio.setIcon(CargarImg(inicio));
        lblPacientes.setIcon(CargarImg(Paciente));
        lblCitas.setIcon(CargarImg(Cita_F));
        
        // build tbCitas
        
        DefaultTableModel md = new DefaultTableModel();
       
        
        ResultSet rs = listartab("SELECT c.id, c.motivo, c.fecha, c.hora, p.Nombre FROM cita c LEFT JOIN paciente p ON c.paciente_id = p.ID_paciente;");
        
        md.setColumnIdentifiers(new Object[]{"id", "Motivo", "Nombre", "Fecha", "Hora"});
        
        try{
            while(rs.next()){
                md.addRow(new Object[] {rs.getString("id"),rs.getString("motivo"), rs.getString("Nombre"), rs.getString("fecha"), rs.getString("hora"), }); 
                tbcitas.setModel(md);
            }           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado en el modelado citas");
        }
    }//GEN-LAST:event_lblCitasMouseClicked

    private void lblPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPacientesMouseClicked
        Panelpestañas.setSelectedIndex(1);
        lblPacientes.setForeground(new Color(24,74,127));
        lblInicio.setForeground(Color.white);
        lblCitas.setForeground(Color.white);
        String inicio = "src/Media/Casa_48.png";     
        String Paciente_F = "src/Media/Pacientes_50_Focus.png";
        String Cita = "src/Media/Calendario-48.png";
        lblInicio.setIcon(CargarImg(inicio));
        lblPacientes.setIcon(CargarImg(Paciente_F));
        lblCitas.setIcon(CargarImg(Cita));
        
        // Actualiza los pacientes de Db
        
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("id");
        md.addColumn("Nombre");
        md.addColumn("Dirección");
        md.addColumn("Teléfono");
        Tpacientes.setModel(md);
        
        ResultSet rs = listartab("select * from paciente");
        
        md.setColumnIdentifiers(new Object[]{"id", "Nombre", "Dirección", "Teléfono"});
        
        try{
            while(rs.next()){
                md.addRow(new Object[] {rs.getInt("ID_paciente"), rs.getString("Nombre"), rs.getString("Direccion"), rs.getString("Telefono")}); 
                Tpacientes.setModel(md);
            }           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado en el modelado");
        }
    }//GEN-LAST:event_lblPacientesMouseClicked

    private void lblInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInicioMouseClicked
        Panelpestañas.setSelectedIndex(5);
        lblPacientes.setForeground(Color.white);
        lblInicio.setForeground(new Color(24,74,127));
        lblCitas.setForeground(Color.white);
        String inicio_F = "src/Media/Casa_48_Focus.png";     
        String Paciente = "src/Media/Pacientes_50.png";
        String Cita = "src/Media/Calendario-48.png";
        lblInicio.setIcon(CargarImg(inicio_F));
        lblPacientes.setIcon(CargarImg(Paciente));
        lblCitas.setIcon(CargarImg(Cita));
    }//GEN-LAST:event_lblInicioMouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        this.setExtendedState(1);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void lblPacientes2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPacientes2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPacientes2MouseClicked

    private void LBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LBLMouseClicked

    private void lblPacientes4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPacientes4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPacientes4MouseClicked

    private void lblPacientes5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPacientes5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPacientes5MouseClicked

    private void panelRound7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound7MouseClicked
        Paciente vista = new Paciente();
        vista.setVisible(true);
    }//GEN-LAST:event_panelRound7MouseClicked

    private void panelRound8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound8MouseClicked
        Paciente pa1 = new Paciente();
        pa1.setVisible(true);
    }//GEN-LAST:event_panelRound8MouseClicked

    private void panelRound10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound10MouseClicked
        Add_User showAdd = new Add_User();
        showAdd.setVisible(true);
    }//GEN-LAST:event_panelRound10MouseClicked

    private void panelRound12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound12MouseClicked
        String id = tbcitas.getValueAt(tbcitas.getSelectedRow(), 0).toString();
        String [] botones = {"Si", "No"};

        int op = JOptionPane.showOptionDialog(this, "Quieres eliminar el registro?","",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,botones, botones[0]);

        if (op == 0) {
            consulta("delete from cita where id = "+id+"");
            JOptionPane.showMessageDialog(null, "Cita eliminada");
        }else if (op == 1){
            JOptionPane.showMessageDialog(null, "No se realizaron cambios");
        }

    }//GEN-LAST:event_panelRound12MouseClicked

    private void panelRound13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelRound13MouseClicked

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
            java.util.logging.Logger.getLogger(Container.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Container.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Container.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Container.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Container().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBL;
    private javax.swing.JTabbedPane Panelpestañas;
    private javax.swing.JTable Tpacientes;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lblCita;
    private javax.swing.JLabel lblCitas;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblPacient;
    private javax.swing.JLabel lblPacientes;
    private javax.swing.JLabel lblPacientes2;
    private javax.swing.JLabel lblPacientes4;
    private javax.swing.JLabel lblPacientes5;
    private javax.swing.JList<String> listDoc;
    private javax.swing.JPanel pCabecera;
    private control_clinica.PanelRound panChart;
    private control_clinica.PanelRound panelRound1;
    private control_clinica.PanelRound panelRound10;
    private control_clinica.PanelRound panelRound12;
    private control_clinica.PanelRound panelRound13;
    private control_clinica.PanelRound panelRound2;
    private control_clinica.PanelRound panelRound3;
    private control_clinica.PanelRound panelRound4;
    private control_clinica.PanelRound panelRound5;
    private control_clinica.PanelRound panelRound6;
    private control_clinica.PanelRound panelRound7;
    private control_clinica.PanelRound panelRound8;
    private control_clinica.PanelRound panelRound9;
    private javax.swing.JTable tbcitas;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
