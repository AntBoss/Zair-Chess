package com.company;

import java.util.ArrayList;

import static com.company.Main.*;
import static com.company.Main.board;

public class comboItem {
    private String stringToDisplay;
    private int indexOfString;
    private piece[][] board;
    private piece[][] testingBoard;
    private ArrayList<piece> pieces;
    private ArrayList<piece> piecesVoidSpaces;

    public comboItem(String stringToDisplay, int indexOfString, piece[][] board,piece[][] testingBoard, ArrayList<piece> pieces, ArrayList<piece> piecesVoidSpaces){
        this.stringToDisplay = stringToDisplay;
        this.indexOfString = indexOfString;
        this.board = board;
        this.testingBoard = testingBoard;
        this.pieces = pieces;
        this.piecesVoidSpaces = piecesVoidSpaces;
    }

    @Override
    public String toString(){
        return stringToDisplay;
    }
    public void setString(String stringToDisplay){
        this.stringToDisplay = stringToDisplay;
    }
    public String getString(){
        return stringToDisplay;
    }
    public int getIndex(){
        return indexOfString;
    }
    public piece[][] getPieceBoard(){
        return board;
    }
    public piece[][] getPieceBoardCheck(){
        return testingBoard;
    }
    public ArrayList<piece> getPieceList(){
        return pieces;
    }
    public ArrayList<piece> getPieceVoidList(){return piecesVoidSpaces;}
    
    public comboItem cloneAll() {
        String stringToDisplay = this.toString();
        int indexOfString = this.getIndex();
        piece[][] board = this.getPieceBoard().clone();
        piece[][] testingBoard = this.getPieceBoardCheck().clone();
        ArrayList<piece> pieces = new ArrayList<>();
        ArrayList<piece> piecesVoidSpaces = new ArrayList<>();

        for (piece piece:this.getPieceList()){
            pieces.add(piece.clone());
        }
        for (piece piece:this.getPieceVoidList()){
            piecesVoidSpaces.add(piece.clone());
        }
        
        
        
        
        return new comboItem(stringToDisplay, indexOfString, board, testingBoard, pieces, piecesVoidSpaces);
    }


}
