package com.example.motos.services;


import com.example.motos.model.Moto;
import com.example.motos.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoService {

    @Autowired
    private MotoRepository repository;

    // Guardar moto
    public Moto guardarMoto(Moto m) {
        return repository.save(m);
    }

    // Leer todas las motos
    public List<Moto> leerMotos() {
        return repository.findAll();
    }

    // Leer una moto por ID
    public Optional<Moto> leerMoto(int id) {
        return repository.findById(id);
    }

    // Eliminar moto
    public boolean eliminarMoto(int id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Moto actualizarMoto(int id, Moto m) {
        Optional<Moto> existente = repository.findById(id);

        if(existente.isPresent()) {
            Moto moto = existente.get();
            moto.setMarca(m.getMarca());
            moto.setModelo(m.getModelo());
            moto.setCilindrada(m.getCilindrada());
            return repository.save(moto);
        } else {
            return null;
        }
    }
}
