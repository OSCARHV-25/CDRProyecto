package cdr_proyecto.proyecto;

import java.util.Objects;

public class CDR {
    //Variables
    private int numeroCuenta;
    private String numeroLlamante;
    private String numeroLlamado;
    private long timestamp;
    private int duracion;

    //Constructor
    public CDR(int numeroCuenta, String numeroLlamante, String numeroLlamado, long timestamp, int duracion) {
        this.numeroCuenta = numeroCuenta;
        this.numeroLlamante = numeroLlamante;
        this.numeroLlamado = numeroLlamado;
        this.timestamp = timestamp;
        this.duracion = duracion;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroLlamante() {
        return numeroLlamante;
    }

    public void setNumeroLlamante(String numeroLlamante) {
        this.numeroLlamante = numeroLlamante;
    }

    public String getNumeroLlamado() {
        return numeroLlamado;
    }

    public void setNumeroLlamado(String numeroLlamado) {
        this.numeroLlamado = numeroLlamado;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "CDR{" +
                "numeroCuenta=" + numeroCuenta +
                ", numeroLlamante='" + numeroLlamante + '\'' +
                ", numeroLlamado='" + numeroLlamado + '\'' +
                ", timestamp=" + timestamp +
                ", duracion=" + duracion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CDR cdr = (CDR) o;
        return numeroCuenta == cdr.numeroCuenta && timestamp == cdr.timestamp && duracion == cdr.duracion && Objects.equals(numeroLlamante, cdr.numeroLlamante) && Objects.equals(numeroLlamado, cdr.numeroLlamado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCuenta, numeroLlamante, numeroLlamado, timestamp, duracion);
    }
}
