package se.kth.iv1350.pointOfSale;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements MessageCreator{
    private PrintWriter printer;

    public FileLogger(String filename) {
        try {
            printer = new PrintWriter(new FileWriter(filename,true));
        }
        catch(IOException exception){
            System.out.println("Could not log error to file, "+exception.getMessage());
        }
    }

    public void log(String message){
        printer.println(message);
        printer.flush();
    }
}
