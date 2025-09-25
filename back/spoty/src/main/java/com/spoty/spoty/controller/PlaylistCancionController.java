package com.spoty.spoty.controller;

import com.spoty.spoty.dto.CancionPlaylistDTO;
import com.spoty.spoty.dto.PlaylistSimpleDTO;
import com.spoty.spoty.dto.AgregarCancionPlaylistDTO;
import com.spoty.spoty.entity.PlaylistCancion;
import com.spoty.spoty.service.PlaylistCancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/playlist-canciones")
@CrossOrigin(origins = "*")
public class PlaylistCancionController {
    
    @Autowired
    private PlaylistCancionService playlistCancionService;
    
    @GetMapping("/playlist/{playlistId}")
    public ResponseEntity<List<CancionPlaylistDTO>> getCancionesByPlaylist(@PathVariable Long playlistId) {
        List<PlaylistCancion> playlistCanciones = playlistCancionService.getCancionesByPlaylist(playlistId);
        List<CancionPlaylistDTO> cancionesDTO = playlistCanciones.stream()
                .map(pc -> new CancionPlaylistDTO(pc.getCancion(), pc.getOrden()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(cancionesDTO);
    }
    
    @GetMapping("/cancion/{cancionId}")
    public ResponseEntity<List<PlaylistSimpleDTO>> getPlaylistsByCancion(@PathVariable Long cancionId) {
        List<PlaylistCancion> playlistCanciones = playlistCancionService.getPlaylistsByCancion(cancionId);
        List<PlaylistSimpleDTO> playlistsDTO = playlistCanciones.stream()
                .map(pc -> new PlaylistSimpleDTO(pc.getPlaylist()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(playlistsDTO);
    }
    
    @PostMapping
    public ResponseEntity<String> agregarCancionAPlaylist(@RequestBody AgregarCancionPlaylistDTO agregarDTO) {
        PlaylistCancion playlistCancion = playlistCancionService.agregarCancionAPlaylist(
            agregarDTO.getPlaylistId(), 
            agregarDTO.getCancionId(),
            agregarDTO.getOrden()
        );
        
        if (playlistCancion != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Canción agregada a la playlist exitosamente");
        }
        return ResponseEntity.badRequest().body("No se pudo agregar la canción a la playlist");
    }
    
    @DeleteMapping("/playlist/{playlistId}/cancion/{cancionId}")
    public ResponseEntity<String> removerCancionDePlaylist(
            @PathVariable Long playlistId, 
            @PathVariable Long cancionId) {
        boolean removida = playlistCancionService.removerCancionDePlaylist(playlistId, cancionId);
        
        if (removida) {
            return ResponseEntity.ok("Canción removida de la playlist exitosamente");
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/playlist/{playlistId}/cancion/{cancionId}/orden")
    public ResponseEntity<String> actualizarOrdenCancion(
            @PathVariable Long playlistId, 
            @PathVariable Long cancionId, 
            @RequestParam Integer nuevoOrden) {
        boolean actualizada = playlistCancionService.actualizarOrdenCancion(playlistId, cancionId, nuevoOrden);
        
        if (actualizada) {
            return ResponseEntity.ok("Orden de la canción actualizado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/count/playlist/{playlistId}")
    public ResponseEntity<Long> countCancionesByPlaylist(@PathVariable Long playlistId) {
        long count = playlistCancionService.countCancionesInPlaylist(playlistId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/exists/playlist/{playlistId}/cancion/{cancionId}")
    public ResponseEntity<Boolean> existeCancionEnPlaylist(
            @PathVariable Long playlistId, 
            @PathVariable Long cancionId) {
        boolean existe = playlistCancionService.existsCancionInPlaylist(playlistId, cancionId);
        return ResponseEntity.ok(existe);
    }
}