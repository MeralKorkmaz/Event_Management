package event.management;

import event.management.domain.user.Role;
import event.management.domain.user.RoleType;
import event.management.payload.request.user.UserRequest;
import event.management.repository.RoleRepository;
import event.management.service.RoleService;
import event.management.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventManagementApplication implements CommandLineRunner {


    private final RoleService roleService;
    private final RoleRepository roleRepository;

    private final UserService userService;;

    public EventManagementApplication(RoleService roleService, RoleRepository roleRepository, UserService userService) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EventManagementApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {


        if(roleService.getAllRole().isEmpty()){
            Role admin = new Role();
            admin.setRoleName("Admin");
            admin.setRoleType(RoleType.ADMIN);
            roleRepository.save(admin);

            Role organizer = new Role();
            organizer.setRoleName("Organizer");
            organizer.setRoleType(RoleType.ORGANIZER);
            roleRepository.save(organizer);


            Role attendee = new Role();
            attendee.setRoleName("Attendee");
            attendee.setRoleType(RoleType.ATTENDEE);
            roleRepository.save(attendee);

            Role sponsor = new Role();
            sponsor.setRoleName("Sponsor");
            sponsor.setRoleType(RoleType.SPONSOR);
            roleRepository.save(sponsor);

        }



        //Built_In admin
        if(userService.countAllAdmin()==0){
            UserRequest adminRequest = new UserRequest();
            adminRequest.setSurname("Korkmaz");
            adminRequest.setEmail("adminm@gmail.com");
            adminRequest.setPassword("Hope181107");
            adminRequest.setName("Meral");
            adminRequest.setUsername("Admin");


            userService.saveUser(adminRequest,"Admin");
        }

    }
}
