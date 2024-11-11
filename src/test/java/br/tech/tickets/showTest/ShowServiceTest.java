package br.tech.tickets.showTest;

import br.tech.tickets.domain.dto.ShowRequest;
import br.tech.tickets.domain.dto.ShowResponse;
import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.repository.ShowRepository;
import br.tech.tickets.service.ShowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
class ShowServiceTest {

    @InjectMocks
    private ShowService showService;

    @Mock
    private ShowRepository showRepositoryMock;

    @BeforeEach
    void setUp() {
        this.showRepositoryMock = Mockito.mock(ShowRepository.class);
        showService = new ShowService(showRepositoryMock);
    }

    @Test
    @DisplayName("Register a new show")
    void registerShow() {
        // ARRANGE
        Artist artist = new Artist("Travis Scott", "Trap");
        ShowRequest showRequest = new ShowRequest(
                1L,
                artist,
                "São Paulo",
                LocalDateTime.of(2020, 10, 20, 21, 0),
                1000,
                0
        );

        Show savedShow = new Show();
        savedShow.setArtist(showRequest.artist());
        savedShow.setLocal(showRequest.local());
        savedShow.setDate(showRequest.date());
        savedShow.setAvailableTickets(showRequest.availableTickets());
        savedShow.setSoldTickets(showRequest.soldTickets());

        when(showRepositoryMock.save(any(Show.class))).thenReturn(savedShow);

        // ACT
        ShowResponse showResponse = showService.registerShow(showRequest);

        // ASSERTIONS
        assertNotNull(showResponse);
        assertEquals(showRequest.artist().getName(), showResponse.artist().getName());
        assertEquals(showRequest.local(), showResponse.local());
        assertEquals(showRequest.date(), showResponse.date());
        assertEquals(showRequest.availableTickets(), showResponse.availableTickets());
        assertEquals(showRequest.soldTickets(), showResponse.soldTickets());

        verify(showRepositoryMock).save(any(Show.class)); // Não é necessário passar `any()` dentro do verify.
    }

    @Test
    @DisplayName("Should return shows by artist name")
    void consultShowsByArtist() {
        Artist artist = new Artist("Travis Scott", "Trap");
        Show show1 = new Show();
        show1.setArtist(artist);
        show1.setDate(LocalDateTime.of(2023, 5, 20, 21, 0));

        Show show2 = new Show();
        show2.setArtist(artist);
        show2.setDate(LocalDateTime.of(2021, 1, 12, 20, 30));

        when(showRepositoryMock.findByArtist(artist)).thenReturn(List.of(show1, show2));

        List<ShowResponse> showResponse = showService.consultShowsByArtist(artist);

        assertNotNull(showResponse);
        assertEquals(2, showResponse.size());
        assertEquals(artist.getName(), showResponse.get(0).artist().getName());
        assertEquals(artist.getName(), showResponse.get(1).artist().getName());
    }

    @Test
    @DisplayName("Should return shows by date")
    void consultShowsByDate() {
        Artist artist = new Artist("Travis Scott", "Trap");
        Artist artist2 = new Artist("Bruno Mars", "Pop");
        LocalDateTime date = LocalDateTime.of(2023, 10, 20, 21, 0);  // Corrigido para LocalDateTime

        Show show1 = new Show();
        show1.setArtist(artist);
        show1.setDate(date);

        Show show2 = new Show();
        show2.setArtist(artist2);
        show2.setDate(date);

        when(showRepositoryMock.findByDate(date)).thenReturn(List.of(show1, show2));

        List<ShowResponse> showResponseList = showService.consultShowsByDate(date);

        assertNotNull(showResponseList);
        assertEquals(2, showResponseList.size());

        assertEquals(show1.getDate(), showResponseList.get(0).date());
        assertEquals(show2.getDate(), showResponseList.get(1).date());

        assertEquals(show1.getDate().getDayOfMonth(), showResponseList.get(0).date().getDayOfMonth());
        assertEquals(show1.getDate().getMonth(), showResponseList.get(0).date().getMonth());
        assertEquals(show1.getDate().getYear(), showResponseList.get(0).date().getYear());

        assertEquals(show2.getDate().getDayOfMonth(), showResponseList.get(1).date().getDayOfMonth());
        assertEquals(show2.getDate().getMonth(), showResponseList.get(1).date().getMonth());
        assertEquals(show2.getDate().getYear(), showResponseList.get(1).date().getYear());
    }
}
