package com.spoty.spoty.repository;

import com.spoty.spoty.entity.Album;
import com.spoty.spoty.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    
    List<Album> findByArtista(Artista artista);
    
    @Query("SELECT a FROM Album a WHERE a.artista.id_artista = :artistaId")
    List<Album> findByIdArtista(@Param("artistaId") Long artistaId);
    
    @Query("SELECT a FROM Album a WHERE a.titulo = :titulo")
    List<Album> findByTitulo(@Param("titulo") Long titulo);
    
    @Query("SELECT a FROM Album a WHERE a.artista.id_artista = :artistaId ORDER BY a.fecha_de_lanzamiento DESC")
    List<Album> findByArtistaIdOrderByFechaDesc(@Param("artistaId") Long artistaId);
    
    @Query("SELECT a FROM Album a ORDER BY a.fecha_de_lanzamiento DESC")
    List<Album> findAllOrderByFechaDesc();
    
    @Query("SELECT COUNT(a) FROM Album a WHERE a.artista.id_artista = :artistaId")
    long countByArtistaId(@Param("artistaId") Long artistaId);
}