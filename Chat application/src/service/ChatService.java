package service;

import models.ChatRoom;
import models.Message;
import models.User;

import java.util.*;

public class ChatService {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, ChatRoom> chatRooms = new HashMap<>();

    public void registerUser(String id, String name) {
        users.put(id, new User(id, name));
    }

    public void createRoom(String roomId, List<String> userIds) {
        ChatRoom room = new ChatRoom(roomId);
        for (String userId : userIds) {
            if (!users.containsKey(userId)) throw new IllegalArgumentException("User not found: " + userId);
            room.addParticipant(userId);
        }
        chatRooms.put(roomId, room);
    }

    public void sendMessage(String roomId, String senderId, String content) {
        if (!chatRooms.containsKey(roomId)) throw new IllegalArgumentException("Room not found");
        if (!users.containsKey(senderId)) throw new IllegalArgumentException("Sender not found");

        ChatRoom room = chatRooms.get(roomId);
        if (!room.isUserInRoom(senderId)) throw new IllegalArgumentException("User not in chat");

        Message msg = new Message(senderId, content);
        room.sendMessage(msg);
    }

    public List<Message> getMessages(String roomId) {
        if (!chatRooms.containsKey(roomId)) throw new IllegalArgumentException("Room not found");
        return chatRooms.get(roomId).getMessages();
    }
}

