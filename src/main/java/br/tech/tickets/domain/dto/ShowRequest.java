package br.tech.tickets.domain.dto;

import br.tech.tickets.domain.entity.Artist;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ShowRequest(Long id, Artist artist, String local, LocalDateTime date, Integer availableTickets, Integer soldTickets) {


}
