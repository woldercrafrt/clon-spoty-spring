package com.spoty.spoty.controller;

import com.spoty.spoty.dto.ComentarioDTO;
import com.spoty.spoty.dto.ComentarioRequestDTO;
import com.spoty.spoty.entity.Comentario;
import com.spoty.spoty.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {
    
    @Autowired
    private ComentarioService comentarioService;
    
    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> getAllComentarios() {
        List<Comentario> comentarios = comentarioService.getAllComentarios();
        List<ComentarioDTO> comentariosDTO = comentarios.stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comentariosDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> getComentarioById(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioService.getComentarioById(id);
        if (comentario.isPresent()) {
            return ResponseEntity.ok(new ComentarioDTO(comentario.get()));
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ComentarioDTO>> getComentariosByUsuario(@PathVariable Long usuarioId) {
        List<Comentario> comentarios = comentarioService.getComentariosByUsuario(usuarioId);
        List<ComentarioDTO> comentariosDTO = comentarios.stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comentariosDTO);
    }
    
    @GetMapping("/cancion/{cancionId}")
    public ResponseEntity<List<ComentarioDTO>> getComentariosByCancion(@PathVariable Long cancionId) {
        List<Comentario> comentarios = comentarioService.getComentariosByCancion(cancionId);
        List<ComentarioDTO> comentariosDTO = comentarios.stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comentariosDTO);
    }
    
    @GetMapping("/playlist/{playlistId}")
    public ResponseEntity<List<ComentarioDTO>> getComentariosByPlaylist(@PathVariable Long playlistId) {
        List<Comentario> comentarios = comentarioService.getComentariosByPlaylist(playlistId);
        List<ComentarioDTO> comentariosDTO = comentarios.stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comentariosDTO);
    }
    
    @GetMapping("/canciones/recientes")
    public ResponseEntity<List<ComentarioDTO>> getComentariosDeCancionesRecientes() {
        List<Comentario> comentarios = comentarioService.getComentariosDeCancionesRecientes();
        List<ComentarioDTO> comentariosDTO = comentarios.stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comentariosDTO);
    }
    
    @GetMapping("/playlists/recientes")
    public ResponseEntity<List<ComentarioDTO>> getComentariosDePlaylistsRecientes() {
        List<Comentario> comentarios = comentarioService.getComentariosDePlaylistsRecientes();
        List<ComentarioDTO> comentariosDTO = comentarios.stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comentariosDTO);
    }
    
    @PostMapping("/cancion")
    public ResponseEntity<ComentarioDTO> createComentarioCancion(@RequestBody ComentarioRequestDTO createDTO) {
        Comentario comentario = comentarioService.createComentarioCancion(
            createDTO.getTexto(), 
            createDTO.getUsuarioId(), 
            createDTO.getCancionId()
        );
        
        if (comentario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ComentarioDTO(comentario));
        }
        return ResponseEntity.badRequest().build();
    }
    
    @PostMapping("/playlist")
    public ResponseEntity<ComentarioDTO> createComentarioPlaylist(@RequestBody ComentarioRequestDTO createDTO) {
        Comentario comentario = comentarioService.createComentarioPlaylist(
            createDTO.getTexto(), 
            createDTO.getUsuarioId(), 
            createDTO.getPlaylistId()
        );
        
        if (comentario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ComentarioDTO(comentario));
        }
        return ResponseEntity.badRequest().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> updateComentario(
            @PathVariable Long id, 
            @RequestBody ComentarioRequestDTO updateDTO) {
        Comentario updatedComentario = comentarioService.updateComentario(id, updateDTO.getTexto());
        if (updatedComentario != null) {
            return ResponseEntity.ok(new ComentarioDTO(updatedComentario));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        if (comentarioService.deleteComentario(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/count/usuario/{usuarioId}")
    public ResponseEntity<Long> countComentariosByUsuario(@PathVariable Long usuarioId) {
        long count = comentarioService.countComentariosByUsuario(usuarioId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/count/cancion/{cancionId}")
    public ResponseEntity<Long> countComentariosByCancion(@PathVariable Long cancionId) {
        long count = comentarioService.countComentariosByCancion(cancionId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/count/playlist/{playlistId}")
    public ResponseEntity<Long> countComentariosByPlaylist(@PathVariable Long playlistId) {
        long count = comentarioService.countComentariosByPlaylist(playlistId);
        return ResponseEntity.ok(count);
    }
}