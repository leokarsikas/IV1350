package se.kth.iv1350.pointOfSale;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLog{
    private PrintWriter printer;

    public FileLog() {
        try {
            printer = new PrintWriter(new FileWriter("seminar4.txt",true));
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
