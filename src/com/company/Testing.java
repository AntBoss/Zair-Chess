package com.company;

import java.util.ArrayList;

import static com.company.Main.board;
import static com.company.Main.boardCheckTemp;

public class Testing {
    public static ArrayList<piece> pieces = new ArrayList<>();
    public static int positionX;   // for checking individually
    public static int positionY;   //


    public static void main(String[] args) {

        for(int outerY=0; outerY<8; outerY++){
            for (int outerX=0; outerX<8; outerX++) {
                pieces.clear(); // clear list

                pieces.add(new pawn(0,2,1));
                pieces.add(new pawn(1,2,1));
                pieces.add(new pawn(2,2,1));
                pieces.add(new pawn(3,2,1));
                pieces.add(new pawn(4,2,1));
                pieces.add(new pawn(5,2,1));
                pieces.add(new pawn(6,2,1));
                pieces.add(new pawn(7,2,1));
                pieces.add(new pawn(outerX,outerY, 0)); // add piece to test

                for (int x = 0; x<8; x++) {   // clear board
                    for (int y = 0; y<8; y++){
                            board[x][y] = new empty();
                    }
                }

                for (piece piece: pieces)   // added selected pieces to board (for each loop in case of multiple pieces)
                    board[piece.x][piece.y] = piece;



                for (int y = 0; y<8; y++) {   // check if movement is valid and populate board
                    for (int x = 0; x<8;x++) {
                        if (x==outerX && y==outerY || board[x][y].Type != Types.EMPTY)
                            continue;
                        if (board[outerX][outerY].valid(x,y,board)) {
                            board[x][y] = new empty("♔");

                        }
                    }
                }

                for (piece piece : pieces) {
                    if (board[outerX][outerY].valid(piece.x, piece.y, board) && (board[piece.x][piece.y] != board[outerX][outerY])) {
                        board[piece.x][piece.y] = new empty("♔");
                    }
                }

                printBoard();
            }
        }
        System.out.println(board[positionX][positionY].valid(0,0, board)); // check individually
        }





    public static void printBoard() {

        System.out.print("   a  b  c  d  e  f  g  h");
        System.out.printf("\n8  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][7].uR, board[1][7].uR, board[2][7].uR, board[3][7].uR, board[4][7].uR, board[5][7].uR, board[6][7].uR, board[7][7].uR);
        System.out.printf("\n7  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][6].uR, board[1][6].uR, board[2][6].uR, board[3][6].uR, board[4][6].uR, board[5][6].uR, board[6][6].uR, board[7][6].uR);
        System.out.printf("\n6  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][5].uR, board[1][5].uR, board[2][5].uR, board[3][5].uR, board[4][5].uR, board[5][5].uR, board[6][5].uR, board[7][5].uR);
        System.out.printf("\n5  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][4].uR, board[1][4].uR, board[2][4].uR, board[3][4].uR, board[4][4].uR, board[5][4].uR, board[6][4].uR, board[7][4].uR);
        System.out.printf("\n4  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][3].uR, board[1][3].uR, board[2][3].uR, board[3][3].uR, board[4][3].uR, board[5][3].uR, board[6][3].uR, board[7][3].uR);
        System.out.printf("\n3  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][2].uR, board[1][2].uR, board[2][2].uR, board[3][2].uR, board[4][2].uR, board[5][2].uR, board[6][2].uR, board[7][2].uR);
        System.out.printf("\n2  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][1].uR, board[1][1].uR, board[2][1].uR, board[3][1].uR, board[4][1].uR, board[5][1].uR, board[6][1].uR, board[7][1].uR);
        System.out.printf("\n1  |%s|%s|%s|%s|%s|%s|%s|%s|\n", board[0][0].uR, board[1][0].uR, board[2][0].uR, board[3][0].uR, board[4][0].uR, board[5][0].uR, board[6][0].uR, board[7][0].uR);

    }

    public static void printBoardIsAttacked(piece[][] board) {

        System.out.print("   a  b  c  d  e  f  g  h");
        System.out.printf("\n8  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][7].isAttacked, board[1][7].isAttacked, board[2][7].isAttacked, board[3][7].isAttacked, board[4][7].isAttacked, board[5][7].isAttacked, board[6][7].isAttacked, board[7][7].isAttacked);
        System.out.printf("\n7  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][6].isAttacked, board[1][6].isAttacked, board[2][6].isAttacked, board[3][6].isAttacked, board[4][6].isAttacked, board[5][6].isAttacked, board[6][6].isAttacked, board[7][6].isAttacked);
        System.out.printf("\n6  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][5].isAttacked, board[1][5].isAttacked, board[2][5].isAttacked, board[3][5].isAttacked, board[4][5].isAttacked, board[5][5].isAttacked, board[6][5].isAttacked, board[7][5].isAttacked);
        System.out.printf("\n5  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][4].isAttacked, board[1][4].isAttacked, board[2][4].isAttacked, board[3][4].isAttacked, board[4][4].isAttacked, board[5][4].isAttacked, board[6][4].isAttacked, board[7][4].isAttacked);
        System.out.printf("\n4  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][3].isAttacked, board[1][3].isAttacked, board[2][3].isAttacked, board[3][3].isAttacked, board[4][3].isAttacked, board[5][3].isAttacked, board[6][3].isAttacked, board[7][3].isAttacked);
        System.out.printf("\n3  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][2].isAttacked, board[1][2].isAttacked, board[2][2].isAttacked, board[3][2].isAttacked, board[4][2].isAttacked, board[5][2].isAttacked, board[6][2].isAttacked, board[7][2].isAttacked);
        System.out.printf("\n2  |%s|%s|%s|%s|%s|%s|%s|%s|", board[0][1].isAttacked, board[1][1].isAttacked, board[2][1].isAttacked, board[3][1].isAttacked, board[4][1].isAttacked, board[5][1].isAttacked, board[6][1].isAttacked, board[7][1].isAttacked);
        System.out.printf("\n1  |%s|%s|%s|%s|%s|%s|%s|%s|\n", board[0][0].isAttacked, board[1][0].isAttacked, board[2][0].isAttacked, board[3][0].isAttacked, board[4][0].isAttacked, board[5][0].isAttacked, board[6][0].isAttacked, board[7][0].isAttacked);

    }
}
