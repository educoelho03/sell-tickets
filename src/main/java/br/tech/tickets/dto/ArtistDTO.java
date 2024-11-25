package br.tech.tickets.dto;

import br.tech.tickets.domain.entity.Artist;

public class ArtistDTO {
    private Long artistId;
    private String name;
    private String musicalType;

    public Artist toObject(){
        return new Artist(
                artistId,
                name,
                musicalType
        );
    }
}


