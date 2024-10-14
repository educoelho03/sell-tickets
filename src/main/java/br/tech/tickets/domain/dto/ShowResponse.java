package br.tech.tickets.domain.dto;

import br.tech.tickets.domain.entity.Artist;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShowResponse(Artist artist, String local, LocalDate date, LocalTime horary, Double price) {
}
