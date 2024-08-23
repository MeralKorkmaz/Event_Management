package event.management.repository;

import event.management.domain.business.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository  extends JpaRepository<Registration, Long> {
}
