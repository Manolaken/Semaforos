package practica3;
/**
 * Thread escritor que inserta mensajes en la cola
 */
class ThreadEscritor extends Thread {
    private final int id; // Identificador del escritor
    private final ColaConcurrente cola; // Cola compartida
    private final int numMensajes; // Número de mensajes a escribir
    
    public ThreadEscritor(int id, ColaConcurrente cola, int numMensajes) {
        this.id = id;
        this.cola = cola;
        this.numMensajes = numMensajes;
    }
    
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