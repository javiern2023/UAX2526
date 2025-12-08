package com.ejemplo.motos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Moto {

    @Id
    @GeneratedValue
    private long id;

    private String marca;
    private String modelo;
    private int cilindrada;
}
