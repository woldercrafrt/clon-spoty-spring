package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSimpleDTO {
    private Long id;
    private String nombre;
    private String email;
    private String plan;
    
    public UsuarioSimpleDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.plan = usuario.getPlan().toString();
    }
}