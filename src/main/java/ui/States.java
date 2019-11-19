package ui;

import java.util.*;

public enum States {
    USER(new HashMap<String, Commands>() {{
        put("1", Commands.TIMETABLE);
        put("2", Commands.TIMETABLE_WITHIN);
        put("3", Commands.SEARCH_BOOK);
        put("4", Commands.FLIGHT_DETAILS);
        put("5", Commands.USER_BOOKINGS);
        put("6", Commands.CANCEL_BOOKING);
        put("7", Commands.LOGOUT);
        put("8", Commands.EXIT);
        put("EXIT", Commands.EXIT);
    }}),

    VISITOR(new HashMap<String, Commands>() {{
        put("1", Commands.TIMETABLE);
        put("2", Commands.TIMETABLE_WITHIN);
        put("3", Commands.FLIGHT_DETAILS);
        put("4", Commands.PASSENGER_BOOKINGS);
        put("5", Commands.LOGIN);
        put("6", Commands.REGISTER);
        put("7", Commands.EXIT);
        put("EXIT", Commands.EXIT);
    }});

    private final Map<String, Commands> commands;

    States(HashMap<String, Commands> commands) {
        this.commands = commands;
    }

    public Map<String, Commands> getCommands() {
        return commands;
    }
}
