package br.tech.tickets.domain.dto;

import java.util.List;

public record ArtistResponse(
        String name,
        String musicalType,
        List<ShowResponse> shows
) {
}
