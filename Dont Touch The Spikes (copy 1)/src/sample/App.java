package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Forma.fxml"));
        loader.load();

        Forma controller=loader.getController();

        Pane forma=loader.getRoot();
        Scene scene=new Scene(forma);

        controller.init();

        scene.setOnKeyPressed(keyEvent -> {
            controller.game.lastPressedKey=keyEvent.getCode();
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
