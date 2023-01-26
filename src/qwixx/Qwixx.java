package qwixx;

import java.util.Scanner;

public class Qwixx {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name1= scan.nextLine();
        String name2= scan.nextLine();


        DisplayCard playerOne = new DisplayCard(name1);
        DisplayCard playerTwo = new DisplayCard(name2);
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
