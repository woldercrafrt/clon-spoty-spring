package com.spoty.spoty.controller;

import com.spoty.spoty.entity.Cancion;
import com.spoty.spoty.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/canciones")
@CrossOrigin(origins = "*")
public class CancionController {
    
    @Autowired
    private CancionService cancionService;
    
    @GetMapping
    public ResponseEntity<List<Cancion>> getAllCanciones() {
        try {
            List<Cancion> canciones = cancionService.getAllCanciones();
            return new ResponseEntity<>(canciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cancion> getCancionById(@PathVariable Long id) {
        Optional<Cancion> cancion = cancionService.getCancionById(id);
        if (cancion.isPresent()) {
            return new ResponseEntity<>(cancion.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<Cancion> createCancion(@RequestBody Cancion cancion) {
        try {
            Cancion savedCancion = cancionService.saveCancion(cancion);
            return new ResponseEntity<>(savedCancion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cancion> updateCancion(@PathVariable Long id, @RequestBody Cancion cancion) {
        Cancion updatedCancion = cancionService.updateCancion(id, cancion);
        if (updatedCancion != null) {
            return new ResponseEntity<>(updatedCancion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCancion(@PathVariable Long id) {
        try {
            if (cancionService.deleteCancion(id)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/album/{albumId}")
    public ResponseEntity<List<Cancion>> getCancionesByAlbumId(@PathVariable Long albumId) {
        try {
            List<Cancion> canciones = cancionService.getCancionesByAlbumId(albumId);
            return new ResponseEntity<>(canciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Cancion>> getCancionesByTitulo(@PathVariable String titulo) {
        try {
            List<Cancion> canciones = cancionService.getCancionesByTitulo(titulo);
            return new ResponseEntity<>(canciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/populares")
    public ResponseEntity<List<Cancion>> getCancionesPopulares() {
        try {
            List<Cancion> canciones = cancionService.getCancionesOrderByReproducciones();
            return new ResponseEntity<>(canciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/album/{albumId}/ordenado")
    public ResponseEntity<List<Cancion>> getCancionesByAlbumOrdenado(@PathVariable Long albumId) {
        try {
            List<Cancion> canciones = cancionService.getCancionesByAlbumId(albumId);
            return new ResponseEntity<>(canciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/populares/{minReproducciones}")
    public ResponseEntity<List<Cancion>> getCancionesConMinReproducciones(@PathVariable Integer minReproducciones) {
        try {
            List<Cancion> canciones = cancionService.getCancionesPopulares(minReproducciones);
            return new ResponseEntity<>(canciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/count/album/{albumId}")
    public ResponseEntity<Long> countCancionesByAlbum(@PathVariable Long albumId) {
        try {
            long count = cancionService.countCancionesByAlbum(albumId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/reproducciones/album/{albumId}")
    public ResponseEntity<Long> getTotalReproduccionesByAlbum(@PathVariable Long albumId) {
        try {
            Long totalReproducciones = cancionService.getTotalReproduccionesByAlbum(albumId);
            return new ResponseEntity<>(totalReproducciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/{id}/reproducir")
    public ResponseEntity<Cancion> reproducirCancion(@PathVariable Long id) {
        try {
            Cancion cancion = cancionService.incrementarReproducciones(id);
            if (cancion != null) {
                return new ResponseEntity<>(cancion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}