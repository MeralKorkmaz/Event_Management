package event.management.payload.mapper;

import event.management.domain.business.Event;
import event.management.payload.request.business.EventRequest;
import event.management.payload.response.business.EventResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class EventMapper {


    public Event mapEventRequestToEvent(EventRequest eventRequest){
        return Event.builder().
                title(eventRequest.getTitle())
                .location(eventRequest.getLocation())
                .description(eventRequest.getDescription())
                .maxAttendees(eventRequest.getMaxAttendees())
                .dateTime(eventRequest.getDateTime())
                .ticketPrice(eventRequest.getTicketPrice())
                .build();

    }

    public EventResponse mapEventToEventResponse(Event event){
        return EventResponse.builder()
                .title(event.getTitle())
                .description(event.getDescription())
                .location(event.getLocation())
                .dateTime(event.getDateTime())
                .maxAttendees(event.getMaxAttendees())
                .ticketPrice(event.getTicketPrice())
                .build();


    }


    public Event mapUpdatedEventToEvent(EventRequest eventRequest){
        return Event.builder()
                .title(eventRequest.getTitle())
                .description(eventRequest.getDescription())
                .location(eventRequest.getLocation())
                .ticketPrice(eventRequest.getTicketPrice())
                .maxAttendees(eventRequest.getMaxAttendees())
                .dateTime(eventRequest.getDateTime())
                .build();
    }


}
