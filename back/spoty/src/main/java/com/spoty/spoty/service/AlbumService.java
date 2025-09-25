package com.spoty.spoty.service;

import com.spoty.spoty.entity.Album;
import com.spoty.spoty.entity.Artista;
import com.spoty.spoty.repository.AlbumRepository;
import com.spoty.spoty.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    
    @Autowired
    private AlbumRepository albumRepository;
    
    @Autowired
    private ArtistaRepository artistaRepository;
    
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }
    
    public Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }
    
    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }
    
    public Album updateAlbum(Long id, Album albumDetails) {
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        if (optionalAlbum.isPresent()) {
            Album album = optionalAlbum.get();
            album.setTitulo(albumDetails.getTitulo());
            album.setFechaLanzamiento(albumDetails.getFechaLanzamiento());
            album.setPortada(albumDetails.getPortada());
            
            // Actualizar la relación con artista si es necesario
            if (albumDetails.getArtista() != null) {
                album.setArtista(albumDetails.getArtista());
            }
            
            return albumRepository.save(album);
        }
        return null;
    }
    
    public boolean deleteAlbum(Long id) {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<Album> getAlbumsByArtista(Artista artista) {
        return albumRepository.findByArtista(artista);
    }
    
    public List<Album> getAlbumsByArtistaId(Long artistaId) {
        return albumRepository.findByIdArtista(artistaId);
    }
    
    public List<Album> getAlbumsByTitulo(String titulo) {
        return albumRepository.findByTitulo(titulo);
    }
    
    public List<Album> getAlbumsByArtistaIdOrderByFecha(Long artistaId) {
        return albumRepository.findByArtistaIdOrderByFechaDesc(artistaId);
    }
    
    public List<Album> getAllAlbumsOrderByFecha() {
        return albumRepository.findAllOrderByFechaDesc();
    }
    
    public long countAlbumsByArtista(Long artistaId) {
        return albumRepository.countByArtistaId(artistaId);
    }
}