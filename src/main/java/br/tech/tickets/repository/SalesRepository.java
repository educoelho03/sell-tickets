package br.tech.tickets.repository;

import br.tech.tickets.domain.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
}
