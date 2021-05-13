package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Circle extends Box2dItem {
    int r;
    Color color;
    public Circle(int x, int y, int r, World world, BodyType type){
        this.r=r;

        BodyDef bodyDef=new BodyDef();
        bodyDef.type=type;
        bodyDef.position=new Vec2((float) x/Main.scale,(float) y/Main.scale);
//        bodyDef.position=new Vec2((float) x,(float) y);

        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=1;
        fixtureDef.friction=(float) 0.5;
        fixtureDef.restitution=(float)0.2;

        CircleShape shape=new CircleShape();
        shape.setRadius(r/Main.scale);
        fixtureDef.shape=shape;

        body=world.createBody(bodyDef);
        fixture=body.createFixture(fixtureDef);
        this.color= Color.BLACK;
    }



    @Override
    public void draw(GraphicsContext context) {
        float x=body.getPosition().x*Main.scale;
        float y=body.getPosition().y*Main.scale;
        float a=(float) (body.getAngle()*360/2/Math.PI);
        float w=this.r;
        float h=this.r;

        context.setFill(color);

        context.translate(x,y);
        context.rotate(a);
        context.fillOval(-w,-h,w*2,h*2);
        context.rotate(-a);
        context.translate(-x,-y);
    }
}
