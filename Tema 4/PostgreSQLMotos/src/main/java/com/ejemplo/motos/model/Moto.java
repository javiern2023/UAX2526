package com.ejemplo.motos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Moto {
    private int id;
    private String marca;
    private String modelo;
    private String cilindrada;
}
