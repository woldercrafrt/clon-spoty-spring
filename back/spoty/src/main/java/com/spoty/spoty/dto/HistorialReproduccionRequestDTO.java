package com.spoty.spoty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialReproduccionRequestDTO {
    private Long usuarioId;
    private Long cancionId;
}