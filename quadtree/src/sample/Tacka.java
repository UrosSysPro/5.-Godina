package sample;

import java.util.List;

public class Tacka {
    public double x,y;
    public Vector2d brzina;
    public Vector2d ubrzanje;

    public static double maxBrzina=2;
    public static double maxUbrzanje=0.1;
    public static int vidljivost=40;

    public  Tacka(double x,double y){
        this.x=x;
        this.y=y;
        brzina=new Vector2d(0,0);
        ubrzanje=new Vector2d(0,0);
    }
    public boolean nalaziUKrugu(int x,int y,int r){
        double razdaljina=Math.sqrt((this.x-x)*(this.x-x)+(this.y-y)*(this.y-y));
        return razdaljina<r;
    }

    public Tacka updated(List<Tacka> list,int w,int h){
        Vector2d p=priblizi(list).limit(maxUbrzanje);
        Vector2d u=udalji(list).limit(maxUbrzanje);
        Vector2d s=prusmeri(list).limit(maxUbrzanje);

        Vector2d ukupno=new Vector2d(p.x+u.x+s.x,p.y+u.y+s.y).limit(maxUbrzanje);

        Tacka novo=new Tacka(x,y);
        novo.brzina=new Vector2d(brzina.x,brzina.y);

        novo.ubrzanje.x=ukupno.x;
        novo.ubrzanje.y=ukupno.y;

        novo.brzina.x+=novo.ubrzanje.x;
        novo.brzina.y+=novo.ubrzanje.y;

        novo.brzina.limit(maxBrzina);

        novo.x+=novo.brzina.x;
        novo.y+=novo.brzina.y;

        if(novo.x>w)novo.x=1;
        if(novo.x<0)novo.x=w-1;
        if(novo.y>h)novo.y=1;
        if(novo.y<0)novo.y=h-1;

        return novo;
    }
    public Vector2d priblizi(List<Tacka> list){
        if(list.size()==0)return new Vector2d(0,0);

        Vector2d prosekPozicija=new Vector2d(0,0);
        for(int i=0;i<list.size();i++){
            prosekPozicija.x+=list.get(i).x;
            prosekPozicija.y+=list.get(i).y;
        }
        prosekPozicija.x/=list.size();
        prosekPozicija.y/=list.size();

        prosekPozicija.x-=x;
        prosekPozicija.y-=y;
        return prosekPozicija;
    }

    public Vector2d udalji(List<Tacka> list){
        Vector2d v=new Vector2d(0,0);
        for(int i=0;i<list.size();i++){
            double r=razdaljinaOdTacke(list.get(i));
            if(r!=0){
                Vector2d trenutni=new Vector2d(x-list.get(i).x,y-list.get(i).y);
                trenutni.pomnozi(vidljivost/r);
                v.saberi(trenutni);
            }
        }
        v.pomnozi((double)1/list.size());

        return v;
    }

    public Vector2d prusmeri(List<Tacka> list){
        if(list.size()==0)return new Vector2d(0,0);

        Vector2d prosecanSmer=new Vector2d(0,0);
        for(int i=0;i<list.size();i++){
            prosecanSmer.x+=list.get(i).brzina.x;
            prosecanSmer.y+=list.get(i).brzina.y;
        }
        prosecanSmer.x/=list.size();
        prosecanSmer.y/=list.size();

        prosecanSmer.x-=brzina.x;
        prosecanSmer.y-=brzina.y;

        return prosecanSmer;
    }

    public double razdaljinaOdTacke(Tacka t){
        return Math.sqrt((t.x-x)*(t.x-x)+(t.y-y)*(t.y-y));
    }
}
