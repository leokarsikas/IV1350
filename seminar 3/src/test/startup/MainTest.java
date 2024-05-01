package test.startup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import se.kth.iv1350.pointOfSale.integration.Printer;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void main() {
        System.out.println("Main");
        String[] args = null;
    }

    @Test 

    public void testPrinter() {
      Printer printer = new Printer();
      String testOutput = "Test output";
      printer.setOutput(testOutput);
      assertEquals(testOutput, printer.getOutput());
     
    
    }
}