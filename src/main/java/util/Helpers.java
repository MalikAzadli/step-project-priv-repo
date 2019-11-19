package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class Helpers {

    public static String center(String text, int len) {
//        String result = String.format("%"+len+"s%s%"+len+"s", "",text,"");
//        float mid = (result.length()/2);
//        float start = mid - (len/2);
//        float end = start + len;
//        return result.substring((int) start, (int) end);


        String line = String.format("");
        return line;
    }


    public static LocalDate parseStringToDate(String str) {
        DateTimeFormatter frm = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(str, frm);
    }
}
