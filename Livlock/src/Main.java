//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Diner alice = new Diner("Alice");
        Diner bob = new Diner("Bob");

        Spoon spoon = new Spoon(alice);

        new Thread(() -> alice.eatWith(spoon, bob)).start();
        new Thread(() -> bob.eatWith(spoon, alice)).start();
    }
}