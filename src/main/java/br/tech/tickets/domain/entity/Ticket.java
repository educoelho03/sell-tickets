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
    private Integer soldTickets;

    public Ticket(BigDecimal price, TicketStatus ticketStatus, Integer soldTickets) {
        this.price = price;
        this.ticketStatus = ticketStatus;
        this.soldTickets = soldTickets;
    }

    public Ticket(){

    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Integer getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Integer soldTickets) {
        this.soldTickets = soldTickets;
    }
}
