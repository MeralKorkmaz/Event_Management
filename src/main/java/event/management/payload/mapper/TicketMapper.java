package event.management.payload.mapper;

import event.management.domain.business.Ticket;
import event.management.payload.request.business.TicketRequest;
import event.management.payload.response.business.TicketResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TicketMapper {

    public Ticket mapTicketRequestToTicket(TicketRequest ticketRequest){
        return  Ticket.builder()
                .price(ticketRequest.getPrice())
                .ticketNumber(ticketRequest.getTicketNumber())
                .issuedDate(ticketRequest.getIssuedDate())
                .build();
    }

    public TicketResponse mapTicketToTicketResponse(Ticket ticket){
        return TicketResponse.builder()
                .price(ticket.getPrice())
                .ticketNumber(ticket.getTicketNumber())
                .issuedDate(ticket.getIssuedDate())
                .build();
    }
}
