package com.softserve.project;

public class Game2048 {
    public static void main(String[] args) {
        Game g=new Game();
        g.printArray();
        for(int i=0; i<3; i++) {
            g.addNewNumber();
        }
        g.printArray();
        for(int i=0;i<3;i++){
            g.addNewNumber();
            g.printArray();
            g.pushUp();
            g.printArray();
            g.addNewNumber();
            g.printArray();
            g.pushDown();
            g.printArray();

            g.addNewNumber();
            g.printArray();
            g.pushLeft();
            g.printArray();
            g.addNewNumber();
            g.printArray();
            g.pushRight();
            g.printArray();
        }
    }
}
