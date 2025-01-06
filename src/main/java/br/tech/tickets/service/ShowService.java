package br.tech.tickets.service;

import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.dto.ShowDTO;
import br.tech.tickets.mapper.ShowMapper;
import br.tech.tickets.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public void createShow(Show show) {
        if(show == null){
            throw new IllegalArgumentException("The show cannot be null");
        }

        showRepository.save(show);
    }

    public List<ShowDTO> consultShowsByArtist(Artist artist) {
        if(artist == null){
            throw new IllegalArgumentException("The artist cannot be null");
        }

        List<Show> shows = showRepository.findByArtist(artist);
        return shows.stream()
                .map(ShowMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ShowDTO> consultShowsByDate(LocalDateTime date) {
        if(date == null){
            throw new IllegalArgumentException("The date cannot be null");
        }

        List<Show> shows = showRepository.findByDate(date);
        return shows.stream()
                .map(ShowMapper::toDto)
                .collect(Collectors.toList());
    }
}
