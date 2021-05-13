package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Block extends Box2dItem{
    public float w,h;
    public Color color;
    public Block(int x, int y, int w, int h, World world, BodyType type){
        this.w=w;
        this.h=h;

        BodyDef bodyDef=new BodyDef();
        bodyDef.type=type;
        bodyDef.position=new Vec2((float) x/Main.scale,(float) y/Main.scale);

        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=1;
        fixtureDef.friction=(float) 0.5;
        fixtureDef.restitution=(float)0.2;

        PolygonShape shape=new PolygonShape();
        shape.setAsBox((float) w/Main.scale,(float) h/Main.scale);
        fixtureDef.shape=shape;

        body=world.createBody(bodyDef);
        fixture=body.createFixture(fixtureDef);
        this.color=Color.BLACK;
    }

    public Block(int x, int y, int w, int h, World world, BodyType type,Color c){
        this(x,y,w,h,world,type);
        this.color=c;
    }
    @Override
    public void draw(GraphicsContext context) {
        float x=body.getPosition().x*Main.scale;
        float y=body.getPosition().y*Main.scale;
        float a=(float) (body.getAngle()*360/2/Math.PI);
        float w=this.w;
        float h=this.h;

        context.setFill(color);

        context.translate(x,y);
        context.rotate(a);
        context.fillRect(-w,-h,w*2,h*2);
        context.rotate(-a);
        context.translate(-x,-y);
    }
}
