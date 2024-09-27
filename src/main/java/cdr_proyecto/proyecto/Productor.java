package cdr_proyecto.proyecto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Productor implements Runnable {
    // Traigo eso de la clase de BufferCompartido y todas las variables que son final se tienen que poner en un constructor
    private final BufferCompartido buffer;
    // Ruta del archivo que va a leer el productor
    private final String rutaArchivo;
    // Variable para el idProductor
    private final String idProductor;
    // Identificador unico para el productor
    private final int identificadorProductor;
    // Total de productores
    private final int totalProductores;

    public Productor(BufferCompartido buffer, String rutaArchivo, String idProductor, int identificadorProductor, int totalProductores) {
        this.buffer = buffer;
        this.rutaArchivo = rutaArchivo;
        this.idProductor = idProductor;
        this.identificadorProductor = identificadorProductor;
        this.totalProductores = totalProductores;
    }

    @Override
    public void run() {
        // Esto es para la ruta del archivo que se leera el lector
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(rutaArchivo)))) { // Se abre el archivo
            List<String> almacenLineas = new ArrayList<>(); //almacena las lineas del archivo
            String linea; // Variable que se llama linea
            while ((linea = lector.readLine()) != null) { // Lee la linea
                almacenLineas.add(linea); // Añade la linea a la lista
            }

            // Ciclo for
            for (int i = identificadorProductor; i < almacenLineas.size(); i += totalProductores) {
                String mensaje = almacenLineas.get(i) + "," + idProductor  + "," + LocalTime.now();  //Crea un mensaje con la linea y el Id del productor
                if (mensaje != null) { //Valida que el mensaje no sea nulo
                    buffer.producir(mensaje); // Lo produce y lo agrega al buffer
                    System.out.println("Producido por: " + idProductor + ": " + mensaje);
                    Thread.sleep(5);
                }
            }

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Si hay una interrupcion vamos a interrumpir el hilo
        }
    }
}

