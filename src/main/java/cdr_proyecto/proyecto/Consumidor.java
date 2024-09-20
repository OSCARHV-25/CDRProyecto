package cdr_proyecto.proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consumidor implements Runnable {

    private final BufferCompartido buffer;

    private final String idConsumidor;


    private static final Lock lock = new ReentrantLock(); // se puede utilizar el sincronized pero tambien el lock funciona como un objeto

    //constructor
    public Consumidor(BufferCompartido buffer, String idConsumidor )  { //throws IOException, se quito esto porque ya no se ocupa la variable impresora
        this.buffer = buffer;
        this.idConsumidor = idConsumidor;

    }
    @Override
    public void run(){

        PreparedStatement ps = null; // para insertar la intruccion sql


        try (Connection conex = Conexion.getConexion()){
            while(true){
                String mensaje = buffer.consumir();

                // cuerpo de nuestra logica
                lock.lock(); // bloquea hasta que se ejecute
                try{
                    //se divide por comas el mensaje
                    String[] partes = mensaje.split(",", 8); // el 8 porque lo que leera por cada palabra
                    if (partes.length == 8){// aqui se valida que si tenga el tamaño 8
                        String numero_cuenta = partes[0];
                        String numero_del_que_llama = partes[1];
                        String numero_al_que_se_llama = partes[2];
                        String fecha = partes[3];
                        int duracion = Integer.parseInt(partes[4]);
                        double tarifa = Double.parseDouble(partes[5]);
                        String categoria = partes[6];
                        String idProductor = partes[7];



                        String sql = "INSERT INTO cdr (numero_cuenta, numero_del_que_llama, numero_al_que_se_llama, fecha, duracion, tarifa, categoria, id_Productor, id_Consumidor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        ps = conex.prepareStatement(sql); // para que se pueda ejecutar la consulta
                        ps.setString(1, numero_cuenta);
                        ps.setString(2, numero_del_que_llama);
                        ps.setString(3, numero_al_que_se_llama);
                        ps.setString(4, fecha);
                        ps.setInt(5, duracion);
                        ps.setDouble(6, tarifa);
                        ps.setString(7, categoria);
                        ps.setString(8, idProductor);
                        ps.setString(9, idConsumidor);
                        ps.executeUpdate();

                        System.out.println("Dato procesado: " + idConsumidor + ", " + numero_cuenta + ", " + numero_del_que_llama + ", "+ numero_al_que_se_llama + fecha + ", " + duracion + ", " + tarifa + ", " + categoria + ", " + idProductor );




                    }
                }finally {

                    lock.unlock(); // Este desloquea cuando ya se termina todos los datos de leer y ya puede salir de la ejecucion
                }
            }
        }catch (InterruptedException | SQLException e){
            Thread.currentThread().interrupt();

        }finally {
            try {
                if(ps != null)
                    ps.close(); // también se cierra el prepareStatement, antes de la conexión


            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
