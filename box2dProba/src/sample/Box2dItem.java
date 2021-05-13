package sample;

import javafx.scene.canvas.GraphicsContext;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

public abstract class Box2dItem {
    public Body body;
    public Fixture fixture;

    public abstract void draw(GraphicsContext context);
}
