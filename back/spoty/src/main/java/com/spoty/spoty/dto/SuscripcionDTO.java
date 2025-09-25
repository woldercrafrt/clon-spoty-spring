package com.spoty.spoty.dto;

import com.spoty.spoty.entity.Suscripcion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuscripcionDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private String beneficios;
    private Boolean activo;
    
    public SuscripcionDTO(Suscripcion suscripcion) {
        this.id = suscripcion.getId();
        this.nombre = suscripcion.getNombre();
        this.precio = suscripcion.getPrecio();
        this.beneficios = suscripcion.getBeneficios();
        this.activo = suscripcion.getActivo();
    }
}