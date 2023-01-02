package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.Timer;

import static com.company.Main.*;

import static javax.swing.SwingConstants.CENTER;




public class GUI implements ActionListener{



        // initialize

        static JFrame mainFrame = new JFrame();
        static JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        static JPanel leftPanel = new JPanel(new GridBagLayout());
        static JPanel rightGamePanel = new JPanel(new GridBagLayout()); // panel for main game
        static JPanel rightDefaultPanel = new JPanel(new GridBagLayout()); // panel for selecting game
        static JPanel rightBuilderPanel = new JPanel(new GridBagLayout());
        static JPanel rightSettingsPanel = new JPanel(new GridBagLayout());
        static JPanel boardPanel = new JPanel();
        static JPanel placeHolderMenuPanel = new JPanel();
        GridBagConstraints gbcP = new GridBagConstraints();
        GridBagConstraints buttonConstraints = new GridBagConstraints();


        static LinkedList<Integer> whiteCurrentSpaces = new LinkedList<>();
        static LinkedList<Integer> blackCurrentSpaces = new LinkedList<>();

        //buttons
        static JButton menuGame = new JButton("game");
        static JButton menuBuilder = new JButton("builder");
        static JButton menuSettings = new JButton("settings");
        static JButton backGameBtn = new JButton("< Back");
        static JPanel backGameBtnPlaceholder = new JPanel();

        // pop up for pawn promotion
        static JPopupMenu popUpPiece = new JPopupMenu();
        static JMenuItem popUpQueen = new JMenuItem();
        static JMenuItem popUpKnight = new JMenuItem();
        static JMenuItem popUpRook = new JMenuItem();
        static JMenuItem popUpBishop = new JMenuItem();


    //settings
    static JLabel settingsColor = new JLabel("Color Scheme: ");
    static JLabel settingsResolution = new JLabel("Resolution: ");
    static JLabel settingsPieces = new JLabel("Pieces: ");
    static JRadioButton resVeryLow = new JRadioButton("Very Low");
    static JRadioButton resLow = new JRadioButton("Low");
    static JRadioButton resMid = new JRadioButton("Standard");
    static JRadioButton resHigh = new JRadioButton("High");
    static JRadioButton resVeryHigh = new JRadioButton("Very High");
    static JRadioButton colDefault = new JRadioButton("Default");
    static JRadioButton col1 = new JRadioButton("Color1");
    static JRadioButton col2 = new JRadioButton("Color2");
    static JRadioButton col3 = new JRadioButton("Color3");
    static JRadioButton col4 = new JRadioButton("Custom");
    static JRadioButton piecesDefault = new JRadioButton("Default");
    static JRadioButton piecesKosal = new JRadioButton("Kosal");
    static JRadioButton piecesKaneo = new JRadioButton("Kaneo");
    static JButton settingsLeftColorBtn = new JButton();
    static JButton settingsRightColorBtn = new JButton();
    static JButton settingsBoardBlackBtn = new JButton();
    static JButton settingsBoardWhiteBtn = new JButton();
    static JButton settingsRightSecondaryBtn = new JButton();
    static JButton settingsRightTertiaryBtn = new JButton();
    static JButton settingsPrevMoveBtn = new JButton();
    static JTextField settingsAlphaTxtField = new JTextField();
    static JLabel settingsLeftColorLbl = new JLabel("Main Color: ");
    static JLabel settingsRightColorLbl = new JLabel("Secondary Color: ");
    static JLabel settingsBoardBlackLbl = new JLabel("Board Black Spaces: ");
    static JLabel settingsBoardWhiteLbl = new JLabel("Board White Spaces: ");
    static JLabel settingsRightSecondaryLbl = new JLabel("Main Label Color: ");
    static JLabel settingsRightTertiaryLbl = new JLabel("Secondary Label Color: ");
    static JLabel settingsPrevMoveLbl = new JLabel("Previous Move Indicator: ");
    static JLabel settingsAlphaLbl = new JLabel("Alpha Value (0 - 255): ");

    //defaultPanel
    static JPanel defaultAccountPanel = new JPanel(new GridBagLayout());
    static JPanel defaultUpperPanel = new JPanel(new GridBagLayout());
    static JPanel defaultNewGamePanel = new JPanel(new GridBagLayout());
    static JPanel miniBoardPanel = new JPanel();
    static JButton defaultContinueBtn = new JButton("Continue");
    static JButton defaultNewGameBtn = new JButton("New Game");
    static JComboBox<comboItem> defaultVariantDrop = new JComboBox<>();
    static JLabel defaultVariantLbl = new JLabel("Variant: ");
    static JLabel defaultOpponentLbl = new JLabel("Play Against: ");
    static JLabel defaultPlayAsLbl = new JLabel("Play as: ");
    static JRadioButton defaultFriendRB = new JRadioButton("Friend");
    static JRadioButton defaultComputerRB = new JRadioButton("Computer");
    static JRadioButton defaultPAWhiteRB = new JRadioButton();
    static JRadioButton defaultPARandomRB = new JRadioButton();
    static JRadioButton defaultPABlackRB = new JRadioButton();

    //builderPanel
    static JPanel builderPiecePanel = new JPanel(new GridBagLayout());
    static JPanel builderPieceInner = new JPanel(){
        @Override
        public void paint(Graphics g){
            int size = Math.min(this.getHeight() / 4- this.getHeight()/32, this.getWidth() / 2 - this.getWidth()/4);
            g.setColor(rightPanelSecondary);
            switch (pos) {
                case -1 -> {}
                case 0 -> g.fillRoundRect(this.getWidth()/16 -size/20, size*0 + this.getHeight()/32*0-size/20, size+2*size/20, size+2*size/20, 12, 12);
                case 1 -> g.fillRoundRect(this.getWidth()/16 -size/20, size*1 + this.getHeight()/32*1-size/20, size+2*size/20, size+2*size/20, 12, 12);
                case 2 -> g.fillRoundRect(this.getWidth()/16 -size/20, size*2 + this.getHeight()/32*2-size/20, size+2*size/20, size+2*size/20, 12, 12);
                case 3 -> g.fillRoundRect(this.getWidth()/16 -size/20, size*3 + this.getHeight()/32*3-size/20, size+2*size/20, size+2*size/20, 12, 12);

                case 4 -> g.fillRoundRect(this.getWidth() -size - this.getHeight()/16-size/20, size*0+ this.getHeight()/32*0-size/20, size+2*size/20, size+2*size/20, 12, 12);
                case 5 -> g.fillRoundRect(this.getWidth() -size - this.getHeight()/16-size/20, size*1+ this.getHeight()/32*1-size/20, size+2*size/20, size+2*size/20, 12, 12);
                case 6 -> g.fillRoundRect(this.getWidth() -size - this.getHeight()/16-size/20, size*2+ this.getHeight()/32*2-size/20, size+2*size/20, size+2*size/20, 12, 12);
                case 7 -> g.fillRoundRect(this.getWidth() -size - this.getHeight()/16-size/20, size*3+ this.getHeight()/32*3-size/20, size+2*size/20, size+2*size/20, 12, 12);
            }


            if (builderPieceColor==0)
                g.setColor(Color.WHITE);
            else g.setColor(Color.WHITE);
            for(int i = 0; i<4; i++){
                g.fillRoundRect(this.getWidth()/16, size*i + this.getHeight()/32*i, size, size, 12, 12);
                g.fillRoundRect(this.getWidth() -size - this.getHeight()/16, size*i+ this.getHeight()/32*i, size, size, 12, 12);




                switch (i) {
                    case (0) -> {
                        g.drawImage(imgs[6*builderPieceColor+1].getScaledInstance(size, size, res), this.getWidth() / 16, 0, this);
                        g.drawImage(imgs[6*builderPieceColor+4].getScaledInstance(size, size, res), this.getWidth() - size - this.getHeight() / 16, 0, this);
                    }
                    case (1) -> {
                        g.drawImage(imgs[6*builderPieceColor+2].getScaledInstance(size, size, res), this.getWidth() / 16, size * i + this.getHeight() / 32 * i, this);
                        g.drawImage(imgs[6*builderPieceColor].getScaledInstance(size, size, res), this.getWidth() - size - this.getHeight() / 16, size * i + this.getHeight() / 32 * i, this);
                    }
                    case (2) -> {
                        g.drawImage(imgs[6*builderPieceColor+5].getScaledInstance(size, size, res), this.getWidth() / 16, size * i + this.getHeight() / 32 * i, this);
                        g.drawImage(imgs[6*builderPieceColor+3].getScaledInstance(size, size, res), this.getWidth() - size - this.getHeight() / 16, size * i + this.getHeight() / 32 * i, this);
                    }
                    case (3) -> {
                        g.setFont(new Font("empty", Font.PLAIN, size / 3));
                        g.setColor((builderPieceColor==1)? Color.DARK_GRAY : Color.DARK_GRAY);
                        g.drawString("empty", this.getWidth() / 16 + size / 16, size * i + this.getHeight() / 32 * i + size / 2);
                        g.drawString("void", this.getWidth() - size - this.getHeight() / 16 + size / 5, size * i + this.getHeight() / 32 * i + size / 2);
                    }
                }
            }
            System.out.println(size);
        }
    };
    static JPanel builderBoardPanel = new JPanel(new GridBagLayout());
    static JComboBox<comboItem> builderVariantDrop = new JComboBox<>();
    static JButton builderDeleteBtn = new JButton("Delete");
    static JButton builderSaveBtn = new JButton("Save");
    static JTextField builderNameTxtField = new JTextField("name");
    static JLabel builderSizeLbl = new JLabel("size: ");
    static JComboBox<Integer> builderSizeCB = new JComboBox<>();
    static int builderPieceColor = 1;
    static StringBuilder builderNameString = new StringBuilder();
        //pieces
        static JRadioButton builderWhiteRB = new JRadioButton();
        static JRadioButton builderBlackRB = new JRadioButton();
        static JButton builderClearBtn = new JButton("empty");
        static JButton builderVoidBtn = new JButton("clear");



    static JLabel materialWhiteSide = new JLabel("material: " + (blackPieceTakenValue-whitePieceTakenValue) + " " + whitePieceTaken);
    static JLabel materialBlackSide = new JLabel("material: " + (whitePieceTakenValue-blackPieceTakenValue) + " " + blackPieceTaken);

    //builder
    static JPanel builderBoard = new JPanel() {
        @Override
        public void paint(Graphics g) {
            boolean isWhite = true;
            for (int y = 0; y<BBboard.length; y++){
                for (int x = 0; x<BBboard.length; x++) {
                    if(BBboard[x][BBboard.length -1 -y].Type == Types.VOID){
                        if(isWhite)
                        g.setColor(rightPanelGreen.darker());
                        else
                        g.setColor(rightPanelGreen.darker().darker());
                    }
                    else if (isWhite)
                        g.setColor(realWhiteSpaces);
                    else g.setColor(blackSpaces);
                    g.fillRect((builderBoard.getWidth()/BBboard.length)*x,(builderBoard.getWidth()/BBboard.length)*y,builderBoard.getWidth()/BBboard.length,builderBoard.getHeight()/BBboard.length);
                    isWhite = !isWhite;
                }
                if(BBboard.length % 2 ==0)
                    isWhite = !isWhite;
            }
            for (piece piece: BBpieces) {
                if (selectedPiece != null && piece.x == selectedPiece.x && piece.y == selectedPiece.y)
                    continue;
                if (piece.color == 1)
                    g.drawImage(imgs[piece.Type.typeToInt].getScaledInstance(builderBoard.getWidth() / BBboard.length, builderBoard.getWidth() / BBboard.length, res), (builderBoard.getWidth() / BBboard.length) * piece.x, ((BBboard.length - 1) - piece.y) * (builderBoard.getWidth() / BBboard.length), this);
                else
                    g.drawImage(imgs[piece.Type.typeToInt + 6].getScaledInstance(builderBoard.getWidth() / BBboard.length, builderBoard.getWidth() / BBboard.length, res), (builderBoard.getWidth() / BBboard.length) * piece.x, ((BBboard.length - 1) - piece.y) * (builderBoard.getWidth() / BBboard.length), this);
            }
        }
    };

    static JPanel miniBoardInner = new JPanel() {
        @Override
        public void paint(Graphics g) {
            boolean isWhite = true;
            for (int y = 0; y<board.length; y++){
                for (int x = 0; x<board.length; x++) {
                    if(board[x][board.length -1 -y].Type == Types.VOID)
                        g.setColor(rightPanelGreen.darker());
                    else if (isWhite)
                        g.setColor(realWhiteSpaces);
                    else g.setColor(blackSpaces);
                    g.fillRect((miniBoardInner.getWidth()/board.length)*x,(miniBoardInner.getWidth()/board.length)*y,miniBoardInner.getWidth()/board.length,miniBoardInner.getHeight()/board.length);
                    isWhite = !isWhite;
                }
                if(board.length % 2 ==0)
                isWhite = !isWhite;
            }
            for (piece piece: pieces) {
                if (selectedPiece != null && piece.x == selectedPiece.x && piece.y == selectedPiece.y)
                    continue;
                if (piece.color == 1)
                    g.drawImage(imgs[piece.Type.typeToInt].getScaledInstance(miniBoardInner.getWidth() / board.length, miniBoardInner.getWidth() / board.length, res), (miniBoardInner.getWidth() / board.length) * piece.x, ((board.length - 1) - piece.y) * (miniBoardInner.getWidth() / board.length), this);
                else
                    g.drawImage(imgs[piece.Type.typeToInt + 6].getScaledInstance(miniBoardInner.getWidth() / board.length, miniBoardInner.getWidth() / board.length, res), (miniBoardInner.getWidth() / board.length) * piece.x, ((board.length - 1) - piece.y) * (miniBoardInner.getWidth() / board.length), this);
            }
        }
    };

