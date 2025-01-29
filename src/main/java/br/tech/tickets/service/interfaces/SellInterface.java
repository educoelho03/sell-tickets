package br.tech.tickets.service.interfaces;

import br.tech.tickets.domain.entity.Show;

public interface SellInterface {
    void sellingTickets(Long showId, int ticketQuantity, int seatNumber);
    void createAndSaveTicket(Show show, int ticketQuantity);
    void validateTicketAvailable(Show show, int ticketQuantity);
    void validateSeatAvailable(Show show, int seatNumber);
    void updateAvailableTickets(Show show, int ticketQuantity);
}
