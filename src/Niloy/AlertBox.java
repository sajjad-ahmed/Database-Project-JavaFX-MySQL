package Niloy;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox
{
    public static boolean approval;

    public static void alert_with_NO_Action(String title, String message)
    {
        Stage window = new Stage();
        window.getIcons().add(new Image("assets/icon.png"));

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(150);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Discard");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(25);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);


        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void delete_Alert(String title, String message)
    {
        Stage window = new Stage();
        window.getIcons().add(new Image("assets/icon.png"));
        approval = false;
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(150);

        Label label = new Label();
        label.setText(message);
        label.setStyle("-fx-margin: 7px");

        //Create two buttons
        Button yesButton = new Button("Yes");
        yesButton.setStyle("-fx-text-fill: Black");
        Button noButton = new Button("No");

        //Clicking will set answer and close window
        yesButton.setOnAction(e ->
        {
            approval = true;
            window.close();
        });
        noButton.setOnAction(e ->
        {
            approval = false;
            window.close();
        });


        HBox hBox = new HBox(30);
        hBox.getChildren().addAll(noButton, yesButton);
        hBox.setAlignment(Pos.CENTER);
        //Add buttons
        VBox parentVbox = new VBox(10);
        parentVbox.setStyle("-fx-background-color: #ffffff");
        parentVbox.getChildren().addAll(label, hBox);
        parentVbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(parentVbox);
        window.setScene(scene);
        window.showAndWait();

    }

    public static void alert_With_DB_DELETE_Operation(String title, String message, DBConnect dbConnect, String tableName, String key)
    {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}