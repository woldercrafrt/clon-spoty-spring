package com.spoty.spoty.service;

import com.spoty.spoty.entity.Cancion;
import com.spoty.spoty.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {
    
    @Autowired
    private CancionRepository cancionRepository;
    
    public List<Cancion> getAllCanciones() {
        return cancionRepository.findAll();
    }
    
    public Optional<Cancion> getCancionById(Long id) {
        return cancionRepository.findById(id);
    }
    
    public Cancion saveCancion(Cancion cancion) {
        return cancionRepository.save(cancion);
    }
    
    public Cancion updateCancion(Long id, Cancion cancionDetails) {
        Optional<Cancion> optionalCancion = cancionRepository.findById(id);
        if (optionalCancion.isPresent()) {
            Cancion cancion = optionalCancion.get();
            cancion.setTitulo(cancionDetails.getTitulo());
            cancion.setDuracion(cancionDetails.getDuracion());
            cancion.setUrlArchivo(cancionDetails.getUrlArchivo());
            cancion.setReproducciones(cancionDetails.getReproducciones());
            
            // Actualizar la relación con álbum si es necesario
            if (cancionDetails.getAlbum() != null) {
                cancion.setAlbum(cancionDetails.getAlbum());
            }
            
            return cancionRepository.save(cancion);
        }
        return null;
    }
    
    public boolean deleteCancion(Long id) {
        if (cancionRepository.existsById(id)) {
            cancionRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<Cancion> getCancionesByAlbumId(Long albumId) {
        return cancionRepository.findByIdAlbum(albumId);
    }
    
    public List<Cancion> getCancionesByTitulo(String titulo) {
        return cancionRepository.findByTitulo(titulo);
    }
    
    public List<Cancion> getCancionesOrderByReproducciones() {
        return cancionRepository.findAllByOrderByReproduccionesDesc();
    }
    
    public List<Cancion> getCancionesByAlbumIdAndTitulo(Long albumId, String titulo) {
        return cancionRepository.findByIdAlbumAndTitulo(albumId, titulo);
    }
    
    public List<Cancion> getCancionesPopulares(Integer minReproducciones) {
        return cancionRepository.findByReproduccionesGreaterThan(minReproducciones);
    }
    
    public long countCancionesByAlbum(Long albumId) {
        return cancionRepository.countByIdAlbum(albumId);
    }
    
    public Long getTotalReproduccionesByAlbum(Long albumId) {
        return cancionRepository.getTotalReproduccionesByAlbum(albumId);
    }
    
    public Cancion incrementarReproducciones(Long id) {
        Optional<Cancion> optionalCancion = cancionRepository.findById(id);
        if (optionalCancion.isPresent()) {
            Cancion cancion = optionalCancion.get();
            cancion.setReproducciones(cancion.getReproducciones() + 1);
            return cancionRepository.save(cancion);
        }
        return null;
    }
}