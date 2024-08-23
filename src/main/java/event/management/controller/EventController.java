package event.management.controller;

import event.management.payload.request.business.EventRequest;
import event.management.payload.response.business.EventResponse;
import event.management.payload.response.business.TicketResponse;
import event.management.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {


    private final EventService eventService;

    @PostMapping("/save")
    public ResponseEntity<EventResponse> saveEvents(@RequestBody @Valid EventRequest eventRequest){

        return ResponseEntity.ok(eventService.saveEvents(eventRequest));

    }

    @GetMapping("/getAllEventsByPage")
    public ResponseEntity<Page<EventResponse>> EventsByPage(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value="sort",defaultValue = "eventnumber") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ){

        return ResponseEntity.ok(eventService.getEventsByPage(page, size, sort, type));
    }


    @PutMapping("/updateEvent/{eventId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ORGANIZER')")
    public ResponseEntity<EventResponse> updateEvent(@RequestBody @Valid EventRequest eventRequest, @PathVariable Long eventId){
        return ResponseEntity.ok(eventService.updateEvent(eventRequest, eventId));
    }

    @DeleteMapping("/deleteEvent/eventId")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZER')")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId){
        return ResponseEntity.ok(eventService.deleteEvent(eventId));
    }
}
