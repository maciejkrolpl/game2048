package com.github.maciejkrolpl.game2048;

import java.util.Arrays;
import java.util.Scanner;


class GameController {

    GameController() {

        Board brd = new Board();

        brd.initialize();
//        brd.setBoard(new int[][]{
//                {4, 8, 16, 32},
//                {2, 4, 8, 16},
//                {32, 64, 1024, 1024},
//                {16, 8, 2, 4}
//        });


        boolean gameContinues = true;
        while (gameContinues) {

            brd.display();
            gameContinues = brd.gameContinues();

            if (brd.getMaxTile() == 2048) {
                System.out.println("\u001B[32m" + "Gratulacje! Osiągnąłeś 2048 punktów na kafelku!");
                System.out.print("Czy chcesz kontynuować grę (t/n) " + "\u001B[0m");
                gameContinues = askForYesOrNo();
            }

            if (gameContinues) {
                int[][] boardActual = brd.getBoard();
                int[][] boardCopy = new int[boardActual.length][];
                for (int i = 0; i < boardActual.length; i++) {
                    boardCopy[i] = boardActual[i].clone();
                }

                char choice = askForMove();

                switch (choice) {
                    case 'w':
                        brd.moveToUp();
                        break;
                    case 'a':
                        brd.moveToLeft();
                        break;
                    case 's':
                        brd.moveToDown();
                        break;
                    case 'd':
                        brd.moveToRight();
                        break;
                    case 'q':
                        gameContinues = false;
                        break;

                }


                if (!Arrays.deepEquals(boardCopy, brd.getBoard())) {
                    brd.insertNewTile();
                }
            } else {
                System.out.println("\n GEJM OŁWER");
            }


        }


    }

    private boolean askForYesOrNo() {
        Scanner sc = new Scanner(System.in);
        boolean rightChoice = false;
        char choice = 0;

        while (!rightChoice) {
            choice = sc.next().charAt(0);
            if (choice == 't' || choice == 'n') {
                rightChoice = true;
            }
        }
        return choice=='t';
    }


    private char askForMove() {

        Scanner sc = new Scanner(System.in);
        boolean rightChoice = false;
        char choice = 0;

        while (!rightChoice) {
            System.out.print("Enter move to run [WASD]");
            choice = sc.next().charAt(0);
            if (choice == 'w'
                    || choice == 's'
                    || choice == 'a'
                    || choice == 'd'
                    || choice == 'q') {
                rightChoice = true;
            }
        }
        return choice;
    }
}
