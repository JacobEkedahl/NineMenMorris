package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import modell.Game;
import modell.HighScore;
import modell.Position;

/**
 *
 * @author Jacob
 */
public class NineMenMorris extends Application {

    //Setting limits on what user can do
    private boolean isPositionClickable;
    private boolean isPlayerOnePiecesClickable;
    private boolean isPlayerTwoPiecesClickable;
    private boolean isPositionHoverable;
    private boolean isGameRunning;

    private ArrayList<String> hovarablePositions;

    private boolean gameRunning;
    private BorderPane mainPane;
    private GridPane whitePlayerPane;

    private Label playerOneLbl;
    private Label playerTwoLbl;

    private ArrayList<ImageView> whitePieces;
    private ArrayList<ImageView> blackPieces;
    private ArrayList<ImageView> positionImages;
    private ArrayList<ImageView> unvisible;

    private ImageView pieceToMove;
    private ImageView positionToMove;
    private ImageView arrowImage;

    private Game gameSession;
    private Controller controller;

    private String[] stringPos;

    @Override
    public void start(Stage primaryStage) {
        initStartWindow(primaryStage);
        isGameRunning = false;
    }

    private void initStartWindow(Stage primaryStage) {
        controller = new Controller(gameSession);
        //Menu
        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Game");
        Menu helpMenu = new Menu("Help");

        //MenuItems
        MenuItem restartGameItem = new MenuItem("New Game"); //alert AI or player?
        MenuItem highScoreItem = new MenuItem("Highscore"); //need a array of moves in game
        MenuItem aboutItem = new MenuItem("About");
        MenuItem rulesItem = new MenuItem("Rules");

        //Connecting items to eventhandler
        newGameHandles newGame = new newGameHandles();
        restartGameItem.setOnAction(newGame);
        itemAboutHandles itemAbout = new itemAboutHandles();
        aboutItem.setOnAction(itemAbout);
        itemRulesHandles itemRules = new itemRulesHandles();
        rulesItem.setOnAction(itemRules);
        highScoreItem.setOnAction(new itemHighscoreHandles());

        //Connect Menu and items
        gameMenu.getItems().addAll(restartGameItem, highScoreItem);
        helpMenu.getItems().addAll(aboutItem, rulesItem);
        menuBar.getMenus().addAll(gameMenu, helpMenu);

        // FlowPane bottomPane = new FlowPane();
        // bottomPane.getChildren().addAll(newGamebtn, regretLastMovebtn);
        //Create a game players and get their names and position..
        //mainPane.setStyle("-fx-background-color: Black");
        mainPane = new BorderPane();
        mainPane.setTop(menuBar);
        File fileBack = new File("src/Images/backgroundAbout.jpg");
        loadBackgroundImage(mainPane, fileBack);

        Scene scene = new Scene(mainPane, 805, 590);
        primaryStage.setTitle("Nine Men Morris!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void addPositionsToBoard(BorderPane mainPane) {

    }

    private void addPiecesToGrid(GridPane grid, ArrayList<ImageView> pieces, int startY) {
        int noOfPieces = 0;
        for (int j = startY; j < (startY + 3); j++) {
            for (int i = 0; i < 3; i++) {

                grid.add(pieces.get(noOfPieces), i, j);
                noOfPieces++;
            }
        }
    }

    private void loadBackgroundImage(Pane pane, File file) {
        Image backImage = new Image(file.toURI().toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

        pane.setBackground(new Background(new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
    }

    private void unMarkAllButtons() {
        for (int i = 0; i < whitePieces.size(); i++) {

        }
    }

    private void changeImageInList(ArrayList<ImageView> listOfImages, Image newImage, String id) {
        for (int i = 0; i < listOfImages.size(); i++) {
            if (id.equals(listOfImages.get(i).getId())) {
                listOfImages.get(i).setImage(newImage);
            }
        }
    }

    private void changeAllImagesInList(ArrayList<ImageView> listOfImages, Image newImage) {
        for (int i = 0; i < listOfImages.size(); i++) {
            listOfImages.get(i).setImage(newImage);
        }
    }

    public void updateTurnUI() {
        if (gameSession.getCurrentPlayer().isBlack()) {
            arrowImage.setImage(initImageArrowBlack());
        } else {
            arrowImage.setImage(initImageArrow());
        }
    }

    public Image initImageArrow() {
        File filePiece = new File("src/Images/arrow.png");
        return new Image(filePiece.toURI().toString());
    }

    private Image initImageArrowBlack() {
        File filePiece = new File("src/Images/arrowBlack.png");
        return new Image(filePiece.toURI().toString());
    }

    private Image initImagePieceWhite() {
        File filePiece = new File("src/Images/whiteCircle.png");
        return new Image(filePiece.toURI().toString());
    }

    private Image initImagePieceBlack() {
        File filePiece = new File("src/Images/blackCircle.png");
        return new Image(filePiece.toURI().toString());
    }

    private Image initImagePieceWhiteMarked() {
        File filePiece = new File("src/Images/whiteCircleClicked.png");
        return new Image(filePiece.toURI().toString());
    }

    private Image initImagePieceBlackMarked() {
        File filePiece = new File("src/Images/blackCircleClicked.png");
        return new Image(filePiece.toURI().toString());
    }

    private Image initImagePositionTrans() {
        File filePosition = new File("src/Images/transparentPos.png");
        return new Image(filePosition.toURI().toString());
    }

    public void movePiece(String pieceId, String pos) {
        ImageView image = getPieceByID(pieceId);

        if (image == null) {
            showText("Cant select your opponents pieces!");
            return;
        }

        double x = image.getLayoutX();
        double y = image.getLayoutY();

        // System.out.println("dx: " + dx + " - dy:" + dy);
        // System.out.println("x: " + x + " y: " + y);
        Line line;
        if (pos.equals("NOPOS")) {
            Random rand = new Random();
            int xValue = 800 * (rand.nextInt(4) + 1);
            int yValue = 700 * (rand.nextInt(5) + 1);
            if (gameSession.getPlaceStage()) {
                x -= x;
                y -= y;
            } else {
                //ImageView tempView = getPieceByID(pieceId);
                x = image.getX();
                y = image.getY();
            }
            line = new Line(x + 25, y + 25, xValue, yValue);
        } else {
            ImageView positionImg = getPositionByID(pos);
            double dx = positionImg.getLayoutX() - 680 - x;
            double dy = positionImg.getLayoutY() + 105 - y;
            x -= x;
            y -= y;
            line = new Line(x + 25, y + 25, dx + 100, dy - 100);
        }

        image.toFront();
        PathTransition transition = new PathTransition();
        transition.setNode(image);
        transition.setDuration(Duration.seconds(1));
        transition.setPath(line);
        transition.setCycleCount(1);
        transition.play();
    }

    private ImageView getPositionByID(String id) {
        for (int i = 0; i < positionImages.size(); i++) {
            if (positionImages.get(i).getId().contains(id)) {
                return positionImages.get(i);
            }
        }
        return null;
    }

    private String convertIDtoString(String id) {
        String targetPos = id;
        if (targetPos.length() == 4) {
            targetPos = targetPos.substring(2);
        } else {
            targetPos = targetPos.substring(3);
        }
        return targetPos;
    }

    private ImageView getPieceByID(String id) {
        if (Integer.parseInt(id) > 8) {
            for (int i = 0; i < blackPieces.size(); i++) {
                if (blackPieces.get(i).getId().equals(id)) {
                    return blackPieces.get(i);
                }
            }
        } else {
            for (int i = 0; i < whitePieces.size(); i++) {
                if (whitePieces.get(i).getId().equals(id)) {
                    return whitePieces.get(i);
                }
            }
        }
        return null;
    }

    private void showText(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("About");
        alert.setContentText(message);
        alert.show();
    }

    private class pieceClicked implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            controller.selectPiece(event);
        }
    }

    private class positionClicked implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            controller.positionClicked(event);
        }

    }

