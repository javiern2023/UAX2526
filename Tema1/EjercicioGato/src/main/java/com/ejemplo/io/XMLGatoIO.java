package com.ejemplo.io;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import com.ejemplo.model.Gato;

import java.io.File;

public class XMLGatoIO {

    public void guardarGato(Gato gato, String rutaXML) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Gato.class);//Prepara el sistema para trabajar con la clase
        Marshaller marshaller = context.createMarshaller();//Convierte un objeto Java a XML
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//Formato
        marshaller.marshal(gato, new File(rutaXML));//Escribe el gato en el fichero XML
    }

    public Gato cargarGato(String rutaXML) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Gato.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();//Convierte un XML a un objeto Java
        return (Gato) unmarshaller.unmarshal(new File(rutaXML)); //Lee el fichero XML y devuelve un objeto gato
    }
}
