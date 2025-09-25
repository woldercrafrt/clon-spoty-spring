package com.spoty.spoty.service;

import com.spoty.spoty.entity.Artista;
import com.spoty.spoty.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {
    
    @Autowired
    private ArtistaRepository artistaRepository;
    
    public List<Artista> getAllArtistas() {
        return artistaRepository.findAll();
    }
    
    public Optional<Artista> getArtistaById(Long id) {
        return artistaRepository.findById(id);
    }
    
    public Optional<Artista> getArtistaByNombre(String nombre) {
        return artistaRepository.findByNombre(nombre);
    }
    
    public Artista saveArtista(Artista artista) {
        return artistaRepository.save(artista);
    }
    
    public Artista updateArtista(Long id, Artista artistaDetails) {
        Optional<Artista> optionalArtista = artistaRepository.findById(id);
        if (optionalArtista.isPresent()) {
            Artista artista = optionalArtista.get();
            artista.setNombre(artistaDetails.getNombre());
            artista.setBiografia(artistaDetails.getBiografia());
            artista.setPais(artistaDetails.getPais());
            artista.setFotoPerfil(artistaDetails.getFotoPerfil());
            return artistaRepository.save(artista);
        }
        return null;
    }
    
    public boolean deleteArtista(Long id) {
        if (artistaRepository.existsById(id)) {
            artistaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public boolean existsByNombre(String nombre) {
        return artistaRepository.existsByNombre(nombre);
    }
    
    public List<Artista> getArtistasByPais(String pais) {
        return artistaRepository.findByPais(pais);
    }
    
    public List<Artista> searchArtistasByNombre(String nombre) {
        return artistaRepository.findByNombreContaining(nombre);
    }
    
    public List<Artista> getArtistasByPaisOrderByNombre(String pais) {
        return artistaRepository.findByPaisOrderByNombre(pais);
    }
    
    public long countArtistasByPais(String pais) {
        return artistaRepository.countByPais(pais);
    }
}