package br.tech.tickets.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    private boolean isOccupied;
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    public Seat(boolean isOccupied, int seatNumber) {
        this.isOccupied = isOccupied;
        this.seatNumber = seatNumber;
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

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
