package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Artista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistaSimpleDTO {
    private Long id;
    private String nombre;
    private String pais;
    private String fotoPerfil;
    
    public ArtistaSimpleDTO(Artista artista) {
        this.id = artista.getId();
        this.nombre = artista.getNombre();
        this.pais = artista.getPais();
        this.fotoPerfil = artista.getFotoPerfil();
    }
}