package practica3;

/**
 * Thread escritor (productor) que inserta mensajes en la cola concurrente.
 * Cada escritor genera un número específico de mensajes y los inserta
 * de forma segura en la cola compartida utilizando sincronización.
 * 
 * @author Manolaken
 * @version 1.0
 */
class ThreadEscritor extends Thread {
    /** Identificador único del escritor */
    private final int id;
    
    /** Cola concurrente compartida donde se insertan los mensajes */
    private final ColaConcurrente cola;
    
    /** Número total de mensajes que este escritor debe producir */
    private final int numMensajes;
    
    /**
     * Constructor del thread escritor.
     * 
     * @param id Identificador único del escritor
     * @param cola Cola concurrente compartida donde insertar mensajes
     * @param numMensajes Número de mensajes que debe producir este escritor
     */
    public ThreadEscritor(int id, ColaConcurrente cola, int numMensajes) {
        this.id = id;
        this.cola = cola;
        this.numMensajes = numMensajes;
    }
    
    /**
     * Método principal del thread que se ejecuta cuando se inicia.
     * Genera y inserta el número especificado de mensajes en la cola concurrente.
     * Cada mensaje incluye el identificador del escritor y un número secuencial.
     */
    @Override
    public void run() {
        System.out.println("Escritor " + id + " iniciado");
        
        for (int i = 1; i <= numMensajes; i++) {
            String mensaje = "Mensaje " + i + " del escritor " + id;
            
            System.out.println("Escritor " + id + " intenta insertar: " + mensaje);
            cola.insertar(mensaje);
            System.out.println("Escritor " + id + " insertó: " + mensaje);
            
            // Simular tiempo de trabajo
            // try {
            //     Thread.sleep((int)(Math.random() * 100));
            // } catch (InterruptedException e) {
            //     Thread.currentThread().interrupt();
            //     return;
            // }
        }
        
        System.out.println("Escritor " + id + " terminado");
    }
}