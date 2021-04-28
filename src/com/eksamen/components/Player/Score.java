package com.eksamen.components.Player;

public class Score {
    private int score;

    public Score(){
        this.score = 0;
    }

    public void scorePoints(int points) {
        this.score = this.score + points;
    }

    protected int getScore() {
        return score;
    }
}
