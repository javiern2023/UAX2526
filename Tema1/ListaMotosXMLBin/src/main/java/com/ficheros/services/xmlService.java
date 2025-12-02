package com.ficheros.services;


import com.ficheros.model.motoList;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;


import java.io.File;

public class xmlService {

    private final String filePath;

    public xmlService(String filePath){
        this.filePath=filePath;
    }
    public void guardarXml(motoList lista) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(motoList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(lista, new File(filePath));
        System.out.println("Lista de motos guardadas en fichero XML");
    }

    public motoList leerXml() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(motoList.class);
        Unmarshaller unmarshaller= context.createUnmarshaller();
        motoList lista=(motoList) unmarshaller.unmarshal(new File(filePath));
        System.out.println("Lista obtenida correctamente");
        return lista;
    }
}
