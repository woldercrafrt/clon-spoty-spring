package com.spoty.spoty.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "artistas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    @Column(length = 100)
    private String pais;

    @Column(name = "foto_perfil", length = 500)
    private String fotoPerfil;

    // Relación con álbumes
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Album> albums;
}
