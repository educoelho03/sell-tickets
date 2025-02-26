package br.tech.tickets.mapper;

import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.controller.dto.ArtistDTO;

public class ArtistMapper {
    public static Artist toEntity(ArtistDTO dto) {
        Artist artist = new Artist();
        artist.setArtistId(dto.getArtistId());
        artist.setName(dto.getArtistName());
        artist.setMusicalType(dto.getMusicalType());

        return artist;
    }

    public static ArtistDTO toDto(Artist artist) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setArtistId(artist.getArtistId());
        artistDTO.setArtistName(artist.getName());
        artistDTO.setMusicalType(artist.getMusicalType());

        return artistDTO;
    }
}
