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

    public LinkedHashMap<String, Object[]> trackMap;

    char[] penalties= {'_', '_', '_', '_'};

    int numPenalties;

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

    public LinkedHashMap<String, Object[]> createTrackMap() {
        LinkedHashMap<String, Object[]> playerMap = new LinkedHashMap<>();
        playerMap.put("red", BoxValues.newTrack());
        playerMap.put("yellow", BoxValues.newTrack());
        playerMap.put("green", BoxValues.newTrack());
        playerMap.put("blue", BoxValues.newTrack());
        return playerMap;
    }

    public static void displayTracks(LinkedHashMap<String, Object[]> card) {
        int count;
        for(String trackColor: card.keySet()) {
            if (trackColor.equals("red") || trackColor.equals("yellow")) {
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

    public void displayPenalties( ) {
        System.out.print("Penalties ");
        for (char penalty:this.penalties) System.out.print(penalty + " ");
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
        if(color.equals("red") || color.equals("yellow")) index = num - 2;
        else index = 12 - num;
        if(this.trackMap.get(color)[index] == BoxValues.AVAILABLE){
            this.trackMap.get(color)[index] = BoxValues.CHECKED;
        }
        else System.out.println("invalid move");
        hideUnavailable(color, index);
    }

    public boolean rowIsLockable(String color){
        //checking row to see if it can lock
        int checkedCount=0;
        for (Object box: trackMap.get(color)) {
            if(box == BoxValues.CHECKED){
                checkedCount++;
            }
        }
        return checkedCount>=5;
    }

    public void markPenalty() {
        int count = 0;
        for(char box: this.penalties) {
            if (box== '_') {
                this.penalties[count] = 'X';
                break;
            }
            else {
                count++;
            }
        }
    }

    public void lockRowOnCard(String color){
        for (int i = 0; i < 11; ++i) {
            if(this.trackMap.get(color)[i] == BoxValues.AVAILABLE)
                this.trackMap.get(color)[i] = BoxValues.UNAVAILABLE;
        }
    }

    public double getScore(){
        double score = 0;
        for (String color: this.trackMap.keySet()) {
            int rowScore = 0;
            for (Object box: this.trackMap.get(color)){
                if (box == DisplayCard.BoxValues.CHECKED) rowScore += 1;
            }
            score += 0.5*Math.pow(rowScore, 2) + 0.5*rowScore;
        }
        score -= 5 * this.numPenalties;
        return score;
    }

}

