package com.spoty.spoty.repository;

import com.spoty.spoty.entity.HistorialReproduccion;
import com.spoty.spoty.entity.Usuario;
import com.spoty.spoty.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistorialReproduccionRepository extends JpaRepository<HistorialReproduccion, Long> {
    
    List<HistorialReproduccion> findByUsuario(Usuario usuario);
    
    List<HistorialReproduccion> findByCancion(Cancion cancion);
    
    @Query("SELECT hr FROM HistorialReproduccion hr WHERE hr.usuario.id = :usuarioId ORDER BY hr.fechaReproduccion DESC")
    List<HistorialReproduccion> findByUsuarioIdOrderByFechaDesc(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT hr FROM HistorialReproduccion hr WHERE hr.cancion.id = :cancionId ORDER BY hr.fechaReproduccion DESC")
    List<HistorialReproduccion> findByCancionIdOrderByFechaDesc(@Param("cancionId") Long cancionId);
    
    @Query("SELECT hr FROM HistorialReproduccion hr WHERE hr.usuario.id = :usuarioId AND hr.fechaReproduccion BETWEEN :fechaInicio AND :fechaFin")
    List<HistorialReproduccion> findByUsuarioIdAndFechaBetween(@Param("usuarioId") Long usuarioId, 
                                                               @Param("fechaInicio") LocalDateTime fechaInicio, 
                                                               @Param("fechaFin") LocalDateTime fechaFin);
    
    @Query("SELECT COUNT(hr) FROM HistorialReproduccion hr WHERE hr.usuario.id = :usuarioId")
    long countByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT COUNT(hr) FROM HistorialReproduccion hr WHERE hr.cancion.id = :cancionId")
    long countByCancionId(@Param("cancionId") Long cancionId);
    
    @Query("SELECT hr.cancion, COUNT(hr) as reproducciones FROM HistorialReproduccion hr WHERE hr.usuario.id = :usuarioId GROUP BY hr.cancion ORDER BY reproducciones DESC")
    List<Object[]> findTopCancionesByUsuario(@Param("usuarioId") Long usuarioId);
}