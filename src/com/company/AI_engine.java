package com.company;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import static com.company.Main.*;
import static com.company.Main.board;


public class AI_engine {
    public static ArrayList<piece> pieces = new ArrayList<>();
    public static LinkedList<LinkedList<Integer[]>> piecesCurrentValid = new LinkedList<>();
    public static int playerPlaying;

    static void start() {
        checkDatabase();
        //CalculateMoves();
    }

    private static void checkDatabase() {

        String move = DatabaseAPI.returnMove(DatabaseAPI.convertToFEN(board), "querybest");
        if (move.contains("nobestmove") || move.contains("invalid")) {
            //System.out.println(DatabaseAPI.convertToFEN(board));
            //CalculateMoves();
            move = DatabaseAPI.returnMove(DatabaseAPI.convertToFEN(board), "queue");
            computerMove.setRepeats(false);
            computerMove.start();
            return;

        }else{
        move = move.substring(move.indexOf(':') + 1, move.length() - 1);
        System.out.println(move);

        move = move.replace('a', '0');
        move = move.replace('b', '1');
        move = move.replace('c', '2');
        move = move.replace('d', '3');
        move = move.replace('e', '4');
        move = move.replace('f', '5');
        move = move.replace('g', '6');
        move = move.replace('h', '7');

        System.out.println(move);
        int xOld = Integer.parseInt(String.valueOf(move.charAt(0)));
        int yOld = Integer.parseInt(String.valueOf(move.charAt(1))) - 1;
        int xNew = Integer.parseInt(String.valueOf(move.charAt(2)));
        int yNew = Integer.parseInt(String.valueOf(move.charAt(3))) - 1;
        ;
        System.out.println("xOld: " + xOld + " yOld: " + yOld + " xNew: " + xNew + " yNew" + yNew);

        if (!GUI.playingAsWhite) {
            xOld = board.length - 1 - xOld;
            xNew = board.length - 1 - xNew;
            yOld = board.length - 1 - yOld;
            yNew = board.length - 1 - yNew;
        }

        if (board[xOld][yOld].valid(xNew, yNew, board))
            board[xOld][yOld].move(xNew, yNew);
        GUI.boardInner.repaint();
        GUI.mainFrame.repaint();
    }
}



    private static void CalculateMoves(){
        boardConstructor originalBoard = new boardConstructor("original", 8, Main.pieces, Main.board, Main.playerPlaying);
        originalBoard.calculate(originalBoard);
    }



    //private static void boardConstructor(){}


    static class boardConstructor{
        int size, playerPlaying, testingCounter = 0;
        ArrayList<piece> pieceList = new ArrayList<>();
        private piece[][] board;
        String tag;

        //getters
        ArrayList<piece> getPieceList(){return pieceList;}
        int getPlayerPlaying(){return playerPlaying;}
        piece[][] getBoard(){return board;}

        boardConstructor(String tag, int size, ArrayList<piece> pieceList,piece[][] boardToClone, int playerPlaying){

            board = boardToClone.clone();
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board.length; y++) {
                    board[x][y] = null;
                }
            }
            this.size = size;
            for (piece piece: pieceList){
                //System.out.println(piece);
                this.pieceList.add(piece.clone());
            }
            this.playerPlaying = playerPlaying;
            this.tag = tag;

