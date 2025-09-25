package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Cancion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancionPlaylistDTO {
    private Long id;
    private String titulo;
    private Integer duracion;
    private String urlArchivo;
    private Long reproducciones;
    private String nombreArtista;
    private String nombreAlbum;
    private Integer orden;
    
    public CancionPlaylistDTO(Cancion cancion, Integer orden) {
        this.id = cancion.getId();
        this.titulo = cancion.getTitulo();
        this.duracion = cancion.getDuracion();
        this.urlArchivo = cancion.getUrlArchivo();
        this.reproducciones = cancion.getReproducciones();
        this.nombreArtista = cancion.getAlbum().getArtista().getNombre();
        this.nombreAlbum = cancion.getAlbum().getTitulo();
        this.orden = orden;
    }
}