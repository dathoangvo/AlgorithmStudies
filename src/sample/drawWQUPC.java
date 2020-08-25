package sample;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class drawWQUPC {
    private int gridWidth;
    private int gridHeight;

    private int screenWidth;
    private int screenHeight;

    private WeightQUPathCompress grid;

    private Scene scene;

    private Group group;


    public drawWQUPC(int init_gridWidth, int init_gridHeight, int init_screenWidth, int init_screenHeight) {
        gridWidth = init_gridWidth;
        gridHeight = init_gridHeight;
        screenWidth = init_screenWidth;
        screenHeight = init_screenHeight;

        grid = new WeightQUPathCompress(gridWidth, gridHeight);

        group = new Group(updateGrid());
        group.getChildren().add(menuBar());
        group.getChildren().addAll(updatePosition());

        scene = new Scene(group, screenWidth, screenHeight);
        scene.setFill(Color.color(0, 0, 0));
    }

    private Rectangle menuBar() {
        Rectangle returnRect = new Rectangle();
        returnRect.setFill(Color.color(0, 0, 1));
        returnRect.setX(0);
        returnRect.setY(0);
        returnRect.setWidth(screenWidth);
        returnRect.setHeight(100);
        return returnRect;
    }

    public javafx.event.EventHandler<MouseEvent> event(int pos) {
        return mouseEvent -> {
            grid.updateOpen(pos);
            group = new Group(updateGrid());
            group.getChildren().addAll(updatePosition());
            scene = new Scene(group, screenWidth, screenHeight);
            scene.setFill(Color.color(0, 0, 0));

        };
    }

    public Text[] updatePosition() {
        Text[] gridPosition = new Text[grid.size()];
        int horizontal = 0;
        int vertical = 0;
        for (int i = 0; i < grid.size(); i++) {
            gridPosition[i] = new Text(Integer.toString(grid.getParent(i)));
            gridPosition[i].setFill(Color.color(0, 0, 0));
            if (horizontal == gridWidth) {
                vertical++;
                horizontal = 0;
            }

            gridPosition[i].setX(horizontal * (int) (screenWidth / gridWidth) + 20);
            gridPosition[i].setY(vertical * (int) ((screenHeight - 100) / gridHeight) + 120);

            horizontal++;
        }

        return gridPosition;
    }

    public javafx.scene.shape.Rectangle[] updateGrid() {
        javafx.scene.shape.Rectangle[] returnGrid = new javafx.scene.shape.Rectangle[grid.size()];
        int horizontal = 0;
        int vertical = 0;
        //grid.updateOpen(0);

        for (int i = 0; i < grid.size(); i++) {

            returnGrid[i] = new javafx.scene.shape.Rectangle();
            if (horizontal == gridWidth) {
                vertical++;
                horizontal = 0;
            }
            if (grid.checkOpen(i)) {
                returnGrid[i].setFill(Color.color(0, .6, .9));
            } else {
                returnGrid[i].setFill(Color.color(1, 1, 1));
            }
            returnGrid[i].setX(horizontal * (int) (screenWidth / gridWidth) + 1);
            returnGrid[i].setY(vertical * (int) ((screenHeight - 100) / gridHeight) + 101);
            returnGrid[i].setWidth((int) (screenWidth / gridWidth) - 2);
            returnGrid[i].setHeight((int) ((screenHeight - 100) / gridHeight) - 2);



            returnGrid[i].addEventFilter(MouseEvent.MOUSE_CLICKED, event(i));

            horizontal++;
        }
        return returnGrid;
    }

    public Scene getScene() {
        return scene;
    }
}
