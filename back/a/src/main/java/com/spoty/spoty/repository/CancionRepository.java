package com.spoty.spoty.repository;

import com.spoty.spoty.entity.Album;
import com.spoty.spoty.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
    
    // Buscar canciones por álbum
    List<Cancion> findByAlbum(Album album);
    
    // Buscar canciones por ID de álbum
    @Query("SELECT c FROM Cancion c WHERE c.album.id_album = :idAlbum")
    List<Cancion> findByIdAlbum(@Param("idAlbum") Long idAlbum);
    
    // Buscar canciones por título
    List<Cancion> findByTitulo(String titulo);
    
    // Buscar canciones ordenadas por reproducciones (descendente)
    List<Cancion> findAllByOrderByReproduccionesDesc();
    
    // Buscar canciones por álbum y título
    @Query("SELECT c FROM Cancion c WHERE c.album.id_album = :idAlbum AND c.titulo = :titulo")
    List<Cancion> findByIdAlbumAndTitulo(@Param("idAlbum") Long idAlbum, @Param("titulo") String titulo);
    
    // Buscar canciones con reproducciones mayores a un mínimo
    @Query("SELECT c FROM Cancion c WHERE c.reproducciones > :minReproductions")
    List<Cancion> findByReproduccionesGreaterThan(@Param("minReproductions") Integer minReproductions);
    
    // Contar canciones por álbum
    @Query("SELECT COUNT(c) FROM Cancion c WHERE c.album.id_album = :idAlbum")
    long countByIdAlbum(@Param("idAlbum") Long idAlbum);
    
    // Obtener total de reproducciones por álbum
    @Query("SELECT SUM(c.reproducciones) FROM Cancion c WHERE c.album.id_album = :idAlbum")
    Long getTotalReproduccionesByAlbum(@Param("idAlbum") Long idAlbum);
}