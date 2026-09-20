package com.ms.catalogo.service;

import com.ms.catalogo.dto.CategoriaRequestDTO;
import com.ms.catalogo.dto.CategoriaResponseDTO;
import com.ms.catalogo.model.Categoria;
import com.ms.catalogo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service 
public class CategoriaService {

    @Autowired 
    private CategoriaRepository categoriaRepository;

    private CategoriaResponseDTO convertirAResponseDTO(Categoria categoria) {
        CategoriaResponseDTO dto = new CategoriaResponseDTO();
        dto.setIdCategoria(categoria.getIdCategoria());
        dto.setNombreCategoria(categoria.getNombreCategoria());
        return dto;
    }

    public List<CategoriaResponseDTO> obtenerCategorias(){
        return categoriaRepository.findAll().stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoriaResponseDTO> obtenerCategoriaPorId(Integer id){
        return categoriaRepository.findById(id).map(this::convertirAResponseDTO);
    }

    public CategoriaResponseDTO guardar(CategoriaRequestDTO dto){
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(dto.getNombreCategoria());
        Categoria guardada = categoriaRepository.save(categoria);
        return convertirAResponseDTO(guardada);
    }

    public CategoriaResponseDTO actualizar(Integer id, CategoriaRequestDTO dtoDetalles){
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombreCategoria(dtoDetalles.getNombreCategoria());
            return convertirAResponseDTO(categoriaRepository.save(categoria));
        }).orElseThrow(() -> new RuntimeException("Categoria no encontrada con el id: " + id));
    }

    public void eliminar(Integer id){
        categoriaRepository.deleteById(id);
    }
}