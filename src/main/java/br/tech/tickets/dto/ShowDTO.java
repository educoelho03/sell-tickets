package br.tech.tickets.dto;

import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;

import java.time.LocalDateTime;

public class ShowDTO {

    private Long artistId;
    private String local;
    private LocalDateTime date;
    private Integer availableTickets;

    // Getters e Setters
    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(Integer availableTickets) {
        this.availableTickets = availableTickets;
    }

    // method para converter ShowDTO em Show (para persistência)
    public Show toEntity(Artist artist) {
        return new Show(artist, local, date, availableTickets);
    }

    // method estático para converter Show em ShowDTO (para resposta)
    public static ShowDTO toDto(Show show) {
        ShowDTO dto = new ShowDTO();
        dto.setArtistId(show.getArtist().getArtistId());  // Supondo que Show tem um campo `artist`
        dto.setLocal(show.getLocal());
        dto.setDate(show.getDate());
        dto.setAvailableTickets(show.getAvailableTickets());
        return dto;
    }
}
