module mediator.mediatortask {
    requires javafx.controls;
    requires javafx.fxml;


    opens mediator.mediatortask to javafx.fxml;
    exports mediator.mediatortask;
}