package App;

import Entidades.Vehiculos;
import Logica.Logica;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Appa extends JFrame {

    Logica logica = new Logica();

    JTextField txtPlaca = new JTextField();

    JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Moto","Carro"});

    JButton btnIngreso = new JButton("Registrar Ingreso");
    JButton btnSalida = new JButton("Registrar Salida");

    JLabel lblMonto = new JLabel("Monto a pagar: 0");

    JTable tablaActivos = new JTable();
    JTable tablaHistorial = new JTable();

    DefaultTableModel modeloActivos;
    DefaultTableModel modeloHistorial;

    public Appa(){

        setTitle("Administración de Parqueo");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelDatos = new JPanel(new GridLayout(2,2));

        panelDatos.add(new JLabel("Placa"));
        panelDatos.add(txtPlaca);

        panelDatos.add(new JLabel("Tipo"));
        panelDatos.add(comboTipo);

        JPanel panelBotones = new JPanel();

        panelBotones.add(btnIngreso);
        panelBotones.add(btnSalida);
        panelBotones.add(lblMonto);

        JPanel panelSuperior = new JPanel(new BorderLayout());

        panelSuperior.add(panelDatos,BorderLayout.NORTH);
        panelSuperior.add(panelBotones,BorderLayout.SOUTH);

        add(panelSuperior,BorderLayout.NORTH);

        JPanel panelTablas = new JPanel(new GridLayout(2,1));

        panelTablas.add(new JScrollPane(tablaActivos));
        panelTablas.add(new JScrollPane(tablaHistorial));

        add(panelTablas,BorderLayout.CENTER);

        inicializarTablas();
        cargarTablas();

        btnIngreso.addActionListener(e -> registrarIngreso());
        btnSalida.addActionListener(e -> registrarSalida());
    }

    private void inicializarTablas(){

        modeloActivos = new DefaultTableModel();

        modeloActivos.addColumn("Placa");
        modeloActivos.addColumn("Tipo");
        modeloActivos.addColumn("Hora Entrada");

        tablaActivos.setModel(modeloActivos);

        modeloHistorial = new DefaultTableModel();

        modeloHistorial.addColumn("Placa");
        modeloHistorial.addColumn("Tipo");
        modeloHistorial.addColumn("Hora Entrada");
        modeloHistorial.addColumn("Hora Salida");
        modeloHistorial.addColumn("Monto");

        tablaHistorial.setModel(modeloHistorial);
    }

    private void cargarTablas(){

        modeloActivos.setRowCount(0);
        modeloHistorial.setRowCount(0);

        List<Vehiculos> lista = logica.obtenerVehiculos();

        for(Vehiculos v : lista){

            if(v.getHoraSalida() == null){

                Object[] fila = new Object[3];

                fila[0] = v.getPlaca();
                fila[1] = v.getTipo();
                fila[2] = v.getHoraEntrada();

                modeloActivos.addRow(fila);
            }
            else{

                Object[] fila = new Object[5];

                fila[0] = v.getPlaca();
                fila[1] = v.getTipo();
                fila[2] = v.getHoraEntrada();
                fila[3] = v.getHoraSalida();
                fila[4] = v.getMonto();

                modeloHistorial.addRow(fila);
            }
        }
    }

    private void registrarIngreso(){

        try{

            String placa = txtPlaca.getText();
            String tipo = comboTipo.getSelectedItem().toString();

            logica.registrarIngreso(placa,tipo);

            cargarTablas();

            txtPlaca.setText("");

        }catch(Exception e){

            lblMonto.setText(e.getMessage());
        }
    }

    private void registrarSalida(){

        int fila = tablaActivos.getSelectedRow();

        if(fila == -1){
            lblMonto.setText("Seleccione un vehículo activo");
            return;
        }

        String placa = tablaActivos.getValueAt(fila,0).toString();

        try{

            double monto = logica.registrarSalida(placa);

            lblMonto.setText("Monto a pagar: " + monto);

            cargarTablas();

        }catch(Exception e){

            lblMonto.setText(e.getMessage());
        }
    }
}