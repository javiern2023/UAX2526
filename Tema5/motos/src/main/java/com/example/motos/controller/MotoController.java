package com.example.motos.controller;

import com.example.motos.model.Moto;
import com.example.motos.services.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoService service;

    // Guardar una moto
    @PostMapping("/guardar")
    public Moto guardar(@RequestBody Moto m) {
        return service.guardarMoto(m);
    }

    // Leer todas las motos
    @GetMapping("/todas")
    public List<Moto> todas() {
        return service.leerMotos();
    }

    // Leer una moto por ID
    @GetMapping("/{id}")
    public Optional<Moto> una(@PathVariable int id) {
        return service.leerMoto(id);
    }

    // Eliminar moto por ID
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        boolean eliminado = service.eliminarMoto(id);
        if(eliminado) {
            return "Moto eliminada correctamente";
        } else {
            return "Moto no encontrada";
        }
    }

    // Actualizar una moto por ID
    @PutMapping("/actualizar/{id}")
    public Moto actualizar(@PathVariable int id, @RequestBody Moto m) {
        return service.actualizarMoto(id, m);
    }
}
