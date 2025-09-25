package com.spoty.spoty.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id_usuario;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String clave;

    @Column(nullable = false)
    private String plan;

    @Column(nullable = false)
    private String rol;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;




}
