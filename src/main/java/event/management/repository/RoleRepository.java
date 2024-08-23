package event.management.repository;

import event.management.domain.user.Role;
import event.management.domain.user.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //TODO look jpa query again
    @Query("SELECT r FROM Role r WHERE r.roleType=?1")
    Optional<Role> findByEnumRoleEquals(RoleType roleType);
}
