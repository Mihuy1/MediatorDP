package mediator.mediatortask;

import javafx.scene.control.TextArea;

public class User {
    private String name;
    private ChatMediator mediator;
    private TextArea chatHistory;

    public User(String name, ChatMediator mediator, TextArea chatHistory) {
        this.name = name;
        this.mediator = mediator;
        this.chatHistory = chatHistory;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String recipient, String message) {
        mediator.sendMessage(this, recipient, message);
        chatHistory.appendText("You to " + recipient + ": " + message + "\n");
    }

    public void receiveMessage(Message message) {
        chatHistory
                .appendText(message.getSender() + " to " + message.getRecipient() + ": " + message.getMessage() + "\n");
    }
}