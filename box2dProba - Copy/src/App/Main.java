package App;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public int w,h,n;
    public Block[] blockovi;
    public World world;

    @Override
    public void start(Stage primaryStage) throws Exception {
        w=800;
        h=600;
        Canvas canvas=new Canvas(w,h);
        Pane pane=new Pane(canvas);
        Scene scene=new Scene(pane);

        world=new World(new Vec2(0,(float) 9.8));

        blockovi=new Block[100];
        n=0;

        scene.setOnMouseClicked(event -> {
            blockovi[n]=new Block(event.getX(),event.getY(),20,20,world);
            blockovi[n].draw(canvas.getGraphicsContext2D());
            n++;
        });


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
