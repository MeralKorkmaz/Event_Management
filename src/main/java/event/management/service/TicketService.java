package event.management.service;

import event.management.domain.business.Ticket;
import event.management.exception.ConflictException;
import event.management.exception.ResourceNotFoundException;
import event.management.payload.mapper.TicketMapper;
import event.management.payload.request.business.TicketRequest;
import event.management.payload.response.business.TicketResponse;
import event.management.payload.response.user.UserResponse;
import event.management.repository.TicketRepository;
import event.management.repository.UserRepository;
import event.management.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final PageableHelper pageableHelper;


    public TicketResponse savedTicket(TicketRequest ticketRequest) {
        isTicketExistByTicketNumber(ticketRequest.getTicketNumber());
       Ticket ticket =  ticketMapper.mapTicketRequestToTicket(ticketRequest);
       Ticket savedTicket = ticketRepository.save(ticket);

       return ticketMapper.mapTicketToTicketResponse(savedTicket);




    }

    private boolean isTicketExistByTicketNumber(int ticketNumber){
        boolean ticketNumberExist = ticketRepository.existsTicketByTicketNumber(ticketNumber);
        if(ticketNumberExist){
            throw  new ConflictException("The ticket number has exist");
        }else{
            return false;
        }
    }

    public TicketResponse getTicketByTicketNumber(int  ticketNumber) {

        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber).orElseThrow(()->  new  ResourceNotFoundException("The ticket does not exist"));

        return ticketMapper.mapTicketToTicketResponse(ticket);



    }

    public Page<TicketResponse> getTicketsByPage(int page, int size, String sort, String type) {

        Pageable pageable=  pageableHelper.getPageable(page, size, sort, type);

         return ticketRepository.findAll(pageable).map(ticketMapper::mapTicketToTicketResponse);
    }
}
