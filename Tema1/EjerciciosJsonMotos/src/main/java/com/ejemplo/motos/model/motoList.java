package com.ejemplo.motos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class motoList {
    private List<moto> motos = new ArrayList<>();

    public void addMoto(moto m){
        motos.add(m);
    }
    public void removeMoto(String modelo){
        for(int i=0; i<motos.size();i++){
            if(motos.get(i).getModelo().equalsIgnoreCase(modelo)){
                motos.remove(i);
                break;
            }
        }
    }
}
