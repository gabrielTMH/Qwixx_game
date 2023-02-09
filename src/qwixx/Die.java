package qwixx;

import java.util.LinkedHashMap;

public class  Die {

    public LinkedHashMap<String, Integer> dieSet = new LinkedHashMap<>();

    public Die() {
        dieSet.put("white1", 0);
        dieSet.put("white2", 0);
        dieSet.put("red", 0);
        dieSet.put("yellow", 0);
        dieSet.put("green", 0);
        dieSet.put("blue", 0);
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
        return dieSet.get("white1")+dieSet.get("white2");
    }

}