# Gestión de Cuentas Bancarias :bank:

## Descripción General 📄

Gestión de Cuentas Bancarias es una aplicación Java que permite realizar operaciones bancarias básicas, como apertura de cuentas, consultas de saldos y realización de transacciones.
Está diseñada para funcionar a través de una interfaz de consola interactiva, donde los usuarios pueden seleccionar opciones desde un menú para interactuar con el sistema bancario.

## Funcionalidades 🚀

El menú principal ofrece las siguientes funcionalidades:

- **Abrir una nueva cuenta** 📝
- **Listar cuentas bancarias** 📋
- **Consultar detalles de una cuenta** 🔍
- **Realizar ingresos en una cuenta** 💰
- **Retirar dinero de una cuenta** 🏧
- **Consultar el saldo de una cuenta** 💵

## Estructura del Proyecto 🏗️

El proyecto está organizado en las siguientes clases principales:

### Clases Principales

- **Main**: Clase que comienza con la ejecución del porgrama. En ella se encuentra el menú y toda la lógica relacionada con la interación del usuario.
  - Métodos principales:
    - `crearCuenta()`: pide al usuario la información necesaria para crear cada tipo de cuenta.
      Para solicitar los datos, se utilizan los siguientes métodos.
    - `pedirFloat(), pedirInt(), pedirString()`: los cuales validan la información introducida por el usuario. Utilizando la función pasada como parámetro, y lanzando los mensajes correspondientes.

- **Banco**: Clase encargada de gestionar todas las cuentas bancarias.
  Utiliza un `HashSet` para almacenar las cuentas, garantizando que no haya duplicados y mejorando el rendimiento en búsquedas y eliminaciones.
  - Métodos principales:
    - `abrirCuenta()`
    - `listadoCuentas()`
    - `informacionCuenta()`
    - `ingresoCuenta()`
    - `retiradaCuenta()`
    - `obtenerSaldo()`

### Clases modelo

- **CuentaBancaria**: Clase base para las cuentas bancarias, con atributos comunes como IBAN y saldo.
- **Persona**: Clase que se encarga de recopilar la información personal del usuario. Es utilizada en las cuentas bancarías.

### Clases Derivadas

- **CuentaAhorro**: Define una cuenta remunerada con un determinado tipo de interés.
- **CuentaCorriente**: Cuenta no remunerada.
- **CuentaCorrienteEmpresa**, **CuentaCorrientePersonal**: Diferentes tipos de cuentas corrientes.

### Clase de utilidad
- **Validator**: Clase que agrupa una seríe de métodos encargados de comprobar si un valor cumple con determinados requisitos. Son utilizados como parámetros para las funciones encargadas de solicitar información al usuario.

### Implementación de la Interfaz Imprimible 🖨️

La aplicación utiliza la interfaz `Imprimible` para estandarizar la representación de las cuentas bancarias. Cada clase que implementa esta interfaz debe definir el método `devolverInfoString()`.

Aunque su uso es similar al de `toString()`, he decidido seguir utilizandolo, ya que en muchas ocasiones no es necesario hacer una llamada explicita al método, como en `println()`,
solo con poner el nombre de un objeto automáticamente se llama al método `toString()`, lo cual me resulta práctico. Si es verdad que puede parecer redundante que `toString()` simplemente llame a `devolverInfoString()`.

El interés de utilizar esté método o la interfáz es el de aplicar el polimorfismo,
en ambos casos se ve claramente, ya que `toString()` es heredado desde la clase `Object` y `devolverInfoString` desde `cuentaBancaria`. Si realizamos una llamada desde un objeto que herede de alguno de ellos, utilizara su definición, en caso de sobreescribirla, o en su defecto la de la clase padre.
