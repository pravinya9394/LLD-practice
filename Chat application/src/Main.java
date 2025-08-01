import models.Message;
import service.ChatService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
        public static void main(String[] args) {
            ChatService chatService = new ChatService();

            // Register users
            chatService.registerUser("u1", "Alice");
            chatService.registerUser("u2", "Bob");
            chatService.registerUser("u3", "Charlie");
            chatService.registerUser("u4", "Diana");

            // Test 1: Create 1:1 room and exchange messages
            chatService.createRoom("room1", List.of("u1", "u2"));
            chatService.sendMessage("room1", "u1", "Hi Bob!");
            chatService.sendMessage("room1", "u2", "Hey Alice!");
            printRoomHistory(chatService, "room1");

            // Test 2: Group chat with 3 members
            chatService.createRoom("room2", List.of("u2", "u3", "u4"));
            chatService.sendMessage("room2", "u2", "Hello team!");
            chatService.sendMessage("room2", "u3", "Hi all!");
            chatService.sendMessage("room2", "u4", "Good to see you!");
            printRoomHistory(chatService, "room2");

            // Test 3: Unauthorized user tries to send message
            try {
                chatService.sendMessage("room1", "u3", "Should not work");
            } catch (Exception e) {
                System.out.println("[Test Passed] Unauthorized send attempt blocked: " + e.getMessage());
            }

            // Test 4: Non-existent room
            try {
                chatService.sendMessage("roomX", "u1", "Invalid room");
            } catch (Exception e) {
                System.out.println("[Test Passed] Non-existent room handled: " + e.getMessage());
            }

            // Test 5: Invalid user send
            try {
                chatService.sendMessage("room1", "uX", "Invalid user");
            } catch (Exception e) {
                System.out.println("[Test Passed] Unknown user handled: " + e.getMessage());
            }

            // Test 6: Empty room history
            chatService.createRoom("room3", List.of("u1", "u3"));
            System.out.println("room3 history (expected 0): " + chatService.getMessages("room3").size());
        }

        private static void printRoomHistory(ChatService service, String roomId) {
            System.out.println("\n📨 Chat History for Room: " + roomId);
            for (Message msg : service.getMessages(roomId)) {
                System.out.println(msg.getTimestamp() + " [" + msg.getSenderId() + "]: " + msg.getContent());
            }
        }
}

//Here are the core design principles applied in your chat application implementation — each aligned with what top-tier companies (like Amazon, Razorpay, Meesho, etc.) expect in an LLD round:
//
//✅ 1. Single Responsibility Principle (SRP)
//Each class has one clear purpose:
//
//User → represents a user
//
//Message → represents a chat message
//
//ChatRoom → manages messages and participants
//
//ChatService → handles business logic
//
//📌 Keeps code modular and easy to test or extend.
//
//✅ 2. Open/Closed Principle (OCP)
//Classes are open for extension but closed for modification:
//
//You can add features like message reactions, attachments, or group admin without changing existing classes.
//
//For example, extending ChatRoom into GroupChatRoom or PrivateChatRoom is straightforward.
//
//✅ 3. Encapsulation
//Internal state like participantIds and messages is hidden inside ChatRoom.
//
//Access is only allowed via defined methods like sendMessage() and addParticipant().
//
//🔒 Keeps data safe and class internals decoupled.
//
//✅ 4. Abstraction
//Classes expose only necessary interfaces (ChatService.sendMessage(), etc.), hiding implementation logic.
//
//User doesn’t need to know how messages are stored or retrieved.
//
//📦 Consumers of the service only see what they need.
//
//✅ 5. Don't Repeat Yourself (DRY)
//Centralized logic in ChatService prevents code duplication for validation and room/message operations.
//
//Clear separation prevents repeating checks in multiple places.
//
//✅ 6. Fail Fast Principle
//Invalid actions like sending a message to a non-existent room or unauthorized user are caught early with descriptive exceptions.
//
//💡 Ensures reliability and debuggability.
//
//✅ 7. Separation of Concerns
//Models (User, Message, ChatRoom) hold data
//
//ChatService encapsulates interaction logic
//
//App acts as a test client/driver
//
//📐 This separation makes the code clean, scalable, and future-proof.
//
//🏁 Bonus: Ready for SOLID Compliance
//You can easily refactor further to apply:
//
//Liskov Substitution (e.g. subclassing chat types)
//
//Dependency Inversion (e.g. abstract storage layer)