package mediator.mediatortask;

public class Message {
    private String message;
    private String sender;
    private String recipient;

    public Message(String message, String sender, String recipient) {
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

}

