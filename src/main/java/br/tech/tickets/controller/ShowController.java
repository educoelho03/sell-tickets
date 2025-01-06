package br.tech.tickets.controller;

import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.dto.ApiResponse;
import br.tech.tickets.dto.ShowDTO;
import br.tech.tickets.mapper.ShowMapper;
import br.tech.tickets.service.SellService;
import br.tech.tickets.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {

    private final ShowService showService;
    private final SellService sellService;

    public ShowController(ShowService showService, SellService sellService) {
        this.showService = showService;
        this.sellService = sellService;
    }

    @GetMapping("/register")
    public ResponseEntity<ShowDTO> createShow(@Valid @RequestBody ShowDTO request) {
        Show show = ShowMapper.toEntity(request);
        showService.createShow(show);
        ShowDTO response = ShowMapper.toDto(show); // Converte a entidade para o DTO
        return ResponseEntity.status(201).body(response);
    }


    @GetMapping("/consult/{date}")
    public ResponseEntity<List<ShowDTO>> getShowsByDate(@PathVariable String date){
        LocalDateTime parseData = LocalDateTime.parse(date);
        List<ShowDTO> createShowRespons = showService.consultShowsByDate(parseData);
        return ResponseEntity.status(200).body(createShowRespons);
    }

    @PostMapping("/{showId}/sell")
    public ResponseEntity<ApiResponse> sellTicket(@PathVariable Long showId, @RequestParam int ticketQuantity, @RequestParam int seatNumber){
        sellService.sellingTickets(showId, ticketQuantity, seatNumber);
        return ResponseEntity.ok(new ApiResponse("Venda realizada com sucesso", null));
    }

}
