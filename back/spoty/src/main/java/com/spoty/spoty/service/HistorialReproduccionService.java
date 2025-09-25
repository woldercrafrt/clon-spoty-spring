package com.spoty.spoty.service;

import com.spoty.spoty.entity.HistorialReproduccion;
import com.spoty.spoty.entity.Usuario;
import com.spoty.spoty.entity.Cancion;
import com.spoty.spoty.repository.HistorialReproduccionRepository;
import com.spoty.spoty.repository.UsuarioRepository;
import com.spoty.spoty.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistorialReproduccionService {
    
    @Autowired
    private HistorialReproduccionRepository historialRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CancionRepository cancionRepository;
    
    public List<HistorialReproduccion> getHistorialByUsuario(Long usuarioId) {
        return historialRepository.findByUsuarioIdOrderByFechaDesc(usuarioId);
    }
    
    public List<HistorialReproduccion> getHistorialByCancion(Long cancionId) {
        return historialRepository.findByCancionIdOrderByFechaDesc(cancionId);
    }
    
    public List<HistorialReproduccion> getHistorialByUsuarioAndFecha(Long usuarioId, 
                                                                     LocalDateTime fechaInicio, 
                                                                     LocalDateTime fechaFin) {
        return historialRepository.findByUsuarioIdAndFechaBetween(usuarioId, fechaInicio, fechaFin);
    }
    
    @Transactional
    public HistorialReproduccion registrarReproduccion(Long usuarioId, Long cancionId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        if (usuario.isPresent() && cancion.isPresent()) {
            HistorialReproduccion historial = new HistorialReproduccion();
            historial.setUsuario(usuario.get());
            historial.setCancion(cancion.get());
            historial.setFechaReproduccion(LocalDateTime.now());
            
            // Incrementar contador de reproducciones de la canción
            Cancion cancionEntity = cancion.get();
            cancionEntity.setReproducciones(cancionEntity.getReproducciones() + 1);
            cancionRepository.save(cancionEntity);
            
            return historialRepository.save(historial);
        }
        return null;
    }
    
    public long countReproduccionesByUsuario(Long usuarioId) {
        return historialRepository.countByUsuarioId(usuarioId);
    }
    
    public long countReproduccionesByCancion(Long cancionId) {
        return historialRepository.countByCancionId(cancionId);
    }
    
    public long countReproduccionesByUsuarioAndCancion(Long usuarioId, Long cancionId) {
        List<HistorialReproduccion> historial = historialRepository.findByUsuarioIdOrderByFechaDesc(usuarioId);
        long count = 0;
        for (HistorialReproduccion hr : historial) {
            if (hr.getCancion() != null && hr.getCancion().getId().equals(cancionId)) {
                count++;
            }
        }
        return count;
    }
    
    public List<Object[]> getTopCancionesByUsuario(Long usuarioId) {
        return historialRepository.findTopCancionesByUsuario(usuarioId);
    }
    
    public boolean deleteHistorial(Long id) {
        if (historialRepository.existsById(id)) {
            historialRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional
    public void limpiarHistorialUsuario(Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<HistorialReproduccion> historial = historialRepository.findByUsuario(usuario.get());
            historialRepository.deleteAll(historial);
        }
    }
    
    @Transactional
    public void eliminarHistorialUsuarioCancion(Long usuarioId, Long cancionId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        Optional<Cancion> cancion = cancionRepository.findById(cancionId);
        
        if (usuario.isPresent() && cancion.isPresent()) {
            List<HistorialReproduccion> historial = historialRepository.findByUsuario(usuario.get());
            for (HistorialReproduccion hr : historial) {
                if (hr.getCancion() != null && hr.getCancion().getId().equals(cancionId)) {
                    historialRepository.delete(hr);
                }
            }
        }
    }
}