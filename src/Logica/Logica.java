package Logica;

import Datos.Datos;
import Entidades.Vehiculos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Logica {

    private Logica dao = new Logica();

    private double tarifaHora = 500;

    public List<Vehiculos> obtenerVehiculos() {
        return dao.leerArchivo();
    }

    public List<Vehiculos> vehiculosActivos() {

        List<Vehiculos> activos = new ArrayList<>();

        for (Vehiculos v : dao.leerArchivo()) {

            if (v.getHoraSalida() == null) {
                activos.add(v);
            }
        }

        return activos;
    }

    public boolean placaActiva(String placa) {

        for (Vehiculos v : vehiculosActivos()) {

            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return true;
            }
        }

        return false;
    }

    public void registrarIngreso(String placa, String tipo) throws Exception {

        if (placa.isEmpty() || tipo.isEmpty()) {
            throw new Exception("Datos obligatorios");
        }

        if (placaActiva(placa)) {
            throw new Exception("Vehículo ya está dentro");
        }

        List<Vehiculos> lista = dao.leerArchivo();

        Vehiculos v = new Vehiculos(placa, tipo, LocalDateTime.now());

        lista.add(v);

        dao.guardarArchivo(lista);
    }

    public double registrarSalida(String placa) throws Exception {

        List<Vehiculos> lista = dao.leerArchivo();

        for (Vehiculos v : lista) {

            if (v.getPlaca().equalsIgnoreCase(placa) && v.getHoraSalida() == null) {

                LocalDateTime salida = LocalDateTime.now();

                long minutos = Duration.between(v.getHoraEntrada(), salida).toMinutes();

                double horas = Math.ceil(minutos / 60.0);

                double monto = horas * tarifaHora;

                v.setHoraSalida(salida);
                v.setMonto(monto);

                dao.guardarArchivo(lista);

                return monto;
            }
        }

        throw new Exception("Vehículo no encontrado");
    }
}