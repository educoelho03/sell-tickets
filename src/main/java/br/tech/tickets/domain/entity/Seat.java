package br.tech.tickets.domain.entity;

import br.tech.tickets.service.ShowService;
import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    private boolean isOccupied;
    private String seatRow;  // Linha onde o assento está (ex: "A", "B", "C")
    private int seatColumn;  // Coluna do assento (ex: 1, 2, 3...)

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    public Seat(boolean isOccupied, String row, int column) {
        this.isOccupied = isOccupied;
        this.seatRow = row;
        this.seatColumn = column;
    }

    public Seat() {
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }
}
