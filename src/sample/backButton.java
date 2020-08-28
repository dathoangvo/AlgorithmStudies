package sample;
import javafx.scene.shape.Polygon;

public class backButton {

    private Polygon backButton;

    private int screenWidth;
    private int screenHeight;

    public backButton(int init_screenWidth, int init_screenHeight) {
        screenWidth = init_screenWidth;
        screenHeight = init_screenHeight;

        backButton = new Polygon();
        backButton.getPoints().addAll(
                screenWidth * .005, screenHeight * .05,
                screenWidth * .04, screenHeight * .02,
                screenWidth * .04, screenHeight * .035,
                screenWidth * .08, screenHeight * .035,
                screenWidth * .08, screenHeight * .065,
                screenWidth * .04, screenHeight * .065,
                screenWidth * .04, screenHeight * .08);
    }

    public Polygon getButton() {
        return backButton;
    }
}
