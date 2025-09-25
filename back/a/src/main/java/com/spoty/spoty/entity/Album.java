package com.spoty.spoty.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Album")
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id_album;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String fecha_de_lanzamiento;

    @Column(nullable = false)
    private String portada; // URL o ruta de la imagen de portada

    //relaciones entre artista
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artista")
    private Artista artista;

    //relacion entre cancion
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<Cancion> canciones = new java.util.ArrayList<>();

    //getter y setter estan con loombock usando @data para mas placer -u-

}
