package qwixx;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Qwixx {
    static LinkedHashMap<String, DisplayCard> players = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        //LinkedHashMap<String, DisplayCard> players = new LinkedHashMap<>();
        int numPlayers = scan.nextInt();
        scan.nextLine(); // ensures we don't add the enter key after the numPlayers as a player
        for (int i = 1; i <= numPlayers; ++i) {
            System.out.print("\nEnter Player " + i + " Name: ");
            String name = scan.nextLine();
            players.put(name, new DisplayCard(name));
        }
        displayCards();
//        for (DisplayCard playerCard: players.values()) playerCard.displayPlayerCard();
        Die dice = new Die();
        dice.rollAll();

        for (String player: players.keySet()) {
            System.out.println(player + ": would you like to check off " + dice.sumWhite() + "? Type yes/no.");
            if(scan.nextLine().equals("yes")) {
                System.out.println("What color would you like to check off?");
                //add something that makes it non case-sensitive
                String color = scan.nextLine();
                players.get(player).checkBox(color, dice.sumWhite());
            }
        }
        displayCards();

    }

    private static void displayCards() {
        for (DisplayCard playerCard: players.values()) playerCard.displayPlayerCard();
    }
}
