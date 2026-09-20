package com.ms.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.catalogo.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
