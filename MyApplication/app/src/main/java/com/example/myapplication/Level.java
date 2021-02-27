package com.example.myapplication;

public class Level {
    public int playerx,playery;
    public Zid[] zidovi;
    public Zid cilj;

    public Level(int playerx,int playery,Zid[] zidovi,Zid cilj){
        this.playerx=playerx;
        this.playery=playery;
        this.zidovi=zidovi;
        this.cilj=cilj;
    }
}
