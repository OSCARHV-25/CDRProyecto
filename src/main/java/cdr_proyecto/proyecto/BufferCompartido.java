package cdr_proyecto.proyecto;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BufferCompartido {
    private final BlockingQueue<String> cola; // cola que maneja los valores

    public BufferCompartido(int capacidad) {
        this.cola = new ArrayBlockingQueue<String>(capacidad);
    }

    public void producir(String item) throws InterruptedException {
        cola.put(item); // El put para producir un item en este caso una linea
        System.out.println("Elemento producido: " + item);
    }


    public String consumir() throws InterruptedException { // el String porque consumir si nos devolvera algo
        String item = cola.take();//El take es para tomar o consumir la linea de la cola
        System.out.println("Elemento consumido: " + item);
        return item; // tiene que retornar el valor que esta retornando de la cola

    }
}