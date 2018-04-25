package com.github.maciejkrolpl.game2048;

import java.util.Random;

/**
 * Klasa przechowująca planszę, czyli obecny stan gry. Nie powinna zarządzać samą rozgrywką, a udostępniać metody
 * pozwalające na wykonanie poszczególnych ruchów.
 */
public class Board {

    private int[][] board = new int[4][4];
    private int score = 0;

    public boolean gameContinues() {

        boolean result = false;

        if (isPossibleDownMove() || isPossibleLeftMove() || isPossibleRightMove() || isPossibleUpMove())

            result = true;

        return result;
    }

    private boolean isPossibleRightMove() {
        rotateClockwise();
        boolean result = isPossibleDownMove();
        rotateCounterClockwise();
        return result;
    }

    private boolean isPossibleLeftMove() {
        rotateCounterClockwise();
        boolean result = isPossibleDownMove();
        rotateClockwise();
        return result;
    }

    private boolean isPossibleUpMove() {
        rotate180();
        boolean result = isPossibleDownMove();
        rotate180();
        return result;
    }


    boolean isPossibleDownMove() {

        /* szukamy czy dla ruchu w dół możemy
        1. zmerdżować
        2. przesunąc w dół (pod jakimkolwiek kafelkiem jest zero*/

        int x;
        int y;

        for (y = 0; y < 4; y++) {
            for (x = 0; x < 3; x++) {
                if (board[x][y]!=0 &&
                        (board[x+1][y] == 0 || board[x][y] == board[x + 1][y]))
                    return true;
            }

        }
        return false;
    }


    public void insertNewTile() {
        int len = 0;
        int[][] list = new int[16][2];
        for (int x = 0; x <= 3; x++) {
            for (int y = 0; y <= 3; y++) {
                if (board[x][y] == 0) {
                    list[len][0] = x;
                    list[len][1] = y;
                    len++;
                }
            }
        }

        Random random = new Random();
        int r = random.nextInt(len);
        int x = list[r][0];
        int y = list[r][1];
        int n = random.nextInt(10);
        board[x][y] = (n<=1) ? 4 : 2;
    }

    public void initialize() {
        insertNewTile();
        insertNewTile();
    }

    public void display() {
        int x;
        int y;

        for (x = 0; x <= 3; x++) {
            for (y = 0; y <= 3; y++) {
                if (board[x][y] == 0) {
                    System.out.print("    .");
                } else {
                    System.out.printf("%5d", board[x][y]);
                }
            }
            if (x == 0) {
                System.out.print("\u001B[31m" + "      SCORE " + score + "\u001B[0m");
            }
            System.out.println();
        }

        System.out.println();
    }

    public void moveToUp() {
        rotate180();
        moveToDown();
        rotate180();
    }

    public void moveToLeft() {

        rotateCounterClockwise();
        moveToDown();
        rotateClockwise();
    }

    public void moveToRight() {
        rotateClockwise();
        moveToDown();
        rotateCounterClockwise();
    }

    public void moveToDown() {
        int x;
        int y;

        for (y = 0; y <= 3; y++) {    // przesuwanie dla każdej kolumny


            for (x = 3; x > 0; x--) {   // zaczynamy od dołu i szukamy pustych pól

                boolean isMerged = false;

                if ((board[x][y] != 0) && (board[x - 1][y] == board[x][y])) {
                    board[x][y] *= 2;
                    score += board[x][y];
                    board[x - 1][y] = 0;
                    isMerged = true;
                }

                findEmptyTile(x, y);

                if ((!isMerged) && (x < 3) && (board[x + 1][y] == board[x][y])) {
                    board[x + 1][y] *= 2;
                    score += board[x + 1][y];
                    board[x][y] = 0;
                }

                findEmptyTile(x, y);


            }

            /*  ************************************************ */
        }

    }

    private void findEmptyTile(int x, int y) {
        if (board[x][y] == 0) {
            int searchX = x;
            while (board[searchX][y] == 0 && searchX > 0) {
                searchX--;
            }  // znajdujemy pełne
            board[x][y] = board[searchX][y];
            board[searchX][y] = 0;
        }
    }

    public void rotateCounterClockwise() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    public void rotate180() {
        rotateClockwise();
        rotateClockwise();
    }

    public void rotateClockwise() {

        int x;
        int y;
        int[][] temp = new int[4][4];

        for (x = 0; x <= 3; x++) {
            for (y = 0; y <= 3; y++) {
                temp[x][y] = board[3 - y][x];
            }


        }
        board = temp.clone();
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
