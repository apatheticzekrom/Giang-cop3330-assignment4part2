/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Benjamin Giang
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class app extends Application
{
    // Launch code
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // More launch code
        try{
            Parent root = FXMLLoader.load(getClass().getResource("appGui.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("ToDoList");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
