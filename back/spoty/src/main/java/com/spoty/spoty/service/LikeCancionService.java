package com.spoty.spoty.service;

import com.spoty.spoty.entity.LikeCancion;
import com.spoty.spoty.entity.Usuario;
import com.spoty.spoty.entity.Cancion;
import com.spoty.spoty.repository.LikeCancionRepository;
import com.spoty.spoty.repository.UsuarioRepository;
import com.spoty.spoty.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LikeCancionService {
    
    @Autowired
    private LikeCancionRepository likeCancionRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CancionRepository cancionRepository;
    
    public List<LikeCancion> getLikesByUsuario(Long usuarioId) {
        return likeCancionRepository.findByUsuarioIdOrderByFechaDesc(usuarioId);
    }
    
    public List<LikeCancion> getLikesByCancion(Long cancionId) {
        return likeCancionRepository.findByCancionIdOrderByFechaDesc(cancionId);
    }
    
    @Transactional
    public LikeCancion darLike(Long usuarioId, Long cancionId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        if (usuario.isPresent() && cancion.isPresent()) {
            // Verificar si ya existe el like
            if (likeCancionRepository.existsByUsuarioAndCancion(usuario.get(), cancion.get())) {
                return null; // Ya existe el like
            }
            
            LikeCancion like = new LikeCancion();
            like.setUsuario(usuario.get());
            like.setCancion(cancion.get());
            like.setFechaLike(LocalDateTime.now());
            
            return likeCancionRepository.save(like);
        }
        return null;
    }
    
    @Transactional
    public boolean quitarLike(Long usuarioId, Long cancionId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        if (usuario.isPresent() && cancion.isPresent()) {
            likeCancionRepository.deleteByUsuarioAndCancion(usuario.get(), cancion.get());
            return true;
        }
        return false;
    }
    
    @Transactional
    public boolean toggleLike(Long usuarioId, Long cancionId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        if (usuario.isPresent() && cancion.isPresent()) {
            if (likeCancionRepository.existsByUsuarioAndCancion(usuario.get(), cancion.get())) {
                likeCancionRepository.deleteByUsuarioAndCancion(usuario.get(), cancion.get());
                return false; // Se quitó el like
            } else {
                LikeCancion like = new LikeCancion();
                like.setUsuario(usuario.get());
                like.setCancion(cancion.get());
                like.setFechaLike(LocalDateTime.now());
                likeCancionRepository.save(like);
                return true; // Se agregó el like
            }
        }
        return false;
    }
    
    public boolean hasLike(Long usuarioId, Long cancionId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        return usuario.isPresent() && cancion.isPresent() && 
               likeCancionRepository.existsByUsuarioAndCancion(usuario.get(), cancion.get());
    }
    
    public long countLikesByUsuario(Long usuarioId) {
        return likeCancionRepository.countByUsuarioId(usuarioId);
    }
    
    public long countLikesByCancion(Long cancionId) {
        return likeCancionRepository.countByCancionId(cancionId);
    }
    
    public List<Object[]> getTopCancionesConMasLikes() {
        return likeCancionRepository.findTopCancionesConMasLikes();
    }
    
    public Optional<LikeCancion> getLikeByUsuarioAndCancion(Long usuarioId, Long cancionId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        if (usuario.isPresent() && cancion.isPresent()) {
            return likeCancionRepository.findByUsuarioAndCancion(usuario.get(), cancion.get());
        }
        return Optional.empty();
    }
}