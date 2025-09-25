package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Artista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistaDTO {
    private Long id;
    private String nombre;
    private String biografia;
    private String pais;
    private String fotoPerfil;
    private List<AlbumSimpleDTO> albums;
    
    public ArtistaDTO(Artista artista) {
        this.id = artista.getId();
        this.nombre = artista.getNombre();
        this.biografia = artista.getBiografia();
        this.pais = artista.getPais();
        this.fotoPerfil = artista.getFotoPerfil();
        if (artista.getAlbums() != null) {
            this.albums = artista.getAlbums().stream()
                    .map(AlbumSimpleDTO::new)
                    .collect(Collectors.toList());
        }
    }
}