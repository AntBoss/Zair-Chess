package com.company;

public class queen extends piece{

    queen(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.value = 9;
        this.color = color;
        uR = (color == 0) ? "♕" : "♛";
        Type = Types.QUEEN;
    }

    @Override
    public boolean valid(int x, int y, piece[][] board) {
        if(board[x][y].color == Main.playerPlaying&& board == Main.board || board[x][y].Type == Types.VOID)
            return false;
        //// diagonal movement ////
        if ((x-this.x == y-this.y || x-this.x == this.y-y || y-this.y == this.x-x || this.y-y == this.x-x)) {

            if (x > this.x && y > this.y) {
                for (int i = 1; i < x-this.x; i++) {
                    if (board[this.x + i][this.y + i].Type != Types.EMPTY)
                        return false;
                }
            }
            else if (x > this.x && y < this.y) {
                for (int i = 1; i < x - this.x; i++) {
                    if (board[this.x + i][this.y - i].Type != Types.EMPTY)
                        return false;
                }
            }
            else if (x < this.x && y > this.y) {
                for (int i = 1; i < this.x-x; i++) {
                    if (board[this.x - i][this.y + i].Type != Types.EMPTY)
                        return false;
                }
            }
            else if (x < this.x && y < this.y) {
                for (int i = 1; i < this.x-x; i++) {
                    if (board[this.x - i][this.y - i].Type != Types.EMPTY)
                        return false;
                }
            }
            if (board == Main.board)
                return validCheck(x,y);
            return true;

        }

        //// rook movement ////
        if (x==this.x || y == this.y) {
            if (x == this.x) {
                if (y > this.y) {
                    for (int i = 1; i < y - this.y; i++) {
                        if (board[x][this.y + i].Type != Types.EMPTY)
                            return false;
                    }
                } else {
                    for (int i = 1; i < this.y - y; i++) {
                        if (board[x][this.y - i].Type != Types.EMPTY)
                            return false;
                    }
                }
            } else {
                if (x > this.x) {
                    for (int i = 1; i < x - this.x; i++) {
                        if (board[this.x + i][y].Type != Types.EMPTY)
                            return false;
                    }
                } else {
                    for (int i = 1; i < this.x - x; i++) {
                        if (board[this.x - i][y].Type != Types.EMPTY)
                            return false;
                    }
                }
            }
            if (board == Main.board)
                return validCheck(x,y);
            return true;

        }

        return false;

    }




}
