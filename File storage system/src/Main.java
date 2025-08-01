import model.Folder;
import model.User;
import service.StorageService;

import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StorageService storage = new StorageService();
        User alice = new User("u1", "Alice");
        storage.createUser(alice);

        Folder root = storage.getRootFolder(alice.id);
        storage.uploadFile(root, "resume.pdf", "My Resume".getBytes(), alice);

        Folder docs = new Folder(UUID.randomUUID().toString(), "Docs", alice);
        root.add(docs);
        storage.uploadFile(docs, "offer.txt", "Congratulations!".getBytes(), alice);

        storage.list(root);
        storage.list(docs);
    }
}