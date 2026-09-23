package com.ms.catalogo.controller;

import com.ms.catalogo.dto.CategoriaRequestDTO;
import com.ms.catalogo.dto.CategoriaResponseDTO;
import com.ms.catalogo.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired 
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaResponseDTO> listarTodos(){
        return categoriaService.obtenerCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> obtenerCategoriaPorId(@PathVariable Integer id) {
        return categoriaService.obtenerCategoriaPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crear(@Valid @RequestBody CategoriaRequestDTO dto){
        CategoriaResponseDTO creada = categoriaService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody CategoriaRequestDTO dto){
        try {
            CategoriaResponseDTO categoriaActualizada = categoriaService.actualizar(id, dto);
            return ResponseEntity.ok(categoriaActualizada);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build(); 
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            categoriaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}