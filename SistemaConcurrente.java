package practica3;

/**
 * Clase principal que coordina el sistema concurrente
 */
public class SistemaConcurrente {
    private static final int CAPACIDAD_COLA = 10;
    private static final int NUM_ESCRITORES = 4;
    private static final int NUM_LECTORES = 5;
    private static final int MENSAJES_POR_ESCRITOR = 8;
    private static final int MENSAJES_POR_LECTOR = 6;
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA PRODUCTOR-CONSUMIDOR CON SEMÁFOROS ===");
        System.out.println("Capacidad de la cola: " + CAPACIDAD_COLA);
        System.out.println("Número de escritores: " + NUM_ESCRITORES);
        System.out.println("Número de lectores: " + NUM_LECTORES);
        System.out.println("Mensajes por escritor: " + MENSAJES_POR_ESCRITOR);
        System.out.println("Mensajes por lector: " + MENSAJES_POR_LECTOR);
        System.out.println("Total mensajes a producir: " + (NUM_ESCRITORES * MENSAJES_POR_ESCRITOR));
        System.out.println("Total mensajes a consumir: " + (NUM_LECTORES * MENSAJES_POR_LECTOR));
        System.out.println("===================================================\n");
        
        // Verificar que se producen y consumen la misma cantidad de mensajes
        int totalProducidos = NUM_ESCRITORES * MENSAJES_POR_ESCRITOR;
        int totalConsumidos = NUM_LECTORES * MENSAJES_POR_LECTOR;
        
        if (totalProducidos != totalConsumidos) {
            System.err.println("ADVERTENCIA: Se producirán " + totalProducidos + 
                             " mensajes pero se consumirán " + totalConsumidos);
        }
        
        // Crear la cola concurrente compartida
        ColaConcurrente cola = new ColaConcurrente(CAPACIDAD_COLA);
        
        // Crear y lanzar los threads escritores
        ThreadEscritor[] escritores = new ThreadEscritor[NUM_ESCRITORES];
        for (int i = 0; i < NUM_ESCRITORES; i++) {
            escritores[i] = new ThreadEscritor(i + 1, cola, MENSAJES_POR_ESCRITOR);
            escritores[i].start();
        }
        
        // Crear y lanzar los threads lectores
        ThreadLector[] lectores = new ThreadLector[NUM_LECTORES];
        for (int i = 0; i < NUM_LECTORES; i++) {
            lectores[i] = new ThreadLector(i + 1, cola, MENSAJES_POR_LECTOR);
            lectores[i].start();
        }
        
        // Esperar a que todos los threads terminen
        try {
            // Esperar escritores
            for (ThreadEscritor escritor : escritores) {
                escritor.join();
            }
            System.out.println("\n--- Todos los escritores han terminado ---\n");
            
            // Esperar lectores
            for (ThreadLector lector : lectores) {
                lector.join();
            }
            System.out.println("\n--- Todos los lectores han terminado ---\n");
            
        } catch (InterruptedException e) {
            System.err.println("Error esperando a los threads: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n=== SISTEMA FINALIZADO CORRECTAMENTE ===");
        System.out.println("Elementos restantes en la cola: " + cola.getNumElementos());
    }
}