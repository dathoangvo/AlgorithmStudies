package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.print.DocFlavor;

public class windowDimension {
    private int temporaryWidth;
    private int temporaryHeight;

    private Scene scene;
    private Group group;

    private boolean dimensionSelected;

    private int aspectWidth;
    private int aspectHeight;

    private int screenWidth;
    private int screenHeight;


    public windowDimension() {
        temporaryWidth = 200;
        temporaryHeight = 200;
        dimensionSelected = false;

        group = new Group();
        group.getChildren().add(box(0, 0));
        group.getChildren().add(text("1920x1080\n 16:9", 0, 20));

        group.getChildren().add(box(0, 100));
        scene = new Scene(group, temporaryWidth, temporaryHeight);
    }

    private Rectangle box(int boxX, int boxY) {
        Rectangle standardBox = new Rectangle();
        standardBox.setX(boxX);
        standardBox.setY(boxY);
        standardBox.setWidth((int ) (temporaryWidth));
        standardBox.setHeight((int) (temporaryHeight / 2.1));
        standardBox.setFill(Color.color(1, 0, 0));

        return standardBox;
    }

    private Text text(String dimension_and_aspect, int textX, int textY) {
        Text standardText = new Text(dimension_and_aspect);
        standardText.setX(textX);
        standardText.setY(textY);
        standardText.setFont(Font.font(20.0));
        standardText.setFill(Color.color(1, 1, 1));

        return standardText;
    }

    private EventHandler<MouseEvent> chosenDimension(int choice) {
        if (choice == 0) {
            aspectWidth = 16;
            aspectHeight = 9;
            screenWidth = 1920;
            screenHeight = 1080;
            dimensionSelected = true;
        }


        return null;
    }

    public Scene getScene() {
        return scene;
    }
}
