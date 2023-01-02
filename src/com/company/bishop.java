package com.company;



public class bishop extends piece{

    bishop(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.value = 3;
        this.color = color;
        uR = (color == 0) ? "♗" : "♝";
        Type = Types.BISHOP;
    }

    @Override
    public boolean valid(int x, int y, piece[][] board) {
        if(board[x][y].color == Main.playerPlaying && board == Main.board || board[x][y].Type == Types.VOID)
            return false;


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
                else {
                    //validCheck2(x,y);
                    return true;
                }
            }
            return false;
        }





}
