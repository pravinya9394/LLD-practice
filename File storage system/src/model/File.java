package model;

public class File extends FileSystemNode {
    public byte[] content;

    public File(String id, String name, byte[] content, User owner) {
        super(id, name, owner);
        this.content = content;
    }

    @Override
    public long getSize() {
        return content.length;
    }
}
