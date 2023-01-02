package com.company;

public class knight extends piece{

    knight(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.value = 3;
        this.color = color;
        uR = (color == 0) ? "♘" : "♞";
        Type = Types.KNIGHT;
    }

    @Override
    public boolean valid(int x, int y, piece[][] board) {
        if(board[x][y].color == Main.playerPlaying&& board == Main.board || board[x][y].Type == Types.VOID)
            return false;
        if ((x == this.x+1 && y == this.y+2) || (x == this.x-1 && y == this.y+2) ||
           (x == this.x+1 && y == this.y-2) || (x == this.x-1 && y == this.y-2) ||
                (x == this.x+2 && y == this.y+1) || (x == this.x+2 && y == this.y-1) ||
                (x == this.x-2 && y == this.y+1) || (x == this.x-2 && y == this.y-1)) {
            if (board == Main.board)
                return validCheck(x,y);
            else {
                //validPseudoMove(x,y,"pseudoMove");
                return true;
            }
        }
        return false;
    }




}
