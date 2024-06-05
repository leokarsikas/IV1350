package Higher_Grade.task2;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        InheritRandom inheritance = new InheritRandom();
        System.out.println("INHERITANCE:");
        System.out.println("InheritRandom boolean: " + inheritance.nextBoolean());
        System.out.println("InheritRandom boolean: " + inheritance.nextBoolean());

        System.out.println("---------------VS---------------");

        System.out.println("COMPOSITION:");
        CompositionRandom composition = new CompositionRandom();
        System.out.println("CompositionRandom boolean: " + composition.nextBoolean());
        System.out.println("CompositionRandom boolean: " + composition.nextBoolean());
        System.out.println();
    }
}
