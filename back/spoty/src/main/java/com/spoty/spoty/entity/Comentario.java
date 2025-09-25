package com.spoty.spoty.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;
    
    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();
    
    // Relación con usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    // Relación opcional con canción
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancion_id")
    private Cancion cancion;
    
    // Relación opcional con playlist
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
}