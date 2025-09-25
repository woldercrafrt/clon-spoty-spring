package com.spoty.spoty.controller;

import com.spoty.spoty.dto.HistorialReproduccionDTO;
import com.spoty.spoty.dto.HistorialReproduccionRequestDTO;
import com.spoty.spoty.dto.CancionSimpleDTO;
import com.spoty.spoty.entity.HistorialReproduccion;
import com.spoty.spoty.entity.Cancion;
import com.spoty.spoty.service.HistorialReproduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/historial")
@CrossOrigin(origins = "*")
public class HistorialReproduccionController {
    
    @Autowired
    private HistorialReproduccionService historialService;
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<HistorialReproduccionDTO>> getHistorialByUsuario(@PathVariable Long usuarioId) {
        List<HistorialReproduccion> historial = historialService.getHistorialByUsuario(usuarioId);
        List<HistorialReproduccionDTO> historialDTO = historial.stream()
                .map(HistorialReproduccionDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(historialDTO);
    }
    
    @GetMapping("/cancion/{cancionId}")
    public ResponseEntity<List<HistorialReproduccionDTO>> getHistorialByCancion(@PathVariable Long cancionId) {
        List<HistorialReproduccion> historial = historialService.getHistorialByCancion(cancionId);
        List<HistorialReproduccionDTO> historialDTO = historial.stream()
                .map(HistorialReproduccionDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(historialDTO);
    }
    
    @GetMapping("/usuario/{usuarioId}/rango")
    public ResponseEntity<List<HistorialReproduccionDTO>> getHistorialByUsuarioAndFechaRange(
            @PathVariable Long usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        List<HistorialReproduccion> historial = historialService.getHistorialByUsuarioAndFecha(
            usuarioId, fechaInicio, fechaFin);
        List<HistorialReproduccionDTO> historialDTO = historial.stream()
                .map(HistorialReproduccionDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(historialDTO);
    }
    
    @GetMapping("/usuario/{usuarioId}/top-canciones")
    public ResponseEntity<List<CancionSimpleDTO>> getTopCancionesByUsuario(
            @PathVariable Long usuarioId,
            @RequestParam(defaultValue = "10") int limit) {
        List<Object[]> topCanciones = historialService.getTopCancionesByUsuario(usuarioId);
        List<CancionSimpleDTO> resultado = topCanciones.stream()
                .map(arr -> {
                    Cancion cancion = (Cancion) arr[0];
                    Number reproducciones = (Number) arr[1];
                    CancionSimpleDTO dto = new CancionSimpleDTO(cancion);
                    dto.setReproducciones(reproducciones != null ? reproducciones.longValue() : 0L);
                    return dto;
                })
                .limit(Math.max(0, limit))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultado);
    }
    
    @PostMapping
    public ResponseEntity<HistorialReproduccionDTO> registrarReproduccion(
            @RequestBody HistorialReproduccionRequestDTO createDTO) {
        HistorialReproduccion historial = historialService.registrarReproduccion(
            createDTO.getUsuarioId(), 
            createDTO.getCancionId()
        );
        
        if (historial != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new HistorialReproduccionDTO(historial));
        }
        return ResponseEntity.badRequest().build();
    }
    
    @GetMapping("/count/usuario/{usuarioId}")
    public ResponseEntity<Long> countReproduccionesByUsuario(@PathVariable Long usuarioId) {
        long count = historialService.countReproduccionesByUsuario(usuarioId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/count/cancion/{cancionId}")
    public ResponseEntity<Long> countReproduccionesByCancion(@PathVariable Long cancionId) {
        long count = historialService.countReproduccionesByCancion(cancionId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/count/usuario/{usuarioId}/cancion/{cancionId}")
    public ResponseEntity<Long> countReproduccionesByUsuarioAndCancion(
            @PathVariable Long usuarioId, 
            @PathVariable Long cancionId) {
        long count = historialService.countReproduccionesByUsuarioAndCancion(usuarioId, cancionId);
        return ResponseEntity.ok(count);
    }
    
    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Void> limpiarHistorialUsuario(@PathVariable Long usuarioId) {
        historialService.limpiarHistorialUsuario(usuarioId);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/usuario/{usuarioId}/cancion/{cancionId}")
    public ResponseEntity<Void> eliminarHistorialUsuarioCancion(
            @PathVariable Long usuarioId, 
            @PathVariable Long cancionId) {
        historialService.eliminarHistorialUsuarioCancion(usuarioId, cancionId);
        return ResponseEntity.noContent().build();
    }
}