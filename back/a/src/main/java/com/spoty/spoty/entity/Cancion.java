package com.spoty.spoty.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Cancion")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id_cancion;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer duracion; // duración en segundos

    @Column(nullable = false)
    private String archivo; // ruta del archivo de audio

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer reproducciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_album", nullable = false)
    private Album album;

}
