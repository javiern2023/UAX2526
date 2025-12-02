package com.hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "motos")

public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "marca", length = 50, nullable = false)
    private String marca;
    @Column(name = "modelo", length = 50)
    private String modelo;
    @Column(name = "cilindrada")
    private String cilindrada;

    public Moto(String marca, String modelo, String cilindrada){
        this.marca=marca;
        this.modelo=modelo;
        this.cilindrada=cilindrada;
    }
}
