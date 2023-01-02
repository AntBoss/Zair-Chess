package com.company;


public class king extends piece{

    king(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.value = 0;
        this.color = color;
        uR = (color == 0) ? "♔" : "♚";
        Type = Types.KING;
        hasMoved =false;
    }

    @Override
    public boolean valid(int x, int y, piece[][] board) {
        int correctForColor = (GUI.playingAsWhite) ? 0 : 1;
        if(board[x][y].color == Main.playerPlaying && board == Main.board || board[x][y].Type == Types.VOID)
            return false;
        if (Math.abs(x-this.x + y-this.y) <= 2  && Math.abs(x-this.x) <= 1 && Math.abs(y-this.y) <= 1) {
            if (board == Main.board)
                return validCheck(x,y);
            else {
                //validCheck2(x,y);
                return true;
            }
        }
        //castling
        else if (!GUI.checked && !board[this.x][this.y].hasMoved && (x==2-correctForColor||x==6-correctForColor)&&(y==0||y==7) && board[x][y].Type == Types.EMPTY){
            if (board[this.x][this.y].color == 0 && Main.playerPlaying == 0 && GUI.playingAsWhite || board[this.x][this.y].color == 1 && Main.playerPlaying == 1 && !GUI.playingAsWhite) {
                if (x == 2-correctForColor && y == 0) {
                    if (!board[0][0].hasMoved && board[0][0].Type == Types.ROOK && board[x-1 + 2*correctForColor][y].Type == Types.EMPTY && (!GUI.playingAsWhite || board[x+1][y].Type == Types.EMPTY)) {
                        if(board[this.x][this.y].validCheck(3-correctForColor,0)) {
                        castledValidCheck = true;
                        if (board == Main.board)
                            return validCheck(x,y);
                        else {
                            return true;
                        }
                        }
                    }
                } else if (x == 6-correctForColor && y == 0) {
                    if (!board[7][0].hasMoved && board[7][0].Type == Types.ROOK && board[x-1 + 2*correctForColor][y].Type == Types.EMPTY&& (GUI.playingAsWhite || board[x-1][y].Type == Types.EMPTY)) {
                        if (board[this.x][this.y].validCheck(5-correctForColor,0)){
                            castledValidCheck = true;
                            if (board == Main.board)
                                return validCheck(x,y);
                            else {
                                return true;
                            }
                        }
                    }
                }
            } else if (board[this.x][this.y].color == 1 && Main.playerPlaying == 1 && GUI.playingAsWhite || board[this.x][this.y].color == 0 && Main.playerPlaying == 0 && !GUI.playingAsWhite){
                if (x == 2-correctForColor && y==7) {
                    if (!board[0][7].hasMoved && board[0][7].Type == Types.ROOK && board[x-1 + 2*correctForColor][y].Type == Types.EMPTY&& (!GUI.playingAsWhite || board[x+1][y].Type == Types.EMPTY)) {
                        if(board[this.x][this.y].validCheck(3-correctForColor,7)) {
                            castledValidCheck = true;
                            if (board == Main.board)
                                return validCheck(x,y);
                            else {
                                return true;
                            }
                        }
                    }
                } else if (x==6-correctForColor&&y==7){
                    if (!board[7][7].hasMoved && board[7][7].Type == Types.ROOK&& board[x-1 + 2*correctForColor][y].Type == Types.EMPTY&& (GUI.playingAsWhite || board[x-1][y].Type == Types.EMPTY)) {
                        if (board[this.x][this.y].validCheck(5-correctForColor, 7)) {
                            castledValidCheck = true;
                            if (board == Main.board)
                                return validCheck(x,y);
                            else {
                                return true;
                            }
                        }
                    }
                }
            }

        }
        return false;
    }

    @Override
    public void validCastle (int x, int y, piece[][] board){
        int correctForColor = (GUI.playingAsWhite) ? 0 : 1;
        if (!GUI.checked && !board[this.x][this.y].hasMoved && (x==2-correctForColor||x==6-correctForColor)&&(y==0||y==7) && board[x][y].Type == Types.EMPTY){
            if (board[this.x][this.y].color == 0 && Main.playerPlaying == 0 && GUI.playingAsWhite || board[this.x][this.y].color == 1 && Main.playerPlaying == 1 && !GUI.playingAsWhite) {
                if (x == 2-correctForColor && y == 0) {
                    if (!board[0][0].hasMoved && board[0][0].Type == Types.ROOK && board[x-1 + 2*correctForColor][y].Type == Types.EMPTY && (!GUI.playingAsWhite || board[x+1][y].Type == Types.EMPTY)) {
                        if(board[this.x][this.y].validCheck(3-correctForColor,0)) {
                            castled = true;
                        }
                    }
                } else if (x == 6-correctForColor && y == 0) {
                    if (!board[7][0].hasMoved && board[7][0].Type == Types.ROOK && board[x-1 + 2*correctForColor][y].Type == Types.EMPTY&& (GUI.playingAsWhite || board[x-1][y].Type == Types.EMPTY)) {
                        if (board[this.x][this.y].validCheck(5-correctForColor,0)){
                            castled = true;
                        }
                    }
                }
            } else if (board[this.x][this.y].color == 1 && Main.playerPlaying == 1 && GUI.playingAsWhite || board[this.x][this.y].color == 0 && Main.playerPlaying == 0 && !GUI.playingAsWhite){
                if (x == 2-correctForColor && y==7) {
                    if (!board[0][7].hasMoved && board[0][7].Type == Types.ROOK && board[x-1 + 2*correctForColor][y].Type == Types.EMPTY&& (!GUI.playingAsWhite || board[x+1][y].Type == Types.EMPTY)) {
                        if(board[this.x][this.y].validCheck(3-correctForColor,7)) {
                            castled = true;
                        }
                    }
                } else if (x==6-correctForColor&&y==7){
                    if (!board[7][7].hasMoved && board[7][7].Type == Types.ROOK&& board[x-1 + 2*correctForColor][y].Type == Types.EMPTY&& (GUI.playingAsWhite || board[x-1][y].Type == Types.EMPTY)) {
                        if (board[this.x][this.y].validCheck(5-correctForColor, 7)) {
                            castled = true;
                        }
                    }
                }
            }

        }
    }





}
