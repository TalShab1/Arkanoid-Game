import game.Game;


public class Ass5Game {
    /**
     * Main method to run the game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}