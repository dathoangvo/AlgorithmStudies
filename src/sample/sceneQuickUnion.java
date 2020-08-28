package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class sceneQuickUnion {
    private int gridWidth;
    private int gridHeight;
    private int screenWidth;
    private int screenHeight;

    private Scene scene;

    private algorithmQuickUnion grid;

    Polygon backButton;

    private int numberDisplayMode;
    private boolean returnToMainMenu;


    public sceneQuickUnion(int init_gridWidth, int init_gridHeight, int init_screenWidth, int init_screenHeight) {
        returnToMainMenu = false;
        gridWidth = init_gridWidth;
        gridHeight = init_gridHeight;
        screenWidth = init_screenWidth;
        screenHeight = init_screenHeight;
        numberDisplayMode = 0;
        grid = new algorithmQuickUnion(gridWidth, gridHeight);

        updateScene();
    }

    private void updateScene() {
        Group group = new Group();

        // backButton, returns to main menu on click
        backButton = new backButton(screenWidth, screenHeight).getButton();
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, menuAction("exit_to_main_menu"));

        // menuBar at the top, no interaction.
        Rectangle menuBar = drawRect(0, 0, screenWidth, (int) (screenHeight * .1 - 2));
        menuBar.setFill(Color.color(1,1,1));

        // Box + Text to toggle grid to be empty
        Rectangle emptyGridRectangle = drawRect(screenWidth * .098, screenHeight * .015,screenWidth * .06, screenHeight * .02);
        if (numberDisplayMode == 0) {emptyGridRectangle.setFill(Color.color(1, 1, 0));} else {emptyGridRectangle.setFill(Color.color(0, 1, 1));}
        Text emptyGridText = drawText("Empty Grid", (int) (screenWidth * .1), (int) (screenHeight * .03), 12);
        emptyGridRectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, menuAction("display_no_numbers"));
        emptyGridText.addEventFilter(MouseEvent.MOUSE_CLICKED, menuAction("display_no_numbers"));

        // Box + Text to toggle grid to display its index
        Rectangle displayIndexRectangle = drawRect(screenWidth * .098,screenHeight * .045,screenWidth * .06,screenHeight * .02);
        if (numberDisplayMode == 1) {displayIndexRectangle.setFill(Color.color(1, 1, 0));} else {displayIndexRectangle.setFill(Color.color(0, 1, 1));}
        Text displayIndexText = drawText("Display Index", (int) (screenWidth * .1), (int) (screenHeight * .06), 12);
        displayIndexRectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, menuAction("display_index"));
        displayIndexText.addEventFilter(MouseEvent.MOUSE_CLICKED, menuAction("display_index"));

        // Box + Text to toggle grid to display its root
        Rectangle displayRootRectangle = drawRect(screenWidth * .098,screenHeight * .075,screenWidth * .06,screenHeight * .02);
        if (numberDisplayMode == 2) {displayRootRectangle.setFill(Color.color(1, 1, 0));} else {displayRootRectangle.setFill(Color.color(0, 1, 1));}
        Text displayRootText = drawText("Display Root", (int) (screenWidth * .1), (int) (screenHeight * .09), 12);
        displayRootRectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, menuAction("display_root"));
        displayRootText.addEventFilter(MouseEvent.MOUSE_CLICKED, menuAction("display_root"));

        group.getChildren().addAll(cycleGrid());
        group.getChildren().addAll(cycleGridDisplayNumber());
        group.getChildren().add(menuBar);
        group.getChildren().add(backButton);
        group.getChildren().add(emptyGridRectangle);
        group.getChildren().add(emptyGridText);
        group.getChildren().add(displayIndexRectangle);
        group.getChildren().add(displayIndexText);
        group.getChildren().add(displayRootRectangle);
        group.getChildren().add(displayRootText);


        scene = new Scene(group, screenWidth, screenHeight);
        scene.setFill(Color.color(0, 0, 0));

    }

    private Rectangle drawRect(double xpos, double ypos, double width, double height) {
        return new Rectangle(xpos, ypos, width, height);
    }

    private Text drawText(String text, int xpos, int ypos, int textSize) {
        Text returnText = new Text(text);
        returnText.setX(xpos);
        returnText.setY(ypos);
        returnText.setFont(Font.font(textSize));
        return returnText;
    }

    private EventHandler<MouseEvent> menuAction(String action) {
        if (action.equals("exit_to_main_menu")) {
            return mouseEvent -> returnToMainMenu = true;
        }
        if (action.equals("display_no_numbers")) {
            return mouseEvent -> {
                numberDisplayMode = 0;
                updateScene();
            };
        }
        if (action.equals("display_index")) {
            return mouseEvent -> {
                numberDisplayMode = 1;
                updateScene();
            };
        }
        if (action.equals("display_root")) {
            return mouseEvent -> {
                numberDisplayMode = 2;
                updateScene();
            };
        }
        return null;
    }


    private EventHandler<MouseEvent> graphicalEvent(int pos) {
        return mouseEvent -> {
            grid.updateOpen(pos);
            updateScene();
        };
    }

    private Text[] cycleGridDisplayNumber() {
        Text[] gridPosition = new Text[grid.size()];
        int horizontal = 0;
        int vertical = 0;
        for (int i = 0; i < grid.size(); i++) {
            if (numberDisplayMode == 0) {
                gridPosition[i] = new Text(" ");
            } else if (numberDisplayMode == 1) {
                gridPosition[i] = new Text(Integer.toString(i));
            } else {
                gridPosition[i] = new Text(Integer.toString(grid.getParent(i)));
            }
            gridPosition[i].setFill(Color.color(0, 0, 0));
            if (horizontal == gridWidth) {
                vertical++;
                horizontal = 0;
            }

            gridPosition[i].setX(horizontal*screenWidth/gridWidth + screenWidth*0.003);
            gridPosition[i].setY(vertical*screenHeight*0.9/gridHeight + screenHeight*0.115);
            gridPosition[i].addEventFilter(MouseEvent.MOUSE_CLICKED, graphicalEvent(i));

            horizontal++;
        }
        return gridPosition;
    }

    Rectangle[] returnGrid;

    private Rectangle[] cycleGrid() {
        returnGrid = new Rectangle[grid.size()];
        int horizontal = 0;
        int vertical = 0;
        for (int i = 0; i < grid.size(); i++) {
            returnGrid[i] = new Rectangle();
            if (horizontal == gridWidth) {
                vertical++;
                horizontal = 0;
            }
            // Sets color of the grid. White if closed, green if opened.
            if (grid.checkOpen(i)) {
                returnGrid[i].setFill(Color.color(0, .9, .6));
            } else {
                returnGrid[i].setFill(Color.color(1, 1, 1));
            }
            returnGrid[i].setX(horizontal*screenWidth/gridWidth + 1);
            returnGrid[i].setY(vertical*screenHeight*0.9/gridHeight + screenHeight*0.1);
            returnGrid[i].setWidth(screenWidth/gridWidth - 1);
            returnGrid[i].setHeight(screenHeight*0.9/gridHeight - 1);
            returnGrid[i].addEventFilter(MouseEvent.MOUSE_CLICKED, graphicalEvent(i));
            horizontal++;
        }
        return returnGrid;
    }

    // Returns the scene to be displayed
    public Scene getScene() {
        return scene;
    }

    // tells primaryScene when to return to main menu.
    public boolean checkReturnToMenu() {
        return returnToMainMenu;
    }
}
