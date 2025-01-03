package br.tech.tickets.controller;

import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.dto.ApiResponse;
import br.tech.tickets.dto.ShowDTO;
import br.tech.tickets.dto.ShowResponseDTO;
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

    @GetMapping("/create")
    public ResponseEntity<ShowDTO> createShow(@Valid @RequestBody Show show) {
        Show savedShow = showService.createShow(show);
        ShowDTO responseDto = ShowDTO.toDto(savedShow); // Converte a entidade para o DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    @GetMapping("/consult/{date}")
    public ResponseEntity<List<ShowResponseDTO>> getShowsByDate(@PathVariable String date){
        LocalDateTime parseData = LocalDateTime.parse(date);
        List<ShowResponseDTO> createShowRespons = showService.consultShowsByDate(parseData);
        return ResponseEntity.status(HttpStatus.OK).body(createShowRespons);
    }

    @PostMapping("/{showId}/sell")
    public ResponseEntity<ApiResponse> sellTicket(@PathVariable Long showId, @RequestParam int ticketQuantity, @RequestParam int seatNumber){
        sellService.sellingTickets(showId, ticketQuantity, seatNumber);
        return ResponseEntity.ok(new ApiResponse("Venda realiada com sucesso", null));
    }

}
