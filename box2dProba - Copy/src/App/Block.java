package App;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.*;

public class Block {

    public double x,y,w,h;

    public Block(double x, double y, double w, double h, World world){
        this.w=w;
        this.h=h;

        BodyDef bodyDef=new BodyDef();
        bodyDef.position.x=(float) x;
        bodyDef.position.y=(float) y;
        bodyDef.type= BodyType.DYNAMIC;

        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=1;
        fixtureDef.restitution=(float) 0.2;
        fixtureDef.friction=(float)0.3;
        fixtureDef.shape=new PolygonShape();
        ((PolygonShape)fixtureDef.shape).setAsBox((float) w,(float) h);


    }
    public void draw(GraphicsContext context){
        context.setFill(Color.BLACK);

//        context.fillRect((int)x,(int)y,(int)w,(int)h);
    }
}
