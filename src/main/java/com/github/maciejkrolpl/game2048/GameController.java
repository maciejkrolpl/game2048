package com.github.maciejkrolpl.game2048;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Ta klasa powinna przechowywać instancję planszy oraz sterować rozgrywką, czyli pytać o to, jaki ruch wykonać.
 * Może się okazać, że przyda jej się również zdolność do wyświetlania planszy.
 */
class GameController {

    GameController() {

        Board brd = new Board();

        brd.initialize();
//        brd.setBoard(new int[][]{
//                {4, 8, 16, 32},
//                {2, 4, 8, 16},
//                {32, 64, 128, 256},
//                {16, 8, 2, 2}
//        });


        boolean gameContinues = true;
        while (gameContinues) {

            brd.display();          // wyświetl tablicę

            gameContinues = brd.gameContinues();

            if (gameContinues) {
                // zapisz kopię tablicy przed ruchem
                int[][] boardActual = brd.getBoard();                   // pobierz bieżącą
                int[][] boardCopy = new int[boardActual.length][];      // zainicjuj nową
                for (int i = 0; i < boardActual.length; i++) {          // skopiuj wiersz po wierszu
                    boardCopy[i] = boardActual[i].clone();
                }

                char choice = askForMove();   // poczekaj na reakcję użytkownika

                switch (choice) {               // zareaguj odpowiednio
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
                if (!Arrays.deepEquals(boardCopy, brd.getBoard())) {   // jesli tablica przed ruchem różni się od
                    brd.insertNewTile();                               // tablicy po ruchu to wstaw nowy kafelek
                }
            } else {
                System.out.println("\n GEJM OŁWER");
            }


        }


    }


    private char askForMove() {

        Scanner sc = new Scanner(System.in);
        boolean rightChoice = false;
        char choice = 0;

        while (!rightChoice) {
            System.out.print("Enter move to run [WASD]");
            choice = sc.next().charAt(0);
            if (choice=='w'
                    || choice=='s'
                    || choice=='a'
                    || choice=='d'
                    || choice=='q') {
                rightChoice = true;
            }
        }
        return choice;
    }
}
