package command_entities;

import model.User;
import ui.*;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Login extends CommandBlueprint {

    public Login(Console console, Validator validator, Switcher switcher) {
        super(console, validator, switcher);
    }

    @Override
    public void execute() {

        List<Pair> requirements = new ArrayList<>(Arrays.asList(
                new Pair("Username: ", InputTypes.REGISTERED_USERNAME),
                new Pair("Password: ", InputTypes.REGISTERED_PASSWORD)));

        List<String> inputs = new ArrayList<>();
        for(Pair p: requirements){
            String input = getInput(p.getMessage(), p.getTypes());
            if(input.isEmpty()) return;
            inputs.add(input);
        }

        Optional<User> result = switcher.login(inputs.get(0), inputs.get(1));
        if (result.isPresent()) {
            user = result.get();
        }
    }
}
