package com.spoty.spoty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistRequestDTO {
    private String nombre;
    private String descripcion;
    private Long usuarioId; // solo para create; puede omitirse en update
    private boolean publica = false;
}