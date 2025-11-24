package practica3;
/**
 * Clase Cola básica FIFO sin sincronización
 */
class Cola {
    private String[] datos; // Array para almacenar los mensajes
    private int capacidad; // Capacidad máxima de la cola
    private int numElementos; // Número actual de elementos en la cola
    private int primero; // Índice del primer elemento
    private int ultimo; // Índice del último elemento

    /**
     * Constructor de la cola
     * @param capacidad Capacidad máxima de la cola
     */
    public Cola(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new String[capacidad];
        this.numElementos = 0;
        this.primero = 0;
        this.ultimo = 0;
    }
    
    /**
     * Inserta un elemento en la cola (sin sincronización)
     * @param mensaje El mensaje a insertar
     */
    public void insertar(String mensaje) {
        datos[ultimo] = mensaje; // Insertar el mensaje en la posición 'ultimo'
        ultimo = (ultimo + 1) % capacidad; // Actualizar el índice 'ultimo' de forma circular
        numElementos++; // Incrementar el número de elementos
    }
    
    /**
     * Extrae un elemento de la cola (sin sincronización)
     * @return El mensaje extraído
     */
    public String extraer() {
        String mensaje = datos[primero]; // Obtener el mensaje en la posición 'primero'
        primero = (primero + 1) % capacidad; // Actualizar el índice 'primero' de forma circular
        numElementos--; // Decrementar el número de elementos
        return mensaje;
    }
    
    /**
     * Verifica si la cola está llena
     * @return true si está llena, false en caso contrario
     */
    public boolean estaLlena() {
        return numElementos == capacidad;
    }
    
    /**
     * Verifica si la cola está vacía
     * @return true si está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return numElementos == 0;
    }
    
    /**
     * Obtiene el número de elementos en la cola
     * @return Número de elementos
     */
    public int getNumElementos() {
        return numElementos;
    }
}