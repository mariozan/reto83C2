/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Class.*;
import Controller.EncomiendaController;
import Model.EncomiendaModel;
import Model.ServicioModel;
import Model.UsuarioModel;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Mario
 */
public class Index extends javax.swing.JFrame {

    /**
     * Creates new form Index
     */
    UsuarioModel modelo_usuario = new UsuarioModel();
    EncomiendaModel modelo_encomienda = new EncomiendaModel();
    ServicioModel modelo_servicio = new ServicioModel();

    ArrayList<Usuario> listaUsuarios = modelo_usuario.Read();
    ArrayList<Encomienda> listaEncomiendas = modelo_encomienda.Read();
    ArrayList<Servicio> listaServicio = modelo_servicio.Read();


    public Index() {
        initComponents();
        cargarListaTablaUsuarios();
        cargarListaTablaEncomienda();
        cargarComboOrigen();
        cargarComboEncomienda();
        cargarListaTablaServicio();
        cargarGraficoEncomiendaPie();
        cargarGraficoEncomiendaBar();
    }
    
    public void cargarGraficoEncomiendaBar(){
        ArrayList<Encomienda> lista_encomienda_p = modelo_encomienda.GetByPresentacion();     
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();       
        for (Encomienda index : lista_encomienda_p) {
            dataset.setValue(index.getCantidad(), index.getPresentacion(), "Cantidad");
        }        
        JFreeChart chart = ChartFactory.createBarChart3D("Grafico de Barras", "Tipo", "", dataset, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel panel = new ChartPanel(chart);
        panelBarras.setLayout(new java.awt.BorderLayout());
        panelBarras.add(panel);
        panelBarras.validate();
    }
    
    public void cargarGraficoEncomiendaPie() {
        ArrayList<Encomienda> lista_encomienda_p = modelo_encomienda.GetByPresentacion();
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        for (Encomienda index : lista_encomienda_p) {
            dataset.setValue(index.getPresentacion(), index.getCantidad());
        }
        JFreeChart chart = ChartFactory.createPieChart("Grafico de Encomiendas por Presentacion", dataset, true, true, true);
        ChartPanel panel = new ChartPanel(chart);
        panelEncomienda.setLayout(new java.awt.BorderLayout());
        panelEncomienda.add(panel);
        panelEncomienda.validate();
    }
    
    public void cargarListaTablaUsuarios() {
        DefaultTableModel tab = new DefaultTableModel();
        String[] cabecera = {"Id", "Nombre", "Apellidos", "Telefono", "Direccion"};
        Object[] datos = new Object[cabecera.length];
        tab.setColumnIdentifiers(cabecera);
        for (int i = 0; i < listaUsuarios.size(); i++) {
            datos[0] = listaUsuarios.get(i).getId();
            datos[1] = listaUsuarios.get(i).getNombre();
            datos[2] = listaUsuarios.get(i).getApellidos();
            datos[3] = listaUsuarios.get(i).getTelefono();
            datos[4] = listaUsuarios.get(i).getDireccion();
            tab.addRow(datos);
        }
        tableUsuarios.setModel(tab);
        
        cargarComboOrigen();
    }
    
    public void cargarListaTablaEncomienda() {
        DefaultTableModel tab = new DefaultTableModel();
        String[] cabecera = {"Id", "Descripcion", "Peso", "Tipo", "Presentacion"};
        Object[] datos = new Object[cabecera.length];
        tab.setColumnIdentifiers(cabecera);
        for (int i = 0; i < listaEncomiendas.size(); i++) {
            datos[0] = listaEncomiendas.get(i).getId();
            datos[1] = listaEncomiendas.get(i).getDescripcion();
            datos[2] = listaEncomiendas.get(i).getPeso();
            datos[3] = listaEncomiendas.get(i).getTipo();
            datos[4] = listaEncomiendas.get(i).getPresentacion();
            tab.addRow(datos);
        }
        tableEncomiendas.setModel(tab);
        cargarGraficoEncomiendaPie();
        }
    
    public void cargarListaTablaServicio() {
        DefaultTableModel tab = new DefaultTableModel();
        String[] cabecera = {"Id", "Fecha", "Hora", "Origen", "Destino", "Encomienda"};
        Object[] datos = new Object[cabecera.length];
        tab.setColumnIdentifiers(cabecera);
        for (int i = 0; i < listaServicio.size(); i++) {
            datos[0] = listaServicio.get(i).getId();
            datos[1] = listaServicio.get(i).getFecha();
            datos[2] = listaServicio.get(i).getHora();
            datos[3] = listaServicio.get(i).getNombre_origen();
            datos[4] = listaServicio.get(i).getNombre_destino();
            datos[5] = listaServicio.get(i).getNombre_encomienda();
            tab.addRow(datos);
        }
        tableServicio.setModel(tab);
     }
    
    public void cargarComboOrigen() {
        comboOrigen.removeAllItems();
        comboOrigen.addItem("");
        comboDestino.removeAllItems();
        comboDestino.addItem("");
        for (Usuario usuario : listaUsuarios) {
            comboOrigen.addItem(usuario.getId() + "-" + usuario.getNombre() + " "+ usuario.getApellidos());
            comboDestino.addItem(usuario.getId() + "-" + usuario.getNombre() + " "+ usuario.getApellidos());
        }
        comboOrigen.setSelectedIndex(0);
        comboDestino.setSelectedIndex(0);
    }
    
    public void cargarComboEncomienda(){
        comboEncomienda.removeAllItems();
        comboEncomienda.addItem("");
        for (Encomienda encomienda : listaEncomiendas) {
            comboEncomienda.addItem(encomienda.getId() + "-" + encomienda.getDescripcion());
        }
    }

    
    public void limpiarCamposUsuario(){
        txtIdUsuario.setValue(0);
        txtNombreUsuario.setText("");
        txtApellidosUsuario.setText("");
        txtDireccionUsuario.setText("");
        txtTelefonoUsuario.setText("");
    }
    
    public void limpiarCamposEncomiendas(){
        txtIdEncomiendas.setValue(0);
        txtDescripcionEncomiendas.setText("");
        txtPesoEncomiendas.setValue(0);
        txtTipoEncomiendas.setText("");
        comboPresentacionEncomiendas.setSelectedItem(0);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        TabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUsuarios = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableEncomiendas = new javax.swing.JTable();
        btnExportEncomienda = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtIdEncomiendas = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcionEncomiendas = new javax.swing.JTextArea();
        txtPesoEncomiendas = new javax.swing.JSpinner();
        txtTipoEncomiendas = new javax.swing.JTextField();
        btnGuadarEncomienda = new javax.swing.JButton();
        btnEditarEncomienda = new javax.swing.JButton();
        btnEliminarEncomienda = new javax.swing.JButton();
        btnBuscarEncomienda = new javax.swing.JButton();
        comboPresentacionEncomiendas = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableServicio = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtIdServicio = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboOrigen = new javax.swing.JComboBox<>();
        comboDestino = new javax.swing.JComboBox<>();
        comboEncomienda = new javax.swing.JComboBox<>();
        txtFechaServicio = new javax.swing.JFormattedTextField();
        btnGuardarServicio = new javax.swing.JButton();
        btnEditarServicio = new javax.swing.JButton();
        btnBuscarServicio = new javax.swing.JButton();
        btnEliminarServicio = new javax.swing.JButton();
        txtHoraServicio = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApellidosUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefonoUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDireccionUsuario = new javax.swing.JTextField();
        btnBuscarUsuario = new javax.swing.JButton();
        btnEditarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        btnGuardarUsuario = new javax.swing.JButton();
        txtIdUsuario = new javax.swing.JSpinner();
        panelEncomienda = new javax.swing.JPanel();
        panelBarras = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tableUsuarios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPane.addTab("ListarUsuarios", jPanel1);

        tableEncomiendas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tableEncomiendas);

        btnExportEncomienda.setText("Expotar");
        btnExportEncomienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportEncomiendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 116, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(btnExportEncomienda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnExportEncomienda)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        TabbedPane.addTab("ListarEncomiendas", jPanel3);

        jLabel12.setText("id");

        jLabel13.setText("descripcion");

        jLabel14.setText("peso");

        jLabel15.setText("tipo");

        jLabel16.setText("presentacion");

        txtDescripcionEncomiendas.setColumns(20);
        txtDescripcionEncomiendas.setRows(5);
        jScrollPane1.setViewportView(txtDescripcionEncomiendas);

        btnGuadarEncomienda.setText("Guardar");
        btnGuadarEncomienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuadarEncomiendaActionPerformed(evt);
            }
        });

        btnEditarEncomienda.setText("Editar");

        btnEliminarEncomienda.setText("Eliminar");

        btnBuscarEncomienda.setText("Buscar");

        comboPresentacionEncomiendas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bolsa", "Caja", "Sobre" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(btnGuadarEncomienda))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnEditarEncomienda)
                        .addGap(33, 33, 33)
                        .addComponent(btnEliminarEncomienda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                        .addComponent(btnBuscarEncomienda)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(txtIdEncomiendas)
                            .addComponent(txtPesoEncomiendas)
                            .addComponent(txtTipoEncomiendas)
                            .addComponent(comboPresentacionEncomiendas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtIdEncomiendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(jLabel13)
                        .addGap(62, 62, 62))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtPesoEncomiendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTipoEncomiendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(comboPresentacionEncomiendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuadarEncomienda)
                    .addComponent(btnEditarEncomienda)
                    .addComponent(btnEliminarEncomienda)
                    .addComponent(btnBuscarEncomienda))
                .addGap(23, 23, 23))
        );

        TabbedPane.addTab("Encomiendas", jPanel4);

        tableServicio.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tableServicio);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPane.addTab("ListarServicios", jPanel5);

        jLabel6.setText("id");

        jLabel7.setText("origen");

        jLabel8.setText("destino");

        jLabel9.setText("encomienda");

        jLabel10.setText("fecha");

        jLabel11.setText("hora");

        comboOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboEncomienda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtFechaServicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y/MM/d"))));

        btnGuardarServicio.setText("Guardar");
        btnGuardarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarServicioActionPerformed(evt);
            }
        });

        btnEditarServicio.setText("Editar");

        btnBuscarServicio.setText("Buscar");

        btnEliminarServicio.setText("Eliminar");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(comboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboOrigen, 0, 148, Short.MAX_VALUE)
                                    .addComponent(txtIdServicio)))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboEncomienda, 0, 148, Short.MAX_VALUE)
                            .addComponent(txtFechaServicio)
                            .addComponent(txtHoraServicio))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardarServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditarServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscarServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnGuardarServicio)))
                .addGap(8, 8, 8)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comboOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(comboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnEditarServicio)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(comboEncomienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnBuscarServicio)))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFechaServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminarServicio)))
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtHoraServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Servicio", jPanel6);

        jLabel1.setText("id");

        jLabel2.setText("nombre");

        jLabel3.setText("apellidos");

        jLabel4.setText("telefono");

        jLabel5.setText("direccion");

        btnBuscarUsuario.setText("Buscar");
        btnBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUsuarioActionPerformed(evt);
            }
        });

        btnEditarUsuario.setText("Editar");
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });

        btnEliminarUsuario.setText("Eliminar");
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        btnGuardarUsuario.setText("Guardar");
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });

        txtIdUsuario.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(btnBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btnEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDireccionUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(txtTelefonoUsuario)
                            .addComponent(txtApellidosUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreUsuario, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnGuardarUsuario)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefonoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtDireccionUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarUsuario)
                    .addComponent(btnBuscarUsuario)
                    .addComponent(btnEliminarUsuario)
                    .addComponent(btnGuardarUsuario))
                .addGap(32, 32, 32))
        );

        TabbedPane.addTab("Usuario", jPanel2);

        javax.swing.GroupLayout panelEncomiendaLayout = new javax.swing.GroupLayout(panelEncomienda);
        panelEncomienda.setLayout(panelEncomiendaLayout);
        panelEncomiendaLayout.setHorizontalGroup(
            panelEncomiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );
        panelEncomiendaLayout.setVerticalGroup(
            panelEncomiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        TabbedPane.addTab("Grafico Encomienda", panelEncomienda);

        javax.swing.GroupLayout panelBarrasLayout = new javax.swing.GroupLayout(panelBarras);
        panelBarras.setLayout(panelBarrasLayout);
        panelBarrasLayout.setHorizontalGroup(
            panelBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );
        panelBarrasLayout.setVerticalGroup(
            panelBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        TabbedPane.addTab("GraficoBarras", panelBarras);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        TabbedPane.getAccessibleContext().setAccessibleName("VerUsuarios");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed
        // TODO add your handling code here:
        int id = (int) txtIdUsuario.getValue(); // Spinner
        String nombre = txtNombreUsuario.getText(); // TextField
        String apellidos = txtApellidosUsuario.getText();
        String telefono = txtTelefonoUsuario.getText();
        String direccion = txtDireccionUsuario.getText();

        if (nombre.equals("") || apellidos.equals("") || telefono.equals("")
            || direccion.equals("")) {
            JOptionPane.showMessageDialog(null, "Error: debe llenar todos los campos");
        } else {
            Usuario new_user = new Usuario(id, nombre, apellidos, direccion, telefono);
            int result = modelo_usuario.Create(new_user);
            new_user.setId(result);
            listaUsuarios.add(new_user);
            JOptionPane.showMessageDialog(this, "Usuario " + nombre + " fue creado correctamente");
            cargarListaTablaUsuarios();
            limpiarCamposUsuario();
        }
    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
        // TODO add your handling code here:
        int id = (int) txtIdUsuario.getValue(); // Spiner
        boolean existe = false;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == id) {
                int resultado = modelo_usuario.Delete(id);
                if(resultado == 1){
                    listaUsuarios.remove(listaUsuarios.get(i));
                    JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente");
                    cargarListaTablaUsuarios();
                    limpiarCamposUsuario();
                }else{
                    JOptionPane.showMessageDialog(this, "Usuario no puede ser eliminado");
                }
                existe = true;
                break;
            }
        }
        if (!existe) {
            JOptionPane.showMessageDialog(this, "El Usuario no esta registrado");
        }
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioActionPerformed
        // TODO add your handling code here:
        int id = (int) txtIdUsuario.getValue(); // Spinner
        if(id != 0){
            String nombre = txtNombreUsuario.getText(); // TextField
            String apellidos = txtApellidosUsuario.getText();
            String telefono = txtTelefonoUsuario.getText();
            String direccion = txtDireccionUsuario.getText();
            boolean existe = false;

            if (id == 0 || nombre.equals("") || apellidos.equals("") || telefono.equals("") || direccion.equals("")) {
                JOptionPane.showMessageDialog(null, "Error: debe llenar todos los campos");
            } else {
                for (int i = 0; i < listaUsuarios.size(); i++) {
                    if (listaUsuarios.get(i).getId() == id) { // Pedro
                        listaUsuarios.get(i).setNombre(nombre);
                        listaUsuarios.get(i).setApellidos(apellidos);
                        listaUsuarios.get(i).setDireccion(direccion);
                        listaUsuarios.get(i).setTelefono(telefono);
                        existe = true;
                        Usuario u = new Usuario(id, nombre, apellidos, direccion, telefono);
                        modelo_usuario.Update(u, id);
                        cargarListaTablaUsuarios();
                        limpiarCamposUsuario();
                        JOptionPane.showMessageDialog(this, "Usuario editado correctamente");
                        break;
                    }
                }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(this, "El Usuario no esta registrado");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Error: debe buscar un registro");
        }
    }//GEN-LAST:event_btnEditarUsuarioActionPerformed

    private void btnBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsuarioActionPerformed
        // TODO add your handling code here:
