package binarnoStablo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void ucitajRucno(Stablo s){
        Scanner scan=new Scanner(System.in);
        int n=Integer.parseInt(scan.next());
        for(int i=0;i<n;i++){
            s.ubaciInt(Integer.parseInt(scan.next()));
        }
        s.update(s.koren);
    }
    public static void ucitajFajl(Stablo s,String ime){
        File file=new File(ime);
        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()){
                s.ubaciInt(Integer.parseInt(scanner.next()));
            }
            s.update(s.koren);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] argv){
        Stablo s=new Stablo();
        ucitajRucno(s);
        s.ispis(s.koren);
        System.out.println();
        s.ispisVisina(s.koren);
        System.out.println();
        s.ispisBalans(s.koren);
        System.out.println();
    }
}
