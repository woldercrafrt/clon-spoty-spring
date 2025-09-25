package com.spoty.spoty.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "canciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cancion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false)
    private Integer duracion; // duración en segundos

    @Column(name = "url_archivo", length = 500)
    private String urlArchivo;

    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long reproducciones = 0L;

    // Relación con álbum
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    // Relaciones
    @OneToMany(mappedBy = "cancion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlaylistCancion> playlistCanciones;
    
    @OneToMany(mappedBy = "cancion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistorialReproduccion> historialReproducciones;
    
    @OneToMany(mappedBy = "cancion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikeCancion> likeCanciones;
    
    @OneToMany(mappedBy = "cancion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comentario> comentarios;
}
