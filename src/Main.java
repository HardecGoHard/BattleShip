import GameWindow.PreparationGameWindow;
import Models.GameHelper;
import Models.ShipsController;


public class Main {
    public static void main(String[] args) {

        PreparationGameWindow gameWindow = new PreparationGameWindow();
        ShipsController friendlyShipsController = new ShipsController();
        GameHelper gameHelper = new GameHelper(gameWindow);

        gameHelper.setFriendModel(friendlyShipsController);

        gameWindow.setFriendlyShipsController(friendlyShipsController);
        gameWindow.setGameHelper(gameHelper);
        gameWindow.startGUI();
    }
}
