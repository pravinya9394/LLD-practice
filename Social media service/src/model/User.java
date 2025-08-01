package model;

import java.util.*;

public class User {
    public String id;
    public String name;
    public Set<User> following = new HashSet<>();
    public Set<User> followers = new HashSet<>();
    public List<Post> posts = new ArrayList<>();

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public void follow(User user) {
        if (!this.equals(user)) {
            this.following.add(user);
            user.followers.add(this);
        }
    }

    public void unfollow(User user) {
        this.following.remove(user);
        user.followers.remove(this);
    }

    public void post(String content) {
        this.posts.add(new Post(content, this));
    }

    public List<Post> getPosts() {
        return posts;
    }
}
