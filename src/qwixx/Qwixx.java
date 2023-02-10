package qwixx;


public class Qwixx {

    static Game game = new Game();
    public static void main(String[] args) {
        game.insertPlayers();
        game.displayCards();
        while (!game.gameOver()) {
            game.turn();
        }
        game.printScores();
    }
}
