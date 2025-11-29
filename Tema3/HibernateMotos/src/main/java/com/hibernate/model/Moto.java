// Declaración del paquete donde se encuentra esta clase
package com.hibernate.model;

// Importación de las anotaciones de JPA (Jakarta Persistence API) para mapeo objeto-relacional
import jakarta.persistence.*;
// Importación de la anotación de Lombok que genera un constructor con todos los argumentos
import lombok.AllArgsConstructor;
// Importación de la anotación de Lombok que genera getters, setters, toString, equals y hashCode
import lombok.Data;
// Importación de la anotación de Lombok que genera un constructor sin argumentos
import lombok.NoArgsConstructor;

// @Data: Anotación de Lombok que genera automáticamente:
// - Métodos getter para todos los campos
// - Métodos setter para todos los campos no-final
// - Método toString() que incluye todos los campos
// - Métodos equals() y hashCode() basados en los campos
@Data

// @NoArgsConstructor: Genera automáticamente un constructor sin parámetros
// Esto es requerido por JPA/Hibernate para crear instancias de la entidad
@NoArgsConstructor

// @AllArgsConstructor: Genera automáticamente un constructor con todos los campos como parámetros
// Útil para crear objetos Moto con todos los valores inicializados
@AllArgsConstructor

// @Entity: Marca esta clase como una entidad JPA
// Indica que esta clase se mapeará a una tabla en la base de datos
@Entity

// @Table: Especifica el nombre de la tabla en la base de datos
// name = "motos" indica que esta entidad se mapeará a la tabla llamada "motos"
@Table(name = "motos")

// Declaración de la clase pública Moto
public class Moto {

    // @Id: Marca este campo como la clave primaria de la entidad
    // Indica que 'id' es el identificador único de cada registro en la tabla
    @Id
    
    // @GeneratedValue: Especifica cómo se genera automáticamente el valor del ID
    // strategy = GenerationType.IDENTITY: El ID se genera automáticamente por la base de datos
    // usando una columna de auto-incremento (AUTO_INCREMENT en MySQL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    // Campo privado que almacena el identificador único de la moto
    private int id;

    // @Column: Especifica los detalles de la columna en la base de datos
    // name = "marca": El nombre de la columna en la tabla será "marca"
    // length = 50: La longitud máxima de la cadena es 50 caracteres (VARCHAR(50))
    // nullable = false: Este campo es obligatorio, no puede ser NULL en la base de datos
    @Column(name = "marca", length = 50, nullable = false)
    
    // Campo privado que almacena la marca de la moto (ej: "Honda", "Yamaha")
    private String marca;
    
    // @Column: Mapea este campo a la columna "modelo" en la base de datos
    // length = 50: Longitud máxima de 50 caracteres
    // Por defecto nullable = true, por lo que este campo puede ser NULL
    @Column(name = "modelo", length = 50)
    
    // Campo privado que almacena el modelo de la moto (ej: "CBR600RR", "YZF-R6")
    private String modelo;
    
    // @Column: Mapea este campo a la columna "cilindrada" en la base de datos
    // No se especifica length, por lo que usará el valor por defecto (255 para VARCHAR)
    @Column(name = "cilindrada")
    
    // Campo privado que almacena la cilindrada de la moto (ej: "600cc", "1000cc")
    private String cilindrada;

    // Constructor personalizado que acepta marca, modelo y cilindrada
    // Este constructor NO incluye el ID porque se genera automáticamente
    // Se usa para crear nuevas motos antes de guardarlas en la base de datos
    public Moto(String marca, String modelo, String cilindrada){
        // Asigna el parámetro 'marca' al campo 'marca' de esta instancia
        this.marca=marca;
        
        // Asigna el parámetro 'modelo' al campo 'modelo' de esta instancia
        this.modelo=modelo;
        
        // Asigna el parámetro 'cilindrada' al campo 'cilindrada' de esta instancia
        this.cilindrada=cilindrada;
    }
}
