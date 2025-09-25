package com.spoty.spoty.service;

import com.spoty.spoty.entity.Playlist;
import com.spoty.spoty.entity.Usuario;
import com.spoty.spoty.repository.PlaylistRepository;
import com.spoty.spoty.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    
    @Autowired
    private PlaylistRepository playlistRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }
    
    public Optional<Playlist> getPlaylistById(Long id) {
        return playlistRepository.findById(id);
    }
    
    public List<Playlist> getPlaylistsByUsuario(Long usuarioId) {
        return playlistRepository.findByUsuarioId(usuarioId);
    }
    
    public List<Playlist> getPlaylistsByUsuarioOrderByFecha(Long usuarioId) {
        return playlistRepository.findByUsuarioIdOrderByFechaDesc(usuarioId);
    }
    
    public List<Playlist> searchPlaylistsByNombre(String nombre) {
        return playlistRepository.findByNombreContaining(nombre);
    }
    
    public Playlist savePlaylist(Playlist playlist) {
        playlist.setFechaCreacion(LocalDateTime.now());
        return playlistRepository.save(playlist);
    }
    
    public Playlist createPlaylist(String nombre, String descripcion, Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            Playlist playlist = new Playlist();
            playlist.setNombre(nombre);
            playlist.setDescripcion(descripcion);
            playlist.setUsuario(usuario.get());
            playlist.setFechaCreacion(LocalDateTime.now());
            return playlistRepository.save(playlist);
        }
        return null;
    }
    
    public Playlist updatePlaylist(Long id, Playlist playlistDetails) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()) {
            Playlist playlist = optionalPlaylist.get();
            playlist.setNombre(playlistDetails.getNombre());
            playlist.setDescripcion(playlistDetails.getDescripcion());
            return playlistRepository.save(playlist);
        }
        return null;
    }
    
    public boolean deletePlaylist(Long id) {
        if (playlistRepository.existsById(id)) {
            playlistRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public long countPlaylistsByUsuario(Long usuarioId) {
        return playlistRepository.countByUsuarioId(usuarioId);
    }
    
    public boolean existsPlaylistByNombreAndUsuario(String nombre, Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        return usuario.isPresent() && playlistRepository.existsByNombreAndUsuario(nombre, usuario.get());
    }
    
    public boolean existsById(Long id) {
        return playlistRepository.existsById(id);
    }
}