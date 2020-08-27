package sample;

import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    private int screenWidth = 1920;
    private int screenHeight = 1080;

    public int mode = 99;

    private quickUnionScene quickUnionScene;

    private mainMenuScene mainMenuScene = new mainMenuScene(screenWidth, screenHeight);

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Algorithm Visualizer");

        //primaryStage.setScene(mainMenuScene.getScene());
        primaryStage.setScene(new windowDimension().getScene());

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
        quickUnionScene = new quickUnionScene(40, 40, screenWidth, screenHeight);
        primaryStage.setScene(quickUnionScene.getScene());
        mode = 1;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
