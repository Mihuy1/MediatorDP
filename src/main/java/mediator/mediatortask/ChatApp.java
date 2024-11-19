package mediator.mediatortask;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChatApp extends Application {

    private TextArea chatHistory1;
    private TextArea chatHistory2;
    private TextArea chatHistory3;
    private ChatMediator mediator;

    @Override
    public void start(Stage primaryStage) {
        mediator = new ChatAppMediator();
        chatHistory1 = createChatHistory();
        chatHistory2 = createChatHistory();
        chatHistory3 = createChatHistory();

        User user1 = new User("User1", mediator, chatHistory1);
        User user2 = new User("User2", mediator, chatHistory2);
        User user3 = new User("User3", mediator, chatHistory3);

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);

        Stage user1Stage = new Stage();
        setupUserWindow(user1Stage, user1, "User1", chatHistory1);
        user1Stage.show();

        Stage user2Stage = new Stage();
        setupUserWindow(user2Stage, user2, "User2", chatHistory2);
        user2Stage.show();

        Stage user3Stage = new Stage();
        setupUserWindow(user3Stage, user3, "User3", chatHistory3);
        user3Stage.show();
    }

    private HBox createTitleBar(String username) {
        HBox titleBar = new HBox();
        titleBar.setPadding(new Insets(10));
        titleBar.setSpacing(10);
        titleBar.setStyle("-fx-background-color: #333; -fx-text-fill: white;");

        Label usernameLabel = new Label("Username: " + username);
        usernameLabel.setFont(new Font(16));

        titleBar.getChildren().add(usernameLabel);
        return titleBar;
    }

    private TextArea createChatHistory() {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 14px;");
        return textArea;
    }

    // Setup user window
    private void setupUserWindow(Stage stage, User user, String username, TextArea chatHistory) {
        HBox titleBar = createTitleBar(username);

        HBox bottomPanel = createBottomPanel(user);

        BorderPane root = new BorderPane();
        root.setTop(titleBar);
        root.setCenter(chatHistory);
        root.setBottom(bottomPanel);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle(username + "'s Chat");
        stage.setScene(scene);
        stage.show();
    }

    private HBox createBottomPanel(User user) {
        HBox bottomPanel = new HBox(10);
        bottomPanel.setPadding(new Insets(10));

        ComboBox<String> recipientSelector = new ComboBox<>();
        recipientSelector.getItems().addAll("User1", "User2", "User3");
        recipientSelector.getItems().remove(user.getName());
        recipientSelector.setValue(recipientSelector.getItems().get(0));

        TextField messageField = new TextField();
        messageField.setPromptText("Type your message...");
        messageField.setPrefWidth(300);

        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> {
            String recipient = recipientSelector.getValue();
            System.out.println("Recipient: " + recipient + " Message: " + messageField.getText() + " Sender: "
                    + user.getName());
            String message = messageField.getText().trim();
            if (!message.isEmpty()) {
                user.sendMessage(recipient, message);
                messageField.clear();
            }
        });

        bottomPanel.getChildren().addAll(recipientSelector, messageField, sendButton);
        return bottomPanel;
    }

    public static void main(String[] args) {
        launch(args);
    }
}