        static JPanel boardInner = new JPanel() {
            @Override
            public void paint (Graphics g){
                boolean isWhite = true;
                for (int y = 0; y<board.length; y++){
                    for (int x = 0; x<board.length; x++) {
                        if(board[x][board.length -1 -y].Type == Types.VOID)
                            g.setColor(rightPanelGreen);
                        else if (isWhite)
                            g.setColor(realWhiteSpaces);
                        else g.setColor(blackSpaces);
                        g.fillRect((boardInner.getWidth()/board.length)*x,(boardInner.getWidth()/board.length)*y,boardInner.getWidth()/board.length,boardInner.getHeight()/board.length);
                        isWhite = !isWhite;
                    }
                    if(board.length % 2 ==0)
                    isWhite = !isWhite;
                }


                //prev movement
                LinkedList<LinkedList<Integer>> currentSpaces = new LinkedList<>();
                currentSpaces.add(whiteCurrentSpaces); currentSpaces.add(blackCurrentSpaces);
                    for (LinkedList<Integer> list : currentSpaces) {
                        for (Integer p : list) {
                            /*if (p / 10 % 2 == 0) {
                                if (p % 10 % 2 == 0)
                                    g.setColor(validBlack);
                                else g.setColor(validWhite);
                            } else {
                                if (p % 10 % 2 == 0)
                                    g.setColor(validWhite);
                                else g.setColor(validBlack);
                            }*/
                            g.setColor(new Color(prevMoveAlpha.getRed(), prevMoveAlpha.getGreen(), prevMoveAlpha.getBlue(), prevColorAlpha));
                            g.fillRect((boardInner.getWidth() / board.length) * (p / 10), (boardInner.getWidth() / board.length) * ((board.length - 1) - p % 10), boardInner.getWidth() / board.length, boardInner.getWidth() / board.length);
                        }
                    }

                //valid movement circles
                if (pressed && (!computerInitiated) || (computerInitiated && (playerPlaying == 0 && playingAsWhite || playerPlaying == 1 && !playingAsWhite))) {
                    int listIndex = -1;
                                for (LinkedList<Integer[]> list : piecesCurrentValid) {
                                    if (Arrays.equals(list.get(0), new Integer[]{ex, ey})) {
                                        listIndex = piecesCurrentValid.indexOf(list);
                                        break;
                                    }
                                }
                    //System.out.println(listIndex);
                                if ((int)(boardInner.getWidth() / (20 * (board.length / 8.0))) != 0) {
                                    vC = validCircle.getScaledInstance((int) (boardInner.getWidth() / (20 * (board.length / 8.0))), (int) (boardInner.getWidth() / (20 * (board.length / 8.0))), res);
                                    vNC = validCircleNotFilled.getScaledInstance(boardInner.getWidth() / board.length, boardInner.getWidth() / board.length, res);
                                }
                                if (listIndex != -1) {
                                    for (Integer[] validPos : piecesCurrentValid.get(listIndex).subList(1, piecesCurrentValid.get(listIndex).size())) {

                                        if (board[validPos[0]][validPos[1]].Type == Types.EMPTY)
                                            g.drawImage(vC, (int)((boardInner.getWidth() / board.length) * (validPos[0]) + (boardInner.getWidth() / (27*(board.length/8.0)))), (int)((boardInner.getWidth() / board.length) * ((board.length - 1) - validPos[1]) + (boardInner.getWidth() / (25*board.length/8.0))), this);
                                        else
                                            g.drawImage(vNC, (boardInner.getWidth() / board.length) * (validPos[0]), (boardInner.getWidth() / board.length) * ((board.length - 1) - validPos[1]), this);
                                    }
                                }
                }

                for (piece piece: pieces) {
                    if(selectedPiece!=null && piece.x == selectedPiece.x && piece.y == selectedPiece.y)
                        continue;
                    if(piece.color == 1)
                    g.drawImage(imgs[piece.Type.typeToInt].getScaledInstance(notZero(boardInner.getWidth()/board.length),notZero(boardInner.getWidth()/board.length), res), (boardInner.getWidth()/board.length)*piece.x, ((board.length - 1) -piece.y)*(boardInner.getWidth()/board.length), this);
                    else
                        g.drawImage(imgs[piece.Type.typeToInt + 6].getScaledInstance(notZero(boardInner.getWidth()/board.length),notZero(boardInner.getWidth()/board.length), res), (boardInner.getWidth()/board.length)*piece.x, ((board.length - 1) -piece.y)*(boardInner.getWidth()/board.length), this);
                }
                if(selectedPiece!=null){
                    if(selectedPiece.color == 1)
                    g.drawImage(imgs[selectedPiece.Type.typeToInt].getScaledInstance(boardInner.getWidth()/board.length,boardInner.getWidth()/board.length, res), piece.xpp, piece.ypp, this);
                    else g.drawImage(imgs[selectedPiece.Type.typeToInt + 6].getScaledInstance(boardInner.getWidth()/board.length,boardInner.getWidth()/board.length, res), piece.xpp, piece.ypp, this);
                }
        }};

        // colors
        static Color leftPanelBlack = new Color(68, 55, 66);
        static Color rightPanelGreen = new Color(102, 161, 130);
        //static Color validBlack = new Color(225, 31, 71);  //237, 37, 78
        static Color blackSpaces = new Color(203, 144, 77);
        //static Color validWhite = new Color(255, 61, 101); //255, 61, 101
        static Color prevMoveAlpha = new Color (229, 255, 102);
        static Color realWhiteSpaces = new Color(255, 255, 255);
        static Color rightPanelSecondary = new Color(102, 161, 130);
        static Color rightPanelTertiery = new Color(68, 55, 66);


        // images
        Image gameIcon = Toolkit.getDefaultToolkit().getImage("assets\\chessFrameIcon.png");



            static BufferedImage bB,bK,bN,bP,bQ,bR,wB,wK,wN,wP,wQ,wR, rClicked,rUnclicked,validCircle,validCircleNotFilled, gamePawnIcon, customizationBoardIcon, settingsSettingsIcon, pieceWhiteC, pieceWhiteU, pieceBlackC, pieceBlackU, pieceRandomC, pieceRandomU;

