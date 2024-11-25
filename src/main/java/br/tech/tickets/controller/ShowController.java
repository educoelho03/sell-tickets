package br.tech.tickets.controller;

import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.dto.CreateShowResponse;
import br.tech.tickets.dto.ShowDTO;
import br.tech.tickets.dto.ShowResponseDTO;
import br.tech.tickets.exception.TicketSaleException;
import br.tech.tickets.service.SellService;
import br.tech.tickets.service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController("/show")
public class ShowController {

    private final ShowService showService;
    private final SellService sellService;

    public ShowController(ShowService showService, SellService sellService) {
        this.showService = showService;
        this.sellService = sellService;
    }

    @PostMapping("/register")
    public ResponseEntity<ShowResponseDTO> register(@RequestBody ShowDTO showDto) {
        Show show = showService.registerShow(showDto.toObject());
        return new ResponseEntity<>(ShowResponseDTO.toDto(show), HttpStatus.CREATED);
    }

    // @GetMapping("/consult/{artist}")
    // public ResponseEntity<List<ShowResponse>> consult(@PathVariable Artist artist){
    //     List<ShowResponse> showResponse = showService.consultShowsByArtist(artist);
    //     return ResponseEntity.status(HttpStatus.OK).body(showResponse);
    // }

    @GetMapping("/consult/{date}")
    public ResponseEntity<List<CreateShowResponse>> consultAllShowsByDate(@PathVariable LocalDateTime date){
        List<CreateShowResponse> createShowRespons = showService.consultShowsByDate(date);
        return ResponseEntity.status(HttpStatus.OK).body(createShowRespons);
    }

    @PostMapping("/{id}/sell")
    public ResponseEntity<String> sellTicket(@PathVariable Long showId, @RequestParam int ticketQuantity, @RequestParam int seatNumber){
        try{
            sellService.sellingTickets(showId, ticketQuantity, seatNumber);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (TicketSaleException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + e.getMessage());
        }
    }

}
