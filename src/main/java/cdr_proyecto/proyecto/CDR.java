package cdr_proyecto.proyecto;


import java.util.Objects;

public class CDR {
    private final String numero_cuenta;
    private final String numero_del_que_llama;
    private final String numero_al_que_se_llama;
    private  final  String fecha;
    private final int duracion;
    private final double tarifa;
    private final String categoria;

    public CDR(String numero_cuenta, String numero_del_que_llama, String numero_al_que_se_llama, String fecha, int duracion, double tarifa, String categoria) {
        this.numero_cuenta = numero_cuenta;
        this.numero_del_que_llama = numero_del_que_llama;
        this.numero_al_que_se_llama = numero_al_que_se_llama;
        this.fecha = fecha;
        this.duracion = duracion;
        this.tarifa = tarifa;
        this.categoria = categoria;
    }


    @Override
    public String toString() {
        return "CDR{" +
                "numero_cuenta=" + numero_cuenta +
                ", numero_del_que_llama=" + numero_del_que_llama +
                ", numero_al_que_se_llama=" + numero_al_que_se_llama +
                ", duracion=" + duracion +
                ", tarifa=" + tarifa +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CDR cdr = (CDR) o;
        return duracion == cdr.duracion && Double.compare(tarifa, cdr.tarifa) == 0 && Objects.equals(numero_cuenta, cdr.numero_cuenta) && Objects.equals(numero_del_que_llama, cdr.numero_del_que_llama) && Objects.equals(numero_al_que_se_llama, cdr.numero_al_que_se_llama) && Objects.equals(fecha, cdr.fecha) && Objects.equals(categoria, cdr.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero_cuenta, numero_del_que_llama, numero_al_que_se_llama, fecha, duracion, tarifa, categoria);
    }
}
