package Entidades;

import java.time.LocalDateTime;

public class Vehiculos {

    private String placa;
    private String tipo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private double monto;

    public Vehiculos(String placa, String tipo, LocalDateTime horaEntrada) {
        this.placa = placa;
        this.tipo = tipo;
        this.horaEntrada = horaEntrada;
    }

    public Vehiculos(String placa, String tipo, LocalDateTime horaEntrada, LocalDateTime horaSalida, double monto) {
        this.placa = placa;
        this.tipo = tipo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.monto = monto;
    }

    public String getPlaca() { return placa; }
    public String getTipo() { return tipo; }
    public LocalDateTime getHoraEntrada() { return horaEntrada; }
    public LocalDateTime getHoraSalida() { return horaSalida; }
    public double getMonto() { return monto; }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}