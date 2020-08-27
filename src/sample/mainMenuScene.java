package sample;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class mainMenuScene {
    private Scene scene;
    private Group group;

    private int screenWidth;
    private int screenHeight;

    private int selection;


    public mainMenuScene(int init_screenWidth, int init_screenHeight) {
        screenWidth = init_screenWidth;
        screenHeight = init_screenHeight;

        selection = 0;

        group = new Group();
        group.getChildren().add(quickUnionBox());
        group.getChildren().add(quickUnionText());
        scene = new Scene(group, screenWidth, screenHeight);
    }

    public EventHandler<MouseEvent> menuSelection(int choice) {
        return mouseEvent -> {
            selection = choice;
        };
    }


    private Rectangle quickUnionBox() {
        Rectangle return_rect = new Rectangle();
        return_rect.setX(350);
        return_rect.setY(50);
        return_rect.setWidth(100);
        return_rect.setHeight(50);
        return_rect.setFill(Color.color(0, 1, 1));

        return_rect.addEventFilter(MouseEvent.MOUSE_CLICKED, menuSelection(1));

        return return_rect;
    }

    private Text quickUnionText() {
        Text quickUnionText = new Text("Quick Union");
        quickUnionText.setX(360);
        quickUnionText.setY(80);

        quickUnionText.addEventFilter(MouseEvent.MOUSE_CLICKED, menuSelection(1));

        return quickUnionText;
    }


    public int getSelection() {
        return selection;
    }
    public Scene getScene() {
        return scene;
    }
}
