package com.spoty.spoty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuscripcionRequestDTO {
    private String nombre;
    private BigDecimal precio;
    private String beneficios;
    private boolean activo = true;
}