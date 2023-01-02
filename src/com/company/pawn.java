package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.company.GUI.*;
import static com.company.Main.*;
import static com.company.Types.*;

public class pawn extends piece {
    static int xPromote;
    static int yPromote;
   // static boolean enPassant = false;

    pawn(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.value = 1;
        this.color = color;
        uR = (color == 0) ? "♙" : "♟";
        Type = PAWN;
        hasMoved = false;
    }

    @Override
    public boolean valid(int x, int y, piece[][] board) {
       // System.out.println(x+ " " +y);
        if(board[x][y].color == Main.playerPlaying&& board == Main.board || board[x][y].Type == Types.VOID)
            return false;
        if ((board[this.x][this.y].color == 0 && playingAsWhite) || (board[this.x][this.y].color == 1 && !playingAsWhite)) {
            if (!hasMoved && board[x][y].Type == EMPTY && board[x][this.y+1].Type == EMPTY && this.x==x && this.y+2 == y) {
                if (board == Main.board)
                    return validCheck(x,y);
                else {
                    //validPseudoMove(x,y);
                    return true;
                }
            }
            else if ((y == this.y + 1 && x == this.x && board[x][y].Type == EMPTY) || (y == this.y + 1 && (x == this.x + 1 || x == this.x - 1) && board[x][y].Type != EMPTY)) {
                if (board == Main.board)
                    return validCheck(x,y);
                else {
                    //validPseudoMove(x,y);
                    return true;
                }
            }
            // en passant
            else if(y==5  && y == this.y + 1 && (x == this.x + 1 || x == this.x - 1) && board[x][y].Type == EMPTY && board[x][y-1].Type == PAWN && !board[x][y-1].hasMovedSecond) {
                enPassantValidCheck = true;
                //System.out.println("en passant");
                if (board == Main.board)
                    return validCheck(x,y);
                else {
                    //enPassant=true;
                    return true;
                }
            }//
            else return false;
        }
        else {
            if (!hasMoved && board[x][y].Type == EMPTY && board[x][this.y-1].Type == EMPTY&& this.x==x && this.y-2 == y) {
                if (board == Main.board)
                return validCheck(x,y);
                else {
                    //validPseudoMove(x,y);
                    return true;
                }
            }

            else if ((y == this.y - 1 && x == this.x && board[x][y].Type == EMPTY) || (y == this.y - 1 && (x == this.x + 1 || x == this.x - 1) && board[x][y].Type != EMPTY)) {
                if (board == Main.board)
                    return validCheck(x,y);
                else {
                    //validPseudoMove(x,y);
                    return true;
                }
            }
            // en passant
            else if(y==2  && y == this.y - 1 && (x == this.x + 1 || x == this.x - 1) && board[x][y].Type == EMPTY && board[x][y+1].Type == PAWN && !board[x][y+1].hasMovedSecond) {
                enPassantValidCheck = true;
                //System.out.println("en passant");
                if (board == Main.board)
                    return validCheck(x,y);
                else {
                    //enPassant=true;
                    return true;
                }
            }//
            else return false;
        }
    }

    @Override
    public boolean validEnPassant(int x, int y, piece[][] board) {
        if (board[x][y].color == Main.playerPlaying && board == Main.board || board[x][y].Type == Types.VOID)
            return false;
        if ((board[this.x][this.y].color == 0 && playingAsWhite) || (board[this.x][this.y].color == 1 && !playingAsWhite)) {
            if (y == 5 && y == this.y + 1 && (x == this.x + 1 || x == this.x - 1) && board[x][y].Type == EMPTY && board[x][y - 1].Type == PAWN && !board[x][y - 1].hasMovedSecond) {
                return true;
            }//
        } else{
            if(y==2  && y == this.y - 1 && (x == this.x + 1 || x == this.x - 1) && board[x][y].Type == EMPTY && board[x][y+1].Type == PAWN && !board[x][y+1].hasMovedSecond) {
                    return true;
            }//
        }
        return false;
    }




