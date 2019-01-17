package com.glhf.bomberball.gameobject;

public class Score {
    int score;
    private Score(){
        score=0;
    }
    private static Score INSTANCE = new Score();
    public static Score getINSTANCE(){
        return INSTANCE;
    }
    public void increaseScore(int n){
        this.score+=n;
    }
}
