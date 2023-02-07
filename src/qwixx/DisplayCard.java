package qwixx;
import java.util.*;

public class DisplayCard {
    public enum BoxValues {
        CHECKED,
        UNAVAILABLE,
        AVAILABLE;
        public static BoxValues[] newTrack() {
            BoxValues[] result = new BoxValues[11];
            Arrays.fill(result, AVAILABLE);
            return result;
        }
    }
//
    public LinkedHashMap<String, Object[]> trackMap;

    public char[] penalties= {'_', '_', '_', '_'};

    public String name;

    public DisplayCard(String name){
        this.name = name;
        this.trackMap = createTrackMap();
    }
    public void displayPlayerCard(){
        System.out.println(this.name);
        displayTracks(trackMap);
        displayPenalties(this);
        System.out.println("\n");
    }

    public LinkedHashMap<String, Object[]> createTrackMap() {
        LinkedHashMap<String, Object[]> playerMap = new LinkedHashMap<>();
        playerMap.put("Red", BoxValues.newTrack());
        playerMap.put("Yellow", BoxValues.newTrack());
        playerMap.put("Green", BoxValues.newTrack());
        playerMap.put("Blue", BoxValues.newTrack());
        return playerMap;
    }

    public static void displayTracks(LinkedHashMap<String, Object[]> card) {
        int count;
        for(String trackColor: card.keySet()) {
            if (trackColor.equals("Red") || trackColor.equals("Yellow")) {
                System.out.print(trackColor + " ");
                count = 2;
                for (Object box: card.get(trackColor)){
                    if (box == BoxValues.CHECKED) System.out.print("X ");
                    else if (box == BoxValues.UNAVAILABLE) System.out.print("- ");
                    else System.out.print(count + " ");
                    ++count;
                }
                System.out.print("@\n");
            }
            else {
                System.out.print(trackColor + " ");
                count = 12;
                for (Object box: card.get(trackColor)){
                    if (box == BoxValues.CHECKED) System.out.print("X ");
                    else if (box == BoxValues.UNAVAILABLE) System.out.print("- ");
                    else System.out.print(count + " ");
                    --count;
                }
                System.out.print("@\n");
            }
        }
    }

    public void displayPenalties(DisplayCard name) {
        System.out.print("Penalties ");
        for (char penalty:name.penalties) System.out.print(penalty + " ");
    }

    public void hideUnavailable(String color, int index) {
        for (int i = index; i >= 0 ; i--) {
            if(this.trackMap.get(color)[i] == BoxValues.AVAILABLE){
                this.trackMap.get(color)[i] = BoxValues.UNAVAILABLE;
            }
        }
    }
    public void checkBox(String color, int num){
        int index;
        //Add statement for illegal move (i.g. 13)
        if(color.equals("Red") || color.equals("Yellow")) index = num - 2;
        else index = 12 - num;
        if(this.trackMap.get(color)[index] == BoxValues.AVAILABLE){
            this.trackMap.get(color)[index] = BoxValues.CHECKED;
        }
        else System.out.println("invalid move");
        hideUnavailable(color, index);
    }

    public void markPenalty() {
        int countPenalties = 0;
        for(char box: this.penalties) {
            if (box== '_') {
                this.penalties[countPenalties] = 'X';
                break;
            }
            else {
                countPenalties++;
            }
        }
    }


}

