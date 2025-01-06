package br.tech.tickets.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PurchasedTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticketId")
    private Ticket ticket;

    private LocalDateTime sellDate;
}
