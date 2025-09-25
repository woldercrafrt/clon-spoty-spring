package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Comentario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {
    private Long id;
    private String texto;
    private LocalDateTime fecha;
    private UsuarioSimpleDTO usuario;
    private CancionSimpleDTO cancion;
    private PlaylistSimpleDTO playlist;
    
    public ComentarioDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.texto = comentario.getTexto();
        this.fecha = comentario.getFecha();
        if (comentario.getUsuario() != null) {
            this.usuario = new UsuarioSimpleDTO(comentario.getUsuario());
        }
        if (comentario.getCancion() != null) {
            this.cancion = new CancionSimpleDTO(comentario.getCancion());
        }
        if (comentario.getPlaylist() != null) {
            this.playlist = new PlaylistSimpleDTO(comentario.getPlaylist());
        }
    }
}