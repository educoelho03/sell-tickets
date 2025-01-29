package br.tech.tickets.service.interfaces;

import br.tech.tickets.controller.dto.ShowDTO;
import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.domain.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowInterface {

    void registerShow(Show show);
    List<ShowDTO> consultShowsByArtist(Artist artist);
    List<ShowDTO> consultShowsByDate(LocalDateTime date);
    List<ShowDTO> consultShowsBoughtByUser(User user);

}
