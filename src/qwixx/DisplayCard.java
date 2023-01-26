package qwixx;
import java.util.*;
public class DisplayCard {

    //sus on public vs static vs private
    public LinkedHashMap<String, boolean[]> trackMap;
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

    public static LinkedHashMap<String, boolean[]> createTrackMap() {
        LinkedHashMap<String, boolean[]> playerMap = new LinkedHashMap<>();
        playerMap.put("Red",new boolean[11]);
        playerMap.put("Yellow",new boolean[11]);
        playerMap.put("Green",new boolean[11]);
        playerMap.put("Blue",new boolean[11]);
        return playerMap;
    }

    public static void displayTracks(LinkedHashMap<String, boolean[]> card) {
        int count;
        for(String trackColor: card.keySet()) {
            if (trackColor.equals("Red") || trackColor.equals("Yellow")) {
                System.out.print(trackColor + " ");
                count = 2;
                for (boolean block: card.get(trackColor)){
                    if (block) System.out.print("X ");
                    else System.out.print(count + " ");
                    count++;
                }
                System.out.print("@\n");
            }
            else {
                System.out.print(trackColor + " ");
                count = 12;
                for (boolean block : card.get(trackColor)) {
                    if (block) System.out.print("X ");
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
    public void checkBoxes(String color, int num){
        int index;
        //Add statement for illegal move (i.g. 13)
        if(color.equals("Red") || color.equals("Yellow")){
            index = num-2;
        }
        else{
            index = 12-num;
        }
        this.trackMap.get(color)[index] = true;
    }
}

