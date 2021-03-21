package sample;

public class Vector2d {
    public double x,y;
    public Vector2d(double x,double y){
        this.x=x;
        this.y=y;
    }
    public Vector2d saberi(Vector2d v){
        x+=v.x;
        y+=v.y;
        return this;
    }
    public Vector2d pomnozi(double a){
        x*=a;
        y*=a;
        return this;
    }
    public double intenzitet(){
        return Math.sqrt(x*x+y*y);
    }
    public Vector2d normal(){
        double i=intenzitet();
        if(i==0)return this;
        x/=i;
        y/=i;
        return  this;
    }
    public Vector2d limit(double max){
        double i=intenzitet();
        if(i>max){
            normal();
            pomnozi(max);
        }
        return this;
    }
}
