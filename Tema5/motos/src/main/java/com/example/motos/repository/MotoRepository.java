package com.example.motos.repository;

import com.example.motos.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository; //Spring Data.
import org.springframework.stereotype.Repository;

//Indica a Spring que esta interfaz forma parte de la capa de acceso a datos.
@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer> {
    // JpaRepository ya trae métodos para guardar, eliminar, buscar y listar
}
