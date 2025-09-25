package com.spoty.spoty.controller;

import com.spoty.spoty.entity.Artista;
import com.spoty.spoty.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artistas")
@CrossOrigin(origins = "*")
public class ArtistaController {
    
    @Autowired
    private ArtistaService artistaService;
    
    @GetMapping
    public ResponseEntity<List<Artista>> getAllArtistas() {
        try {
            List<Artista> artistas = artistaService.getAllArtistas();
            return new ResponseEntity<>(artistas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Artista> getArtistaById(@PathVariable Long id) {
        Optional<Artista> artista = artistaService.getArtistaById(id);
        if (artista.isPresent()) {
            return new ResponseEntity<>(artista.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Artista> getArtistaByNombre(@PathVariable String nombre) {
        Optional<Artista> artista = artistaService.getArtistaByNombre(nombre);
        if (artista.isPresent()) {
            return new ResponseEntity<>(artista.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<Artista> createArtista(@RequestBody Artista artista) {
        try {
            if (artistaService.existsByNombre(artista.getNombre())) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            Artista savedArtista = artistaService.saveArtista(artista);
            return new ResponseEntity<>(savedArtista, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Artista> updateArtista(@PathVariable Long id, @RequestBody Artista artista) {
        Artista updatedArtista = artistaService.updateArtista(id, artista);
        if (updatedArtista != null) {
            return new ResponseEntity<>(updatedArtista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteArtista(@PathVariable Long id) {
        try {
            if (artistaService.deleteArtista(id)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/pais/{pais}")
    public ResponseEntity<List<Artista>> getArtistasByPais(@PathVariable String pais) {
        try {
            List<Artista> artistas = artistaService.getArtistasByPais(pais);
            return new ResponseEntity<>(artistas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/search/{nombre}")
    public ResponseEntity<List<Artista>> searchArtistasByNombre(@PathVariable String nombre) {
        try {
            List<Artista> artistas = artistaService.searchArtistasByNombre(nombre);
            return new ResponseEntity<>(artistas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/pais/{pais}/ordenado")
    public ResponseEntity<List<Artista>> getArtistasByPaisOrdenado(@PathVariable String pais) {
        try {
            List<Artista> artistas = artistaService.getArtistasByPaisOrderByNombre(pais);
            return new ResponseEntity<>(artistas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/count/pais/{pais}")
    public ResponseEntity<Long> countArtistasByPais(@PathVariable String pais) {
        try {
            long count = artistaService.countArtistasByPais(pais);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}