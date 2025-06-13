__________________________________________________________________________________________________________________________

🚗 1. Sistema de Parqueadero ParkHub

__________________________________________________________________________________________________________________________

__________________________________________________________________________________________________________________________


👥 2. Integrantes


- Katherine cicero      192460      ROL: Optimizacion y pruebas

- Jhoan Molina –        192490      ROL: Logica 

- Abel Bonilla –        192488      ROL: Diseño

- Adrian Rincon –       192531      ROL: Logica

__________________________________________________________________________________________________________________________

__________________________________________________________________________________________________________________________

📝 3. Descripción del Problema  


El sistema de parqueadero ParkHub surge ante la necesidad de modernizar y automatizar la gestión de un parqueadero tradicional que manejaba el control de vehículos de forma manual, arrojando cuentas erroneas y perdida de informacion de los usuarios.

**Problemas identificados:**
- Control manual propenso a errores humanos.
- Pérdida de información de vehículos parqueados.
- Dificultad para calcular tarifas con descuentos y recargos.
- Falta de reportes financieros organizados.
- No había diferenciación de precios por tipo de vehículo.
- Ausencia de control de cupos disponibles.

--------------------------------------------------------------------------------------------------------------------------

✨ **Solución Propuesta**
Hemos desarrollado un sistema integral que permite:

- Registrar entrada y salida de vehículos (carros, motos, bicicletas)
- Aplicar tarifas diferenciadas por tipo de vehículo
- Manejar mensualidades con descuentos automáticos
- Aplicar recargos nocturnos según el horario local (Aplica de 8PM a 6AM)
- Controlar cupos disponibles (máximo 200 vehículos)
- Generar reportes financieros detallados
- Buscar vehículos por placa
- Evitar duplicados en el sistema

__________________________________________________________________________________________________________________________


🚀 4. Explicación de Clases y Métodos Principales


| Clase               | Rol                                                        |
| ------------------- | ---------------------------------------------------------- |
| `Vehiculo`          | Clase abstracta padre que representa un vehículo general   |
| `Carro`             | Clase hija que representa un carro                         |
| `Moto`              | Clase hija que representa una motocicleta                  |
| `Bicicleta`         | Clase hija que representa una bicicleta                    |
| `Tarifas`           | Gestiona precios y cálculos de tarifas                     |
| `Registro`          | Almacena información de cada vehículo parqueado            |
| `Parqueadero`       | Clase principal que gestiona todas las operaciones         |
| `ParqueaderoParhub` | Contiene el método main y la interfaz de usuario           |


--------------------------------------------------------------------------------------------------------------------------

## 🚙 Clase `Vehiculo` (Abstracta)

### Propósito: Clase padre que define la estructura básica de cualquier vehículo.

### Atributos:
* `tipo`: tipo de vehículo (String)
* `placa`: placa del vehículo (String)

### Métodos:
* **Constructor** 
* `Vehiculo(String tipo, String placa)`: inicializa tipo y placa
* `getTipo()`: retorna el tipo del vehículo
* `getPlaca()`: retorna la placa del vehículo
* `toString()`: representación en cadena del vehículo

--------------------------------------------------------------------------------------------------------------------------

## 🚗🏍️🚲 Clases Hijas: `Carro`, `Moto`, `Bicicleta`

### Propósito: Representan tipos específicos de vehículos que extienden de `Vehiculo`.

### Características:
* **Herencia**: Todas extienden de la clase `Vehiculo`
* **Constructores**: Cada una inicializa su tipo específico usando `super()`

### Ejemplos:
* `Carro(String placa)`:    llama a `super("Carro", placa)`
* `Moto(String placa)`:     llama a `super("Moto", placa)`
* `Bicicleta(String placa)`:llama a `super("Bicicleta", placa)` {Cabe aclarar algo, somos concientes que las bicicletas no usan placa o por lo menos la mayoria, pero necesitabamos una forma de identificar a las bicis asi que aca podemos poner la descripcion o el color de la bicicleta o la marca}

--------------------------------------------------------------------------------------------------------------------------

## 💰 Clase `Tarifas`

### Propósito: Maneja todo lo relacionado con precios y cálculos de tarifas.

