package br.tech.tickets.service;

import br.tech.tickets.domain.entity.Seat;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.domain.entity.Ticket;
import br.tech.tickets.domain.enums.TicketStatus;
import br.tech.tickets.exception.*;
import br.tech.tickets.repository.ShowRepository;
import br.tech.tickets.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellService {

    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;

    public SellService(TicketRepository ticketRepository, ShowRepository showRepository) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
    }

    @Transactional
    public void sellingTickets(Long showId, int ticketQuantity, int seatNumber){
        Show show = showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException("Show not found"));

        validateTicketAvailable(show, ticketQuantity);
        validateSeatAvailable(show, seatNumber);

        createAndSaveTicket(show, ticketQuantity);
        updateAvailableTickets(show, ticketQuantity);
    }


    private void  createAndSaveTicket(Show show, int ticketQuantity){
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

    private void validateTicketAvailable(Show show, int ticketQuantity){
        int totalSoldTickets = show.getTicket().stream()
                .mapToInt(Ticket::getSoldTickets).sum();

        if(totalSoldTickets + ticketQuantity > show.getAvailableTickets()){
            throw new TicketSoldOutException("Not enough tickets available");
        }
    }

    private void validateSeatAvailable(Show show, int seatNumber){
        Seat seat = show.getSeat().stream().filter(
                s -> s.getSeatNumber() == seatNumber)
                .findFirst()
                .orElseThrow(() -> new SeatNotFoundException("Seat Not Found"));

        if(seat.isOccupied()){
            throw new SeatNotAvailableException("Seat " + seatNumber + " is already occupied.");
        }
    }

    private void updateAvailableTickets(Show show, int ticketQuantity){
        show.setAvailableTickets(show.getAvailableTickets() + ticketQuantity);
        showRepository.save(show);
    }

}
