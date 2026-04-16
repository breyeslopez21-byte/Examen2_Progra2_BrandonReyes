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
    JTextField txtTipo = new JTextField();

    JButton btnIngreso = new JButton("Registrar Ingreso");
    JButton btnSalida = new JButton("Registrar Salida");

    JTable tabla = new JTable();
    DefaultTableModel modelo;

    public Appa() {

        setTitle("Sistema de Parqueo");
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // PANEL DE DATOS
        JPanel panelDatos = new JPanel(new GridLayout(2,2,10,10));

        panelDatos.add(new JLabel("Placa:"));
        panelDatos.add(txtPlaca);

        panelDatos.add(new JLabel("Tipo de Vehículo:"));
        panelDatos.add(txtTipo);

        // PANEL DE BOTONES
        JPanel panelBotones = new JPanel();

        panelBotones.add(btnIngreso);
        panelBotones.add(btnSalida);

        // PANEL SUPERIOR
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelDatos,BorderLayout.NORTH);
        panelSuperior.add(panelBotones,BorderLayout.SOUTH);

        // TABLA
        JScrollPane scroll = new JScrollPane(tabla);

        add(panelSuperior,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);

        cargarTabla();

        btnIngreso.addActionListener(e -> registrarIngreso());
        btnSalida.addActionListener(e -> registrarSalida());
    }

    private void cargarTabla() {

        modelo = new DefaultTableModel();

        modelo.addColumn("Placa");
        modelo.addColumn("Tipo");
        modelo.addColumn("Hora Entrada");
        modelo.addColumn("Hora Salida");
        modelo.addColumn("Monto");

        tabla.setModel(modelo);

        List<Vehiculos> lista = logica.obtenerVehiculos();

        for (Vehiculos v : lista) {

            Object[] fila = new Object[5];

            fila[0] = v.getPlaca();
            fila[1] = v.getTipo();
            fila[2] = v.getHoraEntrada();
            fila[3] = v.getHoraSalida();
            fila[4] = v.getMonto();

            modelo.addRow(fila);
        }
    }

    private void registrarIngreso() {

        try {

            String placa = txtPlaca.getText();
            String tipo = txtTipo.getText();

            logica.registrarIngreso(placa,tipo);

            cargarTabla();

            txtPlaca.setText("");
            txtTipo.setText("");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    private void registrarSalida() {

        try {

            String placa = txtPlaca.getText();

            logica.registrarSalida(placa);

            cargarTabla();

            txtPlaca.setText("");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}