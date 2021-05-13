package sample;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import java.util.Random;

public class Main extends Application {

    public static float scale=30;
    public Box2dItem[] items;
    public int n;
    public Timeline timer;
    public Random random;
    public long staroVreme;
    public int targetFPS;
    @Override
    public void start(Stage stage) throws Exception {
        targetFPS=30;
        staroVreme=0;
        random=new Random();
        int w=800,h=600;
        n=0;
        items=new Box2dItem[1000];

        Label fpslabel=new Label();
        fpslabel.setLayoutX(30);
        fpslabel.setLayoutY(30);

        Canvas canvas=new Canvas(w,h);
        GraphicsContext context=canvas.getGraphicsContext2D();

        Pane pane=new Pane(canvas,fpslabel);
        Scene scene=new Scene(pane);

        World world=new World(new Vec2(0,10));
//        Block b=new Block(w/2,h/2,20,20, world,BodyType.DYNAMIC, Color.CORNFLOWERBLUE);
//        b.draw(context);
        items[0]=new Block(w/2,10,w/2,10,world,BodyType.STATIC);
        items[1]=new Block(w/2,h-10,w/2,10,world,BodyType.STATIC);
        items[2]=new Block(10,h/2,10,h/2,world,BodyType.STATIC);
        items[3]=new Block(w-10,h/2,10,h/2,world,BodyType.STATIC);
        n=4;



        scene.setOnMouseDragged(mouseEvent -> {
            int x=(int)mouseEvent.getX();
            int y=(int)mouseEvent.getY();
            Color c=Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));

            if(mouseEvent.getButton()== MouseButton.PRIMARY){
                items[n]=new Block(x,y,10,10,world,BodyType.DYNAMIC,c);

            }else{
                items[n]=new Circle(x,y,10,world, BodyType.DYNAMIC);
            }
//            items[n]=new Block(x,y,10,10,world,BodyType.DYNAMIC,c);


            n++;
        });



        timer=new Timeline(new KeyFrame(Duration.millis((float)1000/targetFPS),actionEvent -> {
            world.step((float)1/targetFPS,5,5);
            context.clearRect(0,0,w,h);
            for(int i=0;i<n;i++){
                items[i].draw(context);
            }
            long time=System.currentTimeMillis();
            fpslabel.setText(n+""+(int)((double)1000/(time-staroVreme)));
            staroVreme=time;
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();


        stage.setScene(scene);
        stage.setTitle("Box2d");
        stage.setOnCloseRequest(windowEvent -> timer.stop());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
