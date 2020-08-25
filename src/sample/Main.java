package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private int screenWidth = 600;
    private int screenHeight = 700;

    public int mode = 0;

    private drawWQUPC WQUPCl = new drawWQUPC(10, 10, screenWidth, screenHeight);;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Algorithm Visualizer");
        //primaryStage.setScene(new Scene(root, 500, 500));

        //drawWQUPC grid = new drawWQUPC(10, 10, 500, 500);


        //grid.addStage(primaryStage);

        //primaryStage.setScene(grid.getScene());

        Rectangle QuickUnion = new Rectangle();
        QuickUnion.setX(350);
        QuickUnion.setY(50);
        QuickUnion.setWidth(100);
        QuickUnion.setHeight(50);
        QuickUnion.setFill(Color.color(0, 1, 1));

        Text quickUnionText = new Text("Quick Union");
        quickUnionText.setX(360);
        quickUnionText.setY(80);
        updateContent(primaryStage);

        Group group = new Group(QuickUnion, quickUnionText);
        Scene j = new Scene(group, screenWidth, screenHeight);
        primaryStage.setScene(j);
        QuickUnion.addEventFilter(MouseEvent.MOUSE_CLICKED, chooseAlgorithm(primaryStage,0));
        quickUnionText.addEventFilter(MouseEvent.MOUSE_CLICKED, chooseAlgorithm(primaryStage, 0));
        primaryStage.addEventFilter(MouseEvent.MOUSE_MOVED, chooseAlgorithm(primaryStage, 99));
        //primaryStage.addEventFilter(MouseEvent.MOUSE_MOVED, updateGraphics(primaryStage));

    }

    public javafx.event.EventHandler<MouseEvent> updateGraphics(Stage primaryStage) {
        if (mode == 2) {
            primaryStage.setScene(mode0());
        } else if (mode == 1) {
            Group g = new Group();

            primaryStage.setScene(WQUPCl.getScene());
        }
        System.out.println(mode);
        return null;
    }

    private Scene mode0() {
        Rectangle QuickUnion = new Rectangle();
        QuickUnion.setX(350);
        QuickUnion.setY(50);
        QuickUnion.setWidth(100);
        QuickUnion.setHeight(50);
        QuickUnion.setFill(Color.color(0, 1, 1));
        Text quickUnionText = new Text("Quick Union");
        quickUnionText.setX(360);
        quickUnionText.setY(80);
        Group group = new Group(QuickUnion, quickUnionText);
        Scene j = new Scene(group, screenWidth, screenHeight);
        return j;
    }

    public javafx.event.EventHandler<MouseEvent> chooseAlgorithm(Stage primaryStage, int pick) {
        //EventHandler<MouseEvent> chosenAlgorithm = null;
        //drawWQUPC WQUPCl = new drawWQUPC(10, 10, 500, 500);

        if (pick == 0) {
            return mouseEvent -> {
                mode = 1;
                primaryStage.setScene(WQUPCl.getScene());
                //updateContent(primaryStage);
            };

        } else if (pick == 1) {
            return mouseEvent ->
                    System.out.println("EmptyAlgo1");
        } else if (pick == 99) {
            return mouseEvent -> {
                updateGraphics(primaryStage);
            };
        }

        return null;
    }


    public void updateContent(Stage primaryStage) {
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
