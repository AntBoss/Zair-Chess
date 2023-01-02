package com.company;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class DatabaseAPI {

    static String returnMove(String FEN, String queryType) {


        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://www.chessdb.cn/cdb.php?action="+queryType+"&board="+FEN))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(postResponse.body());
            return(postResponse.body());

            //URL url = new URL("http://www.chessdb.cn/cdb.php?action="+queryType+"&board="+FEN);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "error";
    }

    //convert position to FEN
    static String convertToFEN(piece[][] position) {
        StringBuilder FEN = new StringBuilder();
        int numOfEmpty = 0;


        for(int y = 7; y>=0; y--){
            int yTemp = y;
            if (!GUI.playingAsWhite)
                yTemp= 7 - y;
            for(int x = 0; x<8; x++){
                int xTemp = x;
                if (!GUI.playingAsWhite)
                    xTemp = 7-x;


                if(position[xTemp][yTemp].Type != Types.EMPTY){
                    if(numOfEmpty!=0)
                        FEN.append(numOfEmpty);
                    switch (position[xTemp][yTemp].Type) {
                        case PAWN -> FEN.append((position[xTemp][yTemp].color == 0) ? 'P' : 'p');

                        case ROOK -> FEN.append((position[xTemp][yTemp].color == 0) ? 'R' : 'r');

                        case KNIGHT -> FEN.append((position[xTemp][yTemp].color == 0) ? 'N' : 'n');

                        case BISHOP -> FEN.append((position[xTemp][yTemp].color == 0) ? 'B' : 'b');

                        case KING -> FEN.append((position[xTemp][yTemp].color == 0) ? 'K' : 'k');

                        case QUEEN -> FEN.append((position[xTemp][yTemp].color == 0) ? 'Q' : 'q');

                    }
                    numOfEmpty=0;
                } else {
                    numOfEmpty++;
                }

            }
            if (numOfEmpty != 0)
                FEN.append(numOfEmpty);
            numOfEmpty=0;
            FEN.append("/");
        }
        FEN.deleteCharAt(FEN.length()-1);

        FEN.append("%20"); FEN.append((Main.playerPlaying == 0) ? 'w' : 'b'); FEN.append("%20");
        if (!piece.castleBK&&!piece.castleBQ&&!piece.castleWK&&!piece.castleWQ)
            FEN.append('-');
        else {
            if(piece.castleWK)
                FEN.append('K');
            if(piece.castleWQ)
                FEN.append('Q');
            if(piece.castleBK)
                FEN.append('k');
            if(piece.castleBQ)
                FEN.append('q');
        }
        FEN.append("%20"); FEN.append(piece.FEN_enPassant); FEN.append("%20");
        FEN.append(piece.moveRule); FEN.append("%20"); FEN.append(piece.globalMoveCounter);


        System.out.println(FEN);
        return FEN.toString();

    }



}
