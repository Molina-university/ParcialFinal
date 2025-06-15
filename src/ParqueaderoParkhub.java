import java.util.Scanner;

// CLASE PRINCIPAL (MAIN)
public class ParqueaderoParkhub {
    public static Parqueadero parqueadero = new Parqueadero();
    public static Scanner scanner = new Scanner(System.in);
    
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
    public static void mostrarMenu() {
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
    
    public static void ingresarVehiculo() {
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
            
            System.out.print("¿Mensualidad? (si/no): ");
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
    
    public static void sacarVehiculo() {
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
    
    public static void buscarVehiculo() {
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