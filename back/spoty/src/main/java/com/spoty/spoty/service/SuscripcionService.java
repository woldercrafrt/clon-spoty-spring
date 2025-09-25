package com.spoty.spoty.service;

import com.spoty.spoty.entity.Suscripcion;
import com.spoty.spoty.repository.SuscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class SuscripcionService {
    
    @Autowired
    private SuscripcionRepository suscripcionRepository;
    
    public List<Suscripcion> getAllSuscripciones() {
        return suscripcionRepository.findAll();
    }
    
    public Optional<Suscripcion> getSuscripcionById(Long id) {
        return suscripcionRepository.findById(id);
    }
    
    public Optional<Suscripcion> getSuscripcionByNombre(String nombre) {
        return suscripcionRepository.findByNombre(nombre);
    }
    
    public List<Suscripcion> getSuscripcionesActivas() {
        return suscripcionRepository.findActivasOrderByPrecio();
    }
    
    public List<Suscripcion> getSuscripcionesByActivo(Boolean activo) {
        return suscripcionRepository.findByActivo(activo);
    }
    
    public List<Suscripcion> getSuscripcionesByPrecioRange(BigDecimal precioMin, BigDecimal precioMax) {
        return suscripcionRepository.findByPrecioBetween(precioMin, precioMax);
    }
    
    public List<Suscripcion> getSuscripcionesByPrecioMaximo(BigDecimal maxPrecio) {
        return suscripcionRepository.findByPrecioLessThanEqualOrderByPrecio(maxPrecio);
    }
    
    public List<Suscripcion> getSuscripcionesByRangoPrecio(BigDecimal minPrecio, BigDecimal maxPrecio) {
        return suscripcionRepository.findByPrecioBetween(minPrecio, maxPrecio);
    }
    
    public Suscripcion saveSuscripcion(Suscripcion suscripcion) {
        return suscripcionRepository.save(suscripcion);
    }
    
    public Suscripcion updateSuscripcion(Long id, Suscripcion suscripcionDetails) {
        Optional<Suscripcion> optionalSuscripcion = suscripcionRepository.findById(id);
        if (optionalSuscripcion.isPresent()) {
            Suscripcion suscripcion = optionalSuscripcion.get();
            suscripcion.setNombre(suscripcionDetails.getNombre());
            suscripcion.setPrecio(suscripcionDetails.getPrecio());
            suscripcion.setBeneficios(suscripcionDetails.getBeneficios());
            suscripcion.setActivo(suscripcionDetails.getActivo());
            return suscripcionRepository.save(suscripcion);
        }
        return null;
    }
    
    public boolean deleteSuscripcion(Long id) {
        if (suscripcionRepository.existsById(id)) {
            suscripcionRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public boolean existsByNombre(String nombre) {
        return suscripcionRepository.existsByNombre(nombre);
    }
    
    public long countSuscripcionesByActivo(Boolean activo) {
        return suscripcionRepository.countByActivo(activo);
    }
    
    public Suscripcion activarSuscripcion(Long id) {
        Optional<Suscripcion> optionalSuscripcion = suscripcionRepository.findById(id);
        if (optionalSuscripcion.isPresent()) {
            Suscripcion suscripcion = optionalSuscripcion.get();
            suscripcion.setActivo(true);
            return suscripcionRepository.save(suscripcion);
        }
        return null;
    }
    
    public Suscripcion desactivarSuscripcion(Long id) {
        Optional<Suscripcion> optionalSuscripcion = suscripcionRepository.findById(id);
        if (optionalSuscripcion.isPresent()) {
            Suscripcion suscripcion = optionalSuscripcion.get();
            suscripcion.setActivo(false);
            return suscripcionRepository.save(suscripcion);
        }
        return null;
    }
}