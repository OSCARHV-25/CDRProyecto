package cdr_proyecto.proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Productor implements Runnable {

    //Traigo eso de la clase de BufferCompartido y todas las variables que son final se tienen que poner en un constructor
    private final BufferCompartido buffer;

    //Ruta del archivo que va a leer el productor
    private final String rutaArchivo;

    // variable para el idProductor
    private final String idProductor;

    public Productor(BufferCompartido buffer, String rutaArchivo, String idProductor) {
        this.buffer = buffer;
        this.rutaArchivo = rutaArchivo;
        this.idProductor = idProductor;
    }

    @Override
    public void run() {
        //esto es para la ruta del archivo que se creara el que leera el lector
        try(BufferedReader lector = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(rutaArchivo)))){ //se abre el archivo
            String linea; //variable que sellame linea
            //En este leera la linea mientras no sea nulo
            while ((linea = lector.readLine()) != null) { // el lector.readLine leera solo una liena
                String mensaje = linea + "," + idProductor; // este concatena la linea leida con una coma y su id del productor
                buffer.producir(mensaje);// lo agregaremos al buffer la liena leia
                System.out.println("Producido por " + idProductor + " : " + mensaje); //imprime la liena leida con el id y el mensaje que es la concatenacion de la liena leida
                Thread.sleep(5);
                //Thread.sleep((int)(Math.random() * 1000));
            }

        }catch (IOException | InterruptedException e){
            Thread.currentThread().interrupt(); // si hay una interrupcion vamos  a interrumpir el hilo
        }

    }
}
