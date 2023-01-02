package com.company;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Objects;

import static com.company.GUI.computersTurn;
import static com.company.GUI.playingAsWhite;
import static com.company.Main.*;

public abstract class piece implements Cloneable{


    int x, y, color, value;
    static int xpp,ypp,soundCounter;
    String uR;   // unicode representation of piece
    Types Type;
    boolean hasMoved,castled, castledValidCheck,hasMovedSecond = false,enPassantValidCheck = false;
    String isAttacked = " ";
    static private String drawCause = "unknown";
    static int moveRule = 0; // 50moveRuleCounter
    static int globalMoveCounter = 1; // number of moves played (needed for converting position to FEN notation [DatabaseAPI] )
    static private LinkedList<piece> pawnsEnPassantHasMoved = new LinkedList<>();
    public static boolean enPassant = false;
    public static boolean castleWK=true, castleWQ=true, castleBK=true, castleBQ=true; // checks castling rights (needed for converting position to FEN notation [DatabaseAPI] )
    static String FEN_enPassant = "-";

    piece(){}

    @Override
    public piece clone(){
        piece piece;
        try
        {
            piece = (piece) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new Error();
        }

        return piece;
    }

    public static piece getPiece(int x, int y, int xs,int ys){
        xpp = xs;
        ypp = ys;
        for (piece piece : pieces){
            if(piece.x == x && piece.y == y)
                return piece;
        }
        return null;
    }

    public static boolean nameToPiece(Types type){
        for(piece piece:pieces) {
            if(piece.Type == type)
                return true;
        }
        return false;
    }
    public static boolean insufficientBishopCheck() {
        int instancesOfBlack = 0;
        int instancesOfWhite = 0;
        for (piece piece : pieces) {
            if (piece.Type == Types.BISHOP && piece.color == 0) {
                if (piece.x % 2 == 0) {
                    if (piece.y % 2 == 0)
                        instancesOfBlack++;
                    else instancesOfWhite++;
                } else {
                    if (piece.y % 2 == 0)
                        instancesOfWhite++;
                    else instancesOfBlack++;
                }
                for (piece piece2 : pieces) {
                    if (piece2.Type == Types.BISHOP && piece2.color == 1) {
                        if (piece2.x % 2 == 0) {
                            if (piece2.y % 2 == 0)
                                instancesOfBlack++;
                            else instancesOfWhite++;
                        } else {
                            if (piece2.y % 2 == 0)
                                instancesOfWhite++;
                            else instancesOfBlack++;
                        }
                    }
                }
            }
        }
        if (instancesOfWhite==2 || instancesOfBlack==2)
            return true;
        else return false;
    }

    public boolean isAttacking(int x, int y, piece[][] board){return false;}
    public abstract boolean valid(int x, int y, piece[][] board);
    public void validCastle (int x, int y, piece[][] board){}
    public boolean validEnPassant (int x, int y, piece[][] board){return false;}


