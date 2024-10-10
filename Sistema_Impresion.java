
import java.util.ArrayList;

class TrabajoImpresion {
	/*Declaramos las 3 variables esenciales para el funcionamiento del programa, se opto por usar char en vez de string ya que es mas facil de manipualr
	con un switch*/
    private String usuario;
    private String horaEnvio;
    private char prioridad;
    
    // Constructores del trabajo
    public TrabajoImpresion(String usuario, String horaEnvio, char prioridad) {
        this.usuario = usuario;
        this.horaEnvio = horaEnvio;
        /* Un failsafe en el codigo por si la priorida osea el char no es valido, 
        se le asiganra de manera automatica una M, para que no explote el programa*/
        if (prioridad != 'L' && prioridad != 'M' && prioridad != 'H') {
            this.prioridad = 'M';
        } else {
            this.prioridad = prioridad;
        }
    }
    
    //Aqui estamos obteniendo los datos que se necesitan para correr el programa
    public String getUsuario() { return usuario; }
    public String getHoraEnvio() { return horaEnvio; }
    public char getPrioridad() { return prioridad; }
    
    @Override
    public String toString() {
        return "Trabajo de " + usuario + " enviado a las (hora estadar) " + horaEnvio + " (Prioridad: " + prioridad + ")";
    }
}

class ColaImpresion {
    // Use tres listas separadas para cada nivel de prioridad para hacerlo lo mas simple posible y mostrar el alogritmo
    private ArrayList<TrabajoImpresion> altaPrioridad;
    private ArrayList<TrabajoImpresion> mediaPrioridad;
    private ArrayList<TrabajoImpresion> bajaPrioridad;
    
    public ColaImpresion() {
        altaPrioridad = new ArrayList<>();
        mediaPrioridad = new ArrayList<>();
        bajaPrioridad = new ArrayList<>();
    }
    
    //Un simple switch para mostrar la prioridad del trabajo, dependiendo de el char sera puesto la prioridad
    public void agregarTrabajo(TrabajoImpresion trabajo) {
        switch(trabajo.getPrioridad()) {
            case 'H':
                altaPrioridad.add(trabajo);
                break;
            case 'M':
                mediaPrioridad.add(trabajo);
                break;
            case 'L':
                bajaPrioridad.add(trabajo);
                break;
        }
    }
    
    // Método para obtener el siguiente trabajo a imprimir
    public TrabajoImpresion obtenerSiguienteTrabajo() {
        // Esta sera el primer trabajo en ser observado por su alto nivel de prioridad, se usara para ver si el trabajdor tiene alto nivel de prioridad
        if (!altaPrioridad.isEmpty()) {
            return altaPrioridad.remove(0);
        }
        // Hacemos estos chequeos pero para media prioridad, para ver si esxiten dentro de los trabajos a procesar
        if (!mediaPrioridad.isEmpty()) {
            return mediaPrioridad.remove(0);
        }
        // hacemos un chequeo para ver si hay trabajos de poca prioridad
        if (!bajaPrioridad.isEmpty()) {
            return bajaPrioridad.remove(0);
        }
        //retornamos null si no hay trabajos
        return null;
    }
}

// Creador de la cola
class ServicioImpresion {
    private ColaImpresion cola;
    
    public ServicioImpresion() {
        cola = new ColaImpresion();
    }
    
    /*Confirmcaion que el trabajo fue recibido de manera correcta, mostrando los detalles del trabajo, luego se agregan a la cola y son mostrados
    en pantalla*/
    public void enviarTrabajo(String usuario, String hora, char prioridad) {
        TrabajoImpresion trabajo = new TrabajoImpresion(usuario, hora, prioridad);
        cola.agregarTrabajo(trabajo);
        System.out.println("Trabajo recibido: " + trabajo);
    }
    
    /*Estos aqui son los trabajos de impresion, siendo puestos para poder ser llamados desde el main, se asegura que no pueda ser null
    ya que siginifica que no existe*/
    public void procesarTrabajos() {
        TrabajoImpresion trabajo;
        while ((trabajo = cola.obtenerSiguienteTrabajo()) != null) {
            System.out.println("Aqui estan el/los trabajos: " + trabajo);
        }
    }
}