    private class positionEnter implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            controller.positionEnter(event);
        }
    }

    private class newGameHandles implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            controller.initGame();
        }
    }

    private void setTextStyle(Text text) {
        text.setFill(Color.WHITE);
        text.setFont(Font.font(null, FontWeight.BOLD, 25));
    }

    private class itemAboutHandles implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            controller.showAbout();
        }
    }

    private class itemRulesHandles implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            controller.showRules();
        }
    }

    private class itemHighscoreHandles implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            controller.showHighscore();
        }

    }

    private class Controller {

        private Game sharedGame;
        private HighScore highScore;

        /**
         * *
         * Creates a controller and let controller share the viewclass game A
         * highscore is loaded, it is a .ser file
         *
         * @param game
         */
        Controller(Game game) {
            sharedGame = game;
            highScore = new HighScore();
        }

        /**
         * Method called by eventhandler when user tries to see the local
         * highscore loads highscore info into a textArea, highscore looks for
         * other similair names and add same players score together Top 10
         * String is generated
         */
        public void showHighscore() {
            String highscore = "";

            BorderPane backGroundPane = new BorderPane();

            File fileBack = new File("src/Images/backgroundAbout.jpg");
            TextArea text = new TextArea(highScore.toString());
            text.setPrefHeight(300);
            text.setPrefWidth(300);
            loadBackgroundImage(backGroundPane, fileBack);

            text.setEditable(false);

            text.setFont(Font.font(null, FontWeight.BOLD, 25));

            backGroundPane.setCenter(text);
            Text copyright = new Text("©JacobsCode, 2017");
            copyright.setFill(Color.WHITE);
            backGroundPane.setBottom(copyright);
            newWindow(backGroundPane, "Highscore", 805, 590);
        }

        /**
         * returns a string with top 10 players names and their total result in
         * order
         *
         * @return String
         */
        public String getHighScoreTopTen() {
            return highScore.toString();
        }

        /**
         * If someone wins they will be added to highscore
         *
         * @param playerName
         */
        public void addToHighScore(String playerName) {
            highScore.addToHighScore(playerName);
        }

        /**
         * Make those positions that have been hovered over when player removes
         * opponents piece visible again
         */
        public void unMask() {
            for (ImageView view : unvisible) {
                view.setVisible(true);
            }
            unvisible.clear();
        }

        /**
         * Hides all positions make only buttons clickable
         */
        public void MaskAll() {
            for (ImageView view : positionImages) {
                view.setVisible(false);
            }
        }

        /**
         * Makes all positions clickable (for player to place/move a piece)
         */
        public void unMaskAll() {
            for (ImageView view : positionImages) {
                view.setVisible(true);
            }
        }

        /**
         * When player is going to remove a piece, this function hides all
         * position when player hovers over positions.
         *
         * @param event
         */
        public void positionEnter(Event event) {
            hovarablePositions = gameSession.getFreePos();
            ImageView targetView = (ImageView) event.getSource();
            if (gameSession.getState() == 2) {
                targetView.setVisible(false);
                // targetView.setImage(null);
                unvisible.add(targetView);
            }
        }

        /**
         * Private method for the gameLooplogic. This method handles when player
         * click on position and it is the beginning, placingphase of the game.
         * state = 0, choose a piece state = 1, chose a position to move/place
         * the selected piece on state = 2, player has a mill
         *
         * @param event
         */
        private void positionStage(Event event) {
            ImageView selectPos = (ImageView) event.getSource();
            System.out.println(selectPos);
            hovarablePositions = gameSession.getFreePos();
            if (gameSession.getState() == 1) {
                for (int i = 0; i < hovarablePositions.size(); i++) {
                    if (convertIDtoString(selectPos.getId()).equals(hovarablePositions.get(i))) {
                        gameSession.setSelectedPosition(convertIDtoString(selectPos.getId()));
                        movePiece(gameSession.getSelectedPieceID(), gameSession.getSelectedPosition());
                        if (gameSession.getPlaceStage()) {
                            gameSession.placePiece(Integer.parseInt(gameSession.getSelectedPieceID()), gameSession.getSelectedPosition());
                        } else {

                            System.out.println("Piece moved to :" + gameSession.getSelectedPosition());
                            gameSession.movePiece(Integer.parseInt(gameSession.getSelectedPieceID()), gameSession.getSelectedPosition());
                        }

                        if (gameSession.isMill(gameSession.getSelectedPiece(), gameSession.getGameBoardPieces())) {
                            gameSession.next();
                            System.out.println("Mill!");
                        } else {
                            if (!gameSession.getPlaceStage()) {
                                MaskAll();
                            }

                            gameSession.again();
                            updateTurnUI();
                        }
                    }
                }

            }
        }

        /**
         * Private method for when gamestate is moving/flying and player is
         * suppose to select a piece
         *
         * @param event
         */
        private void positionClickedNormal(Event event) {
            MaskAll();
            ImageView selectPos = (ImageView) event.getSource();

            System.out.println(selectPos.getId());
            System.out.println(gameSession.getState() + " is the state");
            System.out.println(gameSession.getSelectedPieceID() + " is piece id");
            System.out.println();

            System.out.println(gameSession.getSelectedPosition() + " is piece pos");

            if (gameSession.getState() == 1) {
                hovarablePositions = gameSession.getOption(gameSession.getSelectedPiece());
                for (String s : hovarablePositions) {
                    System.out.print(s + " - ");
                }

                System.out.println("\n\n\n\n");
                for (int i = 0; i < hovarablePositions.size(); i++) {
                    if (convertIDtoString(selectPos.getId()).equals(hovarablePositions.get(i))) {
                        System.out.println(selectPos.getId() + " is the id of pos"); //and is a possible move

                        gameSession.setSelectedPosition(convertIDtoString(selectPos.getId()));
                        gameSession.movePiece(Integer.parseInt(gameSession.getSelectedPieceID()), Position.valueOf(gameSession.getSelectedPosition()));
                        movePiece(gameSession.getSelectedPieceID(), gameSession.getSelectedPosition());

                        //do this player have a mill
                        if (gameSession.isMill(gameSession.getSelectedPiece(), gameSession.getGameBoardPieces())) {
                            gameSession.next();
                        } else {
                            //has this player won
                            if (gameSession.haveCurrentPlayerWon()) {
                                newWinner(gameSession.getCurrentPlayer().getName());
                                highScore.writeToFile();
                            }
                            gameSession.again();
                            updateTurnUI();
                        }
                    }
                }
            }
        }

        /**
         * Eventhandler connected to positions calls this function, it is
         * gamelogic, make sure user choose the correctPiece
         *
         * @param event
         */
        public void positionClicked(Event event) {
            if (gameSession.getPlaceStage()) {
                positionStage(event);
            } else {
                positionClickedNormal(event);
            }
        }

        /**
         * Pieces eventhandler calls this method and it handles gamelogic for
         * ingame use.
         *
         * @param event
         */
        public void selectPiece(Event event) {
            changeAllImagesInList(whitePieces, initImagePieceWhite()); //std image
            changeAllImagesInList(blackPieces, initImagePieceBlack());

            if (gameSession.getPlaceStage()) {
                voidSelectPieceStage(event);
            } else {
                selectedPieceNormal(event);
            }
        }

        /**
         * Private method for the positionClicked method, it handles event when
         * player tries to select a piece during the normal phase of the game
         *
         * @param event
         */
        private void selectedPieceNormal(Event event) {
            ImageView tempPiece = new ImageView();
            tempPiece = (ImageView) event.getSource();
            unMaskAll();

            if (gameSession.getState() == 0) {
                if (gameSession.getCurrentPlayer().isBlack()) {
                    if (Integer.parseInt(tempPiece.getId()) > 8) {
                        gameSession.setSelectedPiece(Integer.parseInt(tempPiece.getId())); //modell change
                    }
                } else {
                    if (Integer.parseInt(tempPiece.getId()) < 9) {
                        gameSession.setSelectedPiece(Integer.parseInt(tempPiece.getId()));
                    }
                }
                System.out.println(Integer.parseInt(tempPiece.getId()) + " is id");
                System.out.println(tempPiece.getId() + " Id of imageView");
                gameSession.next();
            }

            if (gameSession.getState() == 2) {
                if (!gameSession.getCurrentPlayer().isBlack()) {
                    if (Integer.parseInt(tempPiece.getId()) > 8) {
                        gameSession.setSelectedPiece(Integer.parseInt(tempPiece.getId())); //modell change
                    }
                } else {
                    gameSession.setSelectedPiece(Integer.parseInt(tempPiece.getId()));
                }
                if (gameSession.isPieceOnBoard(Integer.parseInt(tempPiece.getId()))) {
                    movePiece(tempPiece.getId(), "NOPOS"); //send it away on UI
                    gameSession.removePiece(Integer.parseInt(tempPiece.getId())); //remove from Modell
                    //Look if thisplayer has one
                    if (gameSession.haveCurrentPlayerWon()) {
                        newWinner(gameSession.getCurrentPlayer().getName());
                        //call method gaemWon with playerName as parameter in
                    }

                    gameSession.again();
                    updateTurnUI();
                    MaskAll();
                }
            }
        }

        /**
         * handles logic during the placing stage of the game concerning pieces
         *
         * @param event
         */
        private void voidSelectPieceStage(Event event) {
            ImageView tempPiece = new ImageView();
            tempPiece = (ImageView) event.getSource();

            if (gameSession.getState() == 0 || gameSession.getState() == 1) { //Being able to change choice before clicking on a pos
                //first stage
                if (gameSession.getCurrentPlayer().isBlack()) {
                    if (Integer.parseInt(tempPiece.getId()) > 8) {
                        gameSession.setSelectedPiece(Integer.parseInt(tempPiece.getId())); //modell change
                        changeImageInList(blackPieces, initImagePieceBlackMarked(), tempPiece.getId()); //UI change
                        hovarablePositions = gameSession.getOption(gameSession.getSelectedPiece());
                    }
                } else {
                    if (Integer.parseInt(tempPiece.getId()) < 9) {
                        gameSession.setSelectedPiece(Integer.parseInt(tempPiece.getId()));
                        changeImageInList(whitePieces, initImagePieceWhiteMarked(), tempPiece.getId());
                        hovarablePositions = gameSession.getOption(gameSession.getSelectedPiece());
                    }
                }
                if (gameSession.getState() == 0) { //change state when position needs to be observent for input
                    gameSession.next();
                }
            } else if (gameSession.getState() == 2) {
                if (!gameSession.getCurrentPlayer().isBlack()) {
                    if (Integer.parseInt(tempPiece.getId()) > 8) {
                        gameSession.setSelectedPiece(Integer.parseInt(tempPiece.getId())); //modell change
                    }
                } else {
                    gameSession.setSelectedPiece(Integer.parseInt(tempPiece.getId()));
                }
                System.out.println(tempPiece.getId());
                if (gameSession.isPieceOnBoard(Integer.parseInt(tempPiece.getId()))) {
                    //positionReset(gameSession.positionFromPiece(Integer.parseInt(tempPiece.getId())));
                    movePiece(tempPiece.getId(), "NOPOS");
                    gameSession.removePiece(Integer.parseInt(tempPiece.getId())); //needs to check if this piece is on gameBoard
                    //remove piece
                    //check if player has won

                    unMask();
                    gameSession.again();
                    updateTurnUI();

                    for (String s : hovarablePositions) {
                        System.out.println(s);
                    }

                }
            }
        }

        /**
         * Add winner to highscore, shows an alertWindow with congratiulations
         *
         * @param winnerName
         */
        private void newWinner(String winnerName) {
            showText("Congratulations " + winnerName + "!\n" + "You've crushed your opponent!");
            highScore.addToHighScore(winnerName);
        }

        /**
         * Method called by eventhandler connected to the menuitem about, show
         * an non rezizable window same size as currentWindow and info about the
         * developer
         */
        public void showAbout() {
            BorderPane backGroundPane = new BorderPane();

            //Button btn = new Button("Hello");
            //backGroundPane.getChildren().add(btn);
            File fileBack = new File("src/Images/backgroundAbout.jpg");
            loadBackgroundImage(backGroundPane, fileBack);

            Text text = new Text("This game was created by Jacob Ekedahl and Tobias Degnell.");

            text.setFill(Color.WHITE);
            text.setFont(Font.font(null, FontWeight.BOLD, 25));

            backGroundPane.setCenter(text);
            Text copyright = new Text("©JacobsCode, 2017");
            copyright.setFill(Color.WHITE);
            backGroundPane.setBottom(copyright);
            newWindow(backGroundPane, "About", 805, 590);
        }

        /**
         * Shows a new window, same size as original gameWindow with text that
         * explains what this game is and how to play it
         */
        public void showRules() {
            BorderPane backGroundPane = new BorderPane();

            //Button btn = new Button("Hello");
            //backGroundPane.getChildren().add(btn);
            File fileBack = new File("src/Images/backgroundAbout.jpg");
            loadBackgroundImage(backGroundPane, fileBack);

            Text text = new Text("Nine Men's Morris is a very old game that has been played for thousands of years all over the world. It was most \n"
                    + "popular about five hundred years ago and was played by Monks in churches \n"
                    + "as well as on village greens throughout England.\n"
                    + "The game is for two players. One player has nine black playing pieces, the other has nine white playing pieces. \n"
                    + "The first player to reduce their opponent to two pieces wins.\n"
                    + "In the first stage of the game players take turns \n"
                    + "placing their pieces on the board, attempting to make a line of three. \n"
                    + "Once all the pieces have been placed, players may slide them, still attempting to make a line of three. \n"
                    + "Whenever a player makes a line of three, they remove an opponent's piece of his choice. \n "
                    + "When one player has only three pieces left, he is allowed to move any piece from any point on the board \n"
                    + "to any other point in order to more effectively block the winning opponent and to make a line of three. \n"
                    + "As soon as one player is left with only two pieces or cant move a piece at all, his opponent wins.");

            text.setFill(Color.WHITE);
            text.setFont(Font.font(null, FontWeight.BOLD, 15));

            backGroundPane.setTop(text);
            Text copyright = new Text("©JacobsCode, 2017");
            copyright.setFill(Color.WHITE);
            backGroundPane.setBottom(copyright);
            newWindow(backGroundPane, "Rules", 805, 590);
        }

        /**
         * When player wants to start a new game she/he gets to answer a couple
         * of setupquestions
         */
        public void initGame() {

            String playerOneName = "Player 1";
            String playerTwoName = "Player 2";
            boolean againstAi = false;
            boolean playerIsBlack = false;
            hovarablePositions = new ArrayList<String>();

            if (isGameRunning) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("End game");
                alert.setHeaderText("Do you wish to end the current game?");
                alert.setContentText("Are you ok with this?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    gameSession.gameOver();
                } else {
                    showText("Ok continue!");
                    return;
                }
            }

            TextInputDialog dialog = new TextInputDialog("Player 1");
            dialog.setTitle("Player 1 name");
            dialog.setHeaderText("Enter name");
            dialog.setContentText("Please enter your name:");

// Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                playerOneName = result.get();
            } else {
                showText("Ok continue!");
                return;
            }

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Choose color");
            alert.setHeaderText("Black or white");
            alert.setContentText("Do you wish to have white pieces?");

            ButtonType buttonTypeOne = new ButtonType("Yes");
            ButtonType buttonTypeTwo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            Optional<ButtonType> resultBlack = alert.showAndWait();
            if (resultBlack.get() == buttonTypeOne) {
                playerIsBlack = false;
            } else if (resultBlack.get() == buttonTypeTwo) {
                playerIsBlack = true;
            } else {
                showText("Ok continue!");
                return;
            }

            Alert alertAI = new Alert(AlertType.CONFIRMATION);
            alertAI.setTitle("Choose opponent");
            alertAI.setHeaderText("Against AI?");
            alertAI.setContentText("Do you wish to play against an AI instead of human?");

            ButtonType buttonYes = new ButtonType("Yes");
            ButtonType buttonNo = new ButtonType("No");

            alertAI.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> resultAI = alertAI.showAndWait();
            if (resultAI.get() == buttonYes) {
                againstAi = true;
            } else if (resultAI.get() == buttonNo) {
                againstAi = false;
            } else {
                showText("Ok continue!");
                return;
            }

            if (againstAi == false) {
                TextInputDialog dialog2 = new TextInputDialog("Player 2");
                dialog2.setTitle("Player 2 name");
                dialog2.setHeaderText("Enter name");
                dialog2.setContentText("Please enter next players name:");

// Traditional way to get the response value.
                Optional<String> resultName = dialog2.showAndWait();
                if (resultName.isPresent()) {
                    playerTwoName = resultName.get();
                } else {
                    showText("Ok continue!");
                    return;
                }
            }
            initGameUIModell(playerIsBlack, playerOneName, playerTwoName);
            isGameRunning = true;
        }

        /**
         * init all controllers, design for the mainGame window. Connecting some
         * of the controlls to correct EventHandlers. Initate positions and
         * pieces with unique idnumber that correlates with modell objects
         * idNumber/positions.
         *
         * @param isPlayerOneBlack
         * @param playerOneName
         * @param playerTwoName
         */
        public void initGameUIModell(boolean isPlayerOneBlack, String playerOneName, String playerTwoName) {

            gameSession = new Game(isPlayerOneBlack, playerOneName, playerTwoName);
            //initiate gamevariables
            stringPos = new String[]{"A7", "D7", "G7",
                "A4", "G4",
                "A1", "D1", "G1",
                "B6", "D6", "F6",
                "B4", "F4",
                "B2", "D2", "F2",
                "C5", "D5", "E5",
                "C4", "E4",
                "C3", "D3", "E3"
            };
            gameRunning = false;
            isPositionClickable = false;
            isPlayerOnePiecesClickable = false;
            isPlayerTwoPiecesClickable = false;
            isPositionHoverable = false;

            //Gameboard positions
            File filePosition = new File("src/Images/transparentPos.png");
            Image positionImage = new Image(filePosition.toURI().toString());
            Group groupOfPos = new Group();

            positionImages = new ArrayList<ImageView>();
            int positionIndex = 0;

            for (int j = 0;
                    j < 3; j++) { //outer quadrant
                for (int i = 0; i < 3; i++) { //24 is number of positions
                    if (!(i == 1 && j == 1)) {
                        ImageView positionView = new ImageView(positionImage);
                        positionView.toBack();
                        positionView.setId("#" + positionIndex);
                        positionIndex++;
                        positionView.setFitHeight(60); //set size of pieces
                        positionView.setFitWidth(60);
                        positionView.setPreserveRatio(true);
                        positionView.setSmooth(true);
                        positionView.setLayoutX(-8 + (i * 265));
                        positionView.setLayoutY(25 + (j * 265));
                        positionView.addEventHandler(MouseEvent.MOUSE_ENTERED, new positionEnter());
                        positionView.addEventHandler(MouseEvent.MOUSE_CLICKED, new positionClicked());
                        positionImages.add(positionView);
                        groupOfPos.getChildren().add(positionView);
                    }
                }
            }

            for (int j = 0;
                    j < 3; j++) { //middle quadrant
                for (int i = 0; i < 3; i++) { //24 is number of positions
                    if (!(i == 1 && j == 1)) {
                        ImageView positionView = new ImageView(positionImage);
                        positionView.toBack();
                        positionView.setId("#" + positionIndex);
                        positionIndex++;
                        positionView.setFitHeight(60); //set size of pieces
                        positionView.setFitWidth(60);
                        positionView.setPreserveRatio(true);
                        positionView.setSmooth(true);
                        positionView.setLayoutX(77 + (i * 180));
                        positionView.setLayoutY(110 + (j * 180));
                        positionView.addEventHandler(MouseEvent.MOUSE_ENTERED, new positionEnter());
                        positionView.addEventHandler(MouseEvent.MOUSE_CLICKED, new positionClicked());
                        positionImages.add(positionView);
                        groupOfPos.getChildren().add(positionView);
                    }
                }
            }

            for (int j = 0;
                    j < 3; j++) { //inner quadrant
                for (int i = 0; i < 3; i++) { //24 is number of positions
                    if (!(i == 1 && j == 1)) {
                        ImageView positionView = new ImageView(positionImage);
                        positionView.toBack();
                        positionView.setId("#" + positionIndex);
                        positionIndex++;
                        positionView.setFitHeight(60); //set size of pieces
                        positionView.setFitWidth(60);
                        positionView.setPreserveRatio(true);
                        positionView.setSmooth(true);
                        positionView.setLayoutX(157 + (i * 100));
                        positionView.setLayoutY(190 + (j * 100));
                        positionView.addEventHandler(MouseEvent.MOUSE_ENTERED, new positionEnter());
                        positionView.addEventHandler(MouseEvent.MOUSE_CLICKED, new positionClicked());
                        positionImages.add(positionView);
                        groupOfPos.getChildren().add(positionView);
                    }
                }
            }

            //init Position value to positionviewItems
            for (int i = 0; i < 24; i++) {
                positionImages.get(i).setId(positionImages.get(i).getId() + stringPos[i]);
            }

            unvisible = new ArrayList<>();
            //Pieces and players name
            Image imageWhite = initImagePieceWhite();
            Image imageBlack = initImagePieceBlack();
            Image imageArrow = initImageArrow();

            whitePieces = new ArrayList<ImageView>();
            blackPieces = new ArrayList<ImageView>();

            for (int i = 0;
                    i < 9; i++) {
                ImageView whitePiece = new ImageView(imageWhite);
                ImageView blackPiece = new ImageView(imageBlack);
                whitePiece.setFitHeight(50); //set size of pieces
                whitePiece.setFitWidth(50);
                blackPiece.setFitHeight(50);
                blackPiece.setFitWidth(50);
                blackPiece.setPreserveRatio(true);
                blackPiece.setSmooth(true);
                whitePiece.setPreserveRatio(true);
                whitePiece.setSmooth(true);

                whitePiece.setId("" + i);
                blackPiece.setId("" + (i + 9));
                whitePiece.setOnMouseClicked(new pieceClicked());
                blackPiece.setOnMouseClicked(new pieceClicked());

                whitePieces.add(whitePiece);
                blackPieces.add(blackPiece);
            }

            arrowImage = new ImageView(imageArrow);
            arrowImage.setFitHeight(80);
            arrowImage.setFitWidth(80);
            arrowImage.setPreserveRatio(true);
            arrowImage.setSmooth(true);

            GridPane grid = new GridPane();

            grid.setHgap(10); //horizontal gap in pixels => that's what you are asking for
            grid.setVgap(10); //vertical gap in pixels
            grid.setPadding(new Insets(10, 10, 10, 10));

            playerOneLbl = new Label(gameSession.getPlayerOneName());
            playerTwoLbl = new Label(gameSession.getPlayerTwoName());

            addPiecesToGrid(grid, whitePieces, 2);
            grid.add(playerOneLbl, 0, 1, 4, 1); //spans over hole row
            addPiecesToGrid(grid, blackPieces, 18);
            grid.add(playerTwoLbl, 0, 17, 4, 1);

            File fileBack = new File("src/Images/backgroundImage.jpg");
            loadBackgroundImage(mainPane, fileBack);

            mainPane.setRight(grid);
            arrowImage.setLayoutX(675);
            arrowImage.setLayoutY(277);
            mainPane.getChildren().add(arrowImage);
            mainPane.getChildren().add(groupOfPos);
        }
    }

    /**
     * Creates a new popupwindow, mostly used by ABout, Rules, Highscore -
     * events.
     *
     * @param pane
     * @param title
     * @param width
     * @param height
     */
    private void newWindow(Pane pane, String title, int width, int height) {
        Stage stage = new Stage();
        stage.setScene(new Scene(pane, width, height));
        stage.setResizable(false);
        stage.setTitle(title);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
