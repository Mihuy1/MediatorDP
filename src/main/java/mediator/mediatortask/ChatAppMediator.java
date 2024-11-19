package mediator.mediatortask;

import java.util.HashMap;
import java.util.Map;

public class ChatAppMediator implements ChatMediator {

    private Map<String, User> users = new HashMap<String, User>();

    @Override
    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public void sendMessage(User sender, String recipient, String message) {
        User recipientUser = users.get(recipient);
        if (recipientUser != null) {
            recipientUser.receiveMessage(new Message(message, sender.getName(), recipient));

        }
    }
}

