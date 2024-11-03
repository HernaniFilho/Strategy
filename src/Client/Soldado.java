package Client;

import Strategies.Strategy;

public class Soldado {
    private Strategy estrategia;

    public void setEstrategia(Strategy s){
        this.estrategia = s;
    }

    public boolean observar() {
        return estrategia.observar();
    }

    public void agir() {
        estrategia.agir();
    }

    public Strategy getEstrategia() {
        return estrategia;
    }
}
