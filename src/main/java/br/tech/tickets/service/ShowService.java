package br.tech.tickets.service;

import br.tech.tickets.domain.dto.ShowResponse;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.domain.dto.ShowRequest;
import br.tech.tickets.repository.ShowRepository;
import org.springframework.stereotype.Service;

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

        Show savedShow = showRepository.save(newShow);

        return new ShowResponse(
                savedShow.getArtist(),
                savedShow.getLocal(),
                savedShow.getDate(),
                savedShow.getHorary(),
                savedShow.getPrice()
        );
    }

    public List<ShowResponse> consultShowsByArtist(String artist){
        List<Show> shows = showRepository.findByArtist(artist);

        return shows.stream().map((show) -> new ShowResponse(
                show.getArtist(),
                show.getLocal(),
                show.getDate(),
                show.getHorary(),
                show.getPrice()
        )).collect(Collectors.toList());

    }
}
