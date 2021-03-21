package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        QuadTree tree=new QuadTree(500,500);

        Pane pane=new Pane(tree.canvas);
        Scene scene=new Scene(pane);

        tree.canvas.setOnMouseClicked(mouseEvent -> {
            tree.add(new Tacka((int)mouseEvent.getX(),(int)mouseEvent.getY()));
            tree.nacrtajTacke(tree.koren);
            tree.nacrtajStablo(tree.koren);
        });
        tree.canvas.setOnMouseMoved(mouseEvent -> {
            tree.context.clearRect(0,0,tree.canvas.getWidth(),tree.canvas.getHeight());
            int r=50;
            tree.nacrtajTacke(tree.koren);
            tree.nacrtajStablo(tree.koren);
            List<Tacka> list=tree.pronadjiUKrugu((int)mouseEvent.getX(),(int)mouseEvent.getY(),r);
            tree.context.setFill(Color.GREEN);
            for(int i=0;i<list.size();i++){
                tree.context.fillRect(list.get(i).x,list.get(i).y,5,5);
            }

            tree.context.setFill(Color.BLACK);
            tree.context.strokeOval(mouseEvent.getX()-r,mouseEvent.getY()-r,2*r,2*r);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
