package service;

import model.*;

import java.util.*;

public class SocialMediaService {
    private final Map<String, User> users = new HashMap<>();

    public User registerUser(String name) {
        User user = new User(name);
        users.put(user.id, user);
        return user;
    }

    public List<Post> getNewsFeed(User user) {
        List<Post> feed = new ArrayList<>();
        for (User followed : user.following) {
            feed.addAll(followed.getPosts());
        }
        feed.sort((a, b) -> Long.compare(b.timestamp, a.timestamp)); // Recent first
        return feed;
    }
}
