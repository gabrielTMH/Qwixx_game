package qwixx;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Qwixx {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedHashMap<String, DisplayCard> players = new LinkedHashMap<>();
        int numPlayers = scan.nextInt();
        int i = 0;
        while (i < numPlayers) {
            String name = scan.nextLine();
            if (!name.equals("")) {
                players.put(name, new DisplayCard(name));
                ++i;
            }
        }
        for (DisplayCard playerCard: players.values()) playerCard.displayPlayerCard();
    }
}
