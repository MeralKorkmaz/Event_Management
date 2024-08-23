package event.management.controller;

import event.management.payload.request.business.TicketRequest;
import event.management.payload.response.business.TicketResponse;
import event.management.payload.response.user.UserResponse;
import event.management.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;



    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN','ORGANIZER')")
    public ResponseEntity<TicketResponse> savedTicket(@RequestBody @Valid TicketRequest ticketRequest){
        return ResponseEntity.ok(ticketService.savedTicket(ticketRequest));
    }

    @GetMapping("/getTicketByTicketNumber/{ticketNumber}")
    public ResponseEntity<TicketResponse> getTicketByTicketNumber(@PathVariable int ticketNumber){
        return ResponseEntity.ok(ticketService.getTicketByTicketNumber(ticketNumber));
    }

    @GetMapping("/getAllTicketsByPage")
    public ResponseEntity<Page<TicketResponse>> getTicketsByPage(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value="sort",defaultValue = "ticketnumber") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ){

      return ResponseEntity.ok(ticketService.getTicketsByPage(page, size, sort, type));
    }

}
