package com.spoty.spoty.dto;

import com.spoty.spoty.entity.HistorialReproduccion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialReproduccionDTO {
    private Long id;
    private LocalDateTime fechaReproduccion;
    private UsuarioSimpleDTO usuario;
    private CancionSimpleDTO cancion;
    
    public HistorialReproduccionDTO(HistorialReproduccion historial) {
        this.id = historial.getId();
        this.fechaReproduccion = historial.getFechaReproduccion();
        if (historial.getUsuario() != null) {
            this.usuario = new UsuarioSimpleDTO(historial.getUsuario());
        }
        if (historial.getCancion() != null) {
            this.cancion = new CancionSimpleDTO(historial.getCancion());
        }
    }
}