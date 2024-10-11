package br.tech.tickets.repository;

import br.tech.tickets.domain.dto.ShowResponse;
import br.tech.tickets.domain.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByArtist(String nameArtist);
}
