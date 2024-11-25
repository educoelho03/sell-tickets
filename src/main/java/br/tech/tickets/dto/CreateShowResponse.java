package br.tech.tickets.dto;

import br.tech.tickets.domain.entity.Artist;

import java.time.LocalDateTime;

public record CreateShowResponse(Artist artist, String local, LocalDateTime date, Integer availableTickets) {
}
