package test.se.kth.iv1350.pointOfSale.startup;

import se.kth.iv1350.pointOfSale.startup.Main;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUpStreams() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void cleanUpStreams() {
        System.setOut(originalSysOut);
        outContent = null;
    }

    @Test
    void testMain(){
        Main.main(null);
        String result = outContent.toString();
        assertTrue(result.contains("Added 59,70 to total revenue. Accumulated revenue:"), "Accumulated Revenue not found in output");
    }
}
