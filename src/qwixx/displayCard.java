package qwixx;
import java.util.*;

public class displayCard {

    static LinkedHashMap<String, boolean[]> trackMap = new LinkedHashMap<>();
    static char[] penalties= {'_', '_', '_', '_'};

    public static void main(String[] args) {
        fillTrackMap();
        displayTracks(trackMap);
        displayPenalties();
    }
    public static void displayPlayerCard(String name){
        System.out.println(name);
        fillTrackMap();
        displayTracks(trackMap);
        displayPenalties();
        System.out.println("\n");
    }

    public static void fillTrackMap() {
        trackMap.put("Red",new boolean[11]);
        trackMap.put("Yellow",new boolean[11]);
        trackMap.put("Green",new boolean[11]);
        trackMap.put("Blue",new boolean[11]);
    }

    public static void displayTracks(LinkedHashMap<String, boolean[]> card) {
        int count;
        for(String trackColor: trackMap.keySet()) {
            if (trackColor.equals("Red") || trackColor.equals("Yellow")) {
                System.out.print(trackColor + " ");
                count = 2;
                for (boolean block: trackMap.get(trackColor)){
                    if (block) System.out.print("- ");
                    else System.out.print(count + " ");
                    count++;
                }
                System.out.print("@\n");
            }
            else {
                System.out.print(trackColor + " ");
                count = 12;
                for (boolean block : trackMap.get(trackColor)) {
                    if (block) System.out.print("- ");
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

}

