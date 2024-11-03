package Strategies;

import java.util.Random;

public class AgressivoStrategy implements Strategy{
    @Override
    public void agir() {
        System.out.println("Agindo agressivamente!");
    }

    @Override
    public boolean observar() {
        int num = new Random().nextInt(4);
        if(num < 1) {
            return true;
        }
        return false;
    }
}
