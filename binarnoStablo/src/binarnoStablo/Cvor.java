package binarnoStablo;

public class Cvor {
    public int vrednost;
    public int balans;
    public int visina;
    public Cvor levi;
    public Cvor desni;
    public Cvor(int a){
        vrednost=a;
        visina=0;
        balans=0;
        levi=null;
        desni=null;
    }
}
