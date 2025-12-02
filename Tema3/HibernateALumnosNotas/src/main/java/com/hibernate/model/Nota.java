package com.hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nota")
    private double nota;

    @ManyToOne //Un alumno puede tener muchas notas
    @JoinColumn(name = "expediente", nullable = false) //Relación de las tablas
    private Alumno alumno;
}
