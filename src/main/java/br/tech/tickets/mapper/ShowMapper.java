package br.tech.tickets.mapper;

import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.controller.dto.ShowDTO;

public class ShowMapper {

    public static Show toEntity(ShowDTO dto) {
        Show show = new Show();
        show.setShowName(dto.getShowName());
        show.setArtist(ArtistMapper.toEntity(dto.getArtist()));
        show.setLocal(dto.getLocal());
        show.setDate(dto.getDate());
        show.setAvailableTickets(dto.getAvailableTickets());

        return show;
    }


    public static ShowDTO toDto(Show show) {
        ShowDTO dto = new ShowDTO();
        dto.setShowName(show.getShowName());
        dto.setArtist(ArtistMapper.toDto(show.getArtist()));
        dto.setLocal(show.getLocal());
        dto.setDate(show.getDate());
        dto.setAvailableTickets(show.getAvailableTickets());

        return dto;
    }
}
