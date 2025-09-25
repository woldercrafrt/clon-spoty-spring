package com.spoty.spoty.service;

import com.spoty.spoty.entity.Comentario;
import com.spoty.spoty.entity.Usuario;
import com.spoty.spoty.entity.Cancion;
import com.spoty.spoty.entity.Playlist;
import com.spoty.spoty.repository.ComentarioRepository;
import com.spoty.spoty.repository.UsuarioRepository;
import com.spoty.spoty.repository.CancionRepository;
import com.spoty.spoty.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {
    
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CancionRepository cancionRepository;
    
    @Autowired
    private PlaylistRepository playlistRepository;
    
    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }
    
    public Optional<Comentario> getComentarioById(Long id) {
        return comentarioRepository.findById(id);
    }
    
    public List<Comentario> getComentariosByUsuario(Long usuarioId) {
        return comentarioRepository.findByUsuarioIdOrderByFechaDesc(usuarioId);
    }
    
    public List<Comentario> getComentariosByCancion(Long cancionId) {
        return comentarioRepository.findByCancionIdOrderByFechaDesc(cancionId);
    }
    
    public List<Comentario> getComentariosByPlaylist(Long playlistId) {
        return comentarioRepository.findByPlaylistIdOrderByFechaDesc(playlistId);
    }
    
    public List<Comentario> getComentariosDeCancionesRecientes() {
        return comentarioRepository.findComentariosDeCancionesOrderByFechaDesc();
    }
    
    public List<Comentario> getComentariosDePlaylistsRecientes() {
        return comentarioRepository.findComentariosDePlaylistsOrderByFechaDesc();
    }
    
    public Comentario createComentarioCancion(String texto, Long usuarioId, Long cancionId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        if (usuario.isPresent() && cancion.isPresent()) {
            Comentario comentario = new Comentario();
            comentario.setTexto(texto);
            comentario.setUsuario(usuario.get());
            comentario.setCancion(cancion.get());
            comentario.setFecha(LocalDateTime.now());
            return comentarioRepository.save(comentario);
        }
        return null;
    }
    
    public Comentario createComentarioPlaylist(String texto, Long usuarioId, Long playlistId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        
        if (usuario.isPresent() && playlist.isPresent()) {
            Comentario comentario = new Comentario();
            comentario.setTexto(texto);
            comentario.setUsuario(usuario.get());
            comentario.setPlaylist(playlist.get());
            comentario.setFecha(LocalDateTime.now());
            return comentarioRepository.save(comentario);
        }
        return null;
    }
    
    public Comentario saveComentario(Comentario comentario) {
        comentario.setFecha(LocalDateTime.now());
        return comentarioRepository.save(comentario);
    }
    
    public Comentario updateComentario(Long id, String nuevoTexto) {
        Optional<Comentario> optionalComentario = comentarioRepository.findById(id);
        if (optionalComentario.isPresent()) {
            Comentario comentario = optionalComentario.get();
            comentario.setTexto(nuevoTexto);
            return comentarioRepository.save(comentario);
        }
        return null;
    }
    
    public boolean deleteComentario(Long id) {
        if (comentarioRepository.existsById(id)) {
            comentarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public long countComentariosByUsuario(Long usuarioId) {
        return comentarioRepository.countByUsuarioId(usuarioId);
    }
    
    public long countComentariosByCancion(Long cancionId) {
        return comentarioRepository.countByCancionId(cancionId);
    }
    
    public long countComentariosByPlaylist(Long playlistId) {
        return comentarioRepository.countByPlaylistId(playlistId);
    }
}