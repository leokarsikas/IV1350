package se.kth.iv1350.pointOfSale;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLog {
    private PrintWriter logContent;

    public FileLog() {
        try {
            logContent = new PrintWriter(new FileWriter("seminar4.txt"));
        }
        catch(IOException e){
        }
    }

    public void log(String msg){
        logContent.println(msg);
    }
}
