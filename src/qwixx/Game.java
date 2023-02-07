package qwixx;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Game {

    static LinkedHashMap<String, DisplayCard> players;

    static Die dice;

    static Scanner scan;

    static String activePlayer;

    static Iterator iterator;

    public Game() {
        dice = new Die();
        players = new LinkedHashMap<>();
        scan = new Scanner(System.in);
    }

    public static void displayCards() {
        for (DisplayCard playerCard : players.values()) playerCard.displayPlayerCard();
    }

    public static void insertPlayers() {
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
        iterator = players.keySet().iterator();
        setActivePlayer();
    }

    public static void turn () {
        dice.rollAll();
        // modify to show board after every player's move
        for (String player : players.keySet()) {
            System.out.println(player + ": would you like to check off " + dice.sumWhite() + "? Type yes/no.");
            if (scan.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("What color would you like to check off?");
                //add something that makes it non case-sensitive?
                String color = scan.nextLine();
                players.get(player).checkBox(color.toLowerCase(), dice.sumWhite());
            }
            players.get(player).displayPlayerCard();
        }
        // rows locked after white dice
        activePlayerTurn();
        setActivePlayer();
        // lock again after active player
    }

    public static void activePlayerTurn () {
        System.out.println(activePlayer + ": would you like to check off the sum of a white " +
                "die and any of the colors? Type yes/no.");
        if (scan.nextLine().equals("yes")) {
            System.out.println("What color would you like to check off?");
            String coloredDie = scan.nextLine();
            System.out.println("Which white die would you like to use?");
            String whiteDie = scan.nextLine();
            players.get(activePlayer).checkBox(coloredDie, dice.dieSet.get(coloredDie) +
                    dice.dieSet.get(whiteDie));
        }
        players.get(activePlayer).displayPlayerCard();
    }

    public static void setActivePlayer () {
        if (iterator.hasNext()) activePlayer = iterator.next().toString();
        else activePlayer = players.keySet().toArray()[0].toString();
    }
}
