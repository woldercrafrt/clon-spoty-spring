package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Playlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSimpleDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private String nombreUsuario;
    private Integer totalCanciones;
    
    public PlaylistSimpleDTO(Playlist playlist) {
        this.id = playlist.getId();
        this.nombre = playlist.getNombre();
        this.descripcion = playlist.getDescripcion();
        this.fechaCreacion = playlist.getFechaCreacion();
        if (playlist.getUsuario() != null) {
            this.nombreUsuario = playlist.getUsuario().getNombre();
        }
        if (playlist.getPlaylistCanciones() != null) {
            this.totalCanciones = playlist.getPlaylistCanciones().size();
        } else {
            this.totalCanciones = 0;
        }
    }
}