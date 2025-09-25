package com.spoty.spoty.controller;

import com.spoty.spoty.dto.LikeCancionDTO;
import com.spoty.spoty.dto.LikeCancionRequestDTO;
import com.spoty.spoty.entity.LikeCancion;
import com.spoty.spoty.service.LikeCancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "*")
public class LikeCancionController {
    
    @Autowired
    private LikeCancionService likeCancionService;
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<LikeCancionDTO>> getLikesByUsuario(@PathVariable Long usuarioId) {
        List<LikeCancion> likes = likeCancionService.getLikesByUsuario(usuarioId);
        List<LikeCancionDTO> likesDTO = likes.stream()
                .map(LikeCancionDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(likesDTO);
    }
    
    @GetMapping("/cancion/{cancionId}")
    public ResponseEntity<List<LikeCancionDTO>> getLikesByCancion(@PathVariable Long cancionId) {
        List<LikeCancion> likes = likeCancionService.getLikesByCancion(cancionId);
        List<LikeCancionDTO> likesDTO = likes.stream()
                .map(LikeCancionDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(likesDTO);
    }
    
    @PostMapping
    public ResponseEntity<LikeCancionDTO> darLike(@RequestBody LikeCancionRequestDTO createDTO) {
        LikeCancion like = likeCancionService.darLike(createDTO.getUsuarioId(), createDTO.getCancionId());
        
        if (like != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new LikeCancionDTO(like));
        }
        return ResponseEntity.badRequest().build();
    }
    
    @DeleteMapping("/usuario/{usuarioId}/cancion/{cancionId}")
    public ResponseEntity<Void> quitarLike(@PathVariable Long usuarioId, @PathVariable Long cancionId) {
        boolean removido = likeCancionService.quitarLike(usuarioId, cancionId);
        
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/toggle")
    public ResponseEntity<String> toggleLike(@RequestBody LikeCancionRequestDTO createDTO) {
        boolean liked = likeCancionService.toggleLike(createDTO.getUsuarioId(), createDTO.getCancionId());
        
        String message = liked ? "Like agregado" : "Like removido";
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("/exists/usuario/{usuarioId}/cancion/{cancionId}")
    public ResponseEntity<Boolean> usuarioHaDadoLike(
            @PathVariable Long usuarioId, 
            @PathVariable Long cancionId) {
        boolean hasLiked = likeCancionService.hasLike(usuarioId, cancionId);
        return ResponseEntity.ok(hasLiked);
    }
    
    @GetMapping("/count/usuario/{usuarioId}")
    public ResponseEntity<Long> countLikesByUsuario(@PathVariable Long usuarioId) {
        long count = likeCancionService.countLikesByUsuario(usuarioId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/count/cancion/{cancionId}")
    public ResponseEntity<Long> countLikesByCancion(@PathVariable Long cancionId) {
        long count = likeCancionService.countLikesByCancion(cancionId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/top-canciones")
    public ResponseEntity<List<Object[]>> getTopCancionesConMasLikes() {
        List<Object[]> topCanciones = likeCancionService.getTopCancionesConMasLikes();
        return ResponseEntity.ok(topCanciones);
    }
}