    public void move(int x, int y) {

        if(playingAsWhite && playerPlaying==0 || !playingAsWhite && playerPlaying==1){
            computersTurn = true;
        }else{
            computersTurn = false;
        }

        soundCounter=-1;
        if (playerPlaying == 1) {
            globalMoveCounter++;
        }
        else moveRule++;


        if(board[this.x][this.y].validEnPassant(x,y,board)) {
            enPassant = true;

        }

        for(piece pawn:pawnsEnPassantHasMoved){
            pawn.hasMovedSecond = true;
        } pawnsEnPassantHasMoved.clear();


        if(board[this.x][this.y].Type == Types.PAWN || board[x][y].Type != Types.EMPTY)
            moveRule=0;


        //sounds
         if(Main.board[x][y].Type == Types.EMPTY && !enPassant){
            soundCounter=0; //move
        } else{
            soundCounter=1; //capture
        }

        board[this.x][this.y].validCastle(x,y,board);
        GUI.checked = false;



        int correctForPlayer = (GUI.playingAsWhite) ? 0 : 2;

        if(enPassant){
            if (Main.playerPlaying == 0 && GUI.playingAsWhite || playerPlaying==1 && !GUI.playingAsWhite) {
                if(GUI.playingAsWhite) {
                    Main.whitePieceTakenValue += Main.board[x][y - 1].value;
                    Main.whitePieceTaken += Main.board[x][y - 1].uR;
                } else{
                    Main.blackPieceTakenValue += Main.board[x][y-1].value;
                    Main.blackPieceTaken += Main.board[x][y-1].uR;
                }
            } else {
                if(!GUI.playingAsWhite) {
                    Main.whitePieceTakenValue += Main.board[x][y + 1].value;
                    Main.whitePieceTaken += Main.board[x][y + 1].uR;
                } else{
                    Main.blackPieceTakenValue += Main.board[x][y+1].value;
                    Main.blackPieceTaken += Main.board[x][y+1].uR;
                }
            }
        }


        if (Main.playerPlaying == 0 && Main.board[x][y].Type != Types.EMPTY) {
            Main.whitePieceTakenValue += Main.board[x][y].value;
            Main.whitePieceTaken += Main.board[x][y].uR;
        } else if (Main.board[x][y].Type != Types.EMPTY){
            Main.blackPieceTakenValue += Main.board[x][y].value;
            Main.blackPieceTaken += Main.board[x][y].uR;
        }

        Main.pieces.remove(Main.board[x][y]);
        Main.board[x][y] = Main.board[this.x][this.y];
        Main.board[this.x][this.y] = new empty();
        this.x = x;
        this.y = y;

        if(Main.board[x][y].Type == Types.KING || Main.board[x][y].Type == Types.ROOK ||Main.board[x][y].Type == Types.PAWN) {
            if (Main.board[x][y].Type == Types.PAWN)
                pawnsEnPassantHasMoved.add(Main.board[x][y]);
            if(!Main.board[x][y].hasMoved) {
                Main.board[x][y].hasMoved = true;
                if (castled){
                    soundCounter=3;
                    castle(x,y, board);}
                castled = false;
            }//en passant
            if (enPassant) {
                if (playerPlaying==0){
                    Main.pieces.remove(Main.board[x][y-1+correctForPlayer]);
                    Main.board[x][y-1+correctForPlayer] = new empty();
                }else {
                    Main.pieces.remove(Main.board[x][y+1-correctForPlayer]);
                    Main.board[x][y+1-correctForPlayer] = new empty();
                }

            }
        }
        enPassant = false;

        if (GUI.playingAsWhite) {
            GUI.materialWhiteSide.setText("material: " + (Main.whitePieceTakenValue - Main.blackPieceTakenValue) + " " + Main.whitePieceTaken);
            GUI.materialBlackSide.setText("material: " + (Main.blackPieceTakenValue - Main.whitePieceTakenValue) + " " + Main.blackPieceTaken);
        }else{
            GUI.materialBlackSide.setText("material: " + (Main.whitePieceTakenValue - Main.blackPieceTakenValue) + " " + Main.whitePieceTaken);
            GUI.materialWhiteSide.setText("material: " + (Main.blackPieceTakenValue - Main.whitePieceTakenValue) + " " + Main.blackPieceTaken);
        }
        playerPlaying= (playerPlaying==0)? 1:0;
        //kingCheck();
        GUI.pressed = false;
        piecesCurrentValid.clear();
        if(board[x][y].Type == Types.PAWN &&  (y==7 || y==0)) {
            pawn.promotePawn(x, y);
        }

        kingCheck(board, pieces, playerPlaying);

        for (piece piece: pieces) {

            if (piece.color == playerPlaying) {

                piecesCurrentValid.addLast(new LinkedList<>());
                piecesCurrentValid.getLast().add(new Integer[]{piece.x, piece.y});

                for (int yy = 0; yy < board.length; yy++) {   // check if movement is valid and populate board
                    for (int xx = 0; xx < board.length; xx++) {
                        //System.out.println(x+" "+y);
                        if (xx == piece.x && yy == piece.y)
                            continue;
                        if (board[piece.x][piece.y].valid(xx, yy, board)) {
                            piecesCurrentValid.getLast().add(new Integer[]{xx, yy});
                        }
                    }
                }
            }
        }
        if(GUI.checked) soundCounter=2; // check

        if (noCurrentMoves()) {
            if (GUI.checked) soundCounter=4;
            else soundCounter =5;
            checkmateTimer.setRepeats(false);
            checkmateTimer.start();
        }
        else if(checkDraw()){
            soundCounter = 6;
            checkmateTimer.setRepeats(false);
            checkmateTimer.start();
        }
        if(GUI.computerInitiated && computersTurn) {
            computerMove.setRepeats(false);
            computerMove.start();
        }

        switch (soundCounter){
            case 0 -> playSound("chess_Move.wav");
            case 1 -> playSound("chess_capture.wav");
            case 2 -> playSound("chess_check.wav");
            case 3 -> playSound("chess_castling.wav");
            case 4 -> playSound("chess_checkmate.wav");
            case 5 -> playSound("chess_stalemate.wav");
            case 6 -> playSound("chess_gameOver.wav");
        }

        if(pawnsEnPassantHasMoved.isEmpty()){
            FEN_enPassant = "-";
            System.out.println(FEN_enPassant);
        }else{
            for (piece pawn : pawnsEnPassantHasMoved) {
                if (!pawn.hasMovedSecond) {
                    if (((board[pawn.x][pawn.y].color == 0 && playingAsWhite) || (board[pawn.x][pawn.y].color == 1 && !playingAsWhite)) && pawn.y == 3) {
                        FEN_enPassant = String.valueOf((char) (pawn.x + 'A')).toLowerCase() + "" + (pawn.y);
                        System.out.println(FEN_enPassant);
                    }
                    else if (pawn.y == 4 && ((board[pawn.x][pawn.y].color == 1 && playingAsWhite) || (board[pawn.x][pawn.y].color == 0 && !playingAsWhite))){
                        FEN_enPassant = String.valueOf((char) (pawn.x + 'A')).toLowerCase() + "" + (pawn.y + 2);
                        System.out.println(FEN_enPassant);
                    }
                } else {
                    FEN_enPassant = "-";
                    System.out.println(FEN_enPassant);
                }
            }
        }


        //DatabaseAPI.returnMove(DatabaseAPI.convertToFEN(board),"querybest");
        //System.out.println("move:    " + DatabaseAPI.convertToFEN(board));


        }

