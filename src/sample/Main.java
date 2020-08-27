package sample;

import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    private int screenWidth = 600;
    private int screenHeight = 700;

    public int mode = 0;

    private quickUnionScene quickUnionScene;

    private mainMenuScene mainMenuScene = new mainMenuScene(screenWidth, screenHeight);

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Algorithm Visualizer");

        primaryStage.setScene(mainMenuScene.getScene());

        primaryStage.addEventFilter(MouseEvent.MOUSE_MOVED, mainMenuSelect(primaryStage, 99));

        primaryStage.show();

    }

    public javafx.event.EventHandler<MouseEvent> updateGraphics(Stage primaryStage) {
        if (mode == 0) {
            primaryStage.setScene(mainMenuScene.getScene());
            setupNode(primaryStage, mainMenuScene.getSelection());
        }
        if (mode == 1) {
            primaryStage.setScene(quickUnionScene.getScene());
            if (quickUnionScene.checkReturnToMenu()) {
                setupNode(primaryStage, 0);
            }
        }
        return null;
    }

    public javafx.event.EventHandler<MouseEvent> mainMenuSelect(Stage primaryStage, int pick) {
        if (pick != 99) {
            return mouseEvent -> {
                setupNode(primaryStage, pick);
            };
        }
        if (pick == 99) {
            return mouseEvent -> {
                updateGraphics(primaryStage);
            };
        }

        return null;
    }

    /**Resets each scene as if opening for the first time
     * @param primaryStage Stage
     * @param selection Scene to setup and display next
     *                  0 = Main Menu
     *                  1 = Quick Union
     */
    private void setupNode(Stage primaryStage, int selection) {
        if (selection == 0) {
            setupMainMenu(primaryStage);
        }
        if (selection == 1) {
            setupQuickUnion(primaryStage);
        }
    }

    /**Sets up the mainMenu selection scene
     * @param primaryStage Stage
     */
    private void setupMainMenu(Stage primaryStage) {
        mainMenuScene = new mainMenuScene(screenWidth, screenHeight);
        primaryStage.setScene(mainMenuScene.getScene());
        mode = 0;
    }

    /**Sets up the QuickUnion Interactive Display scene
     * @param primaryStage Stage
     */
    private void setupQuickUnion(Stage primaryStage) {
        quickUnionScene = new quickUnionScene(10, 10, screenWidth, screenHeight);
        primaryStage.setScene(quickUnionScene.getScene());
        mode = 1;
    }


    public static void main(String[] args) {
        launch(args);

    }
}
