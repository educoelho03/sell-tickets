package br.tech.tickets.exception;

public class SeatNotFoundException extends Exception {

    private final int errorCode;

    public SeatNotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
