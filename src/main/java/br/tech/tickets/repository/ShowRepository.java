package br.tech.tickets.repository;

import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByArtist(Artist artist);
    List<Show> findByDate(LocalDateTime date);

    @Query("SELECT s FROM Show s " +
            "JOIN s.tickets t " +
            "JOIN PurchasedTicket pt ON pt.ticket = t " +
            "JOIN pt.user u " +
            "WHERE u.username = :username")
    List<Show> findShowsBoughtByUsername(@Param("username") String username);
}
