package com.spoty.spoty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgregarCancionPlaylistDTO {
    private Long playlistId;
    private Long cancionId;
    private Integer orden;
}