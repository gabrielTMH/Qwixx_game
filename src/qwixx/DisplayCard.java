package qwixx;
import java.util.*;

/*
    possibly change to enum
 0 -> unmarked
-1 -> unavailable
 1 -> checked
*/

public class DisplayCard {

    //sus on public vs static vs private
    public LinkedHashMap<String, int[]> trackMap;
    //penalty not static
    static char[] penalties= {'_', '_', '_', '_'};

    public String name;

    public DisplayCard(String name){
        this.name = name;
        this.trackMap = createTrackMap();
    }
    public void displayPlayerCard(){
        System.out.println(this.name);
        displayTracks(trackMap);
        displayPenalties();
        System.out.println("\n");
    }

    public static LinkedHashMap<String, int[]> createTrackMap() {
        LinkedHashMap<String, int[]> playerMap = new LinkedHashMap<>();
        playerMap.put("Red",new int[11]);
        playerMap.put("Yellow",new int[11]);
        playerMap.put("Green",new int[11]);
        playerMap.put("Blue",new int[11]);
        return playerMap;
    }

    public static void displayTracks(LinkedHashMap<String, int[]> card) {
        int count;
        for(String trackColor: card.keySet()) {
            if (trackColor.equals("Red") || trackColor.equals("Yellow")) {
                System.out.print(trackColor + " ");
                count = 2;
                for (int box: card.get(trackColor)){
                    if (box == 1) System.out.print("X ");
                    else if (box == -1) System.out.print("- ");
                    else System.out.print(count + " ");
                    count++;
                }
                System.out.print("@\n");
            }
            else {
                System.out.print(trackColor + " ");
                count = 12;
                for (int box : card.get(trackColor)) {
                    if (box == 1) System.out.print("X ");
                    else if (box == -1) System.out.print("- ");
                    else System.out.print(count + " ");
                    count--;
                }
                System.out.print("@\n");
            }
        }
    }

    public static void displayPenalties() {
        System.out.print("Penalties ");
        for (char penalty:penalties) System.out.print(penalty + " ");
    }

    public void hideUnavailable(String color, int index) {
        for (int i = index; i >= 0 ; i--) {
            if(this.trackMap.get(color)[i]==0){
                this.trackMap.get(color)[i] = -1;
            }
        }
    }
    public void checkBox(String color, int num){
        int index;
        //Add statement for illegal move (i.g. 13)
        if(color.equals("Red") || color.equals("Yellow")){
            index = num-2;
        }
        else{
            index = 12-num;
        }
        this.trackMap.get(color)[index] = 1;
        hideUnavailable(color, index);
    }
}

