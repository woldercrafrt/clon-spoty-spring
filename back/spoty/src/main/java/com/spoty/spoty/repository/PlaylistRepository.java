package com.spoty.spoty.repository;

import com.spoty.spoty.entity.Playlist;
import com.spoty.spoty.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    
    List<Playlist> findByUsuario(Usuario usuario);
    
    @Query("SELECT p FROM Playlist p WHERE p.usuario.id = :usuarioId")
    List<Playlist> findByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    List<Playlist> findByNombreContaining(String nombre);
    
    @Query("SELECT p FROM Playlist p WHERE p.usuario.id = :usuarioId ORDER BY p.fechaCreacion DESC")
    List<Playlist> findByUsuarioIdOrderByFechaDesc(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT COUNT(p) FROM Playlist p WHERE p.usuario.id = :usuarioId")
    long countByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    boolean existsByNombreAndUsuario(String nombre, Usuario usuario);
}