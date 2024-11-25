package br.tech.tickets.dto;

import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;

import java.time.LocalDateTime;

public class ShowDTO {
    private Long showId;
    private Artist artist;
    private String local;
    private LocalDateTime date;
    private Integer availableTickets;


    public Show toObject(){
        return new Show(
                showId,
                artist,
                local,
                date,
                availableTickets
        );
    }


}
