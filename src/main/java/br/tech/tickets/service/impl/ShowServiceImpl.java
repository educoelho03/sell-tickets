package br.tech.tickets.service.impl;

import br.tech.tickets.controller.dto.ShowDTO;
import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.domain.entity.User;
import br.tech.tickets.mapper.ShowMapper;
import br.tech.tickets.repository.ShowRepository;
import br.tech.tickets.service.interfaces.ShowInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowInterface {

    private final ShowRepository showRepository;

    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public void registerShow(Show show) {
        if(show == null){
            throw new IllegalArgumentException("The show cannot be null");
        }

        showRepository.save(show);
    }

    @Override
    public List<ShowDTO> consultShowsByArtist(Artist artist) {
        if(artist == null){
            throw new IllegalArgumentException("The artist cannot be null");
        }

        List<Show> shows = showRepository.findByArtist(artist);
        return shows.stream()
                .map(ShowMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> consultShowsByDate(LocalDateTime date) {
        if(date == null){
            throw new IllegalArgumentException("The date cannot be null");
        }

        List<Show> shows = showRepository.findByDate(date);
        return shows.stream()
                .map(ShowMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> consultShowsBoughtByUser(User user) {
        if(user == null){
            throw new IllegalArgumentException("The user cannot be null");
        }

        List<Show> shows = showRepository.findShowsBoughtByUsername(user.getUsername());
        return shows.stream()
                .map(ShowMapper::toDto)
                .collect(Collectors.toList());
    }
}