            for (piece piece : this.pieceList)
                board[piece.x][piece.y] = piece;

            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board.length; y++) {
                    if (board[x][y] == null)
                        board[x][y] = new empty();
                }
            }
        }

        void calculate(boardConstructor originalBoard){

            System.out.println("important: " + minimax(originalBoard, 1, 0,0, originalBoard.getPlayerPlaying() == 0));
            System.out.println(testingCounter + " thelw gania" );
        }

        int minimax(boardConstructor board, int depth, int alpha, int beta, boolean maximizingPlayer){
           if (depth == 0){
               int staticEval = 0;
               for(piece piece : board.getPieceList()){
                   if (piece.color == 0)
                       staticEval += piece.value;
                   else staticEval -= piece.value;
               }
               return staticEval;
           }


           if (maximizingPlayer){
               int maxEval = Integer.MIN_VALUE;
               for(boardConstructor child : positionCalculator(this)) {
                    int eval = minimax(child, depth - 1, 0,0,false);
                    maxEval = Math.max(maxEval, eval);
                    return maxEval;
               }
           }
           else {
               int minEval = Integer.MAX_VALUE;
               for(boardConstructor child : positionCalculator(this)) {
                   int eval = minimax(child, depth - 1, 0,0,true);
                   minEval = Math.min(minEval, eval);
                   return minEval;
               }
           }
           return 2;
        }

        ArrayList<boardConstructor> positionCalculator(boardConstructor currentPosition){
            ArrayList<boardConstructor> childBoardList = new ArrayList<>();
            for (piece piece: currentPosition.pieceList) {

                if (piece.color == currentPosition.getPlayerPlaying()) {

                    for (int yy = 0; yy < currentPosition.getBoard().length; yy++) {   // check if movement is valid and populate board
                        for (int xx = 0; xx < currentPosition.getBoard().length; xx++) {

                            if (xx == piece.x && yy == piece.y)
                                continue;
                            if (currentPosition.getBoard()[piece.x][piece.y].valid(xx, yy, currentPosition.getBoard())) {
                                boardConstructor childBoard = new boardConstructor("new", 8, currentPosition.getPieceList(), currentPosition.getBoard(), currentPosition.getPlayerPlaying());
                                //move sequence
                                if (false) {
                                    if (childBoard.getPlayerPlaying()==0){
                                        childBoard.getPieceList().remove(childBoard.getBoard()[xx][yy-1]);
                                        childBoard.getBoard()[xx][yy-1] = new empty();
                                    }else {
                                        childBoard.getPieceList().remove(childBoard.getBoard()[xx][yy+1]);
                                        childBoard.getBoard()[xx][yy+1] = new empty();
                                    }
                                }
                                if (false){
                                    piece.castle(xx,yy,childBoard.getBoard());
                                }
                                childBoard.getPieceList().remove(childBoard.getBoard()[xx][yy]);
                                childBoard.getBoard()[xx][yy] = childBoard.getBoard()[piece.x][piece.y];
                                childBoard.getBoard()[piece.x][piece.y] = new empty();
                                childBoard.getBoard()[piece.x][piece.y].x = xx;
                                childBoard.getBoard()[piece.x][piece.y].y = yy;



                                //enPassantValidCheck=false;
                                //castledValidCheck = false;
                                testingCounter++;
                            childBoardList.add(childBoard);
                            }

                        }
                    }
                }
            }
            return childBoardList;
        }

    }

    static Timer computerMove = new Timer(6000, e -> {

        String move = DatabaseAPI.returnMove(DatabaseAPI.convertToFEN(board), "querybest");
        if (move.contains("nobestmove") || move.contains("invalid")) {
            //System.out.println(DatabaseAPI.convertToFEN(board));
            //CalculateMoves();


        }else{
            move = move.substring(move.indexOf(':') + 1, move.length() - 1);
            System.out.println(move);

            move = move.replace('a', '0');
            move = move.replace('b', '1');
            move = move.replace('c', '2');
            move = move.replace('d', '3');
            move = move.replace('e', '4');
            move = move.replace('f', '5');
            move = move.replace('g', '6');
            move = move.replace('h', '7');

            System.out.println(move);
            int xOld = Integer.parseInt(String.valueOf(move.charAt(0)));
            int yOld = Integer.parseInt(String.valueOf(move.charAt(1))) - 1;
            int xNew = Integer.parseInt(String.valueOf(move.charAt(2)));
            int yNew = Integer.parseInt(String.valueOf(move.charAt(3))) - 1;
            ;
            System.out.println("xOld: " + xOld + " yOld: " + yOld + " xNew: " + xNew + " yNew" + yNew);

            if (!GUI.playingAsWhite) {
                xOld = board.length - 1 - xOld;
                xNew = board.length - 1 - xNew;
                yOld = board.length - 1 - yOld;
                yNew = board.length - 1 - yNew;
            }

            if (board[xOld][yOld].valid(xNew, yNew, board))
                board[xOld][yOld].move(xNew, yNew);
            GUI.boardInner.repaint();
            GUI.mainFrame.repaint();
        }

    });
    

}
