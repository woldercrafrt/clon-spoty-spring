package com.spoty.spoty.controller;

import com.spoty.spoty.dto.PlaylistDTO;
import com.spoty.spoty.dto.PlaylistSimpleDTO;
import com.spoty.spoty.dto.PlaylistRequestDTO;
import com.spoty.spoty.entity.Playlist;
import com.spoty.spoty.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/playlists")
@CrossOrigin(origins = "*")
public class PlaylistController {
    
    @Autowired
    private PlaylistService playlistService;
    
    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylists() {
        List<Playlist> playlists = playlistService.getAllPlaylists();
        List<PlaylistDTO> playlistsDTO = playlists.stream()
                .map(PlaylistDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(playlistsDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> getPlaylistById(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistService.getPlaylistById(id);
        if (playlist.isPresent()) {
            return ResponseEntity.ok(new PlaylistDTO(playlist.get()));
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PlaylistSimpleDTO>> getPlaylistsByUsuario(@PathVariable Long usuarioId) {
        List<Playlist> playlists = playlistService.getPlaylistsByUsuario(usuarioId);
        List<PlaylistSimpleDTO> playlistsDTO = playlists.stream()
                .map(PlaylistSimpleDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(playlistsDTO);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PlaylistSimpleDTO>> searchPlaylistsByNombre(@RequestParam String nombre) {
        List<Playlist> playlists = playlistService.searchPlaylistsByNombre(nombre);
        List<PlaylistSimpleDTO> playlistsDTO = playlists.stream()
                .map(PlaylistSimpleDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(playlistsDTO);
    }
    
    @PostMapping
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistRequestDTO playlistCreateDTO) {
        Playlist savedPlaylist = playlistService.createPlaylist(
            playlistCreateDTO.getNombre(),
            playlistCreateDTO.getDescripcion(),
            playlistCreateDTO.getUsuarioId()
        );
        
        if (savedPlaylist != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new PlaylistDTO(savedPlaylist));
        }
        return ResponseEntity.badRequest().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PlaylistDTO> updatePlaylist(@PathVariable Long id, @RequestBody PlaylistRequestDTO playlistUpdateDTO) {
        Playlist playlistDetails = new Playlist();
        playlistDetails.setNombre(playlistUpdateDTO.getNombre());
        playlistDetails.setDescripcion(playlistUpdateDTO.getDescripcion());
        
        Playlist updatedPlaylist = playlistService.updatePlaylist(id, playlistDetails);
        if (updatedPlaylist != null) {
            return ResponseEntity.ok(new PlaylistDTO(updatedPlaylist));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        if (playlistService.deletePlaylist(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/count/usuario/{usuarioId}")
    public ResponseEntity<Long> countPlaylistsByUsuario(@PathVariable Long usuarioId) {
        long count = playlistService.countPlaylistsByUsuario(usuarioId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        boolean exists = playlistService.existsById(id);
        return ResponseEntity.ok(exists);
    }
}