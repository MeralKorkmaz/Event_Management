package event.management.domain.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import event.management.domain.user.User;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)

@Entity
@Table(name = "tickets")
public class Ticket{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(unique= true, nullable = false)
    @NotNull
    private int ticketNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate issuedDate;

    @Column(nullable = false)
    @NotNull
    private int price;

    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registrations;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}