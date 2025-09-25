package com.spoty.spoty.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playlist_canciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistCancion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Integer orden;
    
    // Relación con playlist
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;
    
    // Relación con canción
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancion_id", nullable = false)
    private Cancion cancion;
}