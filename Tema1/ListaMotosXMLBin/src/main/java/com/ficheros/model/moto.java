package com.ficheros.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class moto implements Serializable {
    private String marca;
    private String modelo;
    private int cilindrada;

}
