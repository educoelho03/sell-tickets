package br.tech.tickets.service;

import br.tech.tickets.dto.CreateShowResponse;
import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;
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

    public Show registerShow(Show show){
        return showRepository.save(show);
    }

    public List<Show> consultShowsByArtist(Artist artist) {
        return showRepository.findByArtist(artist);
    }

    public List<CreateShowResponse> consultShowsByDate(LocalDateTime date) {
        List<Show> shows = showRepository.findByDate(date);

        return shows.stream().map((show) -> new CreateShowResponse(
                show.getArtist(),
                show.getLocal(),
                show.getDate(),
                show.getAvailableTickets()
        )).collect(Collectors.toList());
    }
}
