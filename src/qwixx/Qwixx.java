package qwixx;

public class Qwixx {
    public static void main(String[] args) {
        DisplayCard playerOne = new DisplayCard("player one");
        DisplayCard playerTwo = new DisplayCard("player two");
        playerOne.displayPlayerCard();
        playerTwo.displayPlayerCard();

        playerOne.checkBoxes("Green", 7);
        playerOne.checkBoxes("Green", 9);
        playerOne.checkBoxes("Red", 2);



        playerTwo.checkBoxes("Green", 6);
        playerTwo.checkBoxes("Green", 11);
        playerTwo.checkBoxes("Red", 9);

        playerOne.displayPlayerCard();
        playerTwo.displayPlayerCard();
    }
}
