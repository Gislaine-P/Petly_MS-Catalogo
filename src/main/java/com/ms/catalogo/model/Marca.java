package com.ms.catalogo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "marca")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Marca {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_marca")
    private Integer idMarca;

    @Column (name = "nombre_marca")
    private String nombreMarca;

    @Column (name = "descripcion")
    private String descripcion;

}
