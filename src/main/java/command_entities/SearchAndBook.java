package command_entities;

import model.User;
import ui.Console;
import ui.InputTypes;
import ui.Switcher;
import ui.Validator;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchAndBook extends CommandBlueprint {

    public SearchAndBook(Console console, Validator validator, Switcher switcher, User user) {
        super(console, validator, switcher, user);
    }

    @Override
    public void execute() {

        List<Pair> requirements = new ArrayList<>(Arrays.asList(
                new Pair("Origin: ", InputTypes.CITY),
                new Pair("Destination: ", InputTypes.CITY),
                new Pair("Date (dd/mm/yyyy): ", InputTypes.DATE),
                new Pair("Number of people: ", InputTypes.INTEGER)));

        List<String> inputs = new ArrayList<>();
        for(Pair p: requirements){
            String input = getInput(p.getMessage(), p.getTypes());
            if(input.isEmpty()) return;
            inputs.add(input);
        }

        String result = switcher.search(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3));

        if (result.isEmpty()) {
            console.printLn("No flights were found for that criterias.");
            return;
        }

        console.printLn(result);
        String flightNo = getInput("Enter flight ID to book or 'exit': ", InputTypes.FLIGHT_NO);

        if (flightNo.isEmpty()) return;


        int numberOfPassenger = Integer.parseInt(inputs.get(3));
        for (int i = 0; i < numberOfPassenger; i++) {
            String[] user = getPassenger(i);
            if (user[0].isEmpty() || user[1].isEmpty()) return;
            console.printLn(switcher.book(flightNo, user[0], user[1], this.user));
        }

    }

    private String[] getPassenger(int num) {
        String[] user = new String[2];
        user[0] = getInput("Name: ", InputTypes.NAME);
        user[1] = getInput("Surname: ", InputTypes.SURNAME);

        return user;
    }
}
