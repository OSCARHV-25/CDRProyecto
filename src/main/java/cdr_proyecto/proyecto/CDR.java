package cdr_proyecto.proyecto;

public class CDR {
    public static void main(String[] args) throws Exception {
        //se define la capacidad del buffer
        BufferCompartido buffer = new BufferCompartido(10);

        //hilos productores
        Thread productor1 = new Thread(new Productor(buffer, "cdr_data.csv", "Productor 1", 0, 3));
        Thread productor2 = new Thread(new Productor(buffer, "cdr_data.csv", "Productor 2", 1, 3));
        Thread productor3 = new Thread(new Productor(buffer, "cdr_data.csv", "Productor 3", 2, 3));

        //se inician los hilos
        productor1.start();
        productor2.start();
        productor3.start();


        //hilos consumidores
        Thread consumidor1 = new Thread(new Consumidor(buffer, "Consumidor 1"));
        Thread consumidor2 = new Thread(new Consumidor(buffer, "Consumidor 2"));

        //iniciamos los hilos consumidores
        consumidor1.start();
        consumidor2.start();


        //se espera a que todos los hilod terminen productores y consumidores
            productor1.join();
            productor2.join();
            productor3.join();
            consumidor1.join();
            consumidor2.join();

    }
}