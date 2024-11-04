package br.tech.tickets.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_show")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false) // Adiciona a chave estrangeira
    private Artist artist;

    private String local;
    private LocalDate date;
    private LocalTime horary;
    private double price;
    private Integer availableTickets;
    private Integer soldTickets;

    public Show(Artist artist, String local, LocalDate date, LocalTime horary, double price, Integer availableTickets, Integer soldTickets) {
        this.artist = artist;
        this.local = local;
        this.date = date;
        this.horary = horary;
        this.price = price;
        this.availableTickets = availableTickets;
        this.soldTickets = soldTickets;
    }

    public Show() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHorary() {
        return horary;
    }

    public void setHorary(LocalTime horary) {
        this.horary = horary;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(Integer maxTickets) {
        this.availableTickets = maxTickets;
    }

    public Integer getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Integer soldTickets) {
        this.soldTickets = soldTickets;
    }
}
