package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class polje {
    public int w,h;
    private int[][] mat;

    public polje(int w,int h){
        this.w=w;
        this.h=h;
        mat=new int[w][h];
        for(int j=0;j<h;j++){
            for(int i=0;i<w;i++){
                mat[i][j]=-1;
            }
        }
    }
    public polje(String imeFajla) throws FileNotFoundException {
        Scanner scan=new Scanner(new File(imeFajla));
        this.w=Integer.parseInt(scan.next());
        this.h=Integer.parseInt(scan.next());
        mat=new int[this.w][this.h];
        for(int j=0;j<h;j++){
            for(int i=0;i<w;i++){
                mat[i][j]=Integer.parseInt(scan.next());
            }
        }
    }
    public void postaviZid(int x,int y){
        mat[x][y]=-2;
    }
    public void start(int x,int y){
        mat[x][y]=0;
        LinkedList<Tacka> red=new LinkedList<Tacka>();
        red.add(new Tacka(x,y));
        while(red.isEmpty()==false){
            Tacka t=red.removeFirst();

            if(t.x-1>=0){
                if(mat[t.x-1][t.y]==-1){
                    mat[t.x-1][t.y]=mat[t.x][t.y]+1;
                    red.add(new Tacka(t.x-1,t.y));
                }
            }
            if(t.x+1<w){
                if(mat[t.x+1][t.y]==-1){
                    mat[t.x+1][t.y]=mat[t.x][t.y]+1;
                    red.add(new Tacka(t.x+1,t.y));
                }
            }
            if(t.y-1>=0){
                if(mat[t.x][t.y-1]==-1){
                    mat[t.x][t.y-1]=mat[t.x][t.y]+1;
                    red.add(new Tacka(t.x,t.y-1));
                }
            }
            if(t.y+1<h){
                if(mat[t.x][t.y+1]==-1){
                    mat[t.x][t.y+1]=mat[t.x][t.y]+1;
                    red.add(new Tacka(t.x,t.y+1));
                }
            }
        }
    }
    public void ispis(){
        for(int j=0;j<h;j++){
            for(int i=0;i<w;i++){
                switch (mat[i][j]){
                    case -1:
                        System.out.print("   ");
                    break;
                    case -2:
                        System.out.print(" ##");
                    break;
                    default:
                        System.out.print(mat[i][j]>=10?" "+mat[i][j]:"  "+mat[i][j]);
                    break;
                }
//                System.out.print(" "+mat[i][j]);
            }
            System.out.println();
        }
    }
    public void nacrtaj(Canvas canvas,int sirina){
        GraphicsContext context=canvas.getGraphicsContext2D();
        double r1=0,g1=0,b1=1;
        double r2=1,g2=0.5,b2=0.5;
        int max=mat[0][0];
        for(int j=0;j<h;j++)
            for(int i=0;i<w;i++)
                if(mat[i][j]>max)
                    max=mat[i][j];

        for(int j=0;j<h;j++){
            for(int i=0;i<w;i++){
                if(mat[i][j]==-2){
                    context.setFill(new Color(0,0,0,1));
                    context.fillRect(sirina*i,sirina*j,sirina,sirina);
                }
                if(mat[i][j]!=-1&&mat[i][j]!=-2){
                    double r,g,b,a;
                    a=(double) mat[i][j]/max;
                    r=r1*a+r2*(1-a);
                    g=g1*a+g2*(1-a);
                    b=b1*a+b2*(1-a);
                    context.setFill(new Color(r,g,b,1));
                    context.fillRect(sirina*i,sirina*j,sirina,sirina);
                }
            }
        }
    }

}
