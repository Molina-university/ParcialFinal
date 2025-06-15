import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Clase para cada vehículo registrado
public class Registro {
    public Vehiculo vehiculo;
    public LocalDateTime entrada;
    public boolean esMensual;
    public boolean esNocturno;
    
    public Registro(Vehiculo vehiculo, boolean esMensual, boolean esNocturno) {
        this.vehiculo = vehiculo;
        this.entrada = LocalDateTime.now();
        this.esMensual = esMensual;
        this.esNocturno = esNocturno;
    }
    
    // ENCAPSULAMIENTO - getters
    public Vehiculo getVehiculo() { return vehiculo; }
    public LocalDateTime getEntrada() { return entrada; }
    public boolean esMensual() { return esMensual; }
    public boolean esNocturno() { return esNocturno; }
    
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM HH:mm");
        return vehiculo.toString() + " | " + entrada.format(formato) + 
               " | " + (esMensual ? "Mensual" : "Diario");
    }
}