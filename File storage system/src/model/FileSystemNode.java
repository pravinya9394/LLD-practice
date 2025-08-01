package model;

import java.time.LocalDateTime;

public abstract class FileSystemNode {
    public String id;
    public String name;
    public User owner;
    public LocalDateTime createdAt;

    public FileSystemNode(String id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.createdAt = LocalDateTime.now();
    }

    public abstract long getSize();
}
