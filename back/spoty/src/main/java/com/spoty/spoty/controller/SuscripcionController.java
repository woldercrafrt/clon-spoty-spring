package com.spoty.spoty.controller;

import com.spoty.spoty.dto.SuscripcionDTO;
import com.spoty.spoty.dto.SuscripcionRequestDTO;
import com.spoty.spoty.entity.Suscripcion;
import com.spoty.spoty.service.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/suscripciones")
@CrossOrigin(origins = "*")
public class SuscripcionController {
    
    @Autowired
    private SuscripcionService suscripcionService;
    
    @GetMapping
    public ResponseEntity<List<SuscripcionDTO>> getAllSuscripciones() {
        List<Suscripcion> suscripciones = suscripcionService.getAllSuscripciones();
        List<SuscripcionDTO> suscripcionesDTO = suscripciones.stream()
                .map(SuscripcionDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(suscripcionesDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SuscripcionDTO> getSuscripcionById(@PathVariable Long id) {
        Optional<Suscripcion> suscripcion = suscripcionService.getSuscripcionById(id);
        if (suscripcion.isPresent()) {
            return ResponseEntity.ok(new SuscripcionDTO(suscripcion.get()));
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<SuscripcionDTO> getSuscripcionByNombre(@PathVariable String nombre) {
        Optional<Suscripcion> suscripcion = suscripcionService.getSuscripcionByNombre(nombre);
        if (suscripcion.isPresent()) {
            return ResponseEntity.ok(new SuscripcionDTO(suscripcion.get()));
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/activas")
    public ResponseEntity<List<SuscripcionDTO>> getSuscripcionesActivas() {
        List<Suscripcion> suscripciones = suscripcionService.getSuscripcionesByActivo(true);
        List<SuscripcionDTO> suscripcionesDTO = suscripciones.stream()
                .map(SuscripcionDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(suscripcionesDTO);
    }
    
    @GetMapping("/inactivas")
    public ResponseEntity<List<SuscripcionDTO>> getSuscripcionesInactivas() {
        List<Suscripcion> suscripciones = suscripcionService.getSuscripcionesByActivo(false);
        List<SuscripcionDTO> suscripcionesDTO = suscripciones.stream()
                .map(SuscripcionDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(suscripcionesDTO);
    }
    
    @GetMapping("/precio-rango")
    public ResponseEntity<List<SuscripcionDTO>> getSuscripcionesByPrecioRange(
            @RequestParam BigDecimal precioMin,
            @RequestParam BigDecimal precioMax) {
        List<Suscripcion> suscripciones = suscripcionService.getSuscripcionesByPrecioRange(precioMin, precioMax);
        List<SuscripcionDTO> suscripcionesDTO = suscripciones.stream()
                .map(SuscripcionDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(suscripcionesDTO);
    }
    
    @PostMapping
    public ResponseEntity<SuscripcionDTO> createSuscripcion(@RequestBody SuscripcionRequestDTO createDTO) {
        // Verificar si ya existe una suscripción con ese nombre
        if (suscripcionService.existsByNombre(createDTO.getNombre())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setNombre(createDTO.getNombre());
        suscripcion.setPrecio(createDTO.getPrecio());
        suscripcion.setBeneficios(createDTO.getBeneficios());
        suscripcion.setActivo(createDTO.isActivo());
        
        Suscripcion savedSuscripcion = suscripcionService.saveSuscripcion(suscripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuscripcionDTO(savedSuscripcion));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SuscripcionDTO> updateSuscripcion(
            @PathVariable Long id, 
            @RequestBody SuscripcionRequestDTO updateDTO) {
        Suscripcion suscripcionDetails = new Suscripcion();
        suscripcionDetails.setNombre(updateDTO.getNombre());
        suscripcionDetails.setPrecio(updateDTO.getPrecio());
        suscripcionDetails.setBeneficios(updateDTO.getBeneficios());
        suscripcionDetails.setActivo(updateDTO.isActivo());
        
        Suscripcion updatedSuscripcion = suscripcionService.updateSuscripcion(id, suscripcionDetails);
        if (updatedSuscripcion != null) {
            return ResponseEntity.ok(new SuscripcionDTO(updatedSuscripcion));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuscripcion(@PathVariable Long id) {
        if (suscripcionService.deleteSuscripcion(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/activar")
    public ResponseEntity<SuscripcionDTO> activarSuscripcion(@PathVariable Long id) {
        Suscripcion suscripcion = suscripcionService.activarSuscripcion(id);
        if (suscripcion != null) {
            return ResponseEntity.ok(new SuscripcionDTO(suscripcion));
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<SuscripcionDTO> desactivarSuscripcion(@PathVariable Long id) {
        Suscripcion suscripcion = suscripcionService.desactivarSuscripcion(id);
        if (suscripcion != null) {
            return ResponseEntity.ok(new SuscripcionDTO(suscripcion));
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/exists/nombre/{nombre}")
    public ResponseEntity<Boolean> existsByNombre(@PathVariable String nombre) {
        boolean exists = suscripcionService.existsByNombre(nombre);
        return ResponseEntity.ok(exists);
    }
    
    @GetMapping("/count/activas")
    public ResponseEntity<Long> countSuscripcionesActivas() {
        long count = suscripcionService.countSuscripcionesByActivo(true);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/count/inactivas")
    public ResponseEntity<Long> countSuscripcionesInactivas() {
        long count = suscripcionService.countSuscripcionesByActivo(false);
        return ResponseEntity.ok(count);
    }
}