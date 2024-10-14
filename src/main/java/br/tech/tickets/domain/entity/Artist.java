package br.tech.tickets.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artist_id;
    private String name;
    private String musicalType;

    @OneToMany(mappedBy = "artist")
    private List<Show> shows;

    public Long getId() {
        return artist_id;
    }

    public void setId(Long id) {
        this.artist_id = id;
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
