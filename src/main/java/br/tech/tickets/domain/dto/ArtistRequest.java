package br.tech.tickets.domain.dto;

import br.tech.tickets.domain.entity.Show;

import java.util.List;

public record ArtistRequest(
        Long artist_id,
        String name,
        String musicalType,
        List<ShowRequest> shows
) {



}
