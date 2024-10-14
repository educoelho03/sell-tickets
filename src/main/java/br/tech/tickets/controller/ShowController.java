package br.tech.tickets.controller;

import br.tech.tickets.domain.dto.ShowRequest;
import br.tech.tickets.domain.dto.ShowResponse;
import br.tech.tickets.domain.entity.Artist;
import br.tech.tickets.service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
