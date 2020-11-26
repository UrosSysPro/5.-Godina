package BFS;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] argv) throws FileNotFoundException {
//        polje p=new polje(6,7);
//        p.postaviZid(2,2);
//        p.postaviZid(4,2);
//        p.postaviZid(3,3);
//        p.postaviZid(3,4);
//        p.postaviZid(3,5);
//        p.postaviZid(4,5);
        polje p=new polje("tekst.txt");
        p.start(0,0);
        p.ispis();
    }
}
