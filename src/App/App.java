package App;

import Logica.Logica;
import Entidades.Vehiculos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class App extends JFrame {

    private JTable tablaActuales;
    private JTable tablaHistorial;

    private Logica service = new Logica();

    public App() {

        setTitle("Sistema Parqueo");
        setSize(800,600);
        setLayout(null);

        tablaActuales = new JTable();
        tablaActuales.setBounds(20,50,350,200);
        add(new JScrollPane(tablaActuales));

        tablaHistorial = new JTable();
        tablaHistorial.setBounds(400,50,350,200);
        add(new JScrollPane(tablaHistorial));

        cargarTablas();
    }

    private void cargarTablas(){

        List<Vehiculos> lista = service.obtenerVehiculos();

        DefaultTableModel actuales = new DefaultTableModel(
                new String[]{"Placa","Tipo","Entrada"},0);

        DefaultTableModel historial = new DefaultTableModel(
                new String[]{"Placa","Tipo","Entrada","Salida","Monto"},0);

        for(Vehiculos v : lista){

            if(v.getHoraSalida()==null){

                actuales.addRow(new Object[]{
                        v.getPlaca(),
                        v.getTipo(),
                        v.getHoraEntrada()
                });

            }else{

                historial.addRow(new Object[]{
                        v.getPlaca(),
                        v.getTipo(),
                        v.getHoraEntrada(),
                        v.getHoraSalida(),
                        v.getMonto()
                });

            }
        }

        tablaActuales.setModel(actuales);
        tablaHistorial.setModel(historial);
    }
}