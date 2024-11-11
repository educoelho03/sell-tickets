package br.tech.tickets.controller;

import br.tech.tickets.domain.dto.ShowRequest;
import br.tech.tickets.domain.dto.ShowResponse;
import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController("/show")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }


    @PostMapping("/register")
    public ResponseEntity<ShowResponse> register(@RequestBody ShowRequest showRequest) {
        ShowResponse showResponse = showService.registerShow(showRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(showResponse);
    }

    @GetMapping("/consult/{artist}")
    public ResponseEntity<List<ShowResponse>> consult(@PathVariable Artist artist){
        List<ShowResponse> showResponse = showService.consultShowsByArtist(artist);
        return ResponseEntity.status(HttpStatus.OK).body(showResponse);
    }

    @GetMapping("/consult/{date}")
    public ResponseEntity<List<ShowResponse>> consultAllShowsByDate(@PathVariable LocalDateTime date){
        List<ShowResponse> showResponses = showService.consultShowsByDate(date);
        return ResponseEntity.status(HttpStatus.OK).body(showResponses);
    }

    @PostMapping("/{id}/sell")
    public ResponseEntity<String> sellTicket(@PathVariable Long showId, @RequestParam int quantity){
        try{
            showService.sellingTickets(showId, quantity);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
