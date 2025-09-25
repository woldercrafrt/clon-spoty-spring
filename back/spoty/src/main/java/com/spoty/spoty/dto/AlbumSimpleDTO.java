package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumSimpleDTO {
    private Long id;
    private String titulo;
    private LocalDate fechaLanzamiento;
    private String portada;
    
    public AlbumSimpleDTO(Album album) {
        this.id = album.getId();
        this.titulo = album.getTitulo();
        this.fechaLanzamiento = album.getFechaLanzamiento();
        this.portada = album.getPortada();
    }
}