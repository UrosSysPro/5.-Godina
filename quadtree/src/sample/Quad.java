package sample;

import java.util.LinkedList;
import java.util.List;

public class Quad {
    public int x,y;
    public int w,h;
    public List<Tacka> niz;
    public boolean podeljen;
    public Quad[] nizKvadrata;

    public static int max=10;

    public Quad(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;

        niz=new LinkedList<Tacka>();
        podeljen=false;
        nizKvadrata=null;
    }
    public void add(Tacka t){
        if (podeljen) {
            for(int i=0;i<nizKvadrata.length;i++){
                nizKvadrata[i].add(t);
            }
        }else{
            if(pripada(t)){
                niz.add(t);
                if(niz.size()>max){
                    podeli();
                }
            }
        }
    }
    private void podeli(){
        podeljen=true;
        nizKvadrata=new Quad[4];
        nizKvadrata[0]=new Quad(x+w/2,y    ,w/2,h/2);
        nizKvadrata[1]=new Quad(  x,    y,    w/2,h/2);
        nizKvadrata[2]=new Quad(  x,  y+h/2,w/2,h/2);
        nizKvadrata[3]=new Quad(x+w/2,y+h/2,w/2,h/2);
        for(int i=0;i<niz.size();i++){
            for(int j=0;j<nizKvadrata.length;j++){
                nizKvadrata[j].add(niz.get(i));
            }
        }
    }
    private boolean pripada(Tacka t){
        return x<t.x && x+w>t.x && y<t.y && y+h>t.y;
    }

    public boolean preklapaSaKrugom(int x,int y,int r){
        if(x-r>this.x+w||x+r<this.x||y-r>this.y+h||y+r<this.y){
            return false;
        }
        return true;
    }
}
