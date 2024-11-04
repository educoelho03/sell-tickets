package br.tech.tickets.showTest;

import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.repository.ShowRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ShowRepositoryTest {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Artist successfully from DB")
    void findByArtist_HasShow() {
        // ARRANGE
        Artist artist = new Artist("Travis Scott", "Trap");
        entityManager.persist(artist);

        Show show1 = new Show();
        show1.setArtist(artist);
        show1.setDate(LocalDate.of(2023, 5, 20));
        entityManager.persist(show1);

        Show show2 = new Show();
        show2.setArtist(artist);
        show2.setDate(LocalDate.of(2022, 1, 15));
        entityManager.persist(show2);

        // ACT
        List<Show> shows = showRepository.findByArtist(artist);

        // ASSERT
        assertNotNull(shows);
        assertEquals(2, shows.size());
        assertTrue(shows.stream().anyMatch(show -> show.getDate().equals(show1.getDate())));
        assertTrue(shows.stream().anyMatch(show -> show.getDate().equals(show2.getDate())));
    }

    @Test
    @DisplayName("Should return empty list when no shows found for artist")
    void findByArtist_HasNoShow(){
        Artist artist = new Artist("Eduardo Paiva", "Samba");
        entityManager.persist(artist);

        List<Show> shows = showRepository.findByArtist(artist);

        assertTrue(shows.isEmpty());
    }

    @Test
    @DisplayName("Should be return a list successfully ")
    void findByDate_HasShow(){
        LocalDate date = LocalDate.of(2023, 10, 20);
        Artist artist = new Artist("Travis Scott", "Trap");
        Show show = new Show();

        show.setArtist(artist);
        show.setDate(date);

        entityManager.persist(artist);
        entityManager.persist(show);

        List<Show> shows = showRepository.findByDate(date);

        assertNotNull(shows);
        assertEquals(1, shows.size());
        assertEquals(date, shows.get(0).getDate());
        assertEquals(shows.get(0).getDate(), date);
    }
}