package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    int w,h;
    @Override
    public void start(Stage primaryStage) throws Exception{
        w=30;
        h=30;

        Image image=new Image("slika.jpg",w,h,false,false);
        printImage(image);
        ImageView view=new ImageView(image);
        view.setFitHeight(300);
        view.setFitWidth(300);


        Pane pane=new Pane(view);
        Scene scene=new Scene(pane);


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void printImage(Image image){
        PixelReader reader=image.getPixelReader();
        char[] paleta=new char[]{' ','.',':','/','1','8','#'};

        for(int j=0;j<h;j++){
            for(int i=0;i<w;i++) {
                double r=reader.getColor(i,j).getRed();
                double g=reader.getColor(i,j).getGreen();
                double b=reader.getColor(i,j).getBlue();
                double prosek=(r+g+b)/3;
                int index=(int)Math.round(prosek*(paleta.length-1));
                System.out.print(" "+paleta[index]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
