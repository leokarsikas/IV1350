package Higher_Grade.task2;

import java.util.Random;

public class InheritRandom extends Random {

    @Override
    public boolean nextBoolean() {
        return next(1) != 0;
    }
}
