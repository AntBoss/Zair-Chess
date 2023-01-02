package com.company;

public class voidSpace extends piece{
    voidSpace(int x, int y){   // used for empty spaces
        uR = " ";   // space that corresponds with the width of unicode chess pieces
        Type = Types.VOID;
        this.x = x;
        this.y = y;
        color = 3;   // so that it doesn't interfere with the other two colors
    }

    voidSpace(String symbol){   // used for testing (to populate the board with valid moves)
        uR = symbol;
        Type = Types.VOID;
        color = 3;
    }

    @Override
    public boolean valid(int x, int y, piece[][] board) {return false;}

    @Override
    public void move(int x, int y) {}


}
