import model.Screen;
import model.Show;
import model.Theater;
import model.User;
import service.BookingService;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        User user1 = new User("u1", "Alice");
        User user2 = new User("u2", "Bob");

        // Create theater and screen
        Theater theater = new Theater("PVR Cinemas");
        Screen screen1 = new Screen("Screen1", 10);
        theater.addScreen(screen1);

        // Create show
        Show show1 = new Show("s1", "Inception", screen1);
        theater.addShow(show1);

        // Booking service
        BookingService bookingService = new BookingService();

        // Simulate booking
        bookingService.bookSeats(show1, Arrays.asList(1, 2, 3), user1);

        // Concurrency test
        Thread t1 = new Thread(() -> {
            bookingService.bookSeats(show1, Arrays.asList(3, 4), user2);
        });

        Thread t2 = new Thread(() -> {
            bookingService.bookSeats(show1, Arrays.asList(5, 6), user2);
        });

        t1.start();
        t2.start();
    }
}