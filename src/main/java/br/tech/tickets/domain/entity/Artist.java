package br.tech.tickets.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;
    private String name;
    private String musicalType;

    @OneToMany(mappedBy = "artist")
    private List<Show> shows;

    public Artist(Long artistId, String name, String musicalType) {
        this.artistId = artistId;
        this.name = name;
        this.musicalType = musicalType;
    }

    public Artist() {
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusicalType() {
        return musicalType;
    }

    public void setMusicalType(String musicalType) {
        this.musicalType = musicalType;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
