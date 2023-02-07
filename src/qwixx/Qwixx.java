package qwixx;


public class Qwixx {

    static Game game = new Game();

    static boolean gameOver = false;
    public static void main(String[] args) {
        game.insertPlayers();
        game.displayCards();
        while (!gameOver) {
            game.turn();
        }
    }

}
