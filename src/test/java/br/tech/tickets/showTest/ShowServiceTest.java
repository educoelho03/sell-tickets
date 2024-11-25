package br.tech.tickets.showTest;

import br.tech.tickets.dto.CreateShowRequest;
import br.tech.tickets.dto.CreateShowResponse;
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
import static org.mockito.Mockito.*;

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
        CreateShowRequest showRequest = new CreateShowRequest(
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
        CreateShowResponse createShowResponse = showService.registerShow(showRequest);

        // ASSERTIONS
        assertNotNull(createShowResponse);
        assertEquals(showRequest.artist().getName(), createShowResponse.artist().getName());
        assertEquals(showRequest.local(), createShowResponse.local());
        assertEquals(showRequest.date(), createShowResponse.date());
        assertEquals(showRequest.availableTickets(), createShowResponse.availableTickets());
        assertEquals(showRequest.soldTickets(), createShowResponse.soldTickets());

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

        List<CreateShowResponse> createShowResponse = showService.consultShowsByArtist(artist);

        assertNotNull(createShowResponse);
        assertEquals(2, createShowResponse.size());
        assertEquals(artist.getName(), createShowResponse.get(0).artist().getName());
        assertEquals(artist.getName(), createShowResponse.get(1).artist().getName());
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

        List<CreateShowResponse> createShowResponseList = showService.consultShowsByDate(date);

        assertNotNull(createShowResponseList);
        assertEquals(2, createShowResponseList.size());

        assertEquals(show1.getDate(), createShowResponseList.get(0).date());
        assertEquals(show2.getDate(), createShowResponseList.get(1).date());

        assertEquals(show1.getDate().getDayOfMonth(), createShowResponseList.get(0).date().getDayOfMonth());
        assertEquals(show1.getDate().getMonth(), createShowResponseList.get(0).date().getMonth());
        assertEquals(show1.getDate().getYear(), createShowResponseList.get(0).date().getYear());

        assertEquals(show2.getDate().getDayOfMonth(), createShowResponseList.get(1).date().getDayOfMonth());
        assertEquals(show2.getDate().getMonth(), createShowResponseList.get(1).date().getMonth());
        assertEquals(show2.getDate().getYear(), createShowResponseList.get(1).date().getYear());
    }
}
