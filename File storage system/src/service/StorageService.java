package service;

//import com.filestorage.model.*;

import model.File;
import model.FileSystemNode;
import model.Folder;
import model.User;

import java.util.*;

public class StorageService {
    private final Map<String, Folder> userRootFolders = new HashMap<>();

    public void createUser(User user) {
        userRootFolders.put(user.id, new Folder(UUID.randomUUID().toString(), "/", user));
    }

    public Folder getRootFolder(String userId) {
        return userRootFolders.get(userId);
    }

    public void uploadFile(Folder folder, String name, byte[] content, User owner) {
        File file = new File(UUID.randomUUID().toString(), name, content, owner);
        folder.add(file);
    }

    public void delete(Folder parent, FileSystemNode node) {
        parent.remove(node);
    }

    public void list(Folder folder) {
        System.out.println("Contents of folder: " + folder.name);
        for (FileSystemNode node : folder.getChildren()) {
            System.out.println("- " + node.name + " (" + node.getSize() + " bytes)");
        }
    }
}

