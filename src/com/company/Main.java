package com.company;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import javax.sound.sampled.*;


public class Main {

    //// variables ////
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<piece> normalBoardPieces = new ArrayList<>();
    public static ArrayList<piece> pieces = new ArrayList<>();
    public static ArrayList<piece> piecesVoidSpaces = new ArrayList<>();
    public static ArrayList<piece> piecesCheckTemp = new ArrayList<>();
    public static ArrayList<piece> normalPiecesVoidSpaces = new ArrayList<>();
    public static piece[][] normalBoard = new piece[8][8];
    public static piece[][] normalBoardCheck = new piece[8][8];
    public static piece[][] board = new piece[8][8];
    public static piece[][] boardCheckTemp = new piece[8][8];
    public static piece selectedPiece = null;

    //builder board//
    public static ArrayList<piece> BBpieces = new ArrayList<>();
    public static ArrayList<piece> BBpiecesVoidSpaces = new ArrayList<>();
    public static piece[][] BBboard = new piece[8][8];
    public static piece[][] BBboardCheckTemp = new piece[8][8];

    public static LinkedList<LinkedList<Integer[]>> piecesCurrentValid = new LinkedList<>();
    public static LinkedList<comboItem> customBoardList = new LinkedList<>();
    public static LinkedList<comboItem> customBoardListBuilder = new LinkedList<>();


    public static int playerPlaying = 0;
    public static String whitePieceTaken = "";
    public static String blackPieceTaken = "";
    public static int whitePieceTakenValue = 0;
    public static int blackPieceTakenValue = 0;






    public static void main(String[] args) {

        //// initiate pieces ////
        for (int i = 0; i < 8; i++) {   // white pawns
            normalBoardPieces.add(new pawn(i,1, 0));
        }
        for (int i = 0; i < 8; i++) {   // black pawns
            normalBoardPieces.add(new pawn(i,6, 1));
        }
        // add pieces to list
        int tY = 0;
        for (int i = 0; i<=1; i++) {
            normalBoardPieces.add(new rook(0, tY, i));
            normalBoardPieces.add(new knight(1, tY, i));
            normalBoardPieces.add(new bishop(2, tY, i));
            normalBoardPieces.add(new queen(3, tY, i));
            normalBoardPieces.add(new king(4, tY, i));
            normalBoardPieces.add(new bishop(5, tY, i));
            normalBoardPieces.add(new knight(6, tY, i));
            normalBoardPieces.add(new rook(7, tY, i));
            tY = 7;
        }
        //// end ////

        //// populate board ////
        /*for (piece piece: normalBoardPieces)
            normalBoard[piece.x][piece.y] = piece;
        for (piece piece: normalPiecesVoidSpaces)
            normalBoard[piece.x][piece.y] = piece;
*/
        for (int x = 0; x<8; x++) {
            for (int y = 0; y<8; y++){
                if (normalBoard[x][y] == null)
                    normalBoard[x][y] = new empty();
            }
        }
        pieces.clear();
        for (piece piece:normalBoardPieces){
            pieces.add(piece.clone());
        }

        customBoardList.add(new comboItem("normal", 0, normalBoard, normalBoardCheck,normalBoardPieces, normalPiecesVoidSpaces));
        board = normalBoard.clone();
        // testing
        ArrayList<piece> newPieces = new ArrayList<>();
        ArrayList<piece> newPieceVoidSPaces = new ArrayList<>();
        newPieces.add(new queen(5,5,0));
        newPieceVoidSPaces.add(new voidSpace(0,0));
        newPieceVoidSPaces.add(new voidSpace(9,0));
        newPieceVoidSPaces.add(new voidSpace(0,9));
        newPieceVoidSPaces.add(new voidSpace(9,9));

        newPieceVoidSPaces.add(new voidSpace(1,0));
        newPieceVoidSPaces.add(new voidSpace(0,1));
        newPieceVoidSPaces.add(new voidSpace(1,9));
        newPieceVoidSPaces.add(new voidSpace(0,8));

        newPieceVoidSPaces.add(new voidSpace(8,0));
        newPieceVoidSPaces.add(new voidSpace(9,1));
        newPieceVoidSPaces.add(new voidSpace(9,8));
        newPieceVoidSPaces.add(new voidSpace(8,9));
        piece[][] boardTest = new piece[10][10];
        piece[][] boardTestCheck = new piece[10][10];

        for (int x = 0; x<10; x++) {
            for (int y = 0; y<10; y++){
                if (boardTest[x][y] == null)
                    boardTest[x][y] = new empty();
            }
        }


        customBoardList.add(new comboItem("testBoard", 1, boardTest,boardTestCheck, newPieces, newPieceVoidSPaces));

        for(comboItem Item : customBoardList){ // add board to custom list
            GUI.defaultVariantDrop.addItem(Item);
        }

        DatabaseAPI.returnMove("bqnrkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR%20w%20KQkq%20-%200%201","query");
        DatabaseAPI.convertToFEN(normalBoard);

        new GUI();
    }

    //https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
    public static synchronized void playSound(final String url) {

        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("assets\\soundFX\\"+url));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }
}
