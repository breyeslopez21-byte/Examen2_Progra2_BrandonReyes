package Datos;

import Entidades.Vehiculos;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Datos {

    private final String archivo = "parqueo.txt";

    public List<Vehiculos> leerArchivo() {

        List<Vehiculos> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                String placa = datos[0];
                String tipo = datos[1];
                LocalDateTime entrada = LocalDateTime.parse(datos[2]);

                LocalDateTime salida = datos[3].equals("null")
                        ? null
                        : LocalDateTime.parse(datos[3]);

                double monto = Double.parseDouble(datos[4]);

                lista.add(new Vehiculos(placa, tipo, entrada, salida, monto));
            }

        } catch (Exception e) {
        }

        return lista;
    }

    public void guardarArchivo(List<Vehiculos> lista) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {

            for (Vehiculos v : lista) {

                String salida = v.getHoraSalida() == null
                        ? "null"
                        : v.getHoraSalida().toString();

                bw.write(v.getPlaca() + "," +
                        v.getTipo() + "," +
                        v.getHoraEntrada() + "," +
                        salida + "," +
                        v.getMonto());

                bw.newLine();
            }

        } catch (Exception e) {
        }
    }
}
