package com.spoty.spoty.repository;

import com.spoty.spoty.entity.Comentario;
import com.spoty.spoty.entity.Usuario;
import com.spoty.spoty.entity.Cancion;
import com.spoty.spoty.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
    List<Comentario> findByUsuario(Usuario usuario);
    
    List<Comentario> findByCancion(Cancion cancion);
    
    List<Comentario> findByPlaylist(Playlist playlist);
    
    @Query("SELECT c FROM Comentario c WHERE c.usuario.id = :usuarioId ORDER BY c.fecha DESC")
    List<Comentario> findByUsuarioIdOrderByFechaDesc(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT c FROM Comentario c WHERE c.cancion.id = :cancionId ORDER BY c.fecha DESC")
    List<Comentario> findByCancionIdOrderByFechaDesc(@Param("cancionId") Long cancionId);
    
    @Query("SELECT c FROM Comentario c WHERE c.playlist.id = :playlistId ORDER BY c.fecha DESC")
    List<Comentario> findByPlaylistIdOrderByFechaDesc(@Param("playlistId") Long playlistId);
    
    @Query("SELECT COUNT(c) FROM Comentario c WHERE c.usuario.id = :usuarioId")
    long countByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT COUNT(c) FROM Comentario c WHERE c.cancion.id = :cancionId")
    long countByCancionId(@Param("cancionId") Long cancionId);
    
    @Query("SELECT COUNT(c) FROM Comentario c WHERE c.playlist.id = :playlistId")
    long countByPlaylistId(@Param("playlistId") Long playlistId);
    
    @Query("SELECT c FROM Comentario c WHERE c.cancion IS NOT NULL ORDER BY c.fecha DESC")
    List<Comentario> findComentariosDeCancionesOrderByFechaDesc();
    
    @Query("SELECT c FROM Comentario c WHERE c.playlist IS NOT NULL ORDER BY c.fecha DESC")
    List<Comentario> findComentariosDePlaylistsOrderByFechaDesc();
}