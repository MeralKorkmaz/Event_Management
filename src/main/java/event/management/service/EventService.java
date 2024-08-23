package event.management.service;

import event.management.domain.business.Event;
import event.management.exception.ConflictException;
import event.management.exception.ResourceNotFoundException;
import event.management.payload.mapper.EventMapper;
import event.management.payload.request.business.EventRequest;
import event.management.payload.response.business.EventResponse;
import event.management.repository.EventRepository;
import event.management.repository.UserRepository;
import event.management.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {


    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final PageableHelper pageableHelper;
    private final UserRepository userRepository;

    public EventResponse saveEvents(EventRequest eventRequest) {

        isExistWithTitle(eventRequest.getTitle());

       Event event =  eventMapper.mapEventRequestToEvent(eventRequest);

      Event savedEvent =  eventRepository.save(event);

       return eventMapper.mapEventToEventResponse(savedEvent);



    }

    public boolean isExistWithTitle(String title){

        boolean existWithTitle = eventRepository.existsByTitle(title);

        if(existWithTitle){
            throw  new ConflictException("Event with this title already exist");
        }else{
            return false;
        }
    }

    public Page<EventResponse> getEventsByPage(int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageable(page, size, sort, type);
        return eventRepository.findAll(pageable).map((eventMapper::mapEventToEventResponse));
    }

    public Event existWithEventId(Long eventId){
      return eventRepository.findById(eventId).orElseThrow(()-> new ResourceNotFoundException("The event does not exist"));
    }
    public EventResponse updateEvent(EventRequest eventRequest, Long eventId) {

       Event event =  existWithEventId(eventId);

        isExistWithTitle(eventRequest.getTitle());

        Event updatedEvent = eventMapper.mapUpdatedEventToEvent(eventRequest);
        Event savedEvent = eventRepository.save(updatedEvent);

        return eventMapper.mapEventToEventResponse(savedEvent);





    }

    public String deleteEvent(Long eventId) {

      Event event =   existWithEventId(eventId);
      userRepository.deleteById(event.getId());

      return "Event has been deleted successfully";
    }
}
