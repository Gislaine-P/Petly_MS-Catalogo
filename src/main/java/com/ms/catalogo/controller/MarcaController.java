package com.ms.catalogo.controller;

import com.ms.catalogo.dto.MarcaRequestDTO;
import com.ms.catalogo.dto.MarcaResponseDTO;
import com.ms.catalogo.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/marca")
@CrossOrigin(origins = {"http://localhost:8083", "http://localhost:3000"})
public class MarcaController {

    @Autowired 
    private MarcaService marcaService;

    @GetMapping
    public List<MarcaResponseDTO> listarTodos(){
        return marcaService.obtenerMarcas(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> obtenerMarcaPorId(@PathVariable Integer id) {
        return marcaService.obtenerMarcaPorId(id) 
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MarcaResponseDTO> crear(@Valid @RequestBody MarcaRequestDTO dto){
        MarcaResponseDTO creada = marcaService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody MarcaRequestDTO dto){
        try {
            MarcaResponseDTO marcaActualizada = marcaService.actualizar(id, dto);
            return ResponseEntity.ok(marcaActualizada);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build(); 
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            marcaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}