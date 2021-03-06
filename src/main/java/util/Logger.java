package util;

import util.BinaryIO;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Logger {

    private final File file = new File("db", "logs.txt");

    public Logger(){
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void log(LocalDateTime localDateTime, String message){
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.format("%s    %s",
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(localDateTime),
                    message));
            bw.write("\n\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
