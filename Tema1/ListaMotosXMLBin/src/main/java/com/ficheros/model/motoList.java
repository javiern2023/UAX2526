package com.ficheros.model;


import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlElement;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "motos")
@XmlAccessorType(XmlAccessType.FIELD) //Usa todos los campos directamente
public class motoList implements Serializable {

    @XmlElement(name = "moto")
    private List<moto> motos = new ArrayList<>();

    public List<moto> getMotos(){
        return motos;
    }
    public void addMoto(moto moto){
        motos.add(moto);
    }
    public void setMotos(List<moto> motos){
        this.motos = motos;
    }

}
