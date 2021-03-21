package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.List;

public class QuadTree {
    public Quad koren;
    public Canvas canvas;
    public GraphicsContext context;

    public QuadTree(int w,int h){
        koren=new Quad(0,0,w,h);
        canvas=new Canvas(w,h);
        context=canvas.getGraphicsContext2D();
    }
    public void add(Tacka t){
        koren.add(t);
    }

    public List<Tacka> pronadjiUKrugu(int x,int y,int r){
        List<Tacka> list=new LinkedList<Tacka>();

        pronadjiRekurzivno(koren,list,x,y,r);

        return list;
    }
    public void pronadjiRekurzivno(Quad q,List<Tacka> list,int x,int y,int r){
        if(q.preklapaSaKrugom(x,y,r)){
            if(q.podeljen){
                for(int i=0;i<q.nizKvadrata.length;i++){
                    pronadjiRekurzivno(q.nizKvadrata[i],list,x,y,r);
                }
            }else{
                for(int i=0;i<q.niz.size();i++){
                    if(q.niz.get(i).nalaziUKrugu(x,y,r)){
                        list.add(q.niz.get(i));
                    }
                }
            }
        }
    }

    public void nacrtajStablo(Quad q){
        if(q.podeljen){
            for(int i=0;i<q.nizKvadrata.length;i++){
                nacrtajStablo(q.nizKvadrata[i]);
            }
        }else{
            context.strokeRect(q.x,q.y,q.w,q.h);
        }
    }
    public void nacrtajTacke(Quad q){
        if(q.podeljen){
            for(int i=0;i<q.nizKvadrata.length;i++){
                nacrtajTacke(q.nizKvadrata[i]);
            }
        }else{
            for(int i=0;i<q.niz.size();i++){
                Tacka t=q.niz.get(i);
                context.fillRect(t.x,t.y,2,2);
            }
        }
    }

}
