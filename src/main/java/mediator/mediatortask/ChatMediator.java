package mediator.mediatortask;

public interface ChatMediator {
    void addUser(User user);
    void sendMessage(User sender, String recipient, String message);
}

