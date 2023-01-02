package com.company;

public class rook extends piece {

    rook(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.value = 5;
        this.color = color;
        uR = (color == 0) ? "♖" : "♜";
        Type = Types.ROOK;
        hasMoved = false;
    }

    @Override
    public boolean valid(int x, int y, piece[][] board) {
        if(board[x][y].color == Main.playerPlaying&& board == Main.board || board[x][y].Type == Types.VOID)
            return false;
        if(x == this.x) {
            if (y>this.y) {
                for (int i = 1; i < y - this.y; i++) {
                    if (board[x][this.y + i].Type != Types.EMPTY)
                        return false;
                }
            }else {
                for (int i = 1; i < this.y - y; i++) {
                    if (board[x][this.y - i].Type != Types.EMPTY)
                        return false;
                }
            }
        } else if (y == this.y) {
            if (x > this.x) {
                for (int i = 1; i < x - this.x; i++) {
                    if (board[this.x + i][y].Type != Types.EMPTY)
                        return false;
                }
            }else {
                for (int i = 1; i < this.x - x; i++) {
                    if (board[this.x - i][y].Type != Types.EMPTY)
                        return false;
                }
            }
        } else return false;
        if (board == Main.board)
            return validCheck(x,y);
        else {
            //validPseudoMove(x,y);
            return true;
        }
    }



}
