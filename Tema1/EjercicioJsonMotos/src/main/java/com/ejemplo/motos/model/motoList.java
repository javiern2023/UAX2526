package com.ejemplo.motos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class motoList {
    private List<moto> motos = new ArrayList<>();

    public void addMoto(moto moto) {
        motos.add(moto);
    }

    public void removeMoto(String modelo) {
        for (int i = 0; i < motos.size(); i++) {
            if (motos.get(i).getModelo().equalsIgnoreCase(modelo)) {
                motos.remove(i);
                break;
            }
        }
    }
}