        Timer checkmateTimer = new Timer(150, e -> { // necessary for board to repaint in time
            if(noCurrentMoves()) {
                String winner = (playerPlaying == 1) ? "white" : "black";
                if (GUI.checked) {
                    System.out.println("checkmate");
                    JOptionPane.showConfirmDialog(null, winner + " won!", "", JOptionPane.DEFAULT_OPTION);
                } else {
                    System.out.println("stalemate");
                    JOptionPane.showConfirmDialog(null, "draw through stalemate", "", JOptionPane.DEFAULT_OPTION);
                }
            }
            else if(checkDraw()){
                System.out.println("draw");
                JOptionPane.showConfirmDialog(null, "draw due to " + drawCause, "", JOptionPane.DEFAULT_OPTION);
            }
        });

    /**
     * method that makes the neccessary moves for castling to take place
     */
    public void castle(int x, int y, piece[][] board){
        int correctForColor = (GUI.playingAsWhite) ? 0 : 1;
        if (x==2 - correctForColor && y==0) {
            if (!board[0][0].hasMoved && board[0][0].Type == Types.ROOK) {
                board[3-correctForColor][0] = board[0][0];
                board[0][0] = new empty();
                board[3-correctForColor][0].x = 3-correctForColor;
                board[3-correctForColor][0].y = 0;
            }
        }
            else if (x==6 - correctForColor && y==0) {
            if (!board[7][0].hasMoved && board[7][0].Type == Types.ROOK) {
                board[5-correctForColor][0] = board[7][0];
                board[7][0] = new empty();
                board[5-correctForColor][0].x = 5-correctForColor;
                board[5-correctForColor][0].y = 0;
            }
        }
        else if (x==2 - correctForColor && y==7) {
            if (!board[0][7].hasMoved && board[0][7].Type == Types.ROOK) {
                board[3-correctForColor][7] = board[0][7];
                board[0][7] = new empty();
                board[3-correctForColor][7].x = 3-correctForColor;
                board[3-correctForColor][7].y = 7;
            }
        }
        else if (x==6 - correctForColor && y==7) {
            if (!board[7][7].hasMoved && board[7][7].Type == Types.ROOK) {
                board[5-correctForColor][7] = board[7][7];
                board[7][7] = new empty();
                board[5-correctForColor][7].x = 5-correctForColor;
                board[5-correctForColor][7].y = 7;
            }
        }

        }


    /**
     * when making a move, each piece passes  through its own, overriden, valid method. That valid method, before returning true or false, invokes this method in order to check if the king is in check or not. In order for this method to check if the king is in check after the move and thus make sure a move follows all chess rules, it populates a new board, checking whether the king has any valid moves available to get out of the check
     */
        public boolean kingCheck(piece[][] board, ArrayList<piece> pieces, int playerPlaying) {

            for (piece piece: pieces) {


                if (piece.color != playerPlaying) {
                    for (int y = 0; y<board.length; y++) {   // check if movement is valid and populate board
                        for (int x = 0; x<board.length;x++) {

                            if (x==piece.x && y==piece.y)
                                continue;
                            if (board[piece.x][piece.y].valid(x,y,Main.boardCheckTemp) && board[piece.x][piece.y].Type != Types.PAWN) {
                                board[x][y].isAttacked = "♞";
                                if(Main.board[x][y].Type == Types.KING && Main.board[x][y].color == playerPlaying && board == Main.board) {
                                    System.out.println("king in check");
                                    GUI.checked = true;
                                }

                                    //System.out.println(playerPlaying);
                                if (board[x][y].Type == Types.KING) {
                                    //ystem.out.println(playerPlaying + " almost there " + board[x][y].color);
                                    if (board[x][y].color == playerPlaying){
                                        //validPseudoMove(x,y,"refresh");
                                        //Testing.printBoardIsAttacked(boardCheckTemp);
                                    System.out.println("passed through");
                                    return false;}
                                }
                            }
                            else if (board[piece.x][piece.y].isAttacking(x,y,boardCheckTemp) && board[piece.x][piece.y].Type == Types.PAWN) {
                                board[x][y].isAttacked = "♞";
                                if(Main.board[x][y].Type == Types.KING && Main.board[x][y].color == playerPlaying&& board == Main.board) {
                                    System.out.println("king in check");
                                    GUI.checked = true;
                                }
                                if (board[x][y].Type == Types.KING && board[x][y].color == Main.playerPlaying)
                                    return false;
                            }
                        }
                    }
                }
            }
            //Testing.printBoard();
            //Testing.printBoardIsAttacked(boardCheckTemp);
            //System.out.println(playerPlaying + " " + Main.playerPlaying);
            //System.out.println("\nma i made it!!");
            return true;
    }


