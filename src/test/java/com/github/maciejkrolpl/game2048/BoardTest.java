package com.github.maciejkrolpl.game2048;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    @Test
    public void moveToDownTest() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 0, 0, 4},
                {0, 4, 0, 0},
                {0, 4, 8, 16},
                {0, 0, 2, 0}
        });

        brd.moveToDown();
        int[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 8, 4},
                {2, 8, 2, 16}
        };

        Assert.assertArrayEquals(expected, brd.getBoard() );
    }
    @Test
    public void moveToDownWrongTest() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 0, 0, 4},
                {0, 4, 0, 0},
                {0, 4, 8, 16},
                {0, 0, 2, 0}
        });

        brd.moveToDown();
        int[][] expected = {  // kolumna 2 nie zmerdżowana
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 8, 4},
                {2, 4, 2, 16}
        };

        Assert.assertNotEquals(expected, brd.getBoard() );

    }

    @Test
    public void rotateClockwiseTest() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 0, 0, 4},
                {0, 4, 0, 0},
                {0, 4, 8, 16},
                {0, 0, 2, 0}
        });
        brd.rotateClockwise();
        int[][] expected = {
                {0,0,0,2},
                {0,4,4,0},
                {2,8,0,0},
                {0,16,0,4}
        };

        Assert.assertArrayEquals(expected, brd.getBoard());
    }

    @Test
    public void rotateCounterClockwiseTest() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 0, 0, 4},
                {0, 4, 0, 0},
                {0, 4, 8, 16},
                {0, 0, 2, 0}
        });
        brd.rotateCounterClockwise();

        int[][] expected = {
                {4,0,16,0},
                {0,0,8,2},
                {0,4,4,0},
                {2,0,0,0}
        };

        Assert.assertArrayEquals(expected, brd.getBoard());
    }

    @Test
    public void rotate180Test() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 0, 0, 4},
                {0, 4, 0, 0},
                {0, 4, 8, 16},
                {0, 0, 2, 0}
        });
        brd.rotate180();
        int[][] expected = {
                {0,2,0,0},
                {16,8,4,0},
                {0,0,4,0},
                {4,0,0,2}
        };

        Assert.assertArrayEquals(expected, brd.getBoard());
    }

    @Test
    public void moveToUpTest() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 0, 0, 4},
                {0, 4, 0, 0},
                {0, 4, 8, 16},
                {0, 0, 2, 0}
        });
        brd.moveToUp();
        int[][] expected = {
                {2,8,8,4},
                {0,0,2,16},
                {0,0,0,0},
                {0,0,0,0}
        };

        Assert.assertArrayEquals(expected, brd.getBoard());
    }

    @Test
    public void moveToRightTest() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 0, 0, 4},
                {0, 4, 0, 0},
                {0, 4, 8, 16},
                {0, 0, 2, 0}
        });
        brd.moveToRight();
        int[][] expected = {
                {0,0,2,4},
                {0, 0, 0, 4},
                {0, 4, 8, 16},
                {0, 0, 0, 2}
        };

        Assert.assertArrayEquals(expected, brd.getBoard());
    }

    @Test
    public void moveToLeftTest() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 0, 0, 4},
                {0, 4, 0, 0},
                {0, 4, 8, 16},
                {0, 0, 2, 0}
        });
        brd.moveToLeft();
        int[][] expected = {
                {2, 4, 0, 0},
                {4, 0, 0, 0},
                {4, 8, 16, 0},
                {2, 0, 0, 0}        };

        Assert.assertArrayEquals(expected, brd.getBoard());
    }

    @Test
    public void notPossibleDownMoveTest() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 8, 16},
                {0, 4, 2, 8}
        });
        Assert.assertFalse(brd.isPossibleDownMove());
    }

    @Test
    public void possibleDownMoveTest() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 2, 8, 16},
                {8, 4, 0, 8}
        });
        Assert.assertTrue(brd.isPossibleDownMove());

    }

    @Test
    public void possibleDownMoveTestWithMerge() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {4, 2, 8, 16},
                {2, 4, 32, 8}
        });
        Assert.assertTrue(brd.isPossibleDownMove());
    }

    @Test
    public void gameShouldContinue() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {4, 2, 8, 16},
                {2, 4, 32, 8}
        });
        Assert.assertTrue(brd.gameContinues());
    }

    @Test
    public void gameShouldContinue2() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 4}
        });
        Assert.assertTrue(brd.gameContinues());
    }

    @Test
    public void gameShouldContinue3() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 0}
        });
        Assert.assertTrue(brd.gameContinues());
    }

    @Test
    public void gameShouldNotContinue() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2}
        });
        Assert.assertFalse(brd.gameContinues());
    }

    @Test
    public void gameShouldNotContinue2() {
        Board brd = new Board();
        brd.setBoard(new int[][] {
                {2, 64, 8, 16},
                {4, 128, 32, 2},
                {8, 16, 64, 128},
                {4, 32, 16, 2}
        });
        Assert.assertFalse(brd.gameContinues());
    }
}
