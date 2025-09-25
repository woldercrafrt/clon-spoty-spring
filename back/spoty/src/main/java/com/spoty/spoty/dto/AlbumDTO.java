package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {
    private Long id;
    private String titulo;
    private LocalDate fechaLanzamiento;
    private String portada;
    private ArtistaSimpleDTO artista;
    private List<CancionSimpleDTO> canciones;
    
    public AlbumDTO(Album album) {
        this.id = album.getId();
        this.titulo = album.getTitulo();
        this.fechaLanzamiento = album.getFechaLanzamiento();
        this.portada = album.getPortada();
        if (album.getArtista() != null) {
            this.artista = new ArtistaSimpleDTO(album.getArtista());
        }
        if (album.getCanciones() != null) {
            this.canciones = album.getCanciones().stream()
                    .map(CancionSimpleDTO::new)
                    .collect(Collectors.toList());
        }
    }
}