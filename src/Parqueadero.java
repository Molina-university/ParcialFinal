import java.util.ArrayList;

// Clase principal del parqueadero
public class Parqueadero {
    public ArrayList<Registro> vehiculos; // ARRAYLIST
    public Tarifas tarifas;
    public double[] dineroTipos; // [carros, motos, bicis] - ARREGLO
    public final int CUPOS_TOTALES = 200;
    
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
    
    public void registrarDinero(String tipo, double monto) {
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