### Atributos (Constantes):
* `PRECIO_CARRO = 2000`: tarifa diaria para carros
* `PRECIO_MOTO =  1000`: tarifa diaria para motos
* `PRECIO_BICI =  200`: tarifa diaria para bicicletas
* `RECARGO_NOCTURNO = 1000`: recargo adicional nocturno

### Métodos:
* `calcularPrecio(String tipo, boolean esMensual, boolean esNocturno)`: calcula el precio final según condiciones
* `esNocturno()`: determina si es horario nocturno (8PM - 6AM)
* `mostrarTarifas()`: muestra tabla completa de precios

--------------------------------------------------------------------------------------------------------------------------

## 📋 Clase `Registro`

### Propósito: Almacena información completa de cada vehículo parqueado.

### Atributos:
* `vehiculo`: objeto Vehiculo parqueado
* `entrada`: fecha y hora de entrada (LocalDateTime)
* `esMensual`: indica si tiene mensualidad
* `esNocturno`: indica si ingresó en horario nocturno

### Métodos:
* **Constructor** `Registro(Vehiculo vehiculo, boolean esMensual, boolean esNocturno)`
* Métodos getter para acceder a los atributos
* `toString()`: formato legible del registro

--------------------------------------------------------------------------------------------------------------------------

## 🏢 Clase `Parqueadero`

### Propósito: Clase principal que gestiona todas las operaciones del parqueadero.

### Atributos:
* `vehiculos`: ArrayList de registros de vehículos activos
* `tarifas`: instancia de la clase Tarifas
* `dineroTipos`: arreglo que acumula dinero por tipo [carros, motos, bicis]
* `CUPOS_TOTALES = 200`: capacidad máxima del parqueadero

### Métodos principales:

#### `ingresarVehiculo(Vehiculo vehiculo, boolean esMensual)`
* Valida cupos disponibles
* Verifica que no existan placas duplicadas
* Crea registro y lo añade al sistema
* Aplica recargo nocturno si corresponde

#### `sacarVehiculo(String placa)`
* Busca el vehículo por placa
* Calcula el precio final a pagar
* Registra el dinero en el tipo correspondiente
* Elimina el registro del sistema

#### `buscarVehiculo(String placa)`
* Localiza un vehículo por placa
* Muestra información del registro
* Calcula precio estimado a pagar

#### `mostrarEstado()`
* Muestra información general del parqueadero
* Cupos totales, ocupados y disponibles
* Indica horario actual (diurno/nocturno)

#### `mostrarVehiculosActivos()`
* Lista todos los vehículos actualmente parqueados

#### `mostrarReporteFinanciero()`
* Muestra dinero recaudado por tipo de vehículo
* Calcula total general recaudado

--------------------------------------------------------------------------------------------------------------------------

## 🖥️ Clase `ParqueaderoParhub` (Contiene `main`)

### Propósito: Punto de entrada del programa y manejo de la interfaz de usuario.

### Atributos estáticos:
* `parqueadero`: instancia única del parqueadero
* `scanner`: Scanner para entrada de datos

### Métodos:

#### `main(String[] args)`
* Punto de entrada del programa
* Muestra mensaje de bienvenida
* Ejecuta bucle principal del menú
* Maneja excepciones del sistema

#### `mostrarMenu()`
* Presenta las opciones disponibles al usuario

#### `ingresarVehiculo()`
* Solicita datos del vehículo al usuario
* Valida entrada de datos
* Crea objeto vehículo según tipo seleccionado

#### `sacarVehiculo()` y `buscarVehiculo()`
* Solicitan placa al usuario
* Llaman a métodos correspondientes del parqueadero

--------------------------------------------------------------------------------------------------------------------------

| Método                      | Función principal                                 |
| ----------------------------| ------------------------------------------------  |
| `calcularPrecio()`          | Calcula precio final según condiciones            |
| `ingresarVehiculo()`        | Registra nuevo vehículo en el sistema             |
| `sacarVehiculo()`           | Retira vehículo y procesa pago                    |
| `buscarVehiculo()`          | Localiza vehículo por placa                       |
| `mostrarEstado()`           | Muestra información general del parqueadero       |
| `mostrarReporteFinanciero()`| Genera reporte de ingresos                        |
| `esNocturno()`              | Determina horario para aplicar recargos           |
| `registrarDinero()`         | Acumula ingresos por tipo de vehículo             |

