package BFS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class polje {
    private int w,h;
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

}
