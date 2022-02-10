package com.softserve.project;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private int[][] gameField;
    private Random r = new Random();

    //initialize of game field
    public Game() {
        gameField = new int[4][4];
    }

    //print status of game field
    public void printArray() {
        for (int[] x : gameField) {
            System.out.format("%6d%6d%6d%6d\n", x[0], x[1], x[2], x[3]);
        }
        System.out.print("\n");
    }

    public void addNewNumber() {
        //adding coordinates of free spaces where we can add a new number
        ArrayList<Integer> emptySpacesX = new ArrayList<Integer>();
        ArrayList<Integer> emptySpacesY = new ArrayList<Integer>();
        //adding a available space
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (gameField[x][y] == 0) {
                    emptySpacesX.add(new Integer(x));
                    emptySpacesY.add(new Integer(y));
                }
            }
        }
        //adding random number to the field
        int choice = r.nextInt(emptySpacesX.size());
        int numberChooser = r.nextInt(10);
        int newNumber = 1;
        if (numberChooser == 0) {
            newNumber = 2;
        }
        int X = emptySpacesX.get(choice);
        int Y = emptySpacesY.get(choice);
        gameField[X][Y] = newNumber;
    }

    public void pushUp() {
        System.out.println("Pushing Up....");
        boolean[] alreadyCombined = {false, false, false, false};
        for (int y = 0; y < 4; y++) {
            for (int x = 1; x < 4; x++) {
                if(gameField[x][y]!=0){
                    int value =gameField[x][y];
                    int X=x-1;
                    while((X>=0) && (gameField[X][y]==0)){
                        X--;
                    }
                    if(X==-1){
                        gameField[0][y]=value;
                        gameField[x][y]=0;
                    }
                    else if(gameField[X][y]!=value && x-X != 1) {
                        gameField[X+1][y] = value;
                        gameField[x][y] =0;

                    }
                    else if(gameField[X][y]!=value) {
                        break;
                    }
                    else {
                        if (alreadyCombined[X]) {
                            gameField[X + 1][y] = value;
                            gameField[x][y] = 0;
                        } else {
                            gameField[X][y] *= 2;
                            alreadyCombined[X] = true;
                            gameField[x][y] = 0;
                        }
                    }

                }
            }
        }
    }
    //pushing down
    public void pushDown() {
        System.out.println("Pushing Down....");
        //boolean for correct game rules
        boolean[] alreadyCombined = {false, false, false, false};
        for (int y = 0; y < 4; y++) {
            for (int x = 2; x > -1; x--) {
                if(gameField[x][y]!=0){
                    int value =gameField[x][y];
                    int X=x+1;
                    while((X<=3) && (gameField[X][y]==0)){
                        X++;
                    }
                    if(X==4){
                        gameField[3][y]=value;
                        gameField[x][y]=0;
                    }
                    else if(gameField[X][y]!=value){
                        gameField[X-1][y]=value;
                        gameField[x][y]=0;
                    }
                    else {
                        if (alreadyCombined[X]) {
                            gameField[X - 1][y] = value;
                            gameField[x][y] = 0;
                        } else {
                            gameField[X][y] *= 2;
                            alreadyCombined[X] = true;
                            gameField[x][y] = 0;
                        }
                    }

                }
            }
        }
    }
    public void pushLeft() {
        System.out.println("Pushing Left....");
        //boolean for correct game rules
        boolean[] alreadyCombined = {false, false, false, false};
        for (int x = 0; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if(gameField[x][y]!=0){
                    int value =gameField[x][y];
                    int Y=y-1;
                    while((Y>=0) && (gameField[x][Y]==0)){
                        Y--;
                    }
                    if(Y==-1){
                        gameField[x][0]=value;
                        gameField[x][y]=0;
                    }
                    else if(gameField[x][Y]!=value){
                        gameField[x][Y+1]=value;
                        gameField[x][y]=0;
                    }
                    else {
                        if (alreadyCombined[Y]) {
                            gameField[x][Y+1] = value;
                            gameField[x][y] = 0;
                        } else {
                            gameField[x][Y] *= 2;
                            alreadyCombined[Y] = true;
                            gameField[x][y] = 0;
                        }
                    }
                }
            }
        }
    }
    public void pushRight() {
        System.out.println("Pushing Right....");
        //boolean for correct game rules
        boolean[] alreadyCombined = {false, false, false, false};
        for (int x = 0; x < 4; x++) {
            for (int y = 2; y > -1; y--) {
                if(gameField[x][y]!=0){
                    int value =gameField[x][y];
                    int Y=y+1;
                    while((Y<=3) && (gameField[x][Y]==0)){
                        Y++;
                    }
                    if(Y==4){
                        gameField[x][3]=value;
                        gameField[x][y]=0;
                    }
                    else if(gameField[x][Y]!=value){
                        gameField[x][Y-1]=value;
                        gameField[x][y]=0;
                    }
                    else {
                        if (alreadyCombined[Y]) {
                            gameField[x][Y-1] = value;
                            gameField[x][y] = 0;
                        } else {
                            gameField[x][Y] *= 2;
                            alreadyCombined[Y] = true;
                            gameField[x][y] = 0;
                        }
                    }
                }
            }
        }
    }
}

