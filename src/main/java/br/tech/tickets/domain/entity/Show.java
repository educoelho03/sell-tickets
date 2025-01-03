package br.tech.tickets.domain.entity;

import jakarta.persistence.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_show")
@Accessors(chain = true)
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
    private String local;
    private LocalDateTime date;
    private Integer availableTickets;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "show")
    private List<Seat> seats;


    public Show(Long showId, Artist artist, String local, LocalDateTime date, Integer availableTickets) {
        this.showId = showId;
        this.artist = artist;
        this.local = local;
        this.date = date;
        this.availableTickets = availableTickets;
    }

    public Show() {
    }

    public Show(Artist artist, String local, LocalDateTime date, Integer availableTickets) {
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    public void setAvailableTickets(Integer maxTickets) {
        this.availableTickets = maxTickets;
    }

    public List<Ticket> getTicket() {
        return tickets;
    }

    public void setTicket(List<Ticket> ticket) {
        this.tickets = ticket;
    }

    public List<Seat> getSeat() {
        return seats;
    }

    public void setSeat(List<Seat> seat) {
        this.seats = seat;
    }
}
