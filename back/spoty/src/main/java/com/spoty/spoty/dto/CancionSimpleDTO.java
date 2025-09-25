package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Cancion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancionSimpleDTO {
    private Long id;
    private String titulo;
    private Integer duracion;
    private Long reproducciones;
    private String nombreArtista;
    private String nombreAlbum;
    
    public CancionSimpleDTO(Cancion cancion) {
        this.id = cancion.getId();
        this.titulo = cancion.getTitulo();
        this.duracion = cancion.getDuracion();
        this.reproducciones = cancion.getReproducciones();
        if (cancion.getAlbum() != null) {
            this.nombreAlbum = cancion.getAlbum().getTitulo();
            if (cancion.getAlbum().getArtista() != null) {
                this.nombreArtista = cancion.getAlbum().getArtista().getNombre();
            }
        }
    }
}