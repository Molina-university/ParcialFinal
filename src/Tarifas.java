import java.time.LocalDateTime;

// Clase para manejar precios
public class Tarifas {
    public final int PRECIO_CARRO = 2000;
    public final int PRECIO_MOTO = 1000;
    public final int PRECIO_BICI = 200;
    public final int RECARGO_NOCTURNO = 1000;
    
    // MÉTODO REUTILIZABLE 1
    public int calcularPrecio(String tipo, boolean esMensual, boolean esNocturno) {
        int precio = 0;
        
        // SWITCH para precios base
        switch (tipo) {
            case "Carro": precio = PRECIO_CARRO; break;
            case "Moto": precio = PRECIO_MOTO; break;
            case "Bicicleta": precio = PRECIO_BICI; break;
        }
        
        // Condicionales para determinar el precio
        if (esMensual) {
            precio = (int)(precio * 30 * 0.75); // 25% descuento mensual
        } else if (esNocturno) {
            precio += RECARGO_NOCTURNO;
        }
        
        return precio;
    }
    
    public boolean esNocturno() {
        int hora = LocalDateTime.now().getHour();
        return hora >= 20 || hora < 6;
    }
    
    public void mostrarTarifas() {
        System.out.println("\n===  TARIFAS ===");
        System.out.println("DIARIAS:");
        System.out.println("• Carro: $" + PRECIO_CARRO);
        System.out.println("• Moto: $" + PRECIO_MOTO);  
        System.out.println("• Bicicleta: $" + PRECIO_BICI);
        System.out.println("\nMENSUALES (25% descuento):");
        System.out.println("• Carro: $" + calcularPrecio("Carro", true, false));
        System.out.println("• Moto: $" + calcularPrecio("Moto", true, false));
        System.out.println("• Bicicleta: $" + calcularPrecio("Bicicleta", true, false));
        System.out.println("\nRecargo nocturno (8PM-6AM): +$" + RECARGO_NOCTURNO);
    }
}