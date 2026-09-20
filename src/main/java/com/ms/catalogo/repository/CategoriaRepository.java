package com.ms.catalogo.repository;

import com.ms.catalogo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
