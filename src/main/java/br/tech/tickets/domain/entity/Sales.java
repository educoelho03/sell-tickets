package br.tech.tickets.domain.entity;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ticketId")
    private Ticket ticket;

    private LocalDateTime sellDate;
}
