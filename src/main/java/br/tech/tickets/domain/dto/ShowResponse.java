package br.tech.tickets.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShowResponse(String artist, String local, LocalDate date, LocalTime horary, Double price) {
}