    static {
        try {
            bB = ImageIO.read(new File("assets\\piecesDefault\\bB.png"));
            bK = ImageIO.read(new File("assets\\piecesDefault\\bK.png"));
            bN = ImageIO.read(new File("assets\\piecesDefault\\bN.png"));
            bP = ImageIO.read(new File("assets\\piecesDefault\\bP.png"));
            bQ = ImageIO.read(new File("assets\\piecesDefault\\bQ.png"));
            bR = ImageIO.read(new File("assets\\piecesDefault\\bR.png"));
            wB = ImageIO.read(new File("assets\\piecesDefault\\wB.png"));
            wK = ImageIO.read(new File("assets\\piecesDefault\\wK.png"));
            wN = ImageIO.read(new File("assets\\piecesDefault\\wN.png"));
            wP = ImageIO.read(new File("assets\\piecesDefault\\wP.png"));
            wQ = ImageIO.read(new File("assets\\piecesDefault\\wQ.png"));
            wR = ImageIO.read(new File("assets\\piecesDefault\\wR.png"));
            rClicked = ImageIO.read(new File("assets\\radioClicked.png"));
            rUnclicked = ImageIO.read(new File("assets\\radioUnclicked.png"));
            validCircle = ImageIO.read(new File("assets\\validCircleFilled-min (1).png"));
            validCircleNotFilled = ImageIO.read(new File("assets\\validCircleNotFilled-min (1).png"));
            pieceWhiteC = ImageIO.read(new File("assets\\radioButtons\\radioButton White Selected.png"));
            pieceWhiteU = ImageIO.read(new File("assets\\radioButtons\\radioButton White Unselected.png"));
            pieceBlackC = ImageIO.read(new File("assets\\radioButtons\\radioButton Black Selected.png"));
            pieceBlackU = ImageIO.read(new File("assets\\radioButtons\\radioButton Black Unselected.png"));
            pieceRandomC = ImageIO.read(new File("assets\\radioButtons\\radioButton Random Selected.png"));
            pieceRandomU = ImageIO.read(new File("assets\\radioButtons\\radioButton Random Unselected.png"));

            gamePawnIcon = ImageIO.read(new File("assets\\illustartor-pawn white.png"));
            customizationBoardIcon = ImageIO.read(new File("assets\\illustrator-board-white.png"));
            settingsSettingsIcon = ImageIO.read(new File("assets\\illustator-settings.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static Image vC = validCircle.getScaledInstance(1,1,1);
    static Image vNC = validCircle.getScaledInstance(1,1,1);
    static ImageIcon radioClicked = new ImageIcon(rClicked.getScaledInstance(20,20,BufferedImage.SCALE_AREA_AVERAGING));
    static ImageIcon radioUnclicked = new ImageIcon(rUnclicked.getScaledInstance(20,20,BufferedImage.SCALE_AREA_AVERAGING));
    //builder radio buttons
    static ImageIcon iconPieceWhiteC = new ImageIcon(pieceWhiteC.getScaledInstance(60,60,BufferedImage.SCALE_AREA_AVERAGING));
    static ImageIcon iconPieceWhiteU = new ImageIcon(pieceWhiteU.getScaledInstance(60,60,BufferedImage.SCALE_AREA_AVERAGING));
    static ImageIcon iconPieceBlackC = new ImageIcon(pieceBlackC.getScaledInstance(60,60,BufferedImage.SCALE_AREA_AVERAGING));
    static ImageIcon iconPieceBlackU = new ImageIcon(pieceBlackU.getScaledInstance(60,60,BufferedImage.SCALE_AREA_AVERAGING));
    static ImageIcon iconPieceRandomC = new ImageIcon(pieceRandomC.getScaledInstance(60,60,BufferedImage.SCALE_AREA_AVERAGING));
    static ImageIcon iconPieceRandomU = new ImageIcon(pieceRandomU.getScaledInstance(60,60,BufferedImage.SCALE_AREA_AVERAGING));
            static BufferedImage[] imgs = {bB,bK,bN,bP,bQ,bR,wB,wK,wN,wP,wQ,wR};

         static int ex,bex, exPrev=-1, eyPrev = -1, res=8, currentBoardIndex = 0;
         static int ey,bey, exR = -1, eyR = -1, prevColorAlpha = 160;
         static boolean playingAsWhite = true;
         static boolean pressed = false;
         public static boolean checked = false, computerInitiated, computerInitiatedNGtemp, computersTurn;
         int playerPlayingNGtemp = 0;
         int cBoardIndex = 0; int cBoardIndexBuilder = 0;
         static int pos = -1;
         static Types builderTypeSelected;
         static  comboItem builderTempBoard;



    /**
     * Main GUI Object
     */
    public GUI(){

        /*for (piece piece: pieces) {
            if (piece.color == playerPlaying) {

                piecesCurrentValid.addLast(new LinkedList<>());
                piecesCurrentValid.getLast().add(new Integer[]{piece.x, piece.y});

                for (int y = 0; y < board.length; y++) {   // check if movement is valid and populate board
                    for (int x = 0; x < board.length; x++) {
                        //System.out.println(x+" "+y);
                        if (x == piece.x && y == piece.y)
                            continue;
                        if (board[piece.x][piece.y].valid(x, y, board)) {
                            piecesCurrentValid.getLast().add(new Integer[]{x, y});
                        }
                    }
                }
            }
        }*/

        //new boardBuilder();

        mainFrame.setPreferredSize(new Dimension(1200,800));
        mainFrame.setMinimumSize(new Dimension(300,300));
        boardInner.setPreferredSize(new Dimension(10,10));
        boardPanel.setPreferredSize(new Dimension(10,10));
        materialBlackSide.setPreferredSize(new Dimension(100,100));
        //menuGame.setHorizontalAlignment(SwingConstants.LEFT); menuCustomization.setHorizontalAlignment(SwingConstants.LEFT); menuSettings.setHorizontalAlignment(SwingConstants.LEFT);


        boardPanel.setBackground(null);
        backGameBtnPlaceholder.setBackground(null);
        placeHolderMenuPanel.setBackground(null);

        menuGame.setBackground(null);
        //menuGame.setIconTextGap(30);
        //menuGame.setIcon(new ImageIcon(gamePawnIcon.getScaledInstance(25,40, BufferedImage.SCALE_AREA_AVERAGING)));
        menuBuilder.setBackground(null);
        //menuCustomization.setIconTextGap(30);
        //menuCustomization.setIcon(new ImageIcon(customizationBoardIcon.getScaledInstance(32,40, BufferedImage.SCALE_AREA_AVERAGING)));
        menuSettings.setBackground(null);

        menuGame.setBorder(null); menuBuilder.setBorder(null); menuSettings.setBorder(null);
        menuGame.setFocusPainted(false); menuBuilder.setFocusPainted(false); menuSettings.setFocusPainted(false);
        backGameBtn.setBackground(null);  backGameBtn.setBorder(null); backGameBtn.setFocusPainted(false);
        //menuGame.setFont(new Font(Font.MONOSPACED, Font.PLAIN ,20));
        //menuCustomization.setFont(new Font(Font.MONOSPACED, Font.PLAIN ,20));
        //menuSettings.setFont(new Font(Font.MONOSPACED, Font.PLAIN ,20));
        menuGame.addActionListener(e -> {
            System.out.println("menu game pressed");
            rightBuilderPanel.setVisible(false);
            rightSettingsPanel.setVisible(false);
            rightDefaultPanel.setVisible(true);
            rightGamePanel.setVisible(false);
            mainFrame.revalidate();
            mainFrame.repaint();
        });
        menuBuilder.addActionListener(e -> {
            System.out.println("menu customization pressed");
            rightSettingsPanel.setVisible(false);
            rightGamePanel.setVisible(false);
            rightDefaultPanel.setVisible(false);
            rightBuilderPanel.setVisible(true);
            cBoardIndex = 0;

            builderVariantDrop.removeAllItems();
            customBoardListBuilder.clear();
            for (comboItem Item : customBoardList) {
                customBoardListBuilder.add(Item.cloneAll());
                if (Item.getIndex() == 0)
                    customBoardListBuilder.getFirst().setString("New Board");
                    builderNameTxtField.setText("New Board");
                    builderNameString = new StringBuilder("New Board");
            }

            for(comboItem Item : customBoardListBuilder)
                GUI.builderVariantDrop.addItem(Item);

            mainFrame.revalidate();
            mainFrame.repaint();
        });
        menuSettings.addActionListener(e -> {
            System.out.println("menu settings pressed");
            rightBuilderPanel.setVisible(false);
            rightGamePanel.setVisible(false);
            rightDefaultPanel.setVisible(false);
            rightSettingsPanel.setVisible(true);
            mainFrame.revalidate();
            mainFrame.repaint();
        });
        backGameBtn.addActionListener(e -> {
            System.out.println("back btn pressed");
            rightGamePanel.setVisible(false);
            rightDefaultPanel.setVisible(true);
            mainFrame.revalidate();
            mainFrame.repaint();
        });


        popUpPiece.setLayout(new GridLayout(1,4));
        popUpQueen.addActionListener(this);
        popUpQueen.setActionCommand("queen");
        popUpPiece.add(popUpQueen);
        popUpRook.addActionListener(this);
        popUpRook.setActionCommand("rook");
        popUpPiece.add(popUpRook);
        popUpKnight.addActionListener(this);
        popUpKnight.setActionCommand("knight");
        popUpPiece.add(popUpKnight);
        popUpBishop.addActionListener(this);
        popUpBishop.setActionCommand("bishop");
        popUpPiece.add(popUpBishop);




        mainFrame.add(mainPanel,BorderLayout.CENTER );

        //popup checkmate-stalemate





        //// layouts ////

        //left panel
            gbcP.gridx = 1;
            gbcP.gridy = 1;
            //gbcP.weightx = 1;
            //gbcP.weighty = 3;
            gbcP.fill = GridBagConstraints.BOTH;
        mainPanel.add(leftPanel, gbcP);
        //menu
            buttonConstraints.gridx=1;
            buttonConstraints.gridy = 1;
            buttonConstraints.weightx = 1;
            buttonConstraints.weighty = 1;
            buttonConstraints.anchor = 11;
            //buttonConstraints.weighty = 1;
            buttonConstraints.fill = GridBagConstraints.BOTH;
            buttonConstraints.insets = new Insets(0,0,0,0);
        leftPanel.add(menuGame, buttonConstraints);
            buttonConstraints.gridy = 2;
        leftPanel.add(menuBuilder, buttonConstraints);
            buttonConstraints.gridy = 3;
        leftPanel.add(menuSettings, buttonConstraints);
            buttonConstraints.gridy = 4;
            buttonConstraints.weighty = 8;
        leftPanel.add(placeHolderMenuPanel, buttonConstraints);
        //right panel
            gbcP.weightx = 4;
            gbcP.gridx = 2;
        mainPanel.add(rightBuilderPanel, gbcP);
        mainPanel.add(rightSettingsPanel, gbcP);
        mainPanel.add(rightDefaultPanel, gbcP);
        mainPanel.add(rightGamePanel, gbcP);
        
        //back button
            gbc.weightx=1;
            gbc.weighty=1;
            gbc.gridx=0;
            gbc.gridy=0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.NORTHWEST;
        rightGamePanel.add(backGameBtn, gbc);
        // back button placeholder
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 9;
        rightGamePanel.add(backGameBtnPlaceholder, gbc);
        //board
            //gbc.anchor = GridBagConstraints.NONE;
            gbc.weighty = 17;
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.gridheight = 3;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(40,60,80,60);
        rightGamePanel.add(boardPanel, gbc);
        boardPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = 1;
        //gbc.gridwidth = 1;
        //gbc.gridheight = 1;
        boardPanel.add(materialBlackSide, constraints);
        constraints.gridy = 2;
        //gbc.gridwidth = 1;
        //gbc.gridheight = 3;
        boardPanel.add(boardInner, constraints);
        constraints.gridy=3;
        boardPanel.add(materialWhiteSide, constraints);

        boardPanel.addComponentListener(new ComponentAdapter() { // checks whether frame is resized
                                            @Override
                                            public void componentResized(ComponentEvent e) {
                                                resizePreview(boardInner, boardPanel);
                                                resizePreview(materialBlackSide, boardPanel);
                                                resizePreview(materialWhiteSide, boardPanel);
                                            }
                                        });

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeDefaultComponent(leftPanel, mainPanel, 0.2f, 1);
                resizeDefaultComponent(rightSettingsPanel, mainPanel, 0.8f,1);
                resizeDefaultComponent(rightDefaultPanel, mainPanel, 0.8f,1);
                resizeDefaultComponent(rightGamePanel, mainPanel, 0.8f,1);
                resizeDefaultComponent(rightBuilderPanel, mainPanel, 0.8f,1);
            }
        });

        Timer timerForResize = new Timer(100, e -> {
            System.out.println("Main Panel resized" + leftPanel.getWidth());
            menuGame.setFont(new Font("Arial", Font.PLAIN, (int)(leftPanel.getWidth()*0.08)));
            menuBuilder.setFont(new Font("Arial", Font.PLAIN, (int)(leftPanel.getWidth()*0.08)));
            menuSettings.setFont(new Font("Arial", Font.PLAIN, (int)(leftPanel.getWidth()*0.08)));
            backGameBtn.setFont(new Font("Arial", Font.PLAIN,(int)(backGameBtn.getWidth()*0.13)));

            //button icons
            menuGame.setIconTextGap((int)(0.20 * leftPanel.getWidth()*0.3));
            menuGame.setIcon(new ImageIcon(gamePawnIcon.getScaledInstance((int)(0.25 * leftPanel.getWidth()*0.3),(int)(0.40 * leftPanel.getWidth()*0.3), BufferedImage.SCALE_AREA_AVERAGING)));
            menuBuilder.setIconTextGap((int)(0.20 * leftPanel.getWidth()*0.3));
            menuBuilder.setIcon(new ImageIcon(customizationBoardIcon.getScaledInstance((int)(0.32 * leftPanel.getWidth()*0.3),(int)(0.32 * leftPanel.getWidth()*0.3), BufferedImage.SCALE_AREA_AVERAGING)));
            menuSettings.setIconTextGap((int)(0.20 * leftPanel.getWidth()*0.3));
            menuSettings.setIcon(new ImageIcon(settingsSettingsIcon.getScaledInstance((int)(0.55 * leftPanel.getWidth()*0.3),(int)(0.50 * leftPanel.getWidth()*0.3), BufferedImage.SCALE_AREA_AVERAGING)));

            mainFrame.repaint();
        });
        mainFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                timerForResize.setRepeats(false);
                timerForResize.start();
            }
        });


        //// submenu settings layout ////
        settingsLeftColorLbl.setHorizontalAlignment(CENTER); settingsRightColorLbl.setHorizontalAlignment(CENTER); settingsBoardBlackLbl.setHorizontalAlignment(CENTER); settingsPrevMoveLbl.setHorizontalAlignment(CENTER);
        settingsBoardWhiteLbl.setHorizontalAlignment(CENTER); settingsRightSecondaryLbl.setHorizontalAlignment(CENTER); settingsRightTertiaryLbl.setHorizontalAlignment(CENTER); settingsAlphaLbl.setHorizontalAlignment(CENTER);
        settingsRightTertiaryBtn.setBorder(BorderFactory.createLineBorder(Color.white,1));
        settingsRightSecondaryBtn.setBorder(BorderFactory.createLineBorder(Color.white,1));
        settingsLeftColorBtn.setBorder(BorderFactory.createLineBorder(Color.white,1));
        settingsRightColorBtn.setBorder(BorderFactory.createLineBorder(Color.white,1));
        settingsBoardBlackBtn.setBorder(BorderFactory.createLineBorder(Color.white,1));
        settingsBoardWhiteBtn.setBorder(BorderFactory.createLineBorder(Color.white,1));
        settingsPrevMoveBtn.setBorder(BorderFactory.createLineBorder(Color.white,1));

        JLabel pHLabelSettings = new JLabel(" "); pHLabelSettings.setFont(new Font("Arial", Font.PLAIN,25));
        JPanel pHsettingsResL = new JPanel(); JPanel pHsettingsColL = new JPanel(); JPanel phsettingsPieceL = new JPanel();
        JPanel pHsettingsResR = new JPanel(); JPanel pHsettingsColR = new JPanel();
        pHsettingsResL.setBackground(null); pHsettingsResR.setBackground(null); pHsettingsColL.setBackground(null); pHsettingsColR.setBackground(null); phsettingsPieceL.setBackground(null);
        GridBagConstraints gbcSub = new GridBagConstraints();
        settingsAlphaTxtField.setText(String.valueOf(prevColorAlpha));
        //JRadioButton resVeryLow = new JRadioButton("Very Low");
            resVeryLow.setIcon(radioUnclicked);
            resVeryLow.setForeground(Color.WHITE);
        //JRadioButton resLow = new JRadioButton("Low");
            resLow.setIcon(radioUnclicked);
            resLow.setForeground(Color.WHITE);
        //JRadioButton resMid = new JRadioButton("Standard");
            resMid.setIcon(radioClicked);
            resMid.setSelected(true);
            resMid.setForeground(Color.WHITE);
        //JRadioButton resHigh = new JRadioButton("High");
            resHigh.setIcon(radioUnclicked);
            resHigh.setForeground(Color.WHITE);
        //JRadioButton resVeryHigh = new JRadioButton("Very High");
            resVeryHigh.setIcon(radioUnclicked);
            resVeryHigh.setForeground(Color.WHITE);
        ButtonGroup resGroup = new ButtonGroup();
        resGroup.add(resVeryLow); resGroup.add(resLow); resGroup.add(resMid); resGroup.add(resHigh); resGroup.add(resVeryHigh);


        //Color Radio Buttons
        //JRadioButton colDefault = new JRadioButton("Default");
            colDefault.setIcon(radioClicked);
            colDefault.setSelected(true);
            colDefault.setForeground(Color.WHITE);
        //JRadioButton col1 = new JRadioButton("Color1");
            col1.setIcon(radioUnclicked);
            col1.setForeground(Color.WHITE);
        //JRadioButton col2 = new JRadioButton("Color2");
            col2.setIcon(radioUnclicked);
            col2.setForeground(Color.WHITE);
        //JRadioButton col3 = new JRadioButton("Color3");
            col3.setIcon(radioUnclicked);
            col3.setForeground(Color.WHITE);
        //JRadioButton col4 = new JRadioButton("Color4");
            col4.setIcon(radioUnclicked);
            col4.setForeground(Color.WHITE);
        ButtonGroup colGroup = new ButtonGroup();
        colGroup.add(colDefault); colGroup.add(col1); colGroup.add(col2); colGroup.add(col3); colGroup.add(col4);

        //Pieces Radio Buttons
            piecesDefault.setIcon(radioClicked);
            piecesDefault.setSelected(true);
            piecesDefault.setForeground(Color.white);
            //
            piecesKosal.setIcon(radioUnclicked);
            piecesKosal.setForeground(Color.white);
            //
            piecesKaneo.setIcon(radioUnclicked);
            piecesKaneo.setForeground(Color.white);
        ButtonGroup pieceGroup = new ButtonGroup();
        pieceGroup.add(piecesDefault); pieceGroup.add(piecesKosal); pieceGroup.add(piecesKaneo);

        resVeryLow.addActionListener(e -> {
            System.out.println("resolution changed to very low");
            resVeryLow.setIcon(radioClicked);
            resLow.setIcon(radioUnclicked);
            resMid.setIcon(radioUnclicked);
            resHigh.setIcon(radioUnclicked);
            resVeryHigh.setIcon(radioUnclicked);
            res=1;
        });
        resLow.addActionListener(e -> {
            System.out.println("resolution changed to low");
            resVeryLow.setIcon(radioUnclicked);
            resLow.setIcon(radioClicked);
            resMid.setIcon(radioUnclicked);
            resHigh.setIcon(radioUnclicked);
            resVeryHigh.setIcon(radioUnclicked);
            res=2;
        });
        resMid.addActionListener(e -> {
            System.out.println("resolution changed to standard");
            resVeryLow.setIcon(radioUnclicked);
            resLow.setIcon(radioUnclicked);
            resMid.setIcon(radioClicked);
            resHigh.setIcon(radioUnclicked);
            resVeryHigh.setIcon(radioUnclicked);
            res=4;
        });
        resHigh.addActionListener(e -> {
            System.out.println("resolution changed to high");
            resVeryLow.setIcon(radioUnclicked);
            resLow.setIcon(radioUnclicked);
            resMid.setIcon(radioUnclicked);
            resHigh.setIcon(radioClicked);
            resVeryHigh.setIcon(radioUnclicked);
            res=8;
        });
        resVeryHigh.addActionListener(e -> {
            System.out.println("resolution changed to very high");
            resVeryLow.setIcon(radioUnclicked);
            resLow.setIcon(radioUnclicked);
            resMid.setIcon(radioUnclicked);
            resHigh.setIcon(radioUnclicked);
            resVeryHigh.setIcon(radioClicked);
            res=16;
        });
        ////pieceListeners
        piecesDefault.addActionListener(e -> {
            System.out.println("piece changed to default");
            piecesDefault.setIcon(radioClicked);
            piecesKosal.setIcon(radioUnclicked);
            piecesKaneo.setIcon(radioUnclicked);
            try {
                bB = ImageIO.read(new File("assets\\piecesDefault\\bB.png"));
                bK = ImageIO.read(new File("assets\\piecesDefault\\bK.png"));
                bN = ImageIO.read(new File("assets\\piecesDefault\\bN.png"));
                bP = ImageIO.read(new File("assets\\piecesDefault\\bP.png"));
                bQ = ImageIO.read(new File("assets\\piecesDefault\\bQ.png"));
                bR = ImageIO.read(new File("assets\\piecesDefault\\bR.png"));
                wB = ImageIO.read(new File("assets\\piecesDefault\\wB.png"));
                wK = ImageIO.read(new File("assets\\piecesDefault\\wK.png"));
                wN = ImageIO.read(new File("assets\\piecesDefault\\wN.png"));
                wP = ImageIO.read(new File("assets\\piecesDefault\\wP.png"));
                wQ = ImageIO.read(new File("assets\\piecesDefault\\wQ.png"));
                wR = ImageIO.read(new File("assets\\piecesDefault\\wR.png"));
            } catch (IOException exc) {
                exc.printStackTrace();
            }
            imgs[0] = bB; imgs[1] = bK; imgs[2] = bN; imgs[3] = bP; imgs[4] = bQ; imgs[5] = bR;
            imgs[6] = wB; imgs[7] = wK; imgs[8] = wN; imgs[9] = wP; imgs[10] = wQ; imgs[11] = wR;
        });
        piecesKosal.addActionListener(e -> {
            System.out.println("piece changed to kosal");
            piecesDefault.setIcon(radioUnclicked);
            piecesKosal.setIcon(radioClicked);
            piecesKaneo.setIcon(radioUnclicked);
            try {
                bB = ImageIO.read(new File("assets\\piecesKosal\\bB.png"));
                bK = ImageIO.read(new File("assets\\piecesKosal\\bK.png"));
                bN = ImageIO.read(new File("assets\\piecesKosal\\bN.png"));
                bP = ImageIO.read(new File("assets\\piecesKosal\\bP.png"));
                bQ = ImageIO.read(new File("assets\\piecesKosal\\bQ.png"));
                bR = ImageIO.read(new File("assets\\piecesKosal\\bR.png"));
                wB = ImageIO.read(new File("assets\\piecesKosal\\wB.png"));
                wK = ImageIO.read(new File("assets\\piecesKosal\\wK.png"));
                wN = ImageIO.read(new File("assets\\piecesKosal\\wN.png"));
                wP = ImageIO.read(new File("assets\\piecesKosal\\wP.png"));
                wQ = ImageIO.read(new File("assets\\piecesKosal\\wQ.png"));
                wR = ImageIO.read(new File("assets\\piecesKosal\\wR.png"));
            } catch (IOException exc) {
                exc.printStackTrace();
            }
            imgs[0] = bB; imgs[1] = bK; imgs[2] = bN; imgs[3] = bP; imgs[4] = bQ; imgs[5] = bR;
            imgs[6] = wB; imgs[7] = wK; imgs[8] = wN; imgs[9] = wP; imgs[10] = wQ; imgs[11] = wR;
        });
        piecesKaneo.addActionListener(e -> {
            System.out.println("piece changed to default");
            piecesDefault.setIcon(radioUnclicked);
            piecesKosal.setIcon(radioUnclicked);
            piecesKaneo.setIcon(radioClicked);
            try {
                bB = ImageIO.read(new File("assets\\piecesKaneo\\bB.png"));
                bK = ImageIO.read(new File("assets\\piecesKaneo\\bK.png"));
                bN = ImageIO.read(new File("assets\\piecesKaneo\\bN.png"));
                bP = ImageIO.read(new File("assets\\piecesKaneo\\bP.png"));
                bQ = ImageIO.read(new File("assets\\piecesKaneo\\bQ.png"));
                bR = ImageIO.read(new File("assets\\piecesKaneo\\bR.png"));
                wB = ImageIO.read(new File("assets\\piecesKaneo\\wB.png"));
                wK = ImageIO.read(new File("assets\\piecesKaneo\\wK.png"));
                wN = ImageIO.read(new File("assets\\piecesKaneo\\wN.png"));
                wP = ImageIO.read(new File("assets\\piecesKaneo\\wP.png"));
                wQ = ImageIO.read(new File("assets\\piecesKaneo\\wQ.png"));
                wR = ImageIO.read(new File("assets\\piecesKaneo\\wR.png"));
            } catch (IOException exc) {
                exc.printStackTrace();
            }
            imgs[0] = bB; imgs[1] = bK; imgs[2] = bN; imgs[3] = bP; imgs[4] = bQ; imgs[5] = bR;
            imgs[6] = wB; imgs[7] = wK; imgs[8] = wN; imgs[9] = wP; imgs[10] = wQ; imgs[11] = wR;
        });
        ///colorListeners
        colDefault.addActionListener(e -> {
            System.out.println("Color changed to Default");
            colDefault.setIcon(radioClicked);
            col1.setIcon(radioUnclicked);
            col2.setIcon(radioUnclicked);
            col3.setIcon(radioUnclicked);
            col4.setIcon(radioUnclicked);
            settingsLeftColorBtn.setVisible(false); settingsRightColorBtn.setVisible(false); settingsBoardBlackBtn.setVisible(false);
            settingsBoardWhiteBtn.setVisible(false); settingsRightSecondaryBtn.setVisible(false); settingsRightTertiaryBtn.setVisible(false);
            settingsLeftColorLbl.setVisible(false); settingsRightColorLbl.setVisible(false); settingsBoardBlackLbl.setVisible(false);
            settingsBoardWhiteLbl.setVisible(false); settingsRightSecondaryLbl.setVisible(false); settingsRightTertiaryLbl.setVisible(false);
            settingsPrevMoveBtn.setVisible(false); settingsPrevMoveLbl.setVisible(false); settingsAlphaLbl.setVisible(false); settingsAlphaTxtField.setVisible(false);
            leftPanelBlack = new Color(68, 55, 66);
            rightPanelGreen = new Color(102, 161, 130);
            rightPanelSecondary = new Color(102, 161, 130);
            rightPanelTertiery = new Color(68, 55, 66);
            blackSpaces = new Color(203, 144, 77);
            realWhiteSpaces = new Color(255, 255, 255);
            prevMoveAlpha = new Color (229, 255, 102);
            prevColorAlpha=160;
            updateJComponentsColor();
        });
        col1.addActionListener(e -> {
            System.out.println("Color changed to 1");
            colDefault.setIcon(radioUnclicked);
            col1.setIcon(radioClicked);
            col2.setIcon(radioUnclicked);
            col3.setIcon(radioUnclicked);
            col4.setIcon(radioUnclicked);
            settingsLeftColorBtn.setVisible(false); settingsRightColorBtn.setVisible(false); settingsBoardBlackBtn.setVisible(false);
            settingsBoardWhiteBtn.setVisible(false); settingsRightSecondaryBtn.setVisible(false); settingsRightTertiaryBtn.setVisible(false);
            settingsLeftColorLbl.setVisible(false); settingsRightColorLbl.setVisible(false); settingsBoardBlackLbl.setVisible(false);
            settingsBoardWhiteLbl.setVisible(false); settingsRightSecondaryLbl.setVisible(false); settingsRightTertiaryLbl.setVisible(false);
            settingsPrevMoveBtn.setVisible(false); settingsPrevMoveLbl.setVisible(false); settingsAlphaLbl.setVisible(false); settingsAlphaTxtField.setVisible(false);
            leftPanelBlack = new Color(239, 131, 84); //191, 192, 192
            rightPanelGreen = new Color(191, 192, 192); //239, 131, 84
            rightPanelSecondary = new Color(255, 255, 255);
            rightPanelTertiery = new Color(79, 93, 117);
            blackSpaces = new Color(79, 93, 117); //79, 93, 117
            realWhiteSpaces = new Color(255, 255, 255);
            prevMoveAlpha = new Color (229, 255, 102);
            prevColorAlpha=160;
            updateJComponentsColor();
        });
        col2.addActionListener(e -> {
            System.out.println("Color changed to 2");
            colDefault.setIcon(radioUnclicked);
            col1.setIcon(radioUnclicked);
            col2.setIcon(radioClicked);
            col3.setIcon(radioUnclicked);
            col4.setIcon(radioUnclicked);
            settingsLeftColorBtn.setVisible(false); settingsRightColorBtn.setVisible(false); settingsBoardBlackBtn.setVisible(false);
            settingsBoardWhiteBtn.setVisible(false); settingsRightSecondaryBtn.setVisible(false); settingsRightTertiaryBtn.setVisible(false);
            settingsLeftColorLbl.setVisible(false); settingsRightColorLbl.setVisible(false); settingsBoardBlackLbl.setVisible(false);
            settingsBoardWhiteLbl.setVisible(false); settingsRightSecondaryLbl.setVisible(false); settingsRightTertiaryLbl.setVisible(false);
            settingsPrevMoveBtn.setVisible(false); settingsPrevMoveLbl.setVisible(false); settingsAlphaLbl.setVisible(false); settingsAlphaTxtField.setVisible(false);
            leftPanelBlack = new Color(36, 32, 56); //191, 192, 192
            rightPanelGreen = new Color(114, 90, 193); //239, 131, 84
            rightPanelSecondary = new Color(114, 90, 193);
            rightPanelTertiery = new Color(36, 32, 56);
            blackSpaces = new Color(143, 130, 151); //202, 196, 206
            realWhiteSpaces = new Color(255, 255, 255);
            prevMoveAlpha = new Color (229, 255, 102);
            prevColorAlpha=160;
            updateJComponentsColor();
        });
        col3.addActionListener(e -> {
            System.out.println("Color changed to col3");
            colDefault.setIcon(radioUnclicked);
            col1.setIcon(radioUnclicked);
            col2.setIcon(radioUnclicked);
            col3.setIcon(radioClicked);
            col4.setIcon(radioUnclicked);
            settingsLeftColorBtn.setVisible(false); settingsRightColorBtn.setVisible(false); settingsBoardBlackBtn.setVisible(false);
            settingsBoardWhiteBtn.setVisible(false); settingsRightSecondaryBtn.setVisible(false); settingsRightTertiaryBtn.setVisible(false);
            settingsLeftColorLbl.setVisible(false); settingsRightColorLbl.setVisible(false); settingsBoardBlackLbl.setVisible(false);
            settingsBoardWhiteLbl.setVisible(false); settingsRightSecondaryLbl.setVisible(false); settingsRightTertiaryLbl.setVisible(false);
            settingsPrevMoveBtn.setVisible(false); settingsPrevMoveLbl.setVisible(false); settingsAlphaLbl.setVisible(false); settingsAlphaTxtField.setVisible(false);
            leftPanelBlack = new Color(183, 173, 207); //191, 192, 192
            rightPanelGreen = new Color(222, 231, 231); //239, 131, 84
            rightPanelSecondary = new Color(83, 86, 87);
            rightPanelTertiery = new Color(83, 86, 87);
            blackSpaces = new Color(83, 86, 87); //79, 93, 117
            realWhiteSpaces = new Color(255, 255, 255);
            prevMoveAlpha = new Color (229, 255, 102);
            prevColorAlpha=160;
            updateJComponentsColor();
        });
        col4.addActionListener(e -> {
            System.out.println("Color changed to custom");
            colDefault.setIcon(radioUnclicked);
            col1.setIcon(radioUnclicked);
            col2.setIcon(radioUnclicked);
            col3.setIcon(radioUnclicked);
            col4.setIcon(radioClicked);
            settingsLeftColorBtn.setVisible(true); settingsRightColorBtn.setVisible(true); settingsBoardBlackBtn.setVisible(true);
            settingsBoardWhiteBtn.setVisible(true); settingsRightSecondaryBtn.setVisible(true); settingsRightTertiaryBtn.setVisible(true);
            settingsLeftColorLbl.setVisible(true); settingsRightColorLbl.setVisible(true); settingsBoardBlackLbl.setVisible(true);
            settingsBoardWhiteLbl.setVisible(true); settingsRightSecondaryLbl.setVisible(true); settingsRightTertiaryLbl.setVisible(true);
            settingsPrevMoveBtn.setVisible(true); settingsPrevMoveLbl.setVisible(true); settingsAlphaLbl.setVisible(true); settingsAlphaTxtField.setVisible(true);
            settingsAlphaTxtField.setText(String.valueOf(prevColorAlpha));
        });
        //color picker buttons
        settingsLeftColorBtn.addActionListener(e -> {
            Color temp = JColorChooser.showDialog(null,"Pick a Color", leftPanelBlack);
            leftPanelBlack = (temp== null) ? leftPanelBlack : temp; // necessary for cancel button to work properly
            updateJComponentsColor();
        });
        settingsRightColorBtn.addActionListener(e -> {
            Color temp =  JColorChooser.showDialog(null,"Pick a Color", rightPanelGreen);
            rightPanelGreen = (temp== null) ? rightPanelGreen : temp;
            updateJComponentsColor();
        });
        settingsRightSecondaryBtn.addActionListener(e -> {
            Color temp =  JColorChooser.showDialog(null,"Pick a Color", rightPanelSecondary);
            rightPanelSecondary = (temp== null) ? rightPanelSecondary : temp;
            updateJComponentsColor();
        });
        settingsRightTertiaryBtn.addActionListener(e -> {
            Color temp = JColorChooser.showDialog(null,"Pick a Color", rightPanelTertiery);
            rightPanelTertiery = (temp== null) ? rightPanelTertiery : temp;
            updateJComponentsColor();
        });
        settingsBoardBlackBtn.addActionListener(e -> {
            Color temp = JColorChooser.showDialog(null,"Pick a Color", blackSpaces);
            blackSpaces = (temp== null) ? blackSpaces : temp;
            updateJComponentsColor();
        });
        settingsBoardWhiteBtn.addActionListener(e -> {
            Color temp = JColorChooser.showDialog(null,"Pick a Color", realWhiteSpaces);
            realWhiteSpaces = (temp== null) ? realWhiteSpaces : temp;
            updateJComponentsColor();
        });
        settingsPrevMoveBtn.addActionListener(e -> {
            Color temp = JColorChooser.showDialog(null,"Pick a Color", prevMoveAlpha);
            prevMoveAlpha = (temp== null) ? prevMoveAlpha : temp;
            updateJComponentsColor();
        });

        //txtFiled
        settingsAlphaTxtField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                int num = 0;
                char c = e.getKeyChar();
                String typed = settingsAlphaTxtField.getText();

                if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_ENTER) {
                    e.consume();
                }
                if (!typed.equals("")) {
                    num = Integer.parseInt(settingsAlphaTxtField.getText());
                    if ((num*10+(c-'0')) > 255){
                        e.consume();
                    }
                }
                if(c==KeyEvent.VK_ENTER){
                    if(typed.equals("")) {
                        settingsAlphaTxtField.setText("0");
                        prevColorAlpha=0;
                    }
                    else prevColorAlpha=num;
                    settingsAlphaTxtField.setFocusable(false);
                    settingsAlphaTxtField.setFocusable(true);

                }
            }
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // settings
        gbcSub.gridy = 0;
        gbcSub.gridx = 0;
        gbcSub.weightx=2;
        gbcSub.fill = GridBagConstraints.BOTH;
        rightSettingsPanel.add(pHsettingsResL, gbcSub);
            gbcSub.weightx=1;
            gbcSub.gridx = 1;
        rightSettingsPanel.add(settingsResolution, gbcSub);
           // gbcSub.insets = new Insets(0,10,0,10);
            gbcSub.gridx = 2;
        rightSettingsPanel.add(resVeryLow, gbcSub);
            gbcSub.gridx = 3;
        rightSettingsPanel.add(resLow, gbcSub);
            gbcSub.gridx = 4;
        rightSettingsPanel.add(resMid,gbcSub);
            gbcSub.gridx = 5;
        rightSettingsPanel.add(resHigh, gbcSub);
            gbcSub.gridx = 6;
        rightSettingsPanel.add(resVeryHigh, gbcSub);
            gbcSub.gridx=7;
            gbcSub.weightx=2;
        rightSettingsPanel.add(pHsettingsResR, gbcSub);

            gbcSub.gridy = 0;
            gbcSub.gridx = 0;
            gbcSub.weightx=2;
            gbcSub.fill = GridBagConstraints.BOTH;
        rightSettingsPanel.add(pHsettingsColL, gbcSub);
        //pieces
        gbcSub.gridy = 1;
        gbcSub.gridx = 0;
        gbcSub.weightx=2;
        gbcSub.fill = GridBagConstraints.BOTH;
        rightSettingsPanel.add(phsettingsPieceL, gbcSub);
        gbcSub.weightx=1;
        gbcSub.gridx = 1;
        rightSettingsPanel.add(settingsPieces, gbcSub);
        // gbcSub.insets = new Insets(0,10,0,10);
        gbcSub.gridx = 2;
        rightSettingsPanel.add(piecesDefault, gbcSub);
        gbcSub.gridx = 3;
        rightSettingsPanel.add(piecesKosal, gbcSub);
        gbcSub.gridx = 4;
        rightSettingsPanel.add(piecesKaneo, gbcSub);
        //Colors
        gbcSub.gridy = 2;
        gbcSub.gridx = 0;
        gbcSub.weightx=2;
        gbcSub.fill = GridBagConstraints.BOTH;
        rightSettingsPanel.add(pHsettingsColL, gbcSub);
        gbcSub.weightx=1;
        gbcSub.gridx = 1;
        rightSettingsPanel.add(settingsColor, gbcSub);
        // gbcSub.insets = new Insets(0,10,0,10);
        gbcSub.gridx = 2;
        rightSettingsPanel.add(colDefault, gbcSub);
        gbcSub.gridx = 3;
        rightSettingsPanel.add(col1, gbcSub);
        gbcSub.gridx = 4;
        rightSettingsPanel.add(col2,gbcSub);
        gbcSub.gridx = 5;
        rightSettingsPanel.add(col3, gbcSub);
        gbcSub.gridx = 6;
        rightSettingsPanel.add(col4, gbcSub);
        gbcSub.gridx=7;
        gbcSub.weightx=2;
        rightSettingsPanel.add(pHsettingsColR, gbcSub);
        gbcSub.weightx = 1;
        gbcSub.gridy=3;
        rightSettingsPanel.add(pHLabelSettings,gbcSub);
        gbcSub.gridx=1;
        gbcSub.gridy = 4;
        rightSettingsPanel.add(settingsLeftColorLbl, gbcSub);
        gbcSub.gridx=2;
        rightSettingsPanel.add(settingsLeftColorBtn, gbcSub);
        gbcSub.gridx=3;
        //gbcSub.insets = new Insets(0,0,0,30);
        rightSettingsPanel.add(settingsRightColorLbl,gbcSub);
        //gbcSub.insets = new Insets(0,0,0,0);
        gbcSub.gridx=4;
        rightSettingsPanel.add(settingsRightColorBtn,gbcSub);
        gbcSub.gridx=1;
        gbcSub.gridy = 5;
        rightSettingsPanel.add(settingsRightSecondaryLbl, gbcSub);
        gbcSub.gridx=2;
        rightSettingsPanel.add(settingsRightSecondaryBtn, gbcSub);
        gbcSub.gridx=3;
        //gbcSub.insets = new Insets(0,0,0,30);
        rightSettingsPanel.add(settingsRightTertiaryLbl,gbcSub);
        //gbcSub.insets = new Insets(0,0,0,0);
        gbcSub.gridx=4;
        rightSettingsPanel.add(settingsRightTertiaryBtn,gbcSub);
        gbcSub.gridx=1;
        gbcSub.gridy = 6;
        rightSettingsPanel.add(settingsBoardBlackLbl, gbcSub);
        gbcSub.gridx=2;
        rightSettingsPanel.add(settingsBoardBlackBtn, gbcSub);
        gbcSub.gridx=3;
        //gbcSub.insets = new Insets(0,0,0,30);
        rightSettingsPanel.add(settingsBoardWhiteLbl,gbcSub);
        //gbcSub.insets = new Insets(0,0,0,0);
        gbcSub.gridx=4;
        rightSettingsPanel.add(settingsBoardWhiteBtn,gbcSub);
        gbcSub.gridx = 1;
        gbcSub.gridy = 7;
        rightSettingsPanel.add(settingsPrevMoveLbl, gbcSub);
        gbcSub.gridx=2;
        rightSettingsPanel.add(settingsPrevMoveBtn, gbcSub);
        gbcSub.gridx=3;
        rightSettingsPanel.add(settingsAlphaLbl, gbcSub);
        gbcSub.gridx=4;
        rightSettingsPanel.add(settingsAlphaTxtField, gbcSub);

        settingsLeftColorBtn.setVisible(false); settingsRightColorBtn.setVisible(false); settingsBoardBlackBtn.setVisible(false);
        settingsBoardWhiteBtn.setVisible(false); settingsRightSecondaryBtn.setVisible(false); settingsRightTertiaryBtn.setVisible(false);
        settingsLeftColorLbl.setVisible(false); settingsRightColorLbl.setVisible(false); settingsBoardBlackLbl.setVisible(false);
        settingsBoardWhiteLbl.setVisible(false); settingsRightSecondaryLbl.setVisible(false); settingsRightTertiaryLbl.setVisible(false);
        settingsPrevMoveBtn.setVisible(false); settingsPrevMoveLbl.setVisible(false); settingsAlphaLbl.setVisible(false); settingsAlphaTxtField.setVisible(false);

        rightSettingsPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                settingsResolution.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                resVeryLow.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                resLow.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                resMid.setFont(new Font("Arial", Font.PLAIN,(int)(rightSettingsPanel.getWidth()*0.015)));
                resHigh.setFont(new Font("Arial", Font.PLAIN,(int)(rightSettingsPanel.getWidth()*0.015)));
                resVeryHigh.setFont(new Font("Arial", Font.PLAIN,(int)(rightSettingsPanel.getWidth()*0.015)));

                settingsLeftColorLbl.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                settingsRightColorLbl.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                settingsBoardBlackLbl.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                settingsBoardWhiteLbl.setFont(new Font("Arial", Font.PLAIN,(int)(rightSettingsPanel.getWidth()*0.015)));
                settingsRightSecondaryLbl.setFont(new Font("Arial", Font.PLAIN,(int)(rightSettingsPanel.getWidth()*0.015)));
                settingsRightTertiaryLbl.setFont(new Font("Arial", Font.PLAIN,(int)(rightSettingsPanel.getWidth()*0.015)));

                settingsPrevMoveLbl.setFont(new Font("Arial", Font.PLAIN,(int)(rightSettingsPanel.getWidth()*0.015)));
                settingsAlphaLbl.setFont(new Font("Arial", Font.PLAIN,(int)(rightSettingsPanel.getWidth()*0.015)));

                settingsColor.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                colDefault.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                col1.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                col2.setFont(new Font("Arial", Font.PLAIN,(int)(rightSettingsPanel.getWidth()*0.015)));
                col3.setFont(new Font("Arial", Font.PLAIN,(int)(rightSettingsPanel.getWidth()*0.015)));
                col4.setFont(new Font("Arial", Font.PLAIN,(int)(rightSettingsPanel.getWidth()*0.015)));

                settingsPieces.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                piecesDefault.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                piecesKosal.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));
                piecesKaneo.setFont(new Font("Arial", Font.PLAIN, (int)(rightSettingsPanel.getWidth()*0.015)));

                radioClicked = new ImageIcon(rClicked.getScaledInstance((int) (rightSettingsPanel.getWidth()*0.025),(int) (rightSettingsPanel.getWidth()*0.025),BufferedImage.SCALE_AREA_AVERAGING));
                radioUnclicked = new ImageIcon(rUnclicked.getScaledInstance((int) (rightSettingsPanel.getWidth()*0.025),(int) (rightSettingsPanel.getWidth()*0.025),BufferedImage.SCALE_AREA_AVERAGING));
                resVeryLow.setIcon(radioUnclicked);         colDefault.setIcon(radioUnclicked);         piecesDefault.setIcon(radioUnclicked);
                resLow.setIcon(radioUnclicked);         col1.setIcon(radioUnclicked);           piecesKosal.setIcon(radioUnclicked);
                resMid.setIcon(radioUnclicked);         col2.setIcon(radioUnclicked);           piecesKaneo.setIcon(radioUnclicked);
                resHigh.setIcon(radioUnclicked);            col3.setIcon(radioUnclicked);
                resVeryHigh.setIcon(radioUnclicked);            col4.setIcon(radioUnclicked);
                if(resVeryLow.isSelected()) resVeryLow.setIcon(radioClicked);
                else if(resLow.isSelected()) resLow.setIcon(radioClicked);
                else if(resMid.isSelected()) resMid.setIcon(radioClicked);
                else if(resHigh.isSelected()) resHigh.setIcon(radioClicked);
                else if(resVeryHigh.isSelected()) resVeryHigh.setIcon(radioClicked);

                if(colDefault.isSelected()) colDefault.setIcon(radioClicked);
                else if(col1.isSelected()) col1.setIcon(radioClicked);
                else if(col2.isSelected()) col2.setIcon(radioClicked);
                else if(col3.isSelected()) col3.setIcon(radioClicked);
                else if(col4.isSelected()) col4.setIcon(radioClicked);

                if(piecesDefault.isSelected()) piecesDefault.setIcon(radioClicked);
                else if(piecesKosal.isSelected()) piecesKosal.setIcon(radioClicked);
                else if(piecesKaneo.isSelected()) piecesKaneo.setIcon(radioClicked);
            }
        });
        //// End of Sub Menu settings Layouts ////



        //// submenu default layout ////
        defaultContinueBtn.setEnabled(false);
        ButtonGroup opponentRBG = new ButtonGroup();
        opponentRBG.add(defaultFriendRB);
        opponentRBG.add(defaultComputerRB);
        defaultFriendRB.setIcon(radioClicked);
        defaultComputerRB.setIcon(radioUnclicked);
        defaultFriendRB.setSelected(true);
        ButtonGroup playAsRBG = new ButtonGroup();
        playAsRBG.add(defaultPAWhiteRB);
        playAsRBG.add(defaultPARandomRB);
        playAsRBG.add(defaultPABlackRB);
        defaultPAWhiteRB.setIcon(iconPieceWhiteU);
        defaultPARandomRB.setIcon(iconPieceRandomC);
        defaultPABlackRB.setIcon(iconPieceBlackU);
        defaultPARandomRB.setSelected(true);

        GridBagConstraints defgbc = new GridBagConstraints();
        miniBoardPanel.setBackground(null);

        //button actions
        defaultContinueBtn.addActionListener(e -> {
            System.out.println("menu game pressed");
            rightBuilderPanel.setVisible(false);
            rightSettingsPanel.setVisible(false);
            rightDefaultPanel.setVisible(false);
            rightGamePanel.setVisible(true);
            backGameBtn.setFont(new Font("Arial", Font.PLAIN,(int)(rightDefaultPanel.getWidth()*0.015))); // fix this
            mainFrame.revalidate();
            mainFrame.repaint();
        });

        Timer timerForAI = new Timer(1000,e -> {
            computersTurn=true;
            AI_engine.start();
        });

        defaultNewGameBtn.addActionListener(e -> {
            System.out.println("menu game pressed");
            playSound("chess_gameStarted.wav");
            playerPlaying = 0;
            if (defaultPARandomRB.isSelected())
                playerPlayingNGtemp = (int)(Math.random()*2);
            System.out.println(playerPlayingNGtemp);
            playingAsWhite = playerPlayingNGtemp == 0;
            blackPieceTakenValue = 0; whitePieceTakenValue = 0; whitePieceTaken = ""; blackPieceTaken = "";
            if (playingAsWhite){
            materialWhiteSide.setText("material: " + (whitePieceTakenValue-blackPieceTakenValue) + " " + whitePieceTaken);
            materialBlackSide.setText("material: " + (blackPieceTakenValue-whitePieceTakenValue) + " " + blackPieceTaken);
            }
            else {
                materialBlackSide.setText("material: " + (whitePieceTakenValue-blackPieceTakenValue) + " " + whitePieceTaken);
                materialWhiteSide.setText("material: " + (blackPieceTakenValue-whitePieceTakenValue) + " " + blackPieceTaken);
            }
            blackCurrentSpaces.clear();
            whiteCurrentSpaces.clear();
            pressed = false; checked = false; computersTurn = false;
            exPrev=0; eyPrev = 0; ex=0; ey=0;
            computerInitiated = computerInitiatedNGtemp;

            piece.moveRule = 0; piece.globalMoveCounter=1;

            for (comboItem Item : customBoardList) {
                if (Item.getIndex() == cBoardIndex) {
                    pieces.clear();
                    piecesVoidSpaces.clear();
                    for (piece piece:Item.getPieceList()){
                        pieces.add(piece.clone());
                    }
                    for (piece piece:Item.getPieceVoidList()){
                        piecesVoidSpaces.add(piece.clone());
                    }
                    //pieces.addAll(Item.getPieceList());
                    board = Item.getPieceBoard().clone();
                    for (int x = 0; x < board.length; x++) {
                        for (int y = 0; y < board.length; y++) {
                            board[x][y] = null;
                        }
                    }
                    if (!playingAsWhite) {
                        for (piece piece : pieces) {
                            piece.x = board.length - 1 - piece.x;
                            piece.y = board.length - 1 - piece.y;
                        }
                        for (piece piece : piecesVoidSpaces) {
                            piece.x = board.length - 1 - piece.x;
                            piece.y = board.length - 1 - piece.y;
                        }

                }

                    for (piece piece : pieces)
                        board[piece.x][piece.y] = piece;
                    for (piece piece : piecesVoidSpaces){
                        System.out.println("void in board");
                        board[piece.x][piece.y] = piece;
                        }


                    for (int x = 0; x < board.length; x++) {
                        for (int y = 0; y < board.length; y++) {
                            if (board[x][y] == null)
                                board[x][y] = new empty();
                        }
                    }

                    boardCheckTemp = Item.getPieceBoardCheck().clone();
                }
            }
            piecesCurrentValid.clear();
            for (piece piece: pieces) {
                if (piece.color == playerPlaying) {

                    piecesCurrentValid.addLast(new LinkedList<>());
                    piecesCurrentValid.getLast().add(new Integer[]{piece.x, piece.y});

                    for (int y = 0; y < board.length; y++) {   // check if movement is valid and populate board
                        for (int x = 0; x < board.length; x++) {
                            //System.out.println(x+" "+y);
                            if (x == piece.x && y == piece.y)
                                continue;
                            if (board[piece.x][piece.y].valid(x, y, board)) {
                                piecesCurrentValid.getLast().add(new Integer[]{x, y});
                            }
                        }
                    }
                }
            }

            defaultContinueBtn.setEnabled(true);
            rightBuilderPanel.setVisible(false);
            rightSettingsPanel.setVisible(false);
            rightDefaultPanel.setVisible(false);
            rightGamePanel.setVisible(true);
            backGameBtn.setFont(new Font("Arial", Font.PLAIN,(int)(rightDefaultPanel.getWidth()*0.015))); // fix this
            mainFrame.revalidate();
            mainFrame.repaint();

            if(!playingAsWhite && computerInitiated)
            {
                timerForAI.setRepeats(false);
                timerForAI.start();
            }
        });


        //radio button actions
        defaultFriendRB.addActionListener(e -> {
            defaultFriendRB.setSelected(true);
            defaultFriendRB.setIcon(radioClicked);
            defaultComputerRB.setIcon(radioUnclicked);
            computerInitiatedNGtemp = false;
        });
        defaultComputerRB.addActionListener(e -> {
            defaultComputerRB.setSelected(true);
            defaultFriendRB.setIcon(radioUnclicked);
            defaultComputerRB.setIcon(radioClicked);
            computerInitiatedNGtemp = true;
        });
        defaultPAWhiteRB.addActionListener(e -> {
            defaultPAWhiteRB.setSelected(true);
            defaultPAWhiteRB.setIcon(iconPieceWhiteC);
            defaultPABlackRB.setIcon(iconPieceBlackU);
            defaultPARandomRB.setIcon(iconPieceRandomU);
            playerPlayingNGtemp = 0;
        });
        defaultPARandomRB.addActionListener(e -> {
            defaultPARandomRB.setSelected(true);
            defaultPAWhiteRB.setIcon(iconPieceWhiteU);
            defaultPABlackRB.setIcon(iconPieceBlackU);
            defaultPARandomRB.setIcon(iconPieceRandomC);
        });
        defaultPABlackRB.addActionListener(e -> {
            defaultPABlackRB.setSelected(true);
            defaultPAWhiteRB.setIcon(iconPieceWhiteU);
            defaultPABlackRB.setIcon(iconPieceBlackC);
            defaultPARandomRB.setIcon(iconPieceRandomU);
            playerPlayingNGtemp = 1;
        });

        //dropdown
        defaultVariantDrop.addActionListener(e -> {
            JComboBox vdCB = (JComboBox)e.getSource();
            cBoardIndex = vdCB.getSelectedIndex();

            if (cBoardIndex == 0)
                defaultComputerRB.setEnabled(true);
            else {
                defaultComputerRB.setEnabled(false);
                defaultFriendRB.setSelected(true);
                defaultFriendRB.setIcon(radioClicked);
                defaultComputerRB.setIcon(radioUnclicked);
                computerInitiatedNGtemp = false;
            }
        });

        defgbc.gridy = 1;
        defgbc.gridx = 1;
        defgbc.weighty = 0.60f;
        defgbc.weightx = 1;
        defgbc.fill = GridBagConstraints.BOTH;
        rightDefaultPanel.add(defaultUpperPanel, defgbc);
        defgbc.gridy = 2;
        defgbc.weighty = 0.40f;
        defgbc.fill = GridBagConstraints.BOTH;
        rightDefaultPanel.add(defaultAccountPanel, defgbc);
        //board
        defgbc.weighty = 1;
        defgbc.gridx = 0;
        defgbc.gridy = 0;
        defgbc.gridheight = 3;
        defgbc.anchor = GridBagConstraints.WEST;
        defgbc.insets = new Insets(10,10,10,10);
        defaultUpperPanel.add(miniBoardPanel, defgbc);
        miniBoardPanel.setLayout(new GridBagLayout());
        GridBagConstraints defConstraints = new GridBagConstraints();
        defConstraints.fill = GridBagConstraints.NONE; defgbc.anchor = GridBagConstraints.CENTER;
        defConstraints.gridy = 1;
        defgbc.gridwidth = 1;
        defgbc.gridheight = 1;
        miniBoardPanel.add(miniBoardInner, defConstraints);
        defgbc.gridx=1;
        defgbc.gridy=0;
        defgbc.fill = GridBagConstraints.NONE;
        defgbc.insets = new Insets(10,0,0,10);
        defaultUpperPanel.add(defaultContinueBtn, defgbc);
        defgbc.gridy=1;
        defgbc.anchor = GridBagConstraints.CENTER;
        defgbc.fill = GridBagConstraints.BOTH;
        defgbc.insets = new Insets(0,0,10,10);
        defaultUpperPanel.add(defaultNewGamePanel, defgbc); //// ////
        GridBagConstraints gbcNG = new GridBagConstraints();
        gbcNG.fill = GridBagConstraints.BOTH;
        gbcNG.weightx = 1;
        gbcNG.weighty = 1;
        gbcNG.gridx = 0;
        gbcNG.gridy = 0;
        gbcNG.insets = new Insets(0,20,0,0);
        defaultNewGamePanel.add(defaultVariantLbl, gbcNG);
        defaultVariantLbl.setHorizontalAlignment(CENTER);
        gbcNG.gridx=1;
        gbcNG.insets = new Insets(0,0,0,20);
        defaultNewGamePanel.add(defaultVariantDrop, gbcNG);
        gbcNG.gridy=1;
        gbcNG.gridx=0;
        gbcNG.insets = new Insets(0,20,0,0);
        defaultNewGamePanel.add(defaultOpponentLbl, gbcNG);
        defaultOpponentLbl.setHorizontalAlignment(CENTER);
        gbcNG.gridx=1;
        defaultNewGamePanel.add(defaultFriendRB, gbcNG);
        gbcNG.gridx=2;
        defaultNewGamePanel.add(defaultComputerRB, gbcNG);
        gbcNG.gridy = 2;
        gbcNG.gridx=0;
        defaultNewGamePanel.add(defaultPlayAsLbl, gbcNG);
        gbcNG.gridx = 1;
        defaultNewGamePanel.add(defaultPAWhiteRB, gbcNG);
        gbcNG.gridx=2;
        defaultNewGamePanel.add(defaultPARandomRB, gbcNG);
        gbcNG.gridx=3;
        defaultNewGamePanel.add(defaultPABlackRB, gbcNG);
        gbcNG.gridy = 3;
        gbcNG.gridx = 0;
        gbcNG.gridwidth = 4;
        //gbcNG.weighty = 1;
        //gbcNG.weightx = 16;
        gbcNG.insets = new Insets(2,10,2,10);
        //gbcNG.fill = GridBagConstraints.HORIZONTAL;
        defaultNewGamePanel.add(defaultNewGameBtn, gbcNG);



        miniBoardPanel.addComponentListener(new ComponentAdapter() { // checks whether frame is resized
            @Override
            public void componentResized(ComponentEvent e) {
                resizePreview(miniBoardInner, miniBoardPanel);
            }
        });

        rightDefaultPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeDefaultComponent(defaultUpperPanel, rightDefaultPanel, 1, 0.60f);
                resizeDefaultComponent(defaultAccountPanel, rightDefaultPanel, 1, 0.40f);
                resizeDefaultComponent(miniBoardPanel, defaultUpperPanel, 0.5f, 0.5f);
                resizeDefaultComponent(defaultContinueBtn, defaultUpperPanel, 0.45f, 0.15f);
                resizeDefaultComponent(defaultNewGamePanel, defaultUpperPanel, 0.45f, 0.60f);
                //resizeDefaultComponent(defaultNewGameBtn, defaultNewGamePanel, 1f, 0.10f);

                defaultContinueBtn.setFont((new Font("Arial", Font.PLAIN, (int)(rightDefaultPanel.getWidth()*0.015))));
                defaultVariantLbl.setFont(new Font("Arial", Font.PLAIN, (int)(rightDefaultPanel.getWidth()*0.02)));
                defaultOpponentLbl.setFont(new Font("Arial", Font.PLAIN, (int)(rightDefaultPanel.getWidth()*0.02)));
                defaultPlayAsLbl.setFont(new Font("Arial", Font.PLAIN, (int)(rightDefaultPanel.getWidth()*0.02)));

                defaultFriendRB.setFont(new Font("Arial", Font.PLAIN, (int)(rightDefaultPanel.getWidth()*0.015)));
                defaultComputerRB.setFont(new Font("Arial", Font.PLAIN, (int)(rightDefaultPanel.getWidth()*0.015)));
                //defaultPAWhiteRB.setFont(new Font("Arial", Font.PLAIN, (int)(rightDefaultPanel.getWidth()*0.015)));
                //defaultPARandomRB.setFont(new Font("Arial", Font.PLAIN, (int)(rightDefaultPanel.getWidth()*0.015)));
                //defaultPABlackRB.setFont(new Font("Arial", Font.PLAIN, (int)(rightDefaultPanel.getWidth()*0.015)));
                radioClicked = new ImageIcon(rClicked.getScaledInstance((int) (rightDefaultPanel.getWidth()*0.025),(int) (rightDefaultPanel.getWidth()*0.025),res));
                radioUnclicked = new ImageIcon(rUnclicked.getScaledInstance((int) (rightDefaultPanel.getWidth()*0.025),(int) (rightDefaultPanel.getWidth()*0.025),res));
                iconPieceWhiteC = new ImageIcon(pieceWhiteC.getScaledInstance((int) (rightDefaultPanel.getWidth()*0.06),(int) (rightDefaultPanel.getWidth()*0.06),res));
                iconPieceWhiteU = new ImageIcon(pieceWhiteU.getScaledInstance((int) (rightDefaultPanel.getWidth()*0.06),(int) (rightDefaultPanel.getWidth()*0.06),res));
                iconPieceBlackC = new ImageIcon(pieceBlackC.getScaledInstance((int) (rightDefaultPanel.getWidth()*0.06),(int) (rightDefaultPanel.getWidth()*0.06),res));
                iconPieceBlackU = new ImageIcon(pieceBlackU.getScaledInstance((int) (rightDefaultPanel.getWidth()*0.06),(int) (rightDefaultPanel.getWidth()*0.06),res));
                iconPieceRandomC = new ImageIcon(pieceRandomC.getScaledInstance((int) (rightDefaultPanel.getWidth()*0.06),(int) (rightDefaultPanel.getWidth()*0.06),res));
                iconPieceRandomU = new ImageIcon(pieceRandomU.getScaledInstance((int) (rightDefaultPanel.getWidth()*0.06),(int) (rightDefaultPanel.getWidth()*0.06),res));
                defaultFriendRB.setIcon(radioUnclicked); defaultComputerRB.setIcon(radioUnclicked);
                if(defaultFriendRB.isSelected()) defaultFriendRB.setIcon(radioClicked);
                else defaultComputerRB.setIcon(radioClicked);
                defaultPAWhiteRB.setIcon(iconPieceWhiteU); defaultPARandomRB.setIcon(iconPieceRandomU); defaultPABlackRB.setIcon(iconPieceBlackU);
                if(defaultPAWhiteRB.isSelected()) defaultPAWhiteRB.setIcon(iconPieceWhiteC);
                else if (defaultPARandomRB.isSelected()) defaultPARandomRB.setIcon(iconPieceRandomC);
                else defaultPABlackRB.setIcon(iconPieceBlackC);
            }
        });
        //// End Of Sub Menu Default Layout ////



        //// Sub Menu Builder Layout ////
        for(int i = 1; i<21; i++)
        builderSizeCB.addItem(i);

        builderSizeCB.setSelectedIndex(7);
        builderSizeCB.addActionListener(e -> {

        });

        builderVariantDrop.addActionListener(e -> {
            JComboBox vdCB = (JComboBox)e.getSource();
            cBoardIndexBuilder = vdCB.getSelectedIndex();

            for (comboItem Item : customBoardList) {
                if (Item.getIndex() == cBoardIndexBuilder) {
                    builderNameString = new StringBuilder(Item.getString());
                    builderNameTxtField.setText(String.valueOf(builderNameString));

                    if (Item.getIndex() == 0) {
                    builderNameTxtField.setText("New Board");
                    builderNameString = new StringBuilder("New Board");
                }

                    BBboard = Item.getPieceBoard().clone();
                    builderSizeCB.setSelectedIndex(BBboard.length-1);
                    BBpieces.clear();
                    BBpiecesVoidSpaces.clear();
                    for (piece piece:Item.getPieceList()){
                        BBpieces.add(piece.clone());
                    }
                    for (piece piece:Item.getPieceVoidList()){
                        BBpiecesVoidSpaces.add(piece.clone());
                    }
                    //pieces.addAll(Item.getPieceList());

                    for (int x = 0; x < BBboard.length; x++) {
                        for (int y = 0; y < BBboard.length; y++) {
                            BBboard[x][y] = null;
                        }
                    }
                    /*if (!playingAsWhite) {
                        for (piece piece : BBpieces) {
                            piece.x = BBboard.length - 1 - piece.x;
                            piece.y = BBboard.length - 1 - piece.y;
                        }
                        for (piece piece : BBpiecesVoidSpaces) {
                            piece.x = BBboard.length - 1 - piece.x;
                            piece.y = BBboard.length - 1 - piece.y;
                        }

                    }*/

                    for (piece piece : BBpieces)
                        BBboard[piece.x][piece.y] = piece;
                    for (piece piece : BBpiecesVoidSpaces){
                        System.out.println("void in board");
                        BBboard[piece.x][piece.y] = piece;
                    }


                    for (int x = 0; x < BBboard.length; x++) {
                        for (int y = 0; y < BBboard.length; y++) {
                            if (BBboard[x][y] == null)
                                BBboard[x][y] = new empty();
                        }
                    }

                    BBboardCheckTemp = Item.getPieceBoardCheck().clone();
                    //builderTempBoard = Item.cloneAll();

                }
            }
            builderBoard.repaint();



        });

        //builderBoardPanel.setBackground(Color.magenta);


        ButtonGroup builderPieceColorGroup = new ButtonGroup();
        builderPieceColorGroup.add(builderWhiteRB); builderPieceColorGroup.add(builderBlackRB);
        builderWhiteRB.setSelected(true); builderWhiteRB.setIcon(iconPieceWhiteC);
        builderBlackRB.setIcon(iconPieceBlackU);


        //layout//
        JPanel builderTopPanel = new JPanel(new GridBagLayout());
        JPanel paddingBuilderTopPanel = new JPanel();
        builderTopPanel.setBackground(null);
        paddingBuilderTopPanel.setBackground(null);

        GridBagConstraints conBuilder = new GridBagConstraints();
        conBuilder.gridy = 1;
        conBuilder.gridwidth = 3;
        conBuilder.insets = new Insets(0,0,20,0);
        rightBuilderPanel.add(builderTopPanel, conBuilder);

        GridBagConstraints conIn = new GridBagConstraints();
        conIn.weightx = 0.1;
        conIn.gridx = 0;
        conIn.anchor = GridBagConstraints.WEST;
        builderTopPanel.add(builderVariantDrop, conIn);
        conIn.gridx=1;
        builderTopPanel.add(builderNameTxtField, conIn);
        conIn.gridx = 2;
        conIn.anchor = GridBagConstraints.EAST;
        builderTopPanel.add(builderSizeLbl, conIn);
        conIn.gridx = 3;
        conIn.anchor = GridBagConstraints.WEST;
        builderTopPanel.add(builderSizeCB, conIn);
        conIn.gridx = 4;
        builderTopPanel.add(paddingBuilderTopPanel, conIn);

        conBuilder.gridwidth = 1;
        conBuilder.anchor = GridBagConstraints.CENTER;
        conBuilder.gridx = 0;
        conBuilder.gridy = 2;
        //conBuilder.gridwidth = 0;
        //conBuilder.gridheight = 3;
        conBuilder.fill = GridBagConstraints.BOTH;
        conBuilder.insets = new Insets(0,0,0,10);
        rightBuilderPanel.add(builderBoardPanel, conBuilder);
        conBuilder.insets = new Insets(0,0,0,0);
        conBuilder.gridx = 1;
        conBuilder.gridwidth = 2;
        rightBuilderPanel.add(builderPiecePanel, conBuilder);
        GridBagConstraints conB = new GridBagConstraints();
        conB.fill = GridBagConstraints.NONE;
        conB.gridy = 3;
        //gbc.gridwidth = 1;
        //gbc.gridheight = 1;
        builderBoardPanel.add(builderBoard, conB);
        conB.weighty = 1; conB.weightx = 1;
        conB.anchor = GridBagConstraints.NORTH;
        conB.gridy=0;
        conB.gridx=0;
        conB.gridheight = 2;
        conB.insets = new Insets(20,3,0,0);
        builderPiecePanel.add(builderWhiteRB, conB);
        conB.insets = new Insets(20,0,0,3);
        conB.gridx=1;
        conB.gridheight = 1;
        conB.weighty = 0.5;
        builderPiecePanel.add(builderClearBtn, conB);
        conB.insets = new Insets(2,0,0,3);
        conB.gridx=1;
        conB.gridy = 1;
        builderPiecePanel.add(builderVoidBtn, conB);
        conB.insets = new Insets(20,0,0,3);
        conB.gridx=2;
        conB.gridy =0;
        conB.gridheight = 2;
        conB.weighty = 1;
        builderPiecePanel.add(builderBlackRB, conB);
        conB.gridy = 2;
        conB.gridx = 0;
        //conB.insets = new Insets(5,0,0,0);
        conB.fill = GridBagConstraints.BOTH;
        conB.gridwidth = 3; conB.gridheight = 1;
        conB.weighty = 30;
        //conB.weightx = 2;
        //conB.weighty=10;
        builderPiecePanel.add(builderPieceInner, conB);
        GridBagConstraints conB2 = new GridBagConstraints();
        conB2.gridy=3;
        conB2.gridx = 1;
        conB2.weighty = 0;

        //conB.gridwidth 1 1; conB.gridheight = 1;
        conB2.anchor = GridBagConstraints.WEST;
        conB2.fill = GridBagConstraints.NONE;
        conB2.insets = new Insets(20,0,0,0);
        rightBuilderPanel.add(builderDeleteBtn, conB2);
        conB2.gridx = 2;
        conB2.anchor = GridBagConstraints.EAST;
        rightBuilderPanel.add(builderSaveBtn, conB2);


        builderBoardPanel.addComponentListener(new ComponentAdapter() { // checks whether frame is resized
            @Override
            public void componentResized(ComponentEvent e) {
                resizePreview(builderBoard, builderBoardPanel);
                iconPieceWhiteU = new ImageIcon(pieceWhiteU.getScaledInstance((int) (builderPieceInner.getWidth()*0.2),(int) (builderPieceInner.getWidth()*0.2),res));
                iconPieceBlackU = new ImageIcon(pieceBlackU.getScaledInstance((int) (builderPieceInner.getWidth()*0.2),(int) (builderPieceInner.getWidth()*0.2),res));
                iconPieceWhiteC = new ImageIcon(pieceWhiteC.getScaledInstance((int) (builderPieceInner.getWidth()*0.2),(int) (builderPieceInner.getWidth()*0.2),res));
                iconPieceBlackC = new ImageIcon(pieceBlackC.getScaledInstance((int) (builderPieceInner.getWidth()*0.2),(int) (builderPieceInner.getWidth()*0.2),res));

                builderWhiteRB.setIcon(iconPieceWhiteU); builderBlackRB.setIcon(iconPieceBlackU);
                if(builderWhiteRB.isSelected()) builderWhiteRB.setIcon(iconPieceWhiteC);
                else builderBlackRB.setIcon(iconPieceBlackC);

                builderDeleteBtn.setPreferredSize(new Dimension((int) (builderPieceInner.getWidth()*0.48), (int) (rightBuilderPanel.getHeight()*0.04)));
                builderSaveBtn.setPreferredSize(new Dimension((int) (builderPieceInner.getWidth()*0.48), (int) (rightBuilderPanel.getHeight()*0.04)));
                resizeDefaultComponent(builderTopPanel, rightBuilderPanel, 0.9f, 0.07f);

                builderSaveBtn.setFont((new Font("Arial", Font.PLAIN, (int)(rightBuilderPanel.getWidth()*0.015))));
                builderDeleteBtn.setFont((new Font("Arial", Font.PLAIN, (int)(rightBuilderPanel.getWidth()*0.015))));

                resizeDefaultComponent(builderVariantDrop, builderTopPanel, 0.16f,0.65f);
                resizeDefaultComponent(builderNameTxtField, builderTopPanel, 0.14f,0.65f);
                resizeDefaultComponent(builderSizeLbl, builderTopPanel, 0.1f,0.65f);
                resizeDefaultComponent(builderSizeCB, builderTopPanel, 0.07f,0.65f);
                resizeDefaultComponent(paddingBuilderTopPanel, builderTopPanel, 0.43f,0.8f);

                builderVariantDrop.setFont((new Font("Arial", Font.PLAIN, (int)(rightBuilderPanel.getWidth()*0.015))));
                builderNameTxtField.setFont((new Font("Arial", Font.PLAIN, (int)(rightBuilderPanel.getWidth()*0.015))));
                builderSizeLbl.setFont((new Font("Arial", Font.PLAIN, (int)(rightBuilderPanel.getWidth()*0.015))));
                builderSizeCB.setFont((new Font("Arial", Font.PLAIN, (int)(rightBuilderPanel.getWidth()*0.015))));

            }
        });




        rightBuilderPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeDefaultComponent(builderBoardPanel, rightBuilderPanel, 0.60f, 0.60f);
                resizeDefaultComponent(builderPiecePanel, rightBuilderPanel, 0.25f, 0.60f);
                resizeDefaultComponent(builderVariantDrop, rightBuilderPanel, 0.2f, 0.05f);
                //resizeDefaultComponent(builderClearBtn, builderPiecePanel, 0.2f, 0.2f);
                //resizeDefaultComponent(builderVoidBtn, builderPiecePanel, 0.2f, 0.2f);
                builderClearBtn.setPreferredSize(new Dimension( (int) (builderPieceInner.getWidth()*0.4), (int) (builderPieceInner.getWidth()*0.1)));
                builderVoidBtn.setPreferredSize(new Dimension( (int) (builderPieceInner.getWidth()*0.4), (int) (builderPieceInner.getWidth()*0.1)));

                builderDeleteBtn.setPreferredSize(new Dimension((int) (builderPieceInner.getWidth()*0.48), (int) (rightBuilderPanel.getHeight()*0.04)));
                builderSaveBtn.setPreferredSize(new Dimension((int) (builderPieceInner.getWidth()*0.48), (int) (rightBuilderPanel.getHeight()*0.04)));

                builderClearBtn.setFont((new Font("Arial", Font.PLAIN, (int)(rightBuilderPanel.getWidth()*0.015))));
                builderVoidBtn.setFont((new Font("Arial", Font.PLAIN, (int)(rightBuilderPanel.getWidth()*0.015))));
            }
        });

        //Listeners
        builderWhiteRB.addActionListener(e -> {
                builderWhiteRB.setSelected(true);
                builderWhiteRB.setIcon(iconPieceWhiteC);
                builderBlackRB.setIcon(iconPieceBlackU);
                builderPieceColor = 1;
                //builderPieceInner.revalidate();
                builderPieceInner.repaint();
                mainFrame.repaint();
        });

        builderBlackRB.addActionListener(e -> {
            builderBlackRB.setSelected(true);
            builderWhiteRB.setIcon(iconPieceWhiteU);
            builderBlackRB.setIcon(iconPieceBlackC);
            builderPieceColor = 0;
            //builderPieceInner.revalidate();
            builderPieceInner.repaint();
            mainFrame.repaint();
        });

        builderClearBtn.addActionListener( e -> {
            BBpieces.clear();
            BBpiecesVoidSpaces.clear();
            for (int y =0; y< BBboard.length; y++){
                for (int x = 0; x<BBboard.length; x++){
                    BBboard[x][y] = new empty();
                }
            }
            builderPieceInner.repaint();
            mainFrame.repaint();
        });

        builderVoidBtn.addActionListener( e -> {
            BBpieces.clear();
            BBpiecesVoidSpaces.clear();
            for (int y =0; y< BBboard.length; y++){
                for (int x = 0; x<BBboard.length; x++){
                    piece temp = new voidSpace(x,y);
                    BBboard[x][y] = temp;
                    BBpiecesVoidSpaces.add(temp);
                }
            }
            builderPieceInner.repaint();
            mainFrame.repaint();
        });

        builderSizeCB.addActionListener( e -> {
            JComboBox vdCB = (JComboBox)e.getSource();
            int size = vdCB.getSelectedIndex()+1;

            BBpieces.clear();
            BBpiecesVoidSpaces.clear();
            piece[][] boardTemp = new piece[size][size];
            BBboard = boardTemp.clone();

            for (int y =0; y< boardTemp.length; y++){
                for (int x = 0; x<boardTemp.length; x++){
                    BBboard[x][y] = new empty();
                }
            }
            builderPieceInner.repaint();
            mainFrame.repaint();
        });

        builderNameTxtField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
               // builderNameTxtField.setText(String.valueOf(builderNameString));
            }
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        builderSaveBtn.addActionListener( e -> {
            builderNameString = new StringBuilder(builderNameTxtField.getText());
            comboItem temp = new comboItem(builderNameString.toString(), customBoardList.size(), BBboard,BBboardCheckTemp, BBpieces, BBpiecesVoidSpaces).cloneAll();
            customBoardList.add(temp);
            GUI.defaultVariantDrop.addItem(temp);

            System.out.println("menu game pressed");
            rightBuilderPanel.setVisible(false);
            rightSettingsPanel.setVisible(false);
            rightDefaultPanel.setVisible(true);
            rightGamePanel.setVisible(false);
            mainFrame.revalidate();
            mainFrame.repaint();
        });

        builderDeleteBtn.addActionListener( e -> {
            if (cBoardIndexBuilder == 0){
                System.out.println("menu game pressed");
                rightBuilderPanel.setVisible(false);
                rightSettingsPanel.setVisible(false);
                rightDefaultPanel.setVisible(true);
                rightGamePanel.setVisible(false);
                mainFrame.revalidate();
                mainFrame.repaint();
            } else {
                customBoardList.remove(cBoardIndex+2);
                GUI.defaultVariantDrop.removeItemAt(cBoardIndex+2);
                System.out.println("menu game pressed");
                rightBuilderPanel.setVisible(false);
                rightSettingsPanel.setVisible(false);
                rightDefaultPanel.setVisible(true);
                rightGamePanel.setVisible(false);
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });


        //// End Of SUb Menu Builder Layout ////





        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setIconImage(gameIcon);
        mainFrame.setTitle("Chess");
        rightBuilderPanel.setVisible(false);
        rightSettingsPanel.setVisible(false);
        rightGamePanel.setVisible(false);
        updateJComponentsColor();
        mainFrame.pack();
        mainFrame.setVisible(true);

       timerForResize.start();


        //// Listeners /////////////////////////////////////////////////////////



        boardPanel.addMouseWheelListener(e -> {
            if (e.getWheelRotation() > 0 && boardInner.getWidth() > (Math.min(boardPanel.getWidth() - boardPanel.getWidth()/10, boardPanel.getHeight()-6 - (boardPanel.getHeight()-6)/10)/board.length)*(board.length/2)) {
                System.out.println("scrolled down");
                resizePreviewScrollWheel(boardInner, boardPanel,e.getWheelRotation());
                resizePreviewScrollWheel(materialBlackSide, boardInner,boardPanel, e.getWheelRotation());
                resizePreviewScrollWheel(materialWhiteSide, boardInner,boardPanel, e.getWheelRotation());

            }
            else if (e.getWheelRotation()<0 && boardInner.getWidth() < (Math.min(boardPanel.getWidth() - boardPanel.getWidth()/10, boardPanel.getHeight()-6 - (boardPanel.getHeight()-6)/10)/board.length)*board.length){
                System.out.println("scrolled up");
                resizePreviewScrollWheel(boardInner, boardPanel,e.getWheelRotation());
                resizePreviewScrollWheel(materialBlackSide, boardInner,boardPanel, e.getWheelRotation());
                resizePreviewScrollWheel(materialWhiteSide, boardInner,boardPanel, e.getWheelRotation());
            }


        });

        boardInner.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece!=null) {
                    selectedPiece.x = ex;
                    selectedPiece.y = ey;
                    piece.xpp = e.getX()-boardInner.getWidth()/(17*board.length/8);
                    piece.ypp = e.getY()-boardInner.getWidth()/(17*board.length/8);
                    boardInner.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        boardInner.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {


                if (board[exPrev][eyPrev].color == playerPlaying && (!computerInitiated) || (computerInitiated && (playerPlaying == 0 && playingAsWhite || playerPlaying == 1 && !playingAsWhite))) {
                    System.out.println("clicked " + ex + " " + ey + "   " + exPrev + " " + eyPrev );
                    if (board[exPrev][eyPrev].valid(ex,ey,board)) {
                        System.out.println("Clicked went through");
                        if (playerPlaying == 0) {
                            whiteCurrentSpaces.add(ex * 10 + ey);
                            blackCurrentSpaces.clear();
                        }
                        else {
                            blackCurrentSpaces.add(ex * 10 + ey);
                            whiteCurrentSpaces.clear();
                        }
                        board[exPrev][eyPrev].move(ex, ey);
                        boardInner.repaint();
                        // playerPlaying= (playerPlaying==0)? 1:0;
                    }
                }


                pressed = board[ex][ey].Type != Types.EMPTY && board[ex][ey].color == playerPlaying; // changes pressed to false if conditions are met
                }


            @Override
            public void mousePressed(MouseEvent e) {


                exPrev = ex;
                eyPrev = ey;

                System.out.println("pressed is true");
                pressed=true;

                ex = (int) (e.getX() / (boardInner.getWidth() / (double)board.length));
                ey = (board.length - 1) -(int) (e.getY() / (boardInner.getWidth() / (double)board.length));

                if(playerPlaying == 0 && board[ex][ey].Type == Types.EMPTY && !board[exPrev][eyPrev].valid(ex,ey,board))
                    whiteCurrentSpaces.clear();
                else if(playerPlaying == 1 && board[ex][ey].Type == Types.EMPTY && !board[exPrev][eyPrev].valid(ex,ey,board))
                    blackCurrentSpaces.clear();

                if(playerPlaying == 0 && board[ex][ey].Type != Types.EMPTY){
                    whiteCurrentSpaces.clear();
                    whiteCurrentSpaces.add(ex * 10 + ey);
                }
                else if(playerPlaying == 1 && board[ex][ey].Type != Types.EMPTY){
                    blackCurrentSpaces.clear();
                    blackCurrentSpaces.add(ex * 10 + ey);
                }

            selectedPiece=piece.getPiece(ex,ey,e.getX()-boardInner.getWidth()/(17*board.length/8),e.getY()-boardInner.getWidth()/(17*board.length/8));

                if (board[ex][ey].Type == Types.EMPTY || board[ex][ey].color != playerPlaying) {

                    pressed = false;

                }
            }


            @Override
            public void mouseReleased(MouseEvent e) {

                try {
                    if (!computerInitiated || playerPlaying == 0 && playingAsWhite || playerPlaying == 1 && !playingAsWhite) {

                    if (board[ex][ey].valid((int) (e.getX() / (boardInner.getWidth() / (double) board.length)), ((board.length - 1) - (int) (e.getY() / (boardInner.getWidth() / (double) board.length))), board) && board[ex][ey].color == playerPlaying) {
                        exR = (int) (e.getX() / (boardInner.getWidth() / (double) board.length));
                        eyR = ((board.length - 1) - (int) (e.getY() / (boardInner.getWidth() / (double) board.length)));
                        if (playerPlaying == 0) {
                            whiteCurrentSpaces.add(exR * 10 + eyR);
                            blackCurrentSpaces.clear();
                        } else {
                            blackCurrentSpaces.add(exR * 10 + eyR);
                            whiteCurrentSpaces.clear();
                        }
                        board[ex][ey].move((int) (e.getX() / (boardInner.getWidth() / (double) board.length)), ((board.length - 1) - (int) (e.getY() / (boardInner.getWidth() / (double) board.length))));
                    }
                }
                selectedPiece = null;
                boardInner.repaint();
            }catch (ArrayIndexOutOfBoundsException exception){
                    selectedPiece = null;
                    boardInner.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        backGameBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                backGameBtn.setBackground(rightPanelGreen.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                backGameBtn.setBackground(rightPanelGreen);
            }
        });
        menuGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                menuGame.setBackground(leftPanelBlack.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                menuGame.setBackground(leftPanelBlack);
            }
        });
        menuBuilder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                menuBuilder.setBackground(leftPanelBlack.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                menuBuilder.setBackground(leftPanelBlack);
            }
        });
        menuSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                menuSettings.setBackground(leftPanelBlack.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                menuSettings.setBackground(leftPanelBlack);
            }
        });

        //builder
        builderPieceInner.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                bex = e.getX();
                bey = e.getY();
                int size = Math.min(builderPieceInner.getHeight() / 4- builderPieceInner.getHeight()/32, builderPieceInner.getWidth() / 2 - builderPieceInner.getWidth()/4);
                pos = -1;
                for (int i =0; i<4; i++){
                    if (bex > builderPieceInner.getWidth()/16 && bex < builderPieceInner.getWidth()/16+size  &&  bey > size*i + builderPieceInner.getHeight()/32*i && bey < size*i + builderPieceInner.getHeight()/32*i+size) {
                        pos = i;
                        break;
                    }
                    if (bex > builderPieceInner.getWidth() -size - builderPieceInner.getHeight()/16 && bex < builderPieceInner.getWidth() - builderPieceInner.getHeight()/16  &&  bey > size*i+ builderPieceInner.getHeight()/32*i && bey < size*i+ builderPieceInner.getHeight()/32*i+size) {
                        pos = i + 4;
                        break;
                    }
                }


                builderPieceInner.repaint();
                mainFrame.repaint();


            }

            @Override
            public void mousePressed(MouseEvent e) { bex = e.getX();
                bey = e.getY();
                int size = Math.min(builderPieceInner.getHeight() / 4- builderPieceInner.getHeight()/32, builderPieceInner.getWidth() / 2 - builderPieceInner.getWidth()/4);
                pos = -1;
                for (int i =0; i<4; i++){
                    if (bex > builderPieceInner.getWidth()/16 && bex < builderPieceInner.getWidth()/16+size  &&  bey > size*i + builderPieceInner.getHeight()/32*i && bey < size*i + builderPieceInner.getHeight()/32*i+size) {
                        pos = i;
                        break;
                    }
                    if (bex > builderPieceInner.getWidth() -size - builderPieceInner.getHeight()/16 && bex < builderPieceInner.getWidth() - builderPieceInner.getHeight()/16  &&  bey > size*i+ builderPieceInner.getHeight()/32*i && bey < size*i+ builderPieceInner.getHeight()/32*i+size) {
                        pos = i + 4;
                        break;
                    }
                }


                builderPieceInner.repaint();
                mainFrame.repaint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}


        });

        builderBoard.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bex = (int) (e.getX() / (builderBoard.getWidth() / (double)BBboard.length));
                bey = (BBboard.length - 1) -(int) (e.getY() / (builderBoard.getWidth() / (double)BBboard.length));

                switch (pos) {
                    case 0 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new king(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 1 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new knight(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 2 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new rook(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 3 -> {
                        if(BBboard[bex][bey].Type == Types.VOID)
                            BBpiecesVoidSpaces.remove(BBboard[bex][bey]);
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new empty();
                    }
                    case 4 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new queen(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 5 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new bishop(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 6 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new pawn(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 7 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        piece pieceTemp = new voidSpace(bex,bey);
                        BBboard[bex][bey] = pieceTemp;
                        BBpiecesVoidSpaces.add(pieceTemp);

                    }
                }
                builderBoard.repaint();
                mainFrame.repaint();


            }

            @Override
            public void mousePressed(MouseEvent e) {
                bex = (int) (e.getX() / (builderBoard.getWidth() / (double)BBboard.length));
                bey = (BBboard.length - 1) -(int) (e.getY() / (builderBoard.getWidth() / (double)BBboard.length));

                switch (pos) {
                    case 0 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new king(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 1 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new knight(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 2 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new rook(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 3 -> {
                        if(BBboard[bex][bey].Type == Types.VOID)
                            BBpiecesVoidSpaces.remove(BBboard[bex][bey]);
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new empty();
                    }
                    case 4 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new queen(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 5 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new bishop(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 6 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        BBboard[bex][bey] = new pawn(bex, bey, (builderPieceColor==0) ? 1:0);
                        BBpieces.add(BBboard[bex][bey]);
                    }
                    case 7 -> {
                        BBpieces.remove(BBboard[bex][bey]);
                        piece pieceTemp = new voidSpace(bex,bey);
                        BBboard[bex][bey] = pieceTemp;
                        BBpiecesVoidSpaces.add(pieceTemp);

                    }
                }
                builderBoard.repaint();
                mainFrame.repaint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}


        });
    }

    private static <T extends JComponent> void resizeDefaultComponent (T innerComponent, JPanel container, float widthPercentage, float heightPercentage){
        int w = container.getWidth();
        int h = container.getHeight();
        innerComponent.setMinimumSize(new Dimension((int)(w*widthPercentage), (int)(h*heightPercentage)));
        innerComponent.setPreferredSize(new Dimension((int)(w*widthPercentage), (int)(h*heightPercentage)));
        container.revalidate();
    }

    private static void resizePreview(JPanel innerPanel, JPanel container) { // method to resize chess board
        int w = container.getWidth();
        int h = container.getHeight();
        int size =  (Math.min(w - w/10, h-6 - (h-6)/10)/board.length)*board.length;
        innerPanel.setPreferredSize(new Dimension(size, size));
        //System.out.println(size);
        container.revalidate();
    }
    private static void resizePreview(JLabel innerLabel, JPanel container) { // method to resize chess board
        int w = container.getWidth();
        int h = container.getHeight();
        int size =  (Math.min(w, h-6)/board.length)*board.length;
        innerLabel.setPreferredSize(new Dimension(size-size/10, size/20));
        innerLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN ,size/30));
        container.revalidate();
    }
    private static void resizePreviewScrollWheel(JPanel innerPanel, JPanel container, int rotation) { // method to resize chess board
        int w = innerPanel.getWidth();
        int size =  (rotation > 0) ? ((w - w/10)/board.length)*board.length : ((w + w/10)/board.length)*board.length;

        int ContainerW = container.getWidth();
        int ContainerH = container.getHeight();
        int sizeContainer = (Math.min(ContainerW - ContainerW/10, ContainerH-6 - (ContainerH-6)/10)/board.length)*board.length;
        if (size < sizeContainer)
        innerPanel.setPreferredSize(new Dimension(size, size));
        else innerPanel.setPreferredSize(new Dimension(sizeContainer, sizeContainer));
        container.revalidate();
    }
    private static void resizePreviewScrollWheel(JLabel innerLabel, JPanel boardInner,JPanel container, int rotation) { // method to resize chess board


        if (boardInner.getWidth() - boardInner.getWidth()/10 < resizePreviewReturn(innerLabel, container) && rotation > 0 || boardInner.getWidth() + boardInner.getWidth()/10 < resizePreviewReturn(innerLabel, container) && rotation < 0 ) {
            if (rotation > 0) {
                innerLabel.setPreferredSize(new Dimension(boardInner.getWidth() - boardInner.getWidth() / 10, innerLabel.getHeight()));
                innerLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN ,innerLabel.getFont().getSize() - innerLabel.getFont().getSize()/12));
            }
        else {
            innerLabel.setPreferredSize(new Dimension(boardInner.getWidth() + boardInner.getWidth()/10, innerLabel.getHeight()));
                innerLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN ,innerLabel.getFont().getSize() + innerLabel.getFont().getSize()/12));
            }
        } else resizePreview(innerLabel,container);

        container.revalidate();
    }
    private static int resizePreviewReturn(JLabel innerPanel, JPanel container) { // method to resize chess board
        int w = container.getWidth();
        int h = container.getHeight();
        int size =  (Math.min(w, h-6)/board.length)*board.length;
        return size-size/10;

    }
    private static int notZero(int width){
        return width == 0 ? 1 : width;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("action initiated");
        String command = e.getActionCommand();
        int p = (playerPlaying == 0) ? 1:0;
        switch (command) {
            case "queen" -> {
                System.out.println("promoted to Queen");
                return;
            }
            case "rook" -> {
                System.out.println("promoted to Rook");
                Main.pieces.remove(Main.board[pawn.xPromote][pawn.yPromote]);
                Main.pieces.add(new rook(pawn.xPromote, pawn.yPromote, p));
                if (p == 0) {
                    whitePieceTakenValue -= 4;
                } else blackPieceTakenValue -= 4;
            }
            case "knight" -> {
                System.out.println("promoted to Knight");
                Main.pieces.remove(Main.board[pawn.xPromote][pawn.yPromote]);
                Main.pieces.add(new knight(pawn.xPromote, pawn.yPromote, p));
                if (p == 0) {
                    whitePieceTakenValue -= 6;
                } else blackPieceTakenValue -= 6;
            }
            case "bishop" -> {
                System.out.println("promoted to Bishop");
                Main.pieces.remove(Main.board[pawn.xPromote][pawn.yPromote]);
                Main.pieces.add(new bishop(pawn.xPromote, pawn.yPromote, p));
                if (p == 0) {
                    whitePieceTakenValue -= 6;
                } else blackPieceTakenValue -= 6;
            }

        }
        board[pawn.xPromote][pawn.yPromote]=pieces.get(pieces.size()-1);
        piecesCurrentValid.clear();
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
        GUI.materialWhiteSide.setText("material: " + (Main.whitePieceTakenValue-Main.blackPieceTakenValue) + " " + Main.whitePieceTaken);
        GUI.materialBlackSide.setText("material: " + (Main.blackPieceTakenValue-Main.whitePieceTakenValue) + " " + Main.blackPieceTaken);
        boardInner.repaint();
    }

    public static void colorIcon(BufferedImage image){
        for (int y = 0; y<image.getHeight(); y++){
            for (int x = 0; x<image.getWidth(); x++){
                int pixelIsTranparent = image.getRGB(x,y);
                if((pixelIsTranparent>>24) == 0x00){
                    continue;
                }
                image.setRGB(x,y,rightPanelSecondary.getRGB());
            }
        }
    }




    public static void updateJComponentsColor(){
        mainPanel.setBackground(rightPanelGreen);
        rightGamePanel.setBackground(rightPanelGreen);
        leftPanel.setBackground(leftPanelBlack);
        rightBuilderPanel.setBackground(rightPanelGreen);
        rightSettingsPanel.setBackground(leftPanelBlack.darker());
        rightDefaultPanel.setBackground(rightPanelGreen.darker());
        //boardPanel.setBackground(Color.magenta);

        menuGame.setForeground(rightPanelSecondary);
        menuBuilder.setForeground(rightPanelSecondary);
        menuSettings.setForeground(rightPanelSecondary);
        menuGame.setBackground(leftPanelBlack);
        menuBuilder.setBackground(leftPanelBlack);
        menuSettings.setBackground(leftPanelBlack);

        //change color of Icons
        colorIcon(gamePawnIcon); colorIcon(customizationBoardIcon); colorIcon(settingsSettingsIcon);
        if(leftPanel.getWidth() !=0) {
            menuGame.setIcon(new ImageIcon(gamePawnIcon.getScaledInstance((int) (0.25 * leftPanel.getWidth() * 0.3), (int) (0.40 * leftPanel.getWidth() * 0.3), BufferedImage.SCALE_AREA_AVERAGING)));
            menuBuilder.setIcon(new ImageIcon(customizationBoardIcon.getScaledInstance((int)(0.32 * leftPanel.getWidth()*0.3),(int)(0.32 * leftPanel.getWidth()*0.3), BufferedImage.SCALE_AREA_AVERAGING)));
            menuSettings.setIcon(new ImageIcon(settingsSettingsIcon.getScaledInstance((int)(0.55 * leftPanel.getWidth()*0.3),(int)(0.50     * leftPanel.getWidth()*0.3), BufferedImage.SCALE_AREA_AVERAGING)));
        }

        backGameBtn.setForeground(rightPanelTertiery);
        backGameBtn.setBackground(rightPanelGreen);

        resVeryLow.setBackground(leftPanelBlack.darker());
        resLow.setBackground(leftPanelBlack.darker());
        resMid.setBackground(leftPanelBlack.darker());
        resHigh.setBackground(leftPanelBlack.darker());
        resVeryHigh.setBackground(leftPanelBlack.darker());
        colDefault.setBackground(leftPanelBlack.darker());
        col1.setBackground(leftPanelBlack.darker());
        col2.setBackground(leftPanelBlack.darker());
        col3.setBackground(leftPanelBlack.darker());
        col4.setBackground(leftPanelBlack.darker());
        piecesDefault.setBackground(leftPanelBlack.darker());
        piecesKosal.setBackground(leftPanelBlack.darker());
        piecesKaneo.setBackground(leftPanelBlack.darker());
        settingsColor.setForeground(rightPanelSecondary);
        settingsResolution.setForeground(rightPanelSecondary);
        settingsPieces.setForeground(rightPanelSecondary);

        materialBlackSide.setForeground(rightPanelTertiery);
        materialWhiteSide.setForeground(rightPanelTertiery);

        settingsRightColorLbl.setForeground(rightPanelSecondary);
        settingsLeftColorLbl.setForeground(rightPanelSecondary);
        settingsBoardBlackLbl.setForeground(rightPanelSecondary);
        settingsBoardWhiteLbl.setForeground(rightPanelSecondary);
        settingsRightSecondaryLbl.setForeground(rightPanelSecondary);
        settingsRightTertiaryLbl.setForeground(rightPanelSecondary);
        settingsAlphaLbl.setForeground(rightPanelSecondary);
        settingsPrevMoveLbl.setForeground(rightPanelSecondary);

        settingsRightColorBtn.setBackground(rightPanelGreen);
        settingsLeftColorBtn.setBackground(leftPanelBlack);
        settingsBoardBlackBtn.setBackground(blackSpaces);
        settingsBoardWhiteBtn.setBackground(realWhiteSpaces);
        settingsRightSecondaryBtn.setBackground(rightPanelSecondary);
        settingsRightTertiaryBtn.setBackground(rightPanelTertiery);
        settingsPrevMoveBtn.setBackground(prevMoveAlpha);

        //default panel
        defaultUpperPanel.setBackground(null);
        //miniBoardPanel.setBackground(Color.magenta);
        defaultAccountPanel.setBackground(leftPanelBlack.darker());
        defaultContinueBtn.setBackground(leftPanelBlack);
        defaultContinueBtn.setForeground(rightPanelSecondary);
        defaultNewGamePanel.setBackground(leftPanelBlack.darker());
        defaultVariantLbl.setForeground(rightPanelSecondary);
        defaultOpponentLbl.setForeground(rightPanelSecondary);
        defaultPlayAsLbl.setForeground(rightPanelSecondary);
        defaultFriendRB.setForeground(Color.WHITE);
        defaultComputerRB.setForeground(Color.WHITE);
        defaultFriendRB.setBackground(leftPanelBlack.darker());
        defaultComputerRB.setBackground(leftPanelBlack.darker());
        defaultPAWhiteRB.setForeground(Color.WHITE);
        defaultPARandomRB.setForeground(Color.WHITE);
        defaultPABlackRB.setForeground(Color.WHITE);
        defaultPAWhiteRB.setBackground(leftPanelBlack.darker());
        defaultPARandomRB.setBackground(leftPanelBlack.darker());
        defaultPABlackRB.setBackground(leftPanelBlack.darker());
        defaultNewGameBtn.setBackground(leftPanelBlack);
        defaultNewGameBtn.setForeground(rightPanelSecondary);
        defaultVariantDrop.setBackground(leftPanelBlack);
        defaultVariantDrop.setForeground(rightPanelSecondary);

        //builder Panel
        builderWhiteRB.setBackground(null); builderBlackRB.setBackground(null);
        builderBoardPanel.setBackground(rightPanelSecondary);
        builderPiecePanel.setBackground(leftPanelBlack);
        builderClearBtn.setBackground(leftPanelBlack.darker());
        builderClearBtn.setForeground(rightPanelSecondary);
        builderVoidBtn.setBackground(leftPanelBlack.darker());
        builderVoidBtn.setForeground(rightPanelSecondary);
        builderDeleteBtn.setBackground(leftPanelBlack);
        builderDeleteBtn.setForeground(rightPanelSecondary);
        builderSaveBtn.setBackground(leftPanelBlack);
        builderSaveBtn.setForeground(rightPanelSecondary);

        builderVariantDrop.setBackground(leftPanelBlack);
        builderVariantDrop.setForeground(rightPanelSecondary);
        builderNameTxtField.setBackground(leftPanelBlack);
        builderNameTxtField.setForeground(rightPanelSecondary);
        builderSizeLbl.setForeground(leftPanelBlack);
        builderSizeCB.setBackground(leftPanelBlack);
        builderSizeCB.setForeground(rightPanelSecondary);

    }

}
