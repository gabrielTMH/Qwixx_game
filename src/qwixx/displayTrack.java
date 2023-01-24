package qwixx;
import java.util.*;



public class displayTrack {
    static Map<String, boolean[]> trackMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        fillTrackMap();
    }

    public static void fillTrackMap() {
        trackMap.put("Red",new boolean[11]);
        trackMap.put("Yellow",new boolean[11]);
        trackMap.put("Green",new boolean[11]);
        trackMap.put("Blue",new boolean[11]);
    }

    public static void displayTracks(LinkedHashMap card) {
        for (boolean place:trackMap.get("Red")) {
            System.out.println(place);
        }

    }
}

