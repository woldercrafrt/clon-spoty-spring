package com.spoty.spoty.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "like_canciones", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "cancion_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeCancion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_like", nullable = false)
    private LocalDateTime fechaLike = LocalDateTime.now();
    
    // Relación con usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    // Relación con canción
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancion_id", nullable = false)
    private Cancion cancion;
}