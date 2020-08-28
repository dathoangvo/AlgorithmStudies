package sample;

import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {
    private int screenWidth;
    private int screenHeight;
    public String currentScene;

    private sceneMainMenu sceneMainMenu;
    private sceneQuickUnion sceneQuickUnion;



    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Algorithm Visualizer");
        currentScene = "mainMenu";
        screenWidth = 1280;
        screenHeight = 800;
        sceneMainMenu = new sceneMainMenu(screenWidth, screenHeight);
        primaryStage.setScene(sceneMainMenu.getScene());

        primaryStage.addEventFilter(MouseEvent.MOUSE_MOVED, updateOnMouseMovement1(primaryStage));

        primaryStage.show();

    }

    public javafx.event.EventHandler<MouseEvent> updateOnMouseMovement1(Stage primaryStage) {
        return mouseEvent -> updateGraphics(primaryStage);
    }

    public javafx.event.EventHandler<MouseEvent> updateGraphics(Stage primaryStage) {
        if (currentScene.equals("mainMenu")) {
            primaryStage.setScene(sceneMainMenu.getScene());
            setupNode(primaryStage, sceneMainMenu.getSelection());
        }
        if (currentScene.equals("quickUnion")) {
            primaryStage.setScene(sceneQuickUnion.getScene());
            if (sceneQuickUnion.checkReturnToMenu()) {
                setupNode(primaryStage, 0);
            }
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
        sceneMainMenu = new sceneMainMenu(screenWidth, screenHeight);
        primaryStage.setScene(sceneMainMenu.getScene());
        currentScene = "mainMenu";
    }

    /**Sets up the QuickUnion Interactive Display scene
     * @param primaryStage Stage
     */
    private void setupQuickUnion(Stage primaryStage) {
        sceneQuickUnion = new sceneQuickUnion(32, 20, screenWidth, screenHeight);
        primaryStage.setScene(sceneQuickUnion.getScene());
        currentScene = "quickUnion";
    }


    public static void main(String[] args) {
        launch(args);

    }
}
