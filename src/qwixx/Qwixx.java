package qwixx;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Qwixx {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedHashMap<String, DisplayCard> players = new LinkedHashMap<>();
        int numPlayers = scan.nextInt();
        for (int i = 0; i <= numPlayers; ++i) {
            String name = scan.nextLine();
            players.put(name, new DisplayCard(name));
        }

        for (DisplayCard playerCard: players.values()) {
            playerCard.displayPlayerCard();
        }

    }
}
