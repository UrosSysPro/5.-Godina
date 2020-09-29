package binarnoStablo;

public class Stablo {
    public Cvor koren;
    public Cvor neBalansiran;
    public Stablo() {
        koren=null;
        neBalansiran=null;
    }
    // funkcije za unosenje vrednosti u stablo
    public void ubaciInt(int vrednost){
        // ubacujemo novi cvor
        Cvor sledeci=new Cvor(vrednost);
        this.koren=ubaciCvor(this.koren,sledeci);
        // azuriramo visine i balans svih cvorova i pronalazimo nebalansiran cvor
        update(this.koren);
        // izvrtimo stablo tako da opet bude balansirano
        balansiraj(vrednost);
    }
    public Cvor ubaciCvor(Cvor koren,Cvor sledeci){
        if(koren==null)return sledeci;
        if(sledeci.vrednost>koren.vrednost){
            koren.desni=ubaciCvor(koren.desni,sledeci);
        }else{
            koren.levi=ubaciCvor(koren.levi,sledeci);
        }
        return koren;
    }
    public int update(Cvor koren) {
        if(koren==null)return 0;
        int visinaLevog=update(koren.levi);
        int visinaDesnog=update(koren.desni);
        if(visinaDesnog>visinaLevog){
            koren.visina=visinaDesnog+1;
        }else {
            koren.visina=visinaLevog+1;
        }
        koren.balans=Math.abs(visinaDesnog-visinaLevog);
        if(koren.balans>=2&&neBalansiran==null){
            neBalansiran=koren;
        }
        return koren.visina;
    }
    public void balansiraj(int vrednost){
        if(neBalansiran==null)return;
        // cvorovi koje treba da izvrtimo
        Cvor A,B,C;
        // pomocni cvorovi
        Cvor nA,nB,nC;
        A=neBalansiran;
        String slucaj="";
        // odredjujemo a,b,c i koji je slucaj u pitanju
        if(vrednost>A.vrednost){
            B=A.desni;
            slucaj+="D";
        }else{
            slucaj+="L";
            B=A.levi;
        }
        if(vrednost>B.vrednost){
            C=B.desni;
            slucaj+="D";
        }else{
            C=B.levi;
            slucaj+="L";
        }

        nA=new Cvor(A.vrednost);
        nB=new Cvor(B.vrednost);
        nC=new Cvor(C.vrednost);
        // resavamo za pojedinacne slucajeve
        switch (slucaj){
            case "DL":{
                nC.levi=nA;
                nC.desni=nB;
                nA.desni=C.levi;
                nA.levi=A.levi;
                nB.levi=C.desni;
                nB.desni=B.desni;
                //
                A.vrednost=nC.vrednost;
                A.levi=nC.levi;
                A.desni=nC.desni;
            }break;
            case "DD":{
                nB.levi=nA;
                nB.desni=nC;
                nA.levi=A.levi;
                nA.desni=B.levi;
                nC.levi=C.levi;
                nC.desni=C.desni;
                //
                A.vrednost=nB.vrednost;
                A.levi=nB.levi;
                A.desni=nB.desni;
            }break;
            case "LD":{
                nC.levi=nB;
                nC.desni=nA;
                nB.levi=B.levi;
                nB.desni=C.levi;
                nA.levi=C.desni;
                nA.desni=A.desni;
                //
                A.vrednost=nC.vrednost;
                A.levi=nC.levi;
                A.desni=nC.desni;
            }break;
            case "LL":{
                nB.levi=nC;
                nB.desni=nA;
                nC.levi=C.levi;
                nC.desni=C.desni;
                nA.levi=B.desni;
                nA.desni=A.desni;
                //
                A.vrednost=nB.vrednost;
                A.levi=nB.levi;
                A.desni=nB.desni;
            }break;
        }
        // sada znamo da je stablo sigurno balansirano
        neBalansiran=null;
    }
    // funkcije za rad sa stablom
    public boolean proveri(Cvor koren,int vrednost){
        if(koren==null)return false;
        if(koren.vrednost==vrednost) return true;
        if(vrednost>koren.vrednost)return proveri(koren.desni,vrednost);
        return proveri(koren.levi,vrednost);
    }
    // funkcije za ispisivanje stabla i debagovanje
    public void ispis(Cvor koren){
        if(koren==null)return;
        ispis(koren.levi);
        System.out.print(koren.vrednost+" ");
        ispis(koren.desni);
    }
    public void ispisVisina(Cvor koren){
        if(koren==null)return;
        ispisVisina(koren.levi);
        System.out.print(koren.visina+" ");
        ispisVisina(koren.desni);
    }
    public void ispisBalans(Cvor koren){
        if(koren==null)return;
        ispisBalans(koren.levi);
        System.out.print(koren.balans+" ");
        ispisBalans(koren.desni);
    }
}
