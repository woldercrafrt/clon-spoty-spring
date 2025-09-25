package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Playlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private UsuarioSimpleDTO usuario;
    private List<CancionSimpleDTO> canciones;
    private Integer totalCanciones;
    
    public PlaylistDTO(Playlist playlist) {
        this.id = playlist.getId();
        this.nombre = playlist.getNombre();
        this.descripcion = playlist.getDescripcion();
        this.fechaCreacion = playlist.getFechaCreacion();
        if (playlist.getUsuario() != null) {
            this.usuario = new UsuarioSimpleDTO(playlist.getUsuario());
        }
        if (playlist.getPlaylistCanciones() != null) {
            this.canciones = playlist.getPlaylistCanciones().stream()
                    .map(pc -> new CancionSimpleDTO(pc.getCancion()))
                    .collect(Collectors.toList());
            this.totalCanciones = playlist.getPlaylistCanciones().size();
        } else {
            this.totalCanciones = 0;
        }
    }
}