package practica3;
/**
 * Thread lector que extrae mensajes de la cola
 */
class ThreadLector extends Thread {
    private final int id; // Identificador del lector
    private final ColaConcurrente cola; // Cola compartida
    private final int numMensajes; // Número de mensajes a leer

    public ThreadLector(int id, ColaConcurrente cola, int numMensajes) {
        this.id = id;
        this.cola = cola;
        this.numMensajes = numMensajes;
    }
    
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