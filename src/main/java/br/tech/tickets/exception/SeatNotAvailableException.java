package br.tech.tickets.exception;

public class SeatNotAvailableException extends RuntimeException {
  public SeatNotAvailableException(String message) {
    super(message);
  }
}
