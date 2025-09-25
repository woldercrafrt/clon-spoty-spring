package com.spoty.spoty.repository;

import com.spoty.spoty.entity.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {
    
    Optional<Suscripcion> findByNombre(String nombre);
    
    List<Suscripcion> findByActivo(Boolean activo);
    
    @Query("SELECT s FROM Suscripcion s WHERE s.precio <= :maxPrecio ORDER BY s.precio ASC")
    List<Suscripcion> findByPrecioLessThanEqualOrderByPrecio(@Param("maxPrecio") BigDecimal maxPrecio);
    
    @Query("SELECT s FROM Suscripcion s WHERE s.precio BETWEEN :minPrecio AND :maxPrecio")
    List<Suscripcion> findByPrecioBetween(@Param("minPrecio") BigDecimal minPrecio, @Param("maxPrecio") BigDecimal maxPrecio);
    
    @Query("SELECT s FROM Suscripcion s WHERE s.activo = true ORDER BY s.precio ASC")
    List<Suscripcion> findActivasOrderByPrecio();
    
    boolean existsByNombre(String nombre);
    
    @Query("SELECT COUNT(s) FROM Suscripcion s WHERE s.activo = :activo")
    long countByActivo(@Param("activo") Boolean activo);
}