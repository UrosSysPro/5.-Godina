package sample;

public class Tacka {
    public int x,y;
    public Tacka(int x,int y){
        this.x=x;
        this.y=y;
    }
    public Tacka(Tacka t){
        this.x=t.x;
        this.y=t.y;
    }
}
