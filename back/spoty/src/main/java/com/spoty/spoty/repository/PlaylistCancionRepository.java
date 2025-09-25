package com.spoty.spoty.repository;

import com.spoty.spoty.entity.PlaylistCancion;
import com.spoty.spoty.entity.Playlist;
import com.spoty.spoty.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistCancionRepository extends JpaRepository<PlaylistCancion, Long> {
    
    List<PlaylistCancion> findByPlaylist(Playlist playlist);
    
    List<PlaylistCancion> findByCancion(Cancion cancion);
    
    @Query("SELECT pc FROM PlaylistCancion pc WHERE pc.playlist.id = :playlistId ORDER BY pc.orden ASC")
    List<PlaylistCancion> findByPlaylistIdOrderByOrden(@Param("playlistId") Long playlistId);
    
    @Query("SELECT pc FROM PlaylistCancion pc WHERE pc.cancion.id = :cancionId")
    List<PlaylistCancion> findByCancionId(@Param("cancionId") Long cancionId);
    
    boolean existsByPlaylistAndCancion(Playlist playlist, Cancion cancion);
    
    @Query("SELECT COUNT(pc) FROM PlaylistCancion pc WHERE pc.playlist.id = :playlistId")
    long countByPlaylistId(@Param("playlistId") Long playlistId);
    
    @Query("SELECT MAX(pc.orden) FROM PlaylistCancion pc WHERE pc.playlist.id = :playlistId")
    Integer findMaxOrdenByPlaylistId(@Param("playlistId") Long playlistId);
    
    void deleteByPlaylistAndCancion(Playlist playlist, Cancion cancion);
}