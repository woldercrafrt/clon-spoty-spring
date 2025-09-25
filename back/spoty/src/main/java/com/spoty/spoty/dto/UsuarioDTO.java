package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String plan;
    private LocalDateTime fechaRegistro;
    private String rol;
    
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.plan = usuario.getPlan().toString();
        this.fechaRegistro = usuario.getFechaRegistro();
        this.rol = usuario.getRol();
    }
}