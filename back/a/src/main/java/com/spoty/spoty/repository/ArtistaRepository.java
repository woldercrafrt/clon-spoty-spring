package com.spoty.spoty.repository;

import com.spoty.spoty.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    
    Optional<Artista> findByNombre(String nombre);
    
    List<Artista> findByPais(String pais);
    
    boolean existsByNombre(String nombre);
    
    @Query("SELECT a FROM Artista a WHERE a.nombre LIKE %:nombre%")
    List<Artista> findByNombreContaining(@Param("nombre") String nombre);
    
    @Query("SELECT a FROM Artista a WHERE a.pais = :pais ORDER BY a.nombre ASC")
    List<Artista> findByPaisOrderByNombre(@Param("pais") String pais);
    
    @Query("SELECT COUNT(a) FROM Artista a WHERE a.pais = :pais")
    long countByPais(@Param("pais") String pais);
}