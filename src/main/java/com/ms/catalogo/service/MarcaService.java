package com.ms.catalogo.service;

import com.ms.catalogo.dto.MarcaRequestDTO;
import com.ms.catalogo.dto.MarcaResponseDTO;
import com.ms.catalogo.model.Marca;
import com.ms.catalogo.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service 
public class MarcaService {

    @Autowired 
    private MarcaRepository marcaRepository;

    private MarcaResponseDTO convertirAResponseDTO(Marca marca) {
        MarcaResponseDTO dto = new MarcaResponseDTO();
        dto.setIdMarca(marca.getIdMarca());
        dto.setNombreMarca(marca.getNombreMarca());
        dto.setDescripcion(marca.getDescripcion());
        return dto;
    }

    public List<MarcaResponseDTO> obtenerMarcas(){
        return marcaRepository.findAll().stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<MarcaResponseDTO> obtenerMarcaPorId(Integer id){
        return marcaRepository.findById(id).map(this::convertirAResponseDTO);
    }

    public MarcaResponseDTO guardar(MarcaRequestDTO dto){
        Marca marca = new Marca();
        marca.setNombreMarca(dto.getNombreMarca());
        marca.setDescripcion(dto.getDescripcion());
        Marca guardada = marcaRepository.save(marca);
        return convertirAResponseDTO(guardada);
    }

    public MarcaResponseDTO actualizar(Integer id, MarcaRequestDTO dtoDetalles){
        return marcaRepository.findById(id).map(marca -> {
            marca.setNombreMarca(dtoDetalles.getNombreMarca());
            marca.setDescripcion(dtoDetalles.getDescripcion());
            return convertirAResponseDTO(marcaRepository.save(marca));
        }).orElseThrow(() -> new RuntimeException("Marca no encontrada con el id: " + id));
    }

    public void eliminar(Integer id){
        marcaRepository.deleteById(id);
    }
}