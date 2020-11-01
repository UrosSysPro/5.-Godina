package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Canvas canvas=new Canvas(600,300);
        Pane pane=new Pane();
        pane.getChildren().add(canvas);
        Scene scene=new Scene(pane);

        primaryStage.setScene(scene);

        GraphicsContext context=canvas.getGraphicsContext2D();

        Image slika=new Image("\\monster0.jpg");
        PixelReader reader=slika.getPixelReader();

        WritableImage slika2=new WritableImage(300,300);
        PixelWriter writer=slika2.getPixelWriter();

        for(int j=0;j<300;j++){
            for(int i=0;i<300;i++){
                Color boja;
                boja=reader.getColor(i,j);
                double r,g,b,a;
                r=boja.getRed();
                g=boja.getGreen();
                b=boja.getRed();

                boja=reader.getColor(i,j);
                writer.setColor(i,j,new Color(r,g,b,1));
            }
        }

        context.drawImage(slika,0,0);
        context.drawImage(slika2,300,0);

//        canvas.setOnMouseMoved(mouseEvent -> {
//            PixelReader reader=slika.getPixelReader();
//            if(mouseEvent.getX()<300) {
//                Color boja = reader.getColor((int) mouseEvent.getX(), (int) mouseEvent.getY());
//                System.out.println(boja.getRed() * 255 + " " + boja.getGreen() * 255 + " " + boja.getBlue() * 255);
//            }
//        });

        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
