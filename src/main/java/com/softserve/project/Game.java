package main.java.com.softserve.project;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private int[][] gameField;
    private Random r = new Random();

    private GameState state;
    private int score;

    //initialize of game field
    public Game() {
        gameField = new int[4][4];
        addNewNumber();
        addNewNumber();
        state = GameState.CONTINUE;
    }

    public int[][] getGameField() {
        return gameField;
    }

    public int getScore() {
        return score;
    }

    public GameState getState() {
        return state;
    }

    //print status of game field
    public void printArray() {
        for (int[] x : gameField) {
            System.out.format("%6d%6d%6d%6d\n", x[0], x[1], x[2], x[3]);
        }
        System.out.print("\n");
    }


    public void addNewNumber() {
        if(checkBoardFull()){
            return;
        }
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

        for (int y = 0; y < 4; y++) {
            boolean[] alreadyCombined = {false, false, false, false};
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
                    else if(gameField[X][y]!=value) {
                        gameField[x][y] =0;
                        gameField[X+1][y] = value;


                    }
                    else {
                        if (alreadyCombined[X]) {
                            gameField[x][y] = 0;
                            gameField[X + 1][y] = value;

                        } else { //combining of numbers
                            gameField[x][y] = 0;
                            gameField[X][y] *= 2;
                            score+=gameField[X][y];
                            alreadyCombined[X] = true;

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
        for (int y = 0; y < 4; y++) {
            boolean[] alreadyCombined = {false, false, false, false};
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
                        gameField[x][y]=0;
                        gameField[X-1][y]=value;

                    }
                    else {
                        if (alreadyCombined[X]) {
                            gameField[x][y] = 0;
                            gameField[X - 1][y] = value;

                        } else {
                            gameField[x][y] = 0;
                            gameField[X][y] *= 2;
                            score+=gameField[X][y];
                            alreadyCombined[X] = true;

                        }
                    }

                }
            }
        }
    }
    public void pushLeft() {
        System.out.println("Pushing Left....");
        //boolean for correct game rules
        for (int x = 0; x < 4; x++) {
            boolean[] alreadyCombined = {false, false, false, false};
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
                        gameField[x][y]=0;
                        gameField[x][Y+1]=value;

                    }
                    else {
                        if (alreadyCombined[Y]) {
                            gameField[x][y] = 0;
                            gameField[x][Y+1] = value;

                        } else {
                            gameField[x][y] = 0;
                            gameField[x][Y] *= 2;
                            score+=gameField[x][Y];
                            alreadyCombined[Y] = true;

                        }
                    }
                }
            }
        }
    }
    public void pushRight() {
        System.out.println("Pushing Right....");
        //boolean for correct game rules
        for (int x = 0; x < 4; x++) {
            boolean[] alreadyCombined = {false, false, false, false};
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
                        gameField[x][y]=0;
                        gameField[x][Y-1]=value;

                    }
                    else {
                        if (alreadyCombined[Y]) {
                            gameField[x][y] = 0;
                            gameField[x][Y-1] = value;

                        } else {
                            gameField[x][y] = 0;
                            gameField[x][Y] *= 2;
                            score+=gameField[x][Y];
                            alreadyCombined[Y] = true;

                        }
                    }
                }
            }
        }
    }

    //true when it is 1024 on the board
    public boolean checkFor1024() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (gameField[x][y] == 1024)
                {
                    return true;
                }

            }
        }
        return false;
    }

    //true when field is full
    public boolean checkBoardFull(){
        for (int x=0; x<4; x++) {
            for (int y = 0; y < 4; y++) {
                if (gameField[x][y] == 0)
                {
                    return false;
                }
            }
        }
        return true;
    }

    //true if there are moving
    public boolean checkHasMoves() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (x == 0) {
                    if (y != 0) {
                        if (gameField[x][y] == gameField[x][y - 1]) {
                            return true;
                        }
                    }
                } else {
                    if (y != 0) {
                        if (gameField[x][y] == gameField[x][y - 1]) {
                            return true;
                        }

                    }
                    if (gameField[x][y] == gameField[x - 1][y]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void checkState() {
        if (checkFor1024()) {
            state = GameState.WIN;
        } else if (checkBoardFull()) {
            if (checkHasMoves()) {
                state = GameState.CONTINUE;
            } else {
                state = GameState.LOOSE;
            }
        } else {
            state = GameState.CONTINUE;
        }
    }
}