//        int id = (int) txtIdUsuario.getValue();
//        txtTelefonoUsuario.requestFocus();

        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID"));

        boolean existe = false;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == id) {
                txtIdUsuario.setValue(listaUsuarios.get(i).getId());
                txtNombreUsuario.setText(listaUsuarios.get(i).getNombre());
                txtApellidosUsuario.setText(listaUsuarios.get(i).getApellidos());
                txtDireccionUsuario.setText(listaUsuarios.get(i).getDireccion());
                txtTelefonoUsuario.setText(listaUsuarios.get(i).getTelefono());
                existe = true;
                break;
            }
        }
        if (!existe) {
            JOptionPane.showMessageDialog(this, "El Usuario no esta registrada");
        }
    }//GEN-LAST:event_btnBuscarUsuarioActionPerformed

    private void btnGuardarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarServicioActionPerformed
        // TODO add your handling code here:
        int id = (int) txtIdServicio.getValue(); // Spinner
        String origen = comboOrigen.getSelectedItem().toString();
        String[] partes_origen = origen.split("-");
        int id_origen = Integer.parseInt(partes_origen[0]);
        String nombre_origen = partes_origen[1];

        String destino = comboDestino.getSelectedItem().toString();
        String[] partes_destino = destino.split("-");
        int id_destino = Integer.parseInt(partes_destino[0]);
        String nombre_destino = partes_destino[1];

        String encomienda = comboEncomienda.getSelectedItem().toString();
        String[] partes_encomienda = encomienda.split("-");
        int id_encomienda = Integer.parseInt(partes_encomienda[0]);
        String nombre_encomienda = partes_encomienda[1];

        String fecha = txtFechaServicio.getText();
        String hora = txtHoraServicio.getText();

        if (origen.equals("") || destino.equals("") || encomienda.equals("")
            || fecha.equals("") || hora.equals("")) {
            JOptionPane.showMessageDialog(null, "Error: debe llenar todos los campos");
        } else {
            Servicio new_serv = new Servicio(id, id_origen, id_destino, fecha, hora, id_encomienda, nombre_origen, nombre_destino, nombre_encomienda);
            int result = modelo_servicio.Create(new_serv);
            new_serv.setId(result);
            listaServicio.add(new_serv);
            JOptionPane.showMessageDialog(this, "Servicio " + fecha + " fue creado correctamente");
            cargarListaTablaServicio();
            //            limpiarCamposServicio();
        }
    }//GEN-LAST:event_btnGuardarServicioActionPerformed

    private void btnGuadarEncomiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuadarEncomiendaActionPerformed
        // TODO add your handling code here:
        int id = (int) txtIdEncomiendas.getValue(); // Spinner
        String descripcion = txtDescripcionEncomiendas.getText(); // TextField
        int peso = (int) txtPesoEncomiendas.getValue();
        String tipo = txtTipoEncomiendas.getText();
        String presentacion = comboPresentacionEncomiendas.getSelectedItem().toString();

        if (descripcion.equals("") || peso == 0 || tipo.equals("") || presentacion.equals("")) {
            JOptionPane.showMessageDialog(null, "Error: debe llenar todos los campos");
        } else {
            Encomienda enc = new Encomienda(id, descripcion, peso, presentacion, tipo);
            int result = modelo_encomienda.Create(enc);
            enc.setId(result);
            listaEncomiendas.add(enc);
            JOptionPane.showMessageDialog(this, "Encomienda " + descripcion + " fue creado correctamente");
            cargarListaTablaEncomienda();
            limpiarCamposEncomiendas();
        }
    }//GEN-LAST:event_btnGuadarEncomiendaActionPerformed

    private void btnExportEncomiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportEncomiendaActionPerformed
        // TODO add your handling code here:
        EncomiendaController controlador_encomienda = new EncomiendaController();
        try{
            controlador_encomienda.ExportData();
            JOptionPane.showMessageDialog(this, "Informacion Exportada Correctamente");
        }catch(Exception e){
            System.out.println("Error:"+e.getMessage());
        }
    }//GEN-LAST:event_btnExportEncomiendaActionPerformed

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
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JButton btnBuscarEncomienda;
    private javax.swing.JButton btnBuscarServicio;
    private javax.swing.JButton btnBuscarUsuario;
    private javax.swing.JButton btnEditarEncomienda;
    private javax.swing.JButton btnEditarServicio;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnEliminarEncomienda;
    private javax.swing.JButton btnEliminarServicio;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnExportEncomienda;
    private javax.swing.JButton btnGuadarEncomienda;
    private javax.swing.JButton btnGuardarServicio;
    private javax.swing.JButton btnGuardarUsuario;
    private javax.swing.JComboBox<String> comboDestino;
    private javax.swing.JComboBox<String> comboEncomienda;
    private javax.swing.JComboBox<String> comboOrigen;
    private javax.swing.JComboBox<String> comboPresentacionEncomiendas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panelBarras;
    private javax.swing.JPanel panelEncomienda;
    private javax.swing.JTable tableEncomiendas;
    private javax.swing.JTable tableServicio;
    private javax.swing.JTable tableUsuarios;
    private javax.swing.JTextField txtApellidosUsuario;
    private javax.swing.JTextArea txtDescripcionEncomiendas;
    private javax.swing.JTextField txtDireccionUsuario;
    private javax.swing.JFormattedTextField txtFechaServicio;
    private javax.swing.JTextField txtHoraServicio;
    private javax.swing.JSpinner txtIdEncomiendas;
    private javax.swing.JSpinner txtIdServicio;
    private javax.swing.JSpinner txtIdUsuario;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JSpinner txtPesoEncomiendas;
    private javax.swing.JTextField txtTelefonoUsuario;
    private javax.swing.JTextField txtTipoEncomiendas;
    // End of variables declaration//GEN-END:variables
}
