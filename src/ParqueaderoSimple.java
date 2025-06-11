import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// Clase padre - HERENCIA
abstract class Vehiculo {
    protected String tipo;
    protected String placa;
    
    public Vehiculo(String tipo, String placa) {
        this.tipo = tipo;
        this.placa = placa;
    }
    
    // ENCAPSULAMIENTO - getters
    public String getTipo() { return tipo; }
    public String getPlaca() { return placa; }
    
    @Override
    public String toString() {
        return tipo + " - " + placa;
    }
}

// Clases hijas
class Carro extends Vehiculo {
    public Carro(String placa) { super("Carro", placa); }
}

class Moto extends Vehiculo {
    public Moto(String placa) { super("Moto", placa); }
}

class Bicicleta extends Vehiculo {
    public Bicicleta(String placa) { super("Bicicleta", placa); }
}

// Clase para manejar precios
class Tarifas {
    private final int PRECIO_CARRO = 4000;
    private final int PRECIO_MOTO = 2000;
    private final int PRECIO_BICI = 500;
    private final int RECARGO_NOCTURNO = 2000;
    
    // MÉTODO REUTILIZABLE 1
    public int calcularPrecio(String tipo, boolean esMensual, boolean esNocturno) {
        int precio = 0;
        
        // SWITCH para precios base
        switch (tipo) {
            case "Carro": precio = PRECIO_CARRO; break;
            case "Moto": precio = PRECIO_MOTO; break;
            case "Bicicleta": precio = PRECIO_BICI; break;
        }
        
        // CONDICIONALES para modificar precio
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

// Clase para cada vehículo registrado
class Registro {
    private Vehiculo vehiculo;
    private LocalDateTime entrada;
    private boolean esMensual;
    private boolean esNocturno;
    
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

// Clase principal del parqueadero
class Parqueadero {
    private ArrayList<Registro> vehiculos; // ARRAYLIST
    private Tarifas tarifas;
    private double[] dineroTipos; // [carros, motos, bicis] - ARREGLO
    private final int CUPOS_TOTALES = 200;
    
    public Parqueadero() {
        vehiculos = new ArrayList<>();
        tarifas = new Tarifas();
        dineroTipos = new double[3]; // 0=carros, 1=motos, 2=bicis
    }
    
    // MÉTODO REUTILIZABLE 2
    public boolean ingresarVehiculo(Vehiculo vehiculo, boolean esMensual) {
        // Validar cupos
        if (vehiculos.size() >= CUPOS_TOTALES) {
            System.out.println(" No hay cupos disponibles");
            return false;
        }
        
        // BUCLE FOR - verificar duplicados
        for (Registro r : vehiculos) {
            if (r.getVehiculo().getPlaca().equalsIgnoreCase(vehiculo.getPlaca())) {
                System.out.println(" Este vehículo ya está parqueado");
                return false;
            }
        }
        
        boolean esNocturno = tarifas.esNocturno();
        Registro registro = new Registro(vehiculo, esMensual, esNocturno);
        vehiculos.add(registro);
        
        System.out.println("Vehículo ingresado:");
        System.out.println(registro.toString());
        if (esNocturno && !esMensual) {
            System.out.println("Se aplicará recargo nocturno");
        }
        
        return true;
    }
    
    public boolean sacarVehiculo(String placa) {
        // BUCLE WHILE para buscar
        int i = 0;
        while (i < vehiculos.size()) {
            Registro registro = vehiculos.get(i);
            
            if (registro.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                // Calcular precio
                int precio = tarifas.calcularPrecio(
                    registro.getVehiculo().getTipo(),
                    registro.esMensual(),
                    registro.esNocturno()
                );
                
                // Registrar dinero por tipo
                registrarDinero(registro.getVehiculo().getTipo(), precio);
                
                vehiculos.remove(i);
                
                System.out.println("Vehículo retirado:");
                System.out.println(registro.toString());
                System.out.println("Total a pagar: $" + precio);
                
                return true;
            }
            i++;
        }
        
        System.out.println("Vehículo con placa " + placa + " no encontrado");
        return false;
    }
    
    public void buscarVehiculo(String placa) {
        // BUCLE FOR mejorado
        for (Registro registro : vehiculos) {
            if (registro.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                System.out.println("Vehículo encontrado:");
                System.out.println(registro.toString());
                
                // Mostrar precio estimado
                int precio = tarifas.calcularPrecio(
                    registro.getVehiculo().getTipo(),
                    registro.esMensual(),
                    registro.esNocturno()
                );
                System.out.println("Precio a pagar: $" + precio);
                return;
            }
        }
        System.out.println("Vehículo no encontrado");
    }
    
    private void registrarDinero(String tipo, double monto) {
        // SWITCH para categorizar dinero
        switch (tipo) {
            case "Carro": dineroTipos[0] += monto; break;
            case "Moto": dineroTipos[1] += monto; break;
            case "Bicicleta": dineroTipos[2] += monto; break;
        }
    }
    
    public void mostrarEstado() {
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Estado del Parqueadero");
        System.out.println("-----------------------------");
        System.out.println("");
        System.out.println("Cupos totales: " + CUPOS_TOTALES);
        System.out.println("Cupos ocupados: " + vehiculos.size());
        System.out.println("Cupos disponibles: " + (CUPOS_TOTALES - vehiculos.size()));
        
        if (tarifas.esNocturno()) {
            System.out.println("Horario nocturno - Se aplica recargo");
        } else {
            System.out.println("Horario diurno");
        }
    }
    
    public void mostrarVehiculosActivos() {
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Vehículos Activos");
        System.out.println("-----------------------------");
        System.out.println("");
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos parqueados");
        } else {
            // BUCLE FOR con contador
            for (int i = 0; i < vehiculos.size(); i++) {
                System.out.println((i + 1) + ". " + vehiculos.get(i).toString());
            }
        }
    }
    
    public void mostrarReporteFinanciero() {
        double total = dineroTipos[0] + dineroTipos[1] + dineroTipos[2];
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Reporte Financiero");
        System.out.println("-----------------------------");
        System.out.println("");
        System.out.println("Carros: $" + (int)dineroTipos[0]);
        System.out.println("Motos: $" + (int)dineroTipos[1]);
        System.out.println("Bicicletas: $" + (int)dineroTipos[2]);
        System.out.println("TOTAL RECAUDADO: $" + (int)total);
    }
    
    public Tarifas getTarifas() { return tarifas; }
}

// CLASE PRINCIPAL (MAIN)
public class ParqueaderoSimple {
    private static Parqueadero parqueadero = new Parqueadero();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("-----------------------------");
        System.out.println("Bienvenido al Parqueadero ParkHub");
        System.out.println("-----------------------------");

        while (true) {
            try { // TRY-CATCH para manejo de excepciones
                mostrarMenu();
                int opcion = Integer.parseInt(scanner.nextLine());
                
                // SWITCH principal
                switch (opcion) {
                    case 1: ingresarVehiculo(); break;
                    case 2: sacarVehiculo(); break;
                    case 3: buscarVehiculo(); break;
                    case 4: parqueadero.mostrarEstado(); break;
                    case 5: parqueadero.mostrarVehiculosActivos(); break;
                    case 6: parqueadero.getTarifas().mostrarTarifas(); break;
                    case 7: parqueadero.mostrarReporteFinanciero(); break;
                    case 8:
                        System.out.println("¡Gracias por usar el sistema, no vuelva pronto por favor!");
                        return;
                    default:
                        System.out.println("Opción inválida (1-8)");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese solo números");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }

// Se que hay muchos printlns, pero es para que se vea mas bonito el programa    
    private static void mostrarMenu() {
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("MENÚ PRINCIPAL");
        System.out.println("-----------------------------");
        System.out.println("");
        System.out.println("1. Ingresar vehículo");
        System.out.println("2. Sacar vehículo");  
        System.out.println("3. Buscar vehículo");
        System.out.println("4. Ver estado");
        System.out.println("5. Ver vehículos activos");
        System.out.println("6. Ver tarifas");
        System.out.println("7. Reporte financiero");
        System.out.println("8. Salir");
        System.out.print("Opción: ");
    }
    
    private static void ingresarVehiculo() {
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Ingresar Vehículo");
        System.out.println("-----------------------------");
        System.out.println("");
        
        try {
            System.out.println("Tipos disponibles:");
            System.out.println("1. Carro");
            System.out.println("2. Moto");
            System.out.println("3. Bicicleta");
            System.out.print("Tipo (1-3): ");
            
            int tipo = Integer.parseInt(scanner.nextLine());
            
            if (tipo < 1 || tipo > 3) {
                System.out.println("Tipo inválido");
                return;
            }
            
            System.out.print("Placa: ");
            String placa = scanner.nextLine().trim();
            
            if (placa.isEmpty()) {
                System.out.println("La placa no puede estar vacía");
                return;
            }
            
            System.out.print("¿Mensualidad? (s/n): ");
            boolean esMensual = scanner.nextLine().toLowerCase().startsWith("s");
            
            Vehiculo vehiculo = null;
            // SWITCH para crear vehículo
            switch (tipo) {
                case 1: vehiculo = new Carro(placa); break;
                case 2: vehiculo = new Moto(placa); break;
                case 3: vehiculo = new Bicicleta(placa); break;
            }
            
            parqueadero.ingresarVehiculo(vehiculo, esMensual);
            
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido para el tipo");
        }
    }
    
    private static void sacarVehiculo() {
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Sacar Vehículo");
        System.out.println("-----------------------------");
        System.out.println("");
        
        System.out.print("Placa del vehículo: ");
        String placa = scanner.nextLine().trim();
        
        if (placa.isEmpty()) {
            System.out.println("La placa no puede estar vacía");
            return;
        }
        
        parqueadero.sacarVehiculo(placa);
    }
    
    private static void buscarVehiculo() {
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Buscar Vehículo");
        System.out.println("-----------------------------");
        System.out.println("");
        
        System.out.print("Placa del vehículo: ");
        String placa = scanner.nextLine().trim();
        
        if (placa.isEmpty()) {
            System.out.println("La placa no puede estar vacía");
            return;
        }
        
        parqueadero.buscarVehiculo(placa);
    }
}