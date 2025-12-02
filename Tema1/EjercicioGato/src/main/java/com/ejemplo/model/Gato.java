package com.ejemplo.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "gato")
public class Gato {

    @XmlElement
    private String chip;

    @XmlElement
    private String nombre;

    @XmlElement
    private String raza;
}
