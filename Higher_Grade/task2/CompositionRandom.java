package Higher_Grade.task2;
import java.util.Random;

public class CompositionRandom {
    private Random random;

    public CompositionRandom() {
        this.random = new Random();
    }

    public boolean nextBoolean() {
        return this.random.nextBoolean();
    }
}
