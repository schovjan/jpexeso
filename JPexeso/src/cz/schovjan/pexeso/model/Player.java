/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.schovjan.pexeso.model;

/**
 *
 * @author schovjan
 */
public class Player {

    private int score;
    private int stringOfSuccess;

    public Player() {
        stringOfSuccess = 0;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStringOfSuccess() {
        return stringOfSuccess;
    }

    public void setStringOfSuccess(int stringOfSuccess) {
        this.stringOfSuccess = stringOfSuccess;
    }

    public int scoreIncrement() {
        return ++this.score;
    }

}
