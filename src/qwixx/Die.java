package qwixx;

import java.util.LinkedHashMap;

public class  Die {

    public LinkedHashMap<String, Integer> dieSet = new LinkedHashMap<>();

    public Die() {
        dieSet.put("White1", 0);
        dieSet.put("White2", 0);
        dieSet.put("Red", 0);
        dieSet.put("Yellow", 0);
        dieSet.put("Green", 0);
        dieSet.put("Blue", 0);
    }

    public void lockDie(String name) {
//        TODO
//        add this function to remove die when row is locked
    }

    public void rollAll(){
        for (String die:
                dieSet.keySet()) {
            dieSet.put(die, roll());
            System.out.print(die + " " + dieSet.get(die) + " ");
        }
        System.out.println();
    }

    private int roll(){
        return (int)(Math.floor(Math.random() *6))+1;
    }

    public int sumWhite() {
        return dieSet.get("White1")+dieSet.get("White2");
    }

}