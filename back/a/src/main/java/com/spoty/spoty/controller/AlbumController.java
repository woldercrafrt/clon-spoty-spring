package com.spoty.spoty.controller;

import com.spoty.spoty.entity.Album;
import com.spoty.spoty.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/albums")
@CrossOrigin(origins = "*")
public class AlbumController {
    
    @Autowired
    private AlbumService albumService;
    
    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        try {
            List<Album> albums = albumService.getAllAlbums();
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Optional<Album> album = albumService.getAlbumById(id);
        if (album.isPresent()) {
            return new ResponseEntity<>(album.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        try {
            Album savedAlbum = albumService.saveAlbum(album);
            return new ResponseEntity<>(savedAlbum, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        Album updatedAlbum = albumService.updateAlbum(id, album);
        if (updatedAlbum != null) {
            return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAlbum(@PathVariable Long id) {
        try {
            if (albumService.deleteAlbum(id)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/artista/{artistaId}")
    public ResponseEntity<List<Album>> getAlbumsByArtistaId(@PathVariable Long artistaId) {
        try {
            List<Album> albums = albumService.getAlbumsByArtistaId(artistaId);
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Album>> getAlbumsByTitulo(@PathVariable Long titulo) {
        try {
            List<Album> albums = albumService.getAlbumsByTitulo(titulo);
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/artista/{artistaId}/ordenado")
    public ResponseEntity<List<Album>> getAlbumsByArtistaOrdenado(@PathVariable Long artistaId) {
        try {
            List<Album> albums = albumService.getAlbumsByArtistaIdOrderByFecha(artistaId);
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/recientes")
    public ResponseEntity<List<Album>> getAlbumsRecientes() {
        try {
            List<Album> albums = albumService.getAllAlbumsOrderByFecha();
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/count/artista/{artistaId}")
    public ResponseEntity<Long> countAlbumsByArtista(@PathVariable Long artistaId) {
        try {
            long count = albumService.countAlbumsByArtista(artistaId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}