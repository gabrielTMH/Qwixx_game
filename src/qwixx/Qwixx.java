package qwixx;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Qwixx {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        LinkedHashMap<String, DisplayCard> players = new LinkedHashMap<>();
        int numPlayers = scan.nextInt();
        scan.nextLine(); // ensures we don't add the enter key after the numPlayers as a player
        for (int i = 1; i <= numPlayers; ++i) {
            System.out.print("\nEnter Player " + i + " Name: ");
            String name = scan.nextLine();
            players.put(name, new DisplayCard(name));
        }
        for (DisplayCard playerCard: players.values()) playerCard.displayPlayerCard();
    }
}
