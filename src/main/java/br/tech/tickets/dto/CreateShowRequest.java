package br.tech.tickets.dto;

import br.tech.tickets.domain.entity.Artist;

import java.time.LocalDateTime;

public record CreateShowRequest(Long id, Artist artist, String local, LocalDateTime date, Integer availableTickets) {


}
