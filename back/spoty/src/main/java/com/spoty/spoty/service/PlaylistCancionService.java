package com.spoty.spoty.service;

import com.spoty.spoty.entity.PlaylistCancion;
import com.spoty.spoty.entity.Playlist;
import com.spoty.spoty.entity.Cancion;
import com.spoty.spoty.repository.PlaylistCancionRepository;
import com.spoty.spoty.repository.PlaylistRepository;
import com.spoty.spoty.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistCancionService {
    
    @Autowired
    private PlaylistCancionRepository playlistCancionRepository;
    
    @Autowired
    private PlaylistRepository playlistRepository;
    
    @Autowired
    private CancionRepository cancionRepository;
    
    public List<PlaylistCancion> getCancionesByPlaylist(Long playlistId) {
        return playlistCancionRepository.findByPlaylistIdOrderByOrden(playlistId);
    }
    
    public List<PlaylistCancion> getPlaylistsByCancion(Long cancionId) {
        return playlistCancionRepository.findByCancionId(cancionId);
    }
    
    @Transactional
    public PlaylistCancion agregarCancionAPlaylist(Long playlistId, Long cancionId, Integer orden) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        if (playlist.isPresent() && cancion.isPresent()) {
            // Verificar si la canción ya está en la playlist
            if (playlistCancionRepository.existsByPlaylistAndCancion(playlist.get(), cancion.get())) {
                return null; // Ya existe
            }
            
            PlaylistCancion playlistCancion = new PlaylistCancion();
            playlistCancion.setPlaylist(playlist.get());
            playlistCancion.setCancion(cancion.get());
            
            // Si no se especifica orden, agregar al final
            if (orden == null) {
                Integer maxOrden = playlistCancionRepository.findMaxOrdenByPlaylistId(playlistId);
                orden = (maxOrden != null) ? maxOrden + 1 : 1;
            }
            playlistCancion.setOrden(orden);
            
            return playlistCancionRepository.save(playlistCancion);
        }
        return null;
    }
    
    @Transactional
    public boolean removerCancionDePlaylist(Long playlistId, Long cancionId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        if (playlist.isPresent() && cancion.isPresent()) {
            playlistCancionRepository.deleteByPlaylistAndCancion(playlist.get(), cancion.get());
            return true;
        }
        return false;
    }
    
    public PlaylistCancion updateOrden(Long id, Integer nuevoOrden) {
        Optional<PlaylistCancion> optionalPlaylistCancion = playlistCancionRepository.findById(id);
        if (optionalPlaylistCancion.isPresent()) {
            PlaylistCancion playlistCancion = optionalPlaylistCancion.get();
            playlistCancion.setOrden(nuevoOrden);
            return playlistCancionRepository.save(playlistCancion);
        }
        return null;
    }
    
    public long countCancionesInPlaylist(Long playlistId) {
        return playlistCancionRepository.countByPlaylistId(playlistId);
    }
    
    public boolean existsCancionInPlaylist(Long playlistId, Long cancionId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        return playlist.isPresent() && cancion.isPresent() && 
               playlistCancionRepository.existsByPlaylistAndCancion(playlist.get(), cancion.get());
    }
    
    @Transactional
    public boolean actualizarOrdenCancion(Long playlistId, Long cancionId, Integer nuevoOrden) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        if (playlist.isPresent() && cancion.isPresent()) {
            List<PlaylistCancion> playlistCanciones = playlistCancionRepository.findByPlaylistIdOrderByOrden(playlistId);
            
            for (PlaylistCancion pc : playlistCanciones) {
                if (pc.getCancion().getId().equals(cancionId)) {
                    pc.setOrden(nuevoOrden);
                    playlistCancionRepository.save(pc);
                    return true;
                }
            }
        }
        return false;
    }
}