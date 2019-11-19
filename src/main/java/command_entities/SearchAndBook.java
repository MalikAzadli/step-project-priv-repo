package command_entities;

import ui.Console;
import ui.InputTypes;
import ui.Switcher;
import util.Pair;
import util.Toolkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchAndBook implements Executable {

    private final Console console;
    private final Switcher switcher;
    private final Toolkit toolkit;

    public SearchAndBook(Toolkit toolkit) {

        this.switcher = toolkit.getSwitcher();
        this.console = toolkit.getConsole();
        this.toolkit = toolkit;
    }

    @Override
    public void execute() {

        List<Pair> requirements = new ArrayList<>(Arrays.asList(
                new Pair("Origin: ", InputTypes.CITY),
                new Pair("Destination: ", InputTypes.CITY),
                new Pair("Date (dd/mm/yyyy): ", InputTypes.DATE),
                new Pair("Number of people: ", InputTypes.INTEGER)));

        List<String> inputs = new ArrayList<>();
        for (Pair p : requirements) {
            String input = toolkit.getInput(p.getMessage(), p.getTypes());
            if (input.isEmpty()) return;
            inputs.add(input);
        }

        String result = switcher.search(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3));

        if (result.isEmpty()) {
            console.printLn("No flights were found for that criterias.");
            return;
        }

        console.printLn(result);
        String flightNo = toolkit.getInput("Enter flight ID to book or 'exit': ", InputTypes.FLIGHT_NO);

        if (flightNo.isEmpty()) return;


        int numberOfPassenger = Integer.parseInt(inputs.get(3));
        for (int i = 0; i < numberOfPassenger; i++) {
            String[] user = getPassenger(i);
            if (user[0].isEmpty() || user[1].isEmpty()) return;
            console.printLn(switcher.book(flightNo, user[0], user[1], toolkit.getUser()));
        }

    }

    private String[] getPassenger(int num) {
        String[] user = new String[2];
        user[0] = toolkit.getInput("Name: ", InputTypes.NAME);
        user[1] = toolkit.getInput("Surname: ", InputTypes.SURNAME);

        return user;
    }
}
