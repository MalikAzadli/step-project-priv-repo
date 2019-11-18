package ui;

public enum Commands {
    TIMETABLE("Timetable of All Flights"),
    TIMETABLE_WITHIN("Timetable Within Range"),
    SEARCH_BOOK("Search"),
    FLIGHT_DETAILS("Flight Details"),
    LOGIN("Log in"),
    REGISTER("Register"),
    USER_BOOKINGS("My Bookings"),
    PASSENGER_BOOKINGS("Search made bookings"),
    CANCEL_BOOKING("Cancel Booking"),
    LOGOUT("End Session"),
    HELP("Help"),
    EXIT("Exit"),
    NO_COMMAND("No command available");

    private final String description;
    Commands(String descrition){
        this.description = descrition;
    }

    public String getDescription(){
        return description;
    }

}
