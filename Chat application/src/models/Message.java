package models;

import java.time.LocalDateTime;

public class Message {
    private final String senderId;
    private final String content;
    private final LocalDateTime timestamp;

    public Message(String senderId, String content) {
        this.senderId = senderId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getSenderId() { return senderId; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

