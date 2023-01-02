package com.company;

public class empty extends piece{

    empty(){   // used for empty spaces
        uR = " ";   // space that corresponds with the width of unicode chess pieces
        Type = Types.EMPTY;
        color = 3;   // so that it doesn't interfere with the other two colors
    }

    empty(String symbol){   // used for testing (to populate the board with valid moves)
        uR = symbol;
        Type = Types.EMPTY;
        color = 3;
    }

    @Override
    public boolean valid(int x, int y, piece[][] board) {return false;}

    @Override
    public void move(int x, int y) {}


}
