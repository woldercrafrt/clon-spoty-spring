package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Cancion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancionDTO {
    private Long id;
    private String titulo;
    private Integer duracion;
    private String urlArchivo;
    private Long reproducciones;
    private AlbumSimpleDTO album;
    private ArtistaSimpleDTO artista;
    
    public CancionDTO(Cancion cancion) {
        this.id = cancion.getId();
        this.titulo = cancion.getTitulo();
        this.duracion = cancion.getDuracion();
        this.urlArchivo = cancion.getUrlArchivo();
        this.reproducciones = cancion.getReproducciones();
        if (cancion.getAlbum() != null) {
            this.album = new AlbumSimpleDTO(cancion.getAlbum());
            if (cancion.getAlbum().getArtista() != null) {
                this.artista = new ArtistaSimpleDTO(cancion.getAlbum().getArtista());
            }
        }
    }
}