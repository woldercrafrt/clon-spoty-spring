package com.spoty.spoty.dto;

import com.spoty.spoty.entity.LikeCancion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeCancionDTO {
    private Long id;
    private LocalDateTime fechaLike;
    private UsuarioSimpleDTO usuario;
    private CancionSimpleDTO cancion;
    
    public LikeCancionDTO(LikeCancion like) {
        this.id = like.getId();
        this.fechaLike = like.getFechaLike();
        if (like.getUsuario() != null) {
            this.usuario = new UsuarioSimpleDTO(like.getUsuario());
        }
        if (like.getCancion() != null) {
            this.cancion = new CancionSimpleDTO(like.getCancion());
        }
    }
}