__________________________________________________________________________________________________________________________

__________________________________________________________________________________________________________________________


## 🎯 5. Conceptos de Programación Implementados

--------------------------------------------------------------------------------------------------------------------------

### 🔄 **Herencia**
* Clase abstracta `Vehiculo` como padre
* Clases `Carro`, `Moto`, `Bicicleta` como hijas
* Uso de `super()` en constructores

### 🔒 **Encapsulamiento**
* Atributos `protected` y `private`
* Métodos getter para acceso controlado
* Validaciones en métodos públicos

### 🔄 **Polimorfismo**
* Manejo de diferentes tipos de vehículos como `Vehiculo`
* Método `toString()` sobrescrito en diferentes clases

### 📊 **Estructuras de Datos**
* `ArrayList<Registro>` para vehículos activos
* Arreglo `double[]` para dinero por categorías
* Uso de `LocalDateTime` para fechas

### 🔁 **Estructuras de Control**
* Bucles `for` tradicionales y mejorados (`for-each`)
* Bucles `while` para búsquedas
* Estructuras `switch` para menús y categorización
* Condicionales `if-else` para validaciones

### ⚠️ **Manejo de Excepciones**
* `try-catch` para entrada de datos
* `NumberFormatException` para validar números
* Validaciones de entrada vacía

__________________________________________________________________________________________________________________________

__________________________________________________________________________________________________________________________

## 💻 6. Instrucciones para Ejecutar el Código

--------------------------------------------------------------------------------------------------------------------------


## 🖥️ **Opción 1: Desde Visual Studio Code**

### ✅ Importante:
* Tener instalado **Extension Pack for Java**
* Guardar el archivo como `ParqueaderoParhub.java`

### 📌 Pasos:

1. **Abre GitBash** o terminal

2. **Navega a la carpeta** del archivo:
   ```bash
   cd ruta/del/archivo
   ```

3. **Compila el código**:
   ```bash
   javac ParqueaderoParhub.java
   ```

4. **Ejecuta el programa**:
   ```bash
   java ParqueaderoParhub
   ```

--------------------------------------------------------------------------------------------------------------------------

## 💻 **Opción 2: Usando IDE (IntelliJ, Eclipse, NetBeans)**

### 📌 Pasos:
1. **Abre tu IDE favorito**
2. **Crea nuevo proyecto Java**
3. **Crea clase** `ParqueaderoParhub`
4. **Pega todo el código** en el archivo
5. **Ejecuta** con el botón (▶️) o pulsando Run

--------------------------------------------------------------------------------------------------------------------------


## 📂 **Archivos**

* Preferiblemente llama a tu archivo principal como 
```bash
   ParqueaderoParhub.java
   ```
   Para que te permita ejecutarlo correctamente.

* No se requiere de archivos adicionales pues todas las clases estan en el archivo principal
__________________________________________________________________________________________________________________________

__________________________________________________________________________________________________________________________


## 🎮 7. Manual de Usuario

### **Menú Principal:**
1. **Ingresar vehículo**: Registra un nuevo vehículo
2. **Sacar vehículo**: Retira vehículo y procesa pago
3. **Buscar vehículo**: Localiza vehículo por placa
4. **Ver estado**: Información general del parqueadero
5. **Ver vehículos activos**: Lista todos los vehículos parqueados
6. **Ver tarifas**: Muestra tabla completa de precios
7. **Reporte financiero**: Dinero recaudado por categorías
8. **Salir**: Termina el programa

--------------------------------------------------------------------------------------------------------------------------

### **Características del Sistema:**
* ✅ Control de cupos (máximo 200 vehículos)
* ✅ Prevención de placas duplicadas
* ✅ Descuento del 25% en mensualidades
* ✅ Recargo nocturno (8PM - 6AM)
* ✅ Reportes financieros detallados
* ✅ Interfaz intuitiva y manejo de errores
* ✅ Manejo de horario local (horas de entrada y salida)
__________________________________________________________________________________________________________________________

# Sistema de Parqueadero ParkHub

## Diagrama UML
![Diagrama UML del Sistema](./imagenes/UML ParqueaderoParkhub.jpeg)
