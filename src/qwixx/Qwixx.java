package qwixx;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Qwixx {
    static LinkedHashMap<String, DisplayCard> players = new LinkedHashMap<>();

    static Die dice = new Die();

    static Scanner scan = new Scanner(System.in);

    static boolean gameOver = false;

    static String activePlayer;

    static Iterator iterator = players.entrySet().iterator();

    public static void main(String[] args) {
        insertPlayers();
        displayCards();
        while (!gameOver) {
            turn();
            displayCards();
        }
    }

    private static void displayCards() {
        for (DisplayCard playerCard : players.values()) playerCard.displayPlayerCard();
    }

    private static void insertPlayers() {
        System.out.print("Enter number of players (2-5): ");

        int numPlayers = scan.nextInt();

        while (numPlayers < 2 || numPlayers > 5) {
            System.out.print("\nType a number between 2 and 5: ");
            numPlayers = scan.nextInt();
        }

        scan.nextLine(); // ensures we don't add the enter key after the numPlayers as a player

        for (int i = 1; i <= numPlayers; ++i) {
            System.out.print("\nEnter Player " + i + " Name: ");
            String name = scan.nextLine();
            players.put(name, new DisplayCard(name));
        }
    }

    private static void turn () {
        dice.rollAll();

        for (String player : players.keySet()) {
            // skip over if not active player
            System.out.println(player + ": would you like to check off " + dice.sumWhite() + "? Type yes/no.");
            if (scan.nextLine().equals("yes")) {
                System.out.println("What color would you like to check off?");
                //add something that makes it non case-sensitive?
                String color = scan.nextLine();
                players.get(player).checkBox(color, dice.sumWhite());
            }
        }

    }

    private static void activePlayerTurn () {

    }

    private static void setActivePlayer () {
        if (iterator.hasNext()) activePlayer = iterator.next().toString();
        else activePlayer = players.entrySet().toArray()[0].toString();
    }

}
