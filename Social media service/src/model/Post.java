package model;

import java.util.*;

public class Post {
    public String id;
    public String content;
    public User author;
    public long timestamp;
    public Set<User> likedBy = new HashSet<>();

    public Post(String content, User author) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.author = author;
        this.timestamp = System.currentTimeMillis();
    }

    public void like(User user) {
        likedBy.add(user);
    }

    public void unlike(User user) {
        likedBy.remove(user);
    }

    public int likeCount() {
        return likedBy.size();
    }

    @Override
    public String toString() {
        return author.name + ": \"" + content + "\" ❤️ " + likeCount();
    }
}
