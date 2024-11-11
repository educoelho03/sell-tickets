package br.tech.tickets.domain.entity;

import br.tech.tickets.domain.enums.TicketStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "showId")
    private Show show;

    private BigDecimal price;

    @Enumerated
    private TicketStatus ticketStatus;
}
