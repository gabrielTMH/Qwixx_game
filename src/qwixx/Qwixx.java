package qwixx;

public class Qwixx {
    public static void main(String[] args) {
        DisplayCard playerOne = new DisplayCard("player one");
        DisplayCard playerTwo = new DisplayCard("player two");
        playerOne.displayPlayerCard();
        playerTwo.displayPlayerCard();

        playerOne.checkBox("Green", 7);
        playerOne.checkBox("Green", 9);
        playerOne.checkBox("Red", 2);



        playerTwo.checkBox("Green", 6);
        playerTwo.checkBox("Green", 11);
        playerTwo.checkBox("Red", 9);

        playerOne.displayPlayerCard();
        playerTwo.displayPlayerCard();
    }
}
