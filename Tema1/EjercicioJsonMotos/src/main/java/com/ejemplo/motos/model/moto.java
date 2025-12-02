package com.ejemplo.motos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class moto {
    private String marca;
    private String modelo;
    private int cilindrada;
}
