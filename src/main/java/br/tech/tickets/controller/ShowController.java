package br.tech.tickets.controller;

import br.tech.tickets.controller.dto.ArtistDTO;
import br.tech.tickets.controller.dto.UserDTO;
import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.domain.entity.Show;
import br.tech.tickets.controller.dto.ApiResponse;
import br.tech.tickets.controller.dto.ShowDTO;
import br.tech.tickets.domain.entity.User;
import br.tech.tickets.mapper.ArtistMapper;
import br.tech.tickets.mapper.ShowMapper;
import br.tech.tickets.mapper.UserMapper;
import br.tech.tickets.service.impl.SellServiceImpl;
import br.tech.tickets.service.impl.ShowServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowServiceImpl showService;
    private final SellServiceImpl sellService;

    public ShowController(ShowServiceImpl showService, SellServiceImpl sellService) {
        this.showService = showService;
        this.sellService = sellService;
    }

    @PostMapping
    public ResponseEntity<ShowDTO> registerNewShow(@Valid @RequestBody ShowDTO showDto) {
        Show show = ShowMapper.toEntity(showDto);
        showService.registerShow(show);
        ShowDTO response = ShowMapper.toDto(show); // Converte a entidade para o DTO
        return ResponseEntity.status(201).body(response);
    }


    @GetMapping("/{date}")
    public ResponseEntity<List<ShowDTO>> getShowsByDate(@PathVariable String date){
        LocalDateTime parseData = LocalDateTime.parse(date);
        List<ShowDTO> createShowRespons = showService.consultShowsByDate(parseData);
        return ResponseEntity.status(200).body(createShowRespons);
    }

    @GetMapping("/{artist}")
    public ResponseEntity<List<ShowDTO>> getShowsByArtist(@PathVariable ArtistDTO artist){
        Artist artistObj = ArtistMapper.toEntity(artist);
        List<ShowDTO> shows = showService.consultShowsByArtist(artistObj);
        return ResponseEntity.status(200).body(shows);
    }

    @GetMapping("/bought")
    public ResponseEntity<List<ShowDTO>> getShowsBougthByUser(@RequestBody UserDTO userDTO){
        User user = UserMapper.toEntity(userDTO);
        List<ShowDTO> shows = showService.consultShowsBoughtByUser(user);
        return ResponseEntity.status(200).body(shows);
    }

    @PostMapping("/{showId}/sell")
    public ResponseEntity<ApiResponse> sellTicket(@PathVariable Long showId, @RequestParam int ticketQuantity, @RequestParam int seatNumber){
        sellService.sellingTickets(showId, ticketQuantity, seatNumber);
        return ResponseEntity.status(200).body(new ApiResponse("Purchase successfully", null));
    }

}
