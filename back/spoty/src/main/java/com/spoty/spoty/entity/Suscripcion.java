package com.spoty.spoty.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "suscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suscripcion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    
    @Column(columnDefinition = "TEXT")
    private String beneficios;
    
    @Column(nullable = false)
    private Boolean activo = true;
}