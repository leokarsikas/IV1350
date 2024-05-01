package test.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.model.Register;

public class RegisterTest {
    private Register register;

    @BeforeEach
    public void setUp() {
        register = new Register();
    }

    @Test
    public void testUpdateAmountInRegister() {

        register.updateAmountInRegister(100.0, 0.0);
        register.updateAmountInRegister(50.0, 10.0);

        assertEquals(140.0, register.getAmount(), "Incorrect Amount result in register");
    }

}
