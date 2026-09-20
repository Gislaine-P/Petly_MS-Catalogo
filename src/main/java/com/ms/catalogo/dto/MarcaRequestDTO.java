package com.ms.catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MarcaRequestDTO {

    @NotBlank (message = "El nombre de la marca es obligatorio")
    private String nombreMarca;

    @NotBlank (message = "La descripcion de la marca es obligatoria!")
    private String descripcion;

}
