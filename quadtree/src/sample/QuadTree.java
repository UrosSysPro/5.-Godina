package sample;

import java.util.List;

public class QuadTree {
    public Quad koren;
    public QuadTree(int w,int h){
        koren=new Quad(0,0,w,h);
    }
    public void add(Tacka t){
        koren.add(t);
    }
    public List<Tacka> pronadjiUKrugu(){
        return null;
    }
    public void nacrtajStablo(Quad q){
        if(q.podeljen){
            for(int i=0;i<q.nizKvadrata.length;i++){
                nacrtajStablo(q.nizKvadrata[i]);
            }
        }else{
//            context.strokeRect()
        }
    }
    public void nacrtajTacke(Quad q){
        if(q.podeljen){
            for(int i=0;i<q.nizKvadrata.length;i++){
                nacrtajTacke(q.nizKvadrata[i]);
            }
        }else{
//            for(int i=0;i<q.niz.size();i++)
//              context.fillOval()

        }
    }

}
