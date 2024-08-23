package event.management.domain.user;

import event.management.domain.user.RoleType;
import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)

@Entity
@Table(name = "roles")
public class Role{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    //TODO search enumerated again
    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private RoleType roleType;


    private String roleName;



}