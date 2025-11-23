# Concesionario UAX S.L. - Sistema de Gestión de Ventas

Aplicación Java para la gestión de ventas de coches. Utiliza archivos JSON para el inventario disponible y MySQL para las ventas realizadas.

## Estructura del Proyecto

```
Reto1/
├── src/main/
│   ├── java/com/uax/concesionario/
│   │   ├── App.java                          # Punto de entrada de la aplicación
│   │   ├── Model/                            # Modelos de datos (entidades)
│   │   │   ├── Coche.java
│   │   │   ├── Cliente.java
│   │   │   └── VentaCoche.java
│   │   ├── controller/                       # Controlador de interfaz de usuario
│   │   │   └── ConcesionarioController.java
│   │   └── database/                         # Capa de acceso a datos
│   │       ├── ConexionBD.java              # Gestión de conexiones MySQL
│   │       └── GestionBD.java               # DAO - Operaciones CRUD
│   └── resources/
│       └── coches_disponibles.json          # Inventario de coches disponibles
└── pom.xml                                  # Configuración Maven
```

## Requisitos Previos

1. **Java 21 o superior**
2. **Maven 3.x**
3. **MAMP** (o cualquier servidor MySQL)
   - MySQL ejecutándose en puerto `8889`
   - Usuario: `root`
   - Contraseña: `root`

## Configuración Automática de Base de Datos

✨ **La base de datos se crea automáticamente al ejecutar la aplicación por primera vez!**

No necesitas ejecutar ningún script SQL manual. Al iniciar la aplicación:
1. Se conecta al servidor MySQL en `localhost:8889`
2. Crea la base de datos `concesionario_uax` si no existe
3. Crea la tabla `ventas` con todos los campos necesarios
4. Configura los índices automáticamente

**Solo asegúrate de que MAMP esté ejecutándose antes de lanzar la aplicación.**

## Compilación y Ejecución

### Compilar el Proyecto
```bash
cd "/Users/alvaromartinez/IdeaProjects/dam2º/acceso a datos/UAX2526/Reto1"
mvn clean compile
```

### Ejecutar la Aplicación
```bash
mvn exec:java -Dexec.mainClass="com.uax.concesionario.App"
```

## Funcionalidades

La aplicación ofrece un menú interactivo con las siguientes opciones:

1. **Dar de alta un coche nuevo**: Registra un nuevo coche en el inventario (JSON)
2. **Vender un coche a un cliente**: Procesa una venta (elimina de JSON, guarda en MySQL)
3. **Ver coches disponibles**: Muestra el inventario actual
4. **Ver coches vendidos**: Lista todas las ventas realizadas
5. **Salir**: Cierra la aplicación

## Tecnologías Utilizadas

- **Java 21**: Lenguaje de programación
- **Maven**: Gestión de dependencias y build
- **MySQL 8.0**: Base de datos relacional
- **Gson 2.10.1**: Serialización/deserialización JSON
- **MySQL Connector 8.0.33**: Driver JDBC para MySQL

## Arquitectura

El proyecto sigue el patrón **MVC** (Model-View-Controller):

- **Model**: Clases de entidades (`Coche`, `Cliente`, `VentaCoche`)
- **View**: Interfaz de consola (menús y mensajes)
- **Controller**: `ConcesionarioController` - Lógica de presentación
- **DAO**: `GestionBD` - Acceso a datos (JSON + MySQL)

### Patrón de Conexión
- `ConexionBD`: Clase con métodos estáticos para conectar/desconectar MySQL
- `GestionBD`: Unifica operaciones con JSON (disponibles) y MySQL (ventas)

## Datos de Ejemplo

El archivo `data/coches_disponibles.json` incluye 5 coches de ejemplo:
- Toyota Corolla 2023
- Honda Civic 2022
- Seat León 2024
- Volkswagen Golf 2023
- Ford Focus 2022

## Resolución de Problemas

### Error: No se puede conectar a MySQL
- Verifica que MAMP esté ejecutándose
- Confirma que MySQL esté en puerto 8889
- Revisa usuario/contraseña en `ConexionBD.java`

### Error: Base de datos no existe
- Ejecuta el script `data/mysql_schema.sql`
- Verifica que la BD `concesionario_uax` esté creada

### Error de compilación
- Verifica que estés usando Java 21 o superior
- Ejecuta `mvn clean` antes de compilar

## Autor

Proyecto desarrollado para la asignatura de Acceso a Datos - DAM 2º
UAX 2024-2025
