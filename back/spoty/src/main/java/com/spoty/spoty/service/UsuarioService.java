package com.spoty.spoty.service;

import com.spoty.spoty.entity.Usuario;
import com.spoty.spoty.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }
    
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public Usuario saveUsuario(Usuario usuario) {
        if (usuario.getFechaRegistro() == null) {
            usuario.setFechaRegistro(LocalDateTime.now());
        }
        return usuarioRepository.save(usuario);
    }
    
    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setContraseña(usuarioDetails.getContraseña());
            usuario.setPlan(usuarioDetails.getPlan());
            usuario.setRol(usuarioDetails.getRol());
            return usuarioRepository.save(usuario);
        }
        return null;
    }
    
    public boolean deleteUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    
    public List<Usuario> getUsuariosByPlan(String plan) {
        return usuarioRepository.findByPlan(plan);
    }
    
    public List<Usuario> getUsuariosByRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }
    
    public List<Usuario> getUsuariosByPlanAndRol(String plan, String rol) {
        return usuarioRepository.findByPlanAndRol(plan, rol);
    }
    
    public long countUsuariosByPlan(String plan) {
        return usuarioRepository.countByPlan(plan);
    }
}