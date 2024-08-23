package event.management.domain.user;


public enum RoleType{


    ADMIN("Admin"),
    ORGANIZER("Organizer"),

    ATTENDEE("Attendee"),
    SPONSOR("Sponsor");



    public final String name;
    RoleType(String name) {
        this.name = name;
    }


}