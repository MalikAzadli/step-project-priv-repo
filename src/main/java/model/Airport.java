package model;


import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public enum Airport {
    LONDON("LHR"),
    BAKU("GYD"),
    KYIV("KBP"),
    MOSCOW("SVO"),
    ISTANBUL("IST"),
    FRANKFURT("FRA"),
    NEWYORK("JFK");

    private final String code;

    Airport(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Airport getRandomCity() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }

    public static boolean isValid(String city){
        List<Airport> airports = Arrays.stream(values())
                .filter(airport -> city.equalsIgnoreCase(airport.toString()))
                .collect(Collectors.toList());

        if(airports.isEmpty()) return false;
        return true;
    }

    public static Airport get(String city){
        List<Airport> airports = Arrays.stream(values())
                .filter(airport -> city.equalsIgnoreCase(airport.toString()))
                .collect(Collectors.toList());
        return airports.get(0);
    }
}
