package sample;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Flock {
    private List<Tacka> tacke;
    public int w,h;
    public QuadTree tree;
    public Flock(int w,int h){
        tacke=new LinkedList<>();
        tree=new QuadTree(w,h);
        this.w=w;
        this.h=h;
    }
    public void update(){
//        tree=new QuadTree(w,h);
        tree.koren=new Quad(0,0,w,h);
        for(int i=0;i<tacke.size();i++){
            tree.add(tacke.get(i));
        }
        List<Tacka> novaLista=new LinkedList<>();

        for(int i=0;i<tacke.size();i++){
            Tacka t=tacke.get(i);
            List<Tacka> list=tree.pronadjiUKrugu((int)t.x,(int)t.y,Tacka.vidljivost);
            novaLista.add(tacke.get(i).updated(list,w,h));
        }

        tacke=novaLista;


        tree.context.setFill(Color.rgb(255,255,255,0.05));
        tree.context.fillRect(0,0,w,h);
        tree.context.setFill(Color.BLACK);
        tree.nacrtajStablo(tree.koren);
        tree.nacrtajTacke(tree.koren);
    }

    public void add(double x,double y){
        tacke.add(new Tacka(x,y));
    }
}
