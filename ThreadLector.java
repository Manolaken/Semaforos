package practica3;

/**
 * Thread lector (consumidor) que extrae mensajes de la cola concurrente.
 * Cada lector consume un número específico de mensajes de la cola compartida
 * de forma segura utilizando sincronización con semáforos.
 * 
 * @author Manolaken
 * @version 1.0
 */
class ThreadLector extends Thread {
    /** Identificador único del lector */
    private final int id;
    
    /** Cola concurrente compartida de donde se extraen los mensajes */
    private final ColaConcurrente cola;
    
    /** Número total de mensajes que este lector debe consumir */
    private final int numMensajes;

    /**
     * Constructor del thread lector.
     * 
     * @param id Identificador único del lector
     * @param cola Cola concurrente compartida de donde extraer mensajes
     * @param numMensajes Número de mensajes que debe consumir este lector
     */
    public ThreadLector(int id, ColaConcurrente cola, int numMensajes) {
        this.id = id;
        this.cola = cola;
        this.numMensajes = numMensajes;
    }
    
    /**
     * Método principal del thread que se ejecuta cuando se inicia.
     * Extrae el número especificado de mensajes de la cola concurrente
     * y los procesa secuencialmente hasta completar su tarea.
     */
    @Override
    public void run() {
        System.out.println("Lector " + id + " iniciado");
        
        for (int i = 1; i <= numMensajes; i++) {
            System.out.println("Lector " + id + " intenta extraer mensaje " + i);
            String mensaje = cola.extraer();
            System.out.println(">>> Lector " + id + " extrajo: " + mensaje);
            
            // Simular tiempo de procesamiento
            // try {
            //     Thread.sleep((int)(Math.random() * 150));
            // } catch (InterruptedException e) {
            //     Thread.currentThread().interrupt();
            //     return;
            // }
        }
        
        System.out.println("Lector " + id + " terminado");
    }
}