package br.tech.tickets.controller.dto;

public class ArtistDTO {

    private Long artistId;
    private String artistName;
    private String musicalType;

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getMusicalType() {
        return musicalType;
    }

    public void setMusicalType(String musicalType) {
        this.musicalType = musicalType;
    }
}
