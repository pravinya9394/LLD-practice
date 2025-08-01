import model.*;
import service.SocialMediaService;

public class Main {
    public static void main(String[] args) {
        SocialMediaService sm = new SocialMediaService();

        User alice = sm.registerUser("Alice");
        User bob = sm.registerUser("Bob");
        User carol = sm.registerUser("Carol");

        alice.follow(bob);
        alice.follow(carol);

        bob.post("Bob's first post");
        carol.post("Carol's happy update");
        bob.post("Bob's second post");

        bob.getPosts().get(0).like(alice);
        bob.getPosts().get(1).like(carol);
        bob.getPosts().get(1).like(alice);

        System.out.println("Alice's Feed:");
        for (Post post : sm.getNewsFeed(alice)) {
            System.out.println(post);
        }
    }
}
