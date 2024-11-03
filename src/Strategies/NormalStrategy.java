package Strategies;

import java.util.Random;

public class NormalStrategy implements Strategy{
    @Override
    public void agir() {
        System.out.println("Agindo normalmente");
    }

    @Override
    public boolean observar() {
        return new Random().nextBoolean();
    }
}
