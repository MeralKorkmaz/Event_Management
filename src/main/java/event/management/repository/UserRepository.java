package event.management.repository;

import event.management.domain.user.RoleType;
import event.management.domain.user.User;
import event.management.payload.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);



    @Query("SELECT u FROM User u WHERE u.role.roleName = ?1")
    Page<User> findByUserRole(String roleName, Pageable pageable);

    User findByUsername(String userName);

    User findByUsernameEquals(String username);

    @Query(value = "SELECT count(u) FROM User u WHERE u.role.roleType = ?1")
    long countAdmin(RoleType roleType);
}
