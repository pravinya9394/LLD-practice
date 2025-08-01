package models;

import java.util.*;

public class ChatRoom {
    private final String roomId;
    private final Set<String> participantIds;
    private final List<Message> messages;

    public ChatRoom(String roomId) {
        this.roomId = roomId;
        this.participantIds = new HashSet<>();
        this.messages = new ArrayList<>();
    }

    public void addParticipant(String userId) {
        participantIds.add(userId);
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public boolean isUserInRoom(String userId) {
        return participantIds.contains(userId);
    }

    public String getRoomId() { return roomId; }
}
