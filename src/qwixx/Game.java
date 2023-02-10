package qwixx;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Game {

    LinkedHashMap<String, DisplayCard> players;

    Die dice;

    Scanner scan;

    String activePlayer;

    Iterator<String> iterator;

    boolean actionTaken;

    int numLockedRows;

    boolean lockRed, lockBlue, lockGreen, lockYellow;

    public Game() {
        numLockedRows = 0;
        dice = new Die();
        players = new LinkedHashMap<>();
        scan = new Scanner(System.in);
    }

    public void displayCards() {
        for (DisplayCard playerCard : players.values()) playerCard.displayPlayerCard();
    }

    public void insertPlayers() {
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

    public void turn () {
        actionTaken = false;
        dice.rollAll();
        for (String player : players.keySet()) {
            System.out.println(player + ": would you like to check off " + dice.sumWhite() + "? Type yes/no.");
            if (scan.nextLine().equals("yes")) {
                System.out.println("What color would you like to check off?");
                String color = scan.nextLine().toLowerCase();
                if (lockingMoveCheck(color, dice.sumWhite())) {
                    if (!players.get(player).rowIsLockable(color))
                        System.out.println("That row is not lockable yet :(");
                    else {
                        setRowToLock(color);
                        players.get(player).checkBox(color, dice.sumWhite());
                        if (player.equals(activePlayer)) actionTaken = true;
                    }
                }
                else {
                    players.get(player).checkBox(color, dice.sumWhite());
                    if (player.equals(activePlayer)) actionTaken = true;
                }

            }
            players.get(player).displayPlayerCard();
        }
        lockRows();
        activePlayerTurn();
        lockRows();
        setActivePlayer();
    }

    public void activePlayerTurn () {
        System.out.println(activePlayer + ": would you like to check off the sum of a white " +
                "die and any of the colors? Type yes/no.");
        if (scan.nextLine().equals("yes")) {
            System.out.println("What color would you like to check off?");
            String coloredDie = scan.nextLine().toLowerCase();
            System.out.println("Which white die would you like to use?");
            String whiteDie = scan.nextLine();
            if (lockingMoveCheck(coloredDie, dice.sumWhite())) {
                if (!players.get(activePlayer).rowIsLockable(coloredDie))
                    System.out.println("That row is not lockable yet :(");
                else {
                    setRowToLock(coloredDie);
                    players.get(activePlayer).checkBox(coloredDie, dice.dieSet.get(coloredDie) +
                            dice.dieSet.get(whiteDie));
                    actionTaken = true;
                }
            }
            else {
                players.get(activePlayer).checkBox(coloredDie, dice.dieSet.get(coloredDie) +
                        dice.dieSet.get(whiteDie));
                actionTaken = true;
            }
        }
        if(!actionTaken) {
            players.get(activePlayer).markPenalty();
            ++players.get(activePlayer).numPenalties;
        }
        players.get(activePlayer).displayPlayerCard();
    }

    public void setActivePlayer () {
        if (!iterator.hasNext()) iterator = players.keySet().iterator();
        activePlayer = iterator.next();
    }

    public void lockRow(String color){
        for (String player: players.keySet()) players.get(player).lockRowOnCard(color);
        dice.lockDie(color);
        ++numLockedRows;
    }

    public void lockRows() {
        if (lockRed) lockRow("red");
        if (lockGreen) lockRow("green");
        if (lockBlue) lockRow("blue");
        if (lockYellow) lockRow("yellow");
    }

    public boolean lockingMoveCheck (String color, int diceResult) {
        if (diceResult == 12 && (color.equals("red") || color.equals("yellow"))) return true;
        else return diceResult == 2 && (color.equals("blue") || color.equals("green"));
    }

    public void setRowToLock(String color) {
        switch (color) {
            case "red" -> lockRed = true;
            case "yellow" -> lockYellow = true;
            case "green" -> lockGreen = true;
            case "blue" -> lockBlue = true;
        }
    }

    public void printScores() {
        for (String player: players.keySet()) System.out.println(player + ": "+ players.get(player).getScore());
    }

    public boolean gameOver() {
        if (numLockedRows >= 2) return true;
        for (String player: players.keySet()) {
            if (players.get(player).numPenalties == 4) return true;
        }
        return false;
    }
}
