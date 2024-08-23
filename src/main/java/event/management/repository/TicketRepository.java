package event.management.repository;

import event.management.domain.business.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    boolean existsTicketByTicketNumber(int ticketNumber);

    Optional<Ticket> findByTicketNumber(int ticketNumber);
}
