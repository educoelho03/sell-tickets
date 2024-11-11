package br.tech.tickets.domain.enums;

import br.tech.tickets.domain.entity.Ticket;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TicketStatus {
    AVAILABLE("available"), SOLD_OUT("sold_out");

    private final String TicketStatus;

    TicketStatus(String ticketStatus){
        this.TicketStatus = ticketStatus;
    }


    @JsonValue
    public String getTicketStatus(){
        return getTicketStatus();
    }

    @JsonCreator
    public static TicketStatus fromValue(String value){
        for(TicketStatus ticket : values()){
            String currentStatus = ticket.getTicketStatus();
            if(currentStatus.equals(value)){
                return ticket;
            }
        }

        throw new IllegalArgumentException("Valor inválido para o tipo de Enum: " + value);
    }
}
