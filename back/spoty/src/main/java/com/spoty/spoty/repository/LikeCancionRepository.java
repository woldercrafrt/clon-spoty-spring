package com.spoty.spoty.repository;

import com.spoty.spoty.entity.LikeCancion;
import com.spoty.spoty.entity.Usuario;
import com.spoty.spoty.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeCancionRepository extends JpaRepository<LikeCancion, Long> {
    
    List<LikeCancion> findByUsuario(Usuario usuario);
    
    List<LikeCancion> findByCancion(Cancion cancion);
    
    @Query("SELECT lc FROM LikeCancion lc WHERE lc.usuario.id = :usuarioId ORDER BY lc.fechaLike DESC")
    List<LikeCancion> findByUsuarioIdOrderByFechaDesc(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT lc FROM LikeCancion lc WHERE lc.cancion.id = :cancionId ORDER BY lc.fechaLike DESC")
    List<LikeCancion> findByCancionIdOrderByFechaDesc(@Param("cancionId") Long cancionId);
    
    Optional<LikeCancion> findByUsuarioAndCancion(Usuario usuario, Cancion cancion);
    
    boolean existsByUsuarioAndCancion(Usuario usuario, Cancion cancion);
    
    @Query("SELECT COUNT(lc) FROM LikeCancion lc WHERE lc.usuario.id = :usuarioId")
    long countByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT COUNT(lc) FROM LikeCancion lc WHERE lc.cancion.id = :cancionId")
    long countByCancionId(@Param("cancionId") Long cancionId);
    
    @Query("SELECT lc.cancion, COUNT(lc) as likes FROM LikeCancion lc GROUP BY lc.cancion ORDER BY likes DESC")
    List<Object[]> findTopCancionesConMasLikes();
    
    void deleteByUsuarioAndCancion(Usuario usuario, Cancion cancion);
}