package envs;

public class Randomizer {
    public static int getRandomInt(){
        java.util.Random rand = new java.util.Random();
        int upperbound = 5000;
        return rand.nextInt(upperbound);
    }
}

