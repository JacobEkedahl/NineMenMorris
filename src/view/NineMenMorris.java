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
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        MenuItem restartGameItem = new MenuItem("Restart Game"); //alert AI or player?
        MenuItem regretMoveItem = new MenuItem("Regret last move"); //need a array of moves in game
        MenuItem aboutItem = new MenuItem("About");
        MenuItem rulesItem = new MenuItem("Rules");

        //Connect Menu and items
        fileMenu.getItems().addAll(openItem, saveItem);
        gameMenu.getItems().addAll(restartGameItem, regretMoveItem);
        helpMenu.getItems().addAll(aboutItem, rulesItem);
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);

        File file = new File("src/view/whiteCircle.png");
        Image imageWhite = new Image(file.toURI().toString());
        file = new File("src/view/blackCircle.png");
        Image imageBlack = new Image(file.toURI().toString());

        whitePieces = new ArrayList<ImageView>();
        blackPieces = new ArrayList<ImageView>();

        for (int i = 0; i < 9; i++) {
            ImageView whitePiece = new ImageView(imageWhite);
            ImageView blackPiece = new ImageView(imageBlack);
            whitePiece.setFitHeight(50);
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
        grid.add(playerOneLbl, 0, 1);
        addPiecesToGrid(grid, blackPieces, 8);
        grid.add(playerTwoLbl, 0, 7);

        //Create a game players and get their names and position..
        //mainPane.setStyle("-fx-background-color: Black");
        mainPane = new BorderPane();
        mainPane.setTop(menuBar);
        mainPane.setRight(grid);

        Scene scene = new Scene(mainPane, 600, 500);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
