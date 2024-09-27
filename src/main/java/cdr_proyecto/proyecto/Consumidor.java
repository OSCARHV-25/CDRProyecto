package cdr_proyecto.proyecto;
import cdr_proyecto.conexion.Conexion; // importamos la clase para usar la conexion
import java.sql.Connection; // importamos para amnejar el crud
import java.sql.PreparedStatement; // las reglas sql
import java.sql.SQLException; // para manejar exepciones de sql

//clase consunmidor
public class Consumidor implements Runnable {

    private final BufferCompartido buffer; //buffer compartido  entre productores y consumidor
    private final String idConsumidor; // para distinguitlo en la bd o en la salida


    //constructor que inicializa en el buffer y idconsumidor
    public Consumidor(BufferCompartido buffer, String idConsumidor) {
        this.buffer = buffer;
        this.idConsumidor = idConsumidor;
    }

    @Override
    public void run() {
        PreparedStatement ps = null; // para insertar datos en la bd
        try (Connection conex = Conexion.getConexion()) { //obtenemos la conexion  en la bd
            while (true) { // bucle para consumir los datos
                String mensaje = buffer.consumir(); // para consmir l9os datos del archivo

                synchronized (this) { // para asegurar la sincronizacion
                    String[] partes = mensaje.split(",", 8);//para dividir el mensaje en las 8 partes

                    // verificamos si son 8 partes de mensajes
                    if (partes.length == 9) {
                        // asignamos cada parte en una variable con su nombre
                        String numero_cuenta = partes[0];
                        String numero_del_que_llama = partes[1];
                        String numero_al_que_se_llama = partes[2];
                        String fecha = partes[3];
                        int duracion = Integer.parseInt(partes[4]); // convcertimos a entero el dato
                        double tarifa = Double.parseDouble(partes[5]); // convertimos en double la tarifa
                        String categoria = partes[6];
                        String idProductor = partes[7];
                        LocalTime hora_producido = LocalTime.parse(partes[8]);

                        // Variable para saber la hora que se consumio el dato
                        LocalTime hora_consumido = LocalTime.now();

                        // insertamos la consulta en la bd
                        String sql = "INSERT INTO cdr (numero_cuenta, numero_del_que_llama, numero_al_que_se_llama, fecha, duracion, tarifa, categoria, id_Productor, id_Consumidor, hora_producido, hora_consumido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        // se prepara la consulta
                        ps = conex.prepareStatement(sql);

                        // parametros para la consulta
                        ps.setString(1, numero_cuenta);
                        ps.setString(2, numero_del_que_llama);
                        ps.setString(3, numero_al_que_se_llama);
                        ps.setString(4, fecha);
                        ps.setInt(5, duracion);
                        ps.setDouble(6, tarifa);
                        ps.setString(7, categoria);
                        ps.setString(8, idProductor);
                        ps.setString(9, idConsumidor);
                        ps.setTime(10, Time.valueOf(hora_producido));
                        ps.setTime(11, Time.valueOf(hora_consumido));
                        // insertamos el registro
                        ps.executeUpdate();

                        // mostramos que el dato ha sido consumido y procesado
                        System.out.println("Dato Consumido: " + idConsumidor + ", " + numero_cuenta + ", " + numero_del_que_llama + ", " + numero_al_que_se_llama + ", " + fecha + ", " + duracion + ", " + tarifa + ", " + categoria + ", " + idProductor);
                    }
                }
            }
        } catch (InterruptedException | SQLException e) { // manejamos la interrupcion y execpcion
            Thread.currentThread().interrupt(); //esto interrumpira el hilo si hay error
        } finally {
            // si no es null se cierra para librerar recursos
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
