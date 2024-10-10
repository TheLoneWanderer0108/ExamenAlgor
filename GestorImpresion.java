
public class GestorImpresion {
    public static void main(String[] args) {
        ServicioImpresion servicio = new ServicioImpresion();
        
        servicio.enviarTrabajo("Goku", "09:00", 'M');
        servicio.enviarTrabajo("Superman", "09:05", 'H');
        servicio.enviarTrabajo("Batman", "09:02", 'L');
        servicio.enviarTrabajo("Sailor Moon", "09:07", 'M');
        servicio.enviarTrabajo("Carl Johnson", "09:01", 'H');
        
        servicio.procesarTrabajos();
    }
}