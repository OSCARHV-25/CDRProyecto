package cdr_proyecto.proyecto;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BufferCompartido {
    private final BlockingQueue<String> queue; // cola que maneja los valores

    public BufferCompartido(int capacidad) {
        this.queue = new ArrayBlockingQueue<String>(capacidad);
    }

    public void producir(String item) throws InterruptedException {
        queue.put(item); // El put para producir un item o un numero
        System.out.println("Elemento producido: " + item);
    }


    public String consumir() throws InterruptedException { // el String porque consumir si nos devolvera algo
        String item = queue.take();//El take es para tomar o consumir un numero de la cola el primero en la cola
        System.out.println("Elemento consumido: " + item);
        return item; // tiene que retornar el valor que esta retornando de la cola

    }
}
