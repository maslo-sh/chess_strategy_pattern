package sample.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gamelayout.fxml"));
        primaryStage.setTitle("Chess Movement Presentation");
        Scene scene = new Scene(root, 610, 720, Color.DARKBLUE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void run(String[] args) {
        launch(args);
    }
}