    /**
     * method that populates a board with future moves and feeds it to the method KingCheck
     */
    public boolean validCheck(int x, int y){
        //System.out.println(x+" "+y);
        //int playerPlayingTemp = (Main.playerPlaying==0) ? 1:0;
        //boardCheckTemp = board.clone();
        for(int p = 0; p<board.length;p++){
            for(int jj = 0; jj<board.length; jj++){
                boardCheckTemp[p][jj] = null;
            }
        }

        int playerPlayingTemp = Main.playerPlaying;
        piecesCheckTemp.clear();

        for (piece piece: pieces){
            piecesCheckTemp.add(piece.clone());
        }

        for (piece piece: piecesCheckTemp)
            boardCheckTemp[piece.x][piece.y] = piece;
        for (piece piece: piecesVoidSpaces)
            boardCheckTemp[piece.x][piece.y] = piece;

        for (int xd = 0; xd<board.length; xd++) {
            for (int yd = 0; yd<board.length; yd++){
                if (boardCheckTemp[xd][yd] == null)
                    boardCheckTemp[xd][yd] = new empty();
            }
        }

        //System.out.println(x+" "+y);
        
        if (enPassantValidCheck) {
            if (playerPlayingTemp==0){
                piecesCheckTemp.remove(Main.boardCheckTemp[x][y-1]);
                Main.boardCheckTemp[x][y-1] = new empty();
            }else {
                piecesCheckTemp.remove(Main.boardCheckTemp[x][y+1]);
                Main.boardCheckTemp[x][y+1] = new empty();
            }
        }
        if (castledValidCheck){
            castle(x,y,boardCheckTemp);
        }
            piecesCheckTemp.remove(Main.boardCheckTemp[x][y]);
            Main.boardCheckTemp[x][y] = Main.boardCheckTemp[this.x][this.y];
            Main.boardCheckTemp[this.x][this.y] = new empty();
            Main.boardCheckTemp[this.x][this.y].x = x;
            Main.boardCheckTemp[this.x][this.y].y = y;



            enPassantValidCheck=false;
            castledValidCheck = false;
        //Main.printBoard();
        //System.out.println("                                         " + boardCheckTemp[x][y] + " " + boardCheckTemp[x][y].Type + " " + Main.boardCheckTemp[this.x][this.y]);

        for (int yp = 0;yp<board.length; yp++) {   // check if movement is valid and populate board
            for (int xp = 0; xp < board.length; xp++) {
                boardCheckTemp[xp][yp].isAttacked = " ";
            }
        }
        //System.out.println("cleared is Atttacking");

        return kingCheck(Main.boardCheckTemp, Main.piecesCheckTemp, playerPlayingTemp);
        //return true;
    }


        public static boolean noCurrentMoves() {
            for (LinkedList<Integer[]> list : piecesCurrentValid) {
                if(list.size() > 1)
                    return false;
            }
            return true;
        }

        public static boolean checkDraw(){
            // dead position (insufficient material)
            if(pieces.size() <= 4){
                if(pieces.size() == 2){
                    drawCause = "insufficient material";
                    return true;
                }
                else if(pieces.size() == 3){
                    if(nameToPiece(Types.BISHOP) || nameToPiece(Types.KNIGHT)) {
                        drawCause = "insufficient material";
                        return true;
                    }
                }
                else if (pieces.size() == 4 && insufficientBishopCheck()){
                    drawCause = "insufficient material";
                    return true;
                }
            }
            // 50 move rule
            else if(moveRule == 50) {
                drawCause = "50 move rule";
                return true;
            }

            return false;
        }


        Timer computerMove = new Timer(1000, e -> {
            System.out.println("ai initiated");
            AI_engine.start();
            System.out.println("ai moved");

        });

     }



