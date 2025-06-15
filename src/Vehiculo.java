// Clase padre - HERENCIA
public abstract class Vehiculo {
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
    public Carro(String placa) { 
        super("Carro", placa); 
    }
}

class Moto extends Vehiculo {
    public Moto(String placa) { 
        super("Moto", placa); 
    }
}

class Bicicleta extends Vehiculo {
    public Bicicleta(String placa) { 
        super("Bicicleta", placa); 
    }
}