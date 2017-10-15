package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modell.Game;

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

    private ArrayList<String> hovarablePositions;

    private boolean gameRunning;
    private BorderPane mainPane;
    private GridPane whitePlayerPane;

    private Label playerOneLbl;
    private Label playerTwoLbl;

    private ArrayList<ImageView> whitePieces;
    private ArrayList<ImageView> blackPieces;
    private ArrayList<ImageView> positionImages;

    private static Game gameSession;

    @Override
    public void start(Stage primaryStage) {
        //initiate gamevariables
        String[] stringPos = {
            "A7", "D7", "G7",
            "A4", "G4",
            "A1", "D1", "G1",
            "B6", "D6", "F6",
            "B4", "F4",
            "B2", "D2", "F2",
            "C5", "D5", "E5",
            "C4", "E4",
            "C3", "D3", "E4"
        };
        gameRunning = false;
        isPositionClickable = false;
        isPlayerOnePiecesClickable = false;
        isPlayerTwoPiecesClickable = false;
        isPositionHoverable = false;

        //Menu
        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Game");
        Menu helpMenu = new Menu("Help");
        Menu fileMenu = new Menu("File");

        //MenuItems
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem restartGameItem = new MenuItem("New Game"); //alert AI or player?
        MenuItem regretMoveItem = new MenuItem("Regret last move"); //need a array of moves in game
        MenuItem aboutItem = new MenuItem("About");
        MenuItem rulesItem = new MenuItem("Rules");

        //Connecting items to eventhandler
        newGameHandles newGame = new newGameHandles();

        restartGameItem.setOnAction(newGame);

        itemAboutHandles itemAbout = new itemAboutHandles();

        aboutItem.setOnAction(itemAbout);
        itemRulesHandles itemRules = new itemRulesHandles();

        rulesItem.setOnAction(itemRules);

        //Connect Menu and items
        fileMenu.getItems()
                .addAll(openItem, saveItem);
        gameMenu.getItems()
                .addAll(restartGameItem, regretMoveItem);
        helpMenu.getItems()
                .addAll(aboutItem, rulesItem);
        menuBar.getMenus()
                .addAll(fileMenu, gameMenu, helpMenu);

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
                    positionView.setId("#" + positionIndex);
                    positionIndex++;
                    positionView.setFitHeight(60); //set size of pieces
                    positionView.setFitWidth(60);
                    positionView.setPreserveRatio(true);
                    positionView.setSmooth(true);
                    positionView.setLayoutX(-8 + (i * 265));
                    positionView.setLayoutY(25 + (j * 265));
                    positionView.addEventHandler(MouseEvent.MOUSE_ENTERED, new positionEnter());
                    positionView.addEventHandler(MouseEvent.MOUSE_EXITED, new positionExit());
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
                    positionView.setId("" + positionIndex);
                    positionIndex++;
                    positionView.setFitHeight(60); //set size of pieces
                    positionView.setFitWidth(60);
                    positionView.setPreserveRatio(true);
                    positionView.setSmooth(true);
                    positionView.setLayoutX(77 + (i * 180));
                    positionView.setLayoutY(110 + (j * 180));
                    positionView.addEventHandler(MouseEvent.MOUSE_ENTERED, new positionEnter());
                    positionView.addEventHandler(MouseEvent.MOUSE_EXITED, new positionExit());
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
                    positionView.setId("" + positionIndex);
                    positionIndex++;
                    positionView.setFitHeight(60); //set size of pieces
                    positionView.setFitWidth(60);
                    positionView.setPreserveRatio(true);
                    positionView.setSmooth(true);
                    positionView.setLayoutX(157 + (i * 100));
                    positionView.setLayoutY(190 + (j * 100));
                    positionView.addEventHandler(MouseEvent.MOUSE_ENTERED, new positionEnter());
                    positionView.addEventHandler(MouseEvent.MOUSE_EXITED, new positionExit());
                    positionImages.add(positionView);
                    groupOfPos.getChildren().add(positionView);
                }
            }
        }

        //init Position value to positionviewItems
        for (int i = 0; i < 24; i++) {
            positionImages.get(i).setId(positionImages.get(i).getId() + stringPos[i]);
        }

        //Pieces and players name
        File file = new File("src/Images/whiteCircle.png");
        Image imageWhite = new Image(file.toURI().toString());
        file = new File("src/Images/blackCircle.png");
        Image imageBlack = new Image(file.toURI().toString());

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

        GridPane grid = new GridPane();

        grid.setHgap(
                10); //horizontal gap in pixels => that's what you are asking for
        grid.setVgap(
                10); //vertical gap in pixels
        grid.setPadding(
                new Insets(10, 10, 10, 10));

        playerOneLbl = new Label("Player 1");
        playerTwoLbl = new Label("Player 2");

        addPiecesToGrid(grid, whitePieces,
                2);
        grid.add(playerOneLbl,
                0, 1, 4, 1); //spans over hole row
        addPiecesToGrid(grid, blackPieces,
                18);
        grid.add(playerTwoLbl,
                0, 17, 4, 1);

        // FlowPane bottomPane = new FlowPane();
        // bottomPane.getChildren().addAll(newGamebtn, regretLastMovebtn);
        //Create a game players and get their names and position..
        //mainPane.setStyle("-fx-background-color: Black");
        mainPane = new BorderPane();
        File fileBack = new File("src/Images/backgroundImage.jpg");
        loadBackgroundImage(mainPane, fileBack);

        mainPane.setTop(menuBar);
        mainPane.setRight(grid);
        mainPane.getChildren().add(groupOfPos);

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

    private class pieceClicked implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            changeAllImagesInList(whitePieces, initImagePieceWhite()); //std image
            changeAllImagesInList(blackPieces, initImagePieceBlack());

            ImageView tempPiece = new ImageView();
            tempPiece = (ImageView) event.getSource();

            if (Integer.parseInt(tempPiece.getId()) < 9) {
                changeImageInList(whitePieces, initImagePieceWhiteMarked(), tempPiece.getId());
            } else {
                changeImageInList(blackPieces, initImagePieceBlackMarked(), tempPiece.getId());
            }

            System.out.println(tempPiece.getId());
        }
    }

    private class positionExit implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            ImageView targetView = (ImageView) event.getSource();
            for (int i = 0; i < positionImages.size(); i++) {
                if (targetView.getId().equals(positionImages.get(i).getId())) {
                    File filePosition = new File("src/Images/transparentPos.png");
                    Image positionImage = new Image(filePosition.toURI().toString());
                    positionImages.get(i).setImage(positionImage);

                }
            }
            System.out.println(targetView.getId());
        }

    }

    private class positionEnter implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            ImageView targetView = (ImageView) event.getSource();
            for (int i = 0; i < positionImages.size(); i++) {
                if (targetView.getId().equals(positionImages.get(i).getId())) {
                    File filePosition = new File("src/Images/hoverPosition.png");
                    Image positionImage = new Image(filePosition.toURI().toString());

                    positionImages.get(i).setImage(positionImage);

                }
            }
            System.out.println(targetView.getId());
        }
    }

    private class newGameHandles implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (gameRunning == true) {
                gameRunning = false;
                //stop current thread then set value to false
            } else {
            }
            //start gamethread
            /* while (true) {
                System.out.println("gameloop");
            }
             */
        }
    }

    private class itemAboutHandles implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
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
            newWindow(backGroundPane);
        }
    }

    private class itemRulesHandles implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
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
            newWindow(backGroundPane);
        }
    }

    private void newWindow(Pane pane) {
        Stage stage = new Stage();
        stage.setScene(new Scene(pane, 805, 590));
        stage.setResizable(false);
        stage.setTitle("About");
        stage.show();
    }

    private void showText(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("About");
        alert.setContentText(message);
        alert.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
