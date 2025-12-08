package com.example.motos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moto {

    @Id
    private int id;         // Identificador único
    private String marca;
    private String modelo;
    private int cilindrada;
}
