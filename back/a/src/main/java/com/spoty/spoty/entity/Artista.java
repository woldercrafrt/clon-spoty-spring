package com.spoty.spoty.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Artista")
@Data
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista")
    private long id_artista;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String biografia;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private String foto_perfil;

    //relacion con album
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <Album> albums = new ArrayList<>();
}
