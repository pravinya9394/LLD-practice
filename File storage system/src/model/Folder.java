package model;

import java.util.*;

public class Folder extends FileSystemNode {
    private List<FileSystemNode> children = new ArrayList<>();

    public Folder(String id, String name, User owner) {
        super(id, name, owner);
    }

    public void add(FileSystemNode node) {
        children.add(node);
    }

    public void remove(FileSystemNode node) {
        children.remove(node);
    }

    public List<FileSystemNode> getChildren() {
        return children;
    }

    @Override
    public long getSize() {
        return children.stream().mapToLong(FileSystemNode::getSize).sum();
    }
}

