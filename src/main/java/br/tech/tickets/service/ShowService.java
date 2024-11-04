package br.tech.tickets.service;

import br.tech.tickets.domain.dto.ShowResponse;
import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.domain.dto.ShowRequest;
import br.tech.tickets.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public ShowResponse registerShow(ShowRequest showRequest){
        Show newShow = new Show();
        newShow.setArtist(showRequest.artist());
        newShow.setLocal(showRequest.local());
        newShow.setDate(showRequest.date());
        newShow.setHorary(showRequest.horary());
        newShow.setPrice(showRequest.price());
        newShow.setAvailableTickets(showRequest.availableTickets());

        Show savedShow = showRepository.save(newShow);

        return new ShowResponse(
                savedShow.getArtist(),
                savedShow.getLocal(),
                savedShow.getDate(),
                savedShow.getHorary(),
                savedShow.getPrice(),
                savedShow.getAvailableTickets(),
                savedShow.getSoldTickets()
        );
    }

    public List<ShowResponse> consultShowsByArtist(Artist artist){
        List<Show> shows = showRepository.findByArtist(artist);

        return shows.stream().map((show) -> new ShowResponse(
                show.getArtist(),
                show.getLocal(),
                show.getDate(),
                show.getHorary(),
                show.getPrice(),
                show.getAvailableTickets(),
                show.getSoldTickets()
        )).collect(Collectors.toList());
    }

    public List<ShowResponse> consultShowsByDate(LocalDate date) {
        List<Show> shows = showRepository.findByDate(date);

        return shows.stream().map((show) -> new ShowResponse(
                show.getArtist(),
                show.getLocal(),
                show.getDate(),
                show.getHorary(),
                show.getPrice(),
                show.getAvailableTickets(),
                show.getSoldTickets()
        )).collect(Collectors.toList());
    }

    public void sellingTickets(Long showId, int quantity) throws Exception {
        Show updatedShow = showRepository.findById(showId)
                .orElseThrow(() -> new Exception("Show Not Found"));

        if(updatedShow.getSoldTickets() + quantity > updatedShow.getAvailableTickets()){
            throw new Exception("Ingressos insuficientes");
        }

        updatedShow.setAvailableTickets(updatedShow.getSoldTickets() + quantity);
        showRepository.save(updatedShow);
    }
}
