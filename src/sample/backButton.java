package sample;

import javafx.scene.shape.Polygon;

public class backButton {
    private int x_position;
    private int y_position;

    private Polygon backButton;

    private int screenWidth;// = main.getScreenWidth();
    private int screenHeight;// = main.getScreenHeight();

    public backButton(int init_x, int init_y) {
        x_position = init_x;
        y_position = init_y;



        backButton = new Polygon();
        backButton.getPoints().addAll(
                50.0, 50.0,
                100.0, 30.0,
                100.0, 40.0,
                150.0, 40.0,
                150.0, 60.0,
                100.0, 60.0,
                100.0, 70.0);
    }

    public Polygon getButton() {
        return backButton;
    }
}
