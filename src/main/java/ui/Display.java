package ui;

import util.Helpers;

public class Display {

    public String display(States state) {
        switch (state) {
            case VISITOR:
                return this.visitorMenu();
            case USER:
                return this.userMenu();
        }
        return this.greet();
    }

    /*private String userMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================================================================");
        sb.append("\n");
        sb.append("|                        FLIGHT RESERVATION SYSTEM                        |");
        sb.append("\n");
        sb.append("|            Please choose one of the options below to continue.          |");
        sb.append("\n");
        sb.append("===========================================================================");
        sb.append("\n");
        States.USER.getCommands()
                .forEach((k, v) -> sb.append(Helpers.center(String.format("%s. %s\n", k, v.getDescription()), 50)));
        return sb.toString();
    }*/

    private String greet() {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================================================================");
        sb.append("\n");
        sb.append("|                                 Welcome!                                |");
        sb.append("\n");
        sb.append("|                        FLIGHT RESERVATION SYSTEM                        |");
        sb.append("\n");
        sb.append("|                                                                         |\n");
        sb.append("|            Please choose one of the options below to continue.          |");
        sb.append("\n");
        sb.append("===========================================================================");
        sb.append("\n");
        sb.append("|                         1. Sign in                                      |");
        sb.append("\n");
        sb.append("|                         2. Register                                     |");
        sb.append("\n");
        sb.append("|                         3. Continue                                     |");
        sb.append("\n");
        sb.append("|                         4. Exit                                         |");
        sb.append("\n");
        sb.append("===========================================================================");
        sb.append("\n");
        sb.append(">>> Your selection: ");
        return sb.toString();
    }

    private String visitorMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================================================================");
        sb.append("\n");
        sb.append("|                        FLIGHT RESERVATION SYSTEM                        |");
        sb.append("\n");
        sb.append("|            Please choose one of the options below to continue.          |");
        sb.append("\n");
        sb.append("|                      You are connected as a Visitor                     |");
        sb.append("\n");
        sb.append("===========================================================================");
        sb.append("\n");
        sb.append("|                         1. View Timetable                               |");
        sb.append("\n");
        sb.append("|                         2. Timetable Within Range                       |");
        sb.append("\n");
        sb.append("|                         3. View Flight Details                          |");
        sb.append("\n");
        sb.append("|                         4. Search Made Bookings                         |");
        sb.append("\n");
        sb.append("|                         5. Sign In                                      |");
        sb.append("\n");
        sb.append("|                         6. Register                                     |");
        sb.append("\n");
        sb.append("|                         7. Help                                         |");
        sb.append("\n");
        sb.append("|                         8. Exit                                         |");
        sb.append("\n");
        sb.append("===========================================================================");
        sb.append("\n");
        sb.append(">>> Your selection: ");
        return sb.toString();
    }

    private String userMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================================================================");
        sb.append("\n");
        sb.append("|                        FLIGHT RESERVATION SYSTEM                        |");
        sb.append("\n");
        sb.append("|            Please choose one of the options below to continue.          |");
        sb.append("\n");
        sb.append("===========================================================================");
        sb.append("\n");
        sb.append("|                         1. View Timetable                               |");
        sb.append("\n");
        sb.append("|                         2. Timetable Within Range                       |");
        sb.append("\n");
        sb.append("|                         3. Search                                       |");
        sb.append("\n");
        sb.append("|                         4. View Flight Details                          |");
        sb.append("\n");
        sb.append("|                         5. My Bookings                                  |");
        sb.append("\n");
        sb.append("|                         6. Cancel a Booking                             |");
        sb.append("\n");
        sb.append("|                         7. Help                                         |");
        sb.append("\n");
        sb.append("|                         8. End Session                                  |");
        sb.append("\n");
        sb.append("|                         9. Exit                                         |");
        sb.append("\n");
        sb.append("===========================================================================");
        sb.append("\n");
        sb.append(">>> Your selection: ");
        return sb.toString();
    }
}
