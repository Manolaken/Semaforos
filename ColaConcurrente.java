package practica3;

import java.util.concurrent.Semaphore;
/**
 * Clase ColaConcurrente que garantiza acceso concurrente correcto
 * usando semáforos para sincronización
 */
class ColaConcurrente {
    private Cola cola;                 // Cola subyacente básica
    private Semaphore mutex;           // Exclusión mutua para acceder a la cola
    private Semaphore huecos;          // Cuenta espacios disponibles
    private Semaphore elementos;       // Cuenta elementos disponibles
    
    /**
     * Constructor de la cola concurrente
     * @param capacidad Capacidad máxima de la cola
     */
    public ColaConcurrente(int capacidad) {
        this.cola = new Cola(capacidad);                    // Cola básica
        this.mutex = new Semaphore(1);              // 1 ticket para exclusión mutua
        this.huecos = new Semaphore(capacidad);             // Inicialmente todos los huecos libres
        this.elementos = new Semaphore(0);          // Inicialmente sin elementos
    }
    
    /**
     * Inserta un elemento en la cola de forma segura
     * @param mensaje El mensaje a insertar
     */
    public void insertar(String mensaje) {
        try {
            // Esperar a que haya hueco disponible
            huecos.acquire();
            
            // Entrar en sección crítica
            mutex.acquire();
            
            // Insertar en la cola
            cola.insertar(mensaje);
            
            // Salir de sección crítica
            mutex.release();
            
            // Señalizar que hay un elemento más
            elementos.release();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error al insertar: " + e.getMessage());
        }
    }
    
    /**
     * Extrae un elemento de la cola de forma segura
     * @return El mensaje extraído
     */
    public String extraer() {
        String mensaje = null;
        try {
            // Esperar a que haya elementos disponibles
            elementos.acquire();
            
            // Entrar en sección crítica
            mutex.acquire();
            
            // Extraer de la cola
            mensaje = cola.extraer();
            
            // Salir de sección crítica
            mutex.release();
            
            // Señalizar que hay un hueco más
            huecos.release();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error al extraer: " + e.getMessage());
        }
        return mensaje;
    }
    
    /**
     * Obtiene el número de elementos (solo para información)
     * @return Número de elementos
     */
    public int getNumElementos() {
        return cola.getNumElementos();
    }
}