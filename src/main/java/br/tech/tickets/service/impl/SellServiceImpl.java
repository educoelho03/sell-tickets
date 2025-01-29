package br.tech.tickets.service.impl;

import br.tech.tickets.domain.entity.Seat;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.domain.entity.Ticket;
import br.tech.tickets.domain.enums.TicketStatus;
import br.tech.tickets.exception.SeatNotAvailableException;
import br.tech.tickets.exception.SeatNotFoundException;
import br.tech.tickets.exception.ShowNotFoundException;
import br.tech.tickets.exception.TicketSoldOutException;
import br.tech.tickets.repository.ShowRepository;
import br.tech.tickets.repository.TicketRepository;
import br.tech.tickets.service.interfaces.SellInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellServiceImpl implements SellInterface {

    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;

    public SellServiceImpl(ShowRepository showRepository, TicketRepository ticketRepository) {
        this.showRepository = showRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void sellingTickets(Long showId, int ticketQuantity, int seatNumber) {
        Show show = showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException("Show not found"));

        validateTicketAvailable(show, ticketQuantity);
        validateSeatAvailable(show, seatNumber);

        createAndSaveTicket(show, ticketQuantity);
        updateAvailableTickets(show, ticketQuantity);
    }

    @Override
    public void createAndSaveTicket(Show show, int ticketQuantity) {
        List<Ticket> tickets = new ArrayList<>();

        for(int i = 0; i < ticketQuantity; i++){
            Ticket ticket = new Ticket();
            ticket.setShow(show);
            ticket.setPrice(show.getTicket().get(0).getPrice());
            ticket.setTicketStatus(TicketStatus.SOLD);
            tickets.add(ticket);
        }

        ticketRepository.saveAll(tickets);
    }

    @Override
    public void validateTicketAvailable(Show show, int ticketQuantity) {
        int totalSoldTickets = show.getTicket().stream()
                .mapToInt(Ticket::getSoldTickets).sum();

        if(totalSoldTickets + ticketQuantity > show.getAvailableTickets()){
            throw new TicketSoldOutException("Not enough tickets available");
        }
    }

    @Override
    public void validateSeatAvailable(Show show, int seatNumber) {
        Seat seat = show.getSeat().stream().filter(
                        s -> s.getSeatNumber() == seatNumber)
                .findFirst()
                .orElseThrow(() -> new SeatNotFoundException("Seat Not Found"));

        if(seat.isOccupied()){
            throw new SeatNotAvailableException("Seat " + seatNumber + " is already occupied.");
        }
    }

    @Override
    public void updateAvailableTickets(Show show, int ticketQuantity) {
        show.setAvailableTickets(show.getAvailableTickets() + ticketQuantity);
        showRepository.save(show);
    }
}