    @Override
    public boolean isAttacking(int x, int y, piece[][] board){
        if(board[x][y].color == Main.playerPlaying&& board == Main.board)
            return false;
        if (y == this.y + 1 && (x == this.x + 1 || x == this.x - 1) && Main.playerPlaying == 1)
            return true;
        else if ( y == this.y - 1 && (x == this.x + 1 || x == this.x - 1) && Main.playerPlaying == 0)
            return true;
        return false;
    }


    public static void promotePawn(int x, int y){
        xPromote = x;
        yPromote = y;
        System.out.println("popped up");
        ImageIcon Qicon,Ricon,Kicon,Bicon;
        if(Main.playerPlaying == 0) {
            Qicon = new ImageIcon(imgs[4].getScaledInstance(boardInner.getWidth() / 12, boardInner.getWidth() / 12, BufferedImage.SCALE_AREA_AVERAGING));
            Ricon = new ImageIcon(imgs[5].getScaledInstance(boardInner.getWidth() / 12, boardInner.getWidth() / 12, BufferedImage.SCALE_AREA_AVERAGING));
            Kicon = new ImageIcon(imgs[2].getScaledInstance(boardInner.getWidth() / 12, boardInner.getWidth() / 12, BufferedImage.SCALE_AREA_AVERAGING));
            Bicon = new ImageIcon(imgs[0].getScaledInstance(boardInner.getWidth() / 12, boardInner.getWidth() / 12, BufferedImage.SCALE_AREA_AVERAGING));
        }else {
            Qicon = new ImageIcon(imgs[10].getScaledInstance(boardInner.getWidth() / 12, boardInner.getWidth() / 12, BufferedImage.SCALE_AREA_AVERAGING));
            Ricon = new ImageIcon(imgs[11].getScaledInstance(boardInner.getWidth() / 12, boardInner.getWidth() / 12, BufferedImage.SCALE_AREA_AVERAGING));
            Kicon = new ImageIcon(imgs[8].getScaledInstance(boardInner.getWidth() / 12, boardInner.getWidth() / 12, BufferedImage.SCALE_AREA_AVERAGING));
            Bicon = new ImageIcon(imgs[6].getScaledInstance(boardInner.getWidth() / 12, boardInner.getWidth() / 12, BufferedImage.SCALE_AREA_AVERAGING));
        }
        GUI.popUpQueen.setIcon(Qicon);
        GUI.popUpRook.setIcon(Ricon);
        GUI.popUpKnight.setIcon(Kicon);
        GUI.popUpBishop.setIcon(Bicon);
        GUI.popUpPiece.setPreferredSize(new Dimension(boardInner.getWidth()/2 - boardInner.getWidth()/12, boardInner.getHeight()/10));
        GUI.popUpPiece.show(GUI.boardInner, (int) ((x-1.2) * (boardInner.getWidth() / 8.0)), (int) ((7-y+1.1) * (boardInner.getWidth() / 8.0)));
        // GUI.popUpPiece.show(GUI.boardInner, (int) (x / (boardInner.getWidth() / 8.0)), 7-(int) (y / (boardInner.getWidth() / 8.0)));

        Main.pieces.remove(Main.board[pawn.xPromote][pawn.yPromote]);
        int p = (playerPlaying == 0) ? 1:0;
        Main.pieces.add(new queen(pawn.xPromote, pawn.yPromote, p));
        board[pawn.xPromote][pawn.yPromote]=pieces.get(pieces.size()-1);
        if (p == 0) {
            whitePieceTakenValue += 9;
        } else blackPieceTakenValue += 9;
        GUI.materialWhiteSide.setText("material: " + (Main.whitePieceTakenValue-Main.blackPieceTakenValue) + " " + Main.whitePieceTaken);
        GUI.materialBlackSide.setText("material: " + (Main.blackPieceTakenValue-Main.whitePieceTakenValue) + " " + Main.blackPieceTaken);
    }

}
