package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.util.ArrayList;
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

    private BorderPane mainPane;
    private GridPane whitePlayerPane;

    private Label playerOneLbl;
    private Label playerTwoLbl;

    private ArrayList<ImageView> whitePieces;
    private ArrayList<ImageView> blackPieces;

    private Game gameSession;

    @Override
    public void start(Stage primaryStage) {

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
        fileMenu.getItems().addAll(openItem, saveItem);
        gameMenu.getItems().addAll(restartGameItem, regretMoveItem);
        helpMenu.getItems().addAll(aboutItem, rulesItem);
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);

        //Pieces and players name
        File file = new File("src/view/whiteCircle.png");
        Image imageWhite = new Image(file.toURI().toString());
        file = new File("src/view/blackCircle.png");
        Image imageBlack = new Image(file.toURI().toString());

        whitePieces = new ArrayList<ImageView>();
        blackPieces = new ArrayList<ImageView>();

        for (int i = 0; i < 9; i++) {
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

            whitePieces.add(whitePiece);
            blackPieces.add(blackPiece);
        }

        GridPane grid = new GridPane();
        grid.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        grid.setVgap(10); //vertical gap in pixels
        grid.setPadding(new Insets(10, 10, 10, 10));

        playerOneLbl = new Label("Player 1");
        playerTwoLbl = new Label("Player 2");

        addPiecesToGrid(grid, whitePieces, 2);
        grid.add(playerOneLbl, 0, 1, 4, 1); //spans over hole row
        addPiecesToGrid(grid, blackPieces, 18);
        grid.add(playerTwoLbl, 0, 17, 4, 1);

        // FlowPane bottomPane = new FlowPane();
        // bottomPane.getChildren().addAll(newGamebtn, regretLastMovebtn);
        //Create a game players and get their names and position..
        //mainPane.setStyle("-fx-background-color: Black");
        mainPane = new BorderPane();
        File fileBack = new File("src/view/backgroundImage.jpg");
        loadBackgroundImage(mainPane, fileBack);
        mainPane.setTop(menuBar);
        mainPane.setRight(grid);

        Scene scene = new Scene(mainPane, 805, 590);

        primaryStage.setTitle("Nine Men Morris!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
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
    
    private class newGameHandles implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            
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
            File fileBack = new File("src/view/backgroundAbout.jpg");
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
            File fileBack = new File("src/view/backgroundAbout.jpg");
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
