package br.tech.tickets.dto;

import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ShowResponseDTO {
    private Artist artist;
    private String local;
    private LocalDateTime date;
    private Integer availableTickets;

    // Construtor para inicializar os campos
    public ShowResponseDTO(Artist artist, String local, LocalDateTime date, Integer availableTickets) {
        this.artist = artist;
        this.local = local;
        this.date = date;
        this.availableTickets = availableTickets;
    }

    public static ShowResponseDTO toDto(Show show) {
        return new ShowResponseDTO(
                show.getArtist(),
                show.getLocal(),
                show.getDate(),
                show.getAvailableTickets()
        );
    }

}


