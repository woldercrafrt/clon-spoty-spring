package com.spoty.spoty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioRequestDTO {
    private String texto;
    private Long usuarioId; // para create
    private Long cancionId; // opcional para create
    private Long playlistId; // opcional para create
}