package u3c13_ej8;

class mensaje {
    // Método para imprimir un mensaje en consola
    public void imprimir(String msg) {
        System.out.print("[" + msg);
        try {
            // Detiene el hilo actual durante 1 segundo
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("]");
    }
}

class proceso implements Runnable {
    String msg;
    mensaje target;
    Thread hilo;
    
    // Constructor que inicializa el mensaje y el objeto 'mensaje' 
    public proceso(mensaje target, String msg) {
        super();
        this.target = target; // Guarda la referencia al objeto 'mensaje'
        this.msg = msg; // Guarda el mensaje a imprimir
        hilo = new Thread(this); // Crea un nuevo hilo asociado a esta instancia de 'proceso'
    }
    
    // Método que ejecuta el hilo, llamando al método 'imprimir' de 'mensaje'
    public void run() {
    	synchronized(target) {   //Synchronized block
    		target.imprimir(msg);
    	}
    }
}

class ejemplo8 {
    public static void main(String[] args) {        
        mensaje msg = new mensaje(); // Crea una instancia de 'mensaje'
        
        // Crea tres hilos que comparten el mismo objeto 'mensaje'
        proceso hilo1 = new proceso(msg, "Hola");
        proceso hilo2 = new proceso(msg, "Sincronizado");
        proceso hilo3 = new proceso(msg, "Mundo");
        
        // Inicia los tres hilos
        hilo1.hilo.start();
        hilo2.hilo.start();
        hilo3.hilo.start();
        
        // Espera a que los hilos terminen antes de continuar
        try {
            hilo1.hilo.join();
            hilo2.hilo.join();
            hilo3.hilo.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
