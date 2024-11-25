package br.tech.tickets.service;

import br.tech.tickets.domain.entity.Seat;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.domain.entity.Ticket;
import br.tech.tickets.domain.enums.TicketStatus;
import br.tech.tickets.exception.*;
import br.tech.tickets.repository.ShowRepository;
import br.tech.tickets.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class SellService {

    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;

    public SellService(TicketRepository ticketRepository, ShowRepository showRepository) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
    }

    public void sellingTickets(Long showId, int ticketQuantity, int seatNumber) throws Exception {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException("Show Not Found"));

        int totalSoldTickets = show.getTicket().stream()
                .mapToInt(Ticket::getSoldTickets).sum();

        if(totalSoldTickets + ticketQuantity > show.getAvailableTickets()){
            throw new TicketSoldOutException("Ingressos insuficientes");
        }

        if(!isSeatAvailable(show.getShowId(), seatNumber)){
            throw new SeatNotAvailableException("Assento " + seatNumber + " já esta ocupado.");
        }

        for(int i = 0; i < ticketQuantity; i++){
            Ticket ticket = new Ticket();
            ticket.setShow(show);
            ticket.setPrice(show.getTicket().get(0).getPrice());
            ticket.setTicketStatus(TicketStatus.SOLD);

            ticketRepository.save(ticket);
        }

        show.setAvailableTickets(show.getAvailableTickets() - ticketQuantity);
        showRepository.save(show);
    }

    public boolean isSeatAvailable(Long showId, int seatNumber) throws Exception {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException("Show Not Found"));

        Seat selectedSeat = show.getSeat()
                .stream()
                .filter(seat -> seat.getSeatNumber() == seatNumber)
                .findFirst()
                .orElseThrow(() -> new SeatNotFoundException("Seat Not Found"));

        if (selectedSeat.isOccupied()) {
            throw new SeatNotAvailableException("o Assento " + seatNumber + " ja esta ocupado.");
        }

        return true;
    }

}
