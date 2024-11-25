package br.tech.tickets.dto;

import br.tech.tickets.domain.entity.Artist;

public class ArtistResponseDTO {
    private String name;
    private String musicalType;

    public ArtistResponseDTO(String name, String musicalType) {
        this.name = name;
        this.musicalType = musicalType;
    }

    public static ArtistResponseDTO toDTO(Artist artist){
        return new ArtistResponseDTO(
                artist.getName(),
                artist.getMusicalType()
        );
    }
}
