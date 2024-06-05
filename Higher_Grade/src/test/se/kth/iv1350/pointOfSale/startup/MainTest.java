package test.se.kth.iv1350.pointOfSale.startup;

import se.kth.iv1350.pointOfSale.startup.Main;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {

    private ByteArrayOutputStream outPut;

    @BeforeEach
    public void setUp() {
        outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));
    }
    
    @AfterEach
    public void cleanUp() {
        outPut = null;
    }

    @Test
    void testMain(){
        Main.main(null); // executing main with no arguments, that is, it is a regular run. 
        String result = outPut.toString();
        assertTrue(result.contains("Start new sale! This is sale number 1 today."), "Start new Sale! This is sale number 1 today should be present in output");
    }
}
