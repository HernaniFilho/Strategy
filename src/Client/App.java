package Client;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import Strategies.*;

public class App {
    /*
     * Caso deseje adicionar novas Strategies ao jogo,
     * Primeiramente, para qualquer nova Strategy(interface) basta criá-la e criar sua(s) ConcreteStrategy('s) na package Strategies,
     * Segundamente, é necessário popular o Map estrategias, presente neste arquivom, com a(s) ConcreteStrategy('s) adicional(is) criada(s)
     * Por fim, vale notar que este projeto foi feito com um tipo de Strategy(interface) em mente. Logo caso for adicionar outra que difere
     * no comportamento, este arquivo deve ser adaptado para múltiplas Strategies.
     * 
     * Por exemplo:
     * Há uma classe de soldado, que possui estratégias de soldados.
     * Caso, queira criar uma estratégia nova para soldado, faça uma nova classe que implementa a estratégia de soldado(interface strategy).
     * Se você quiser criar outra classe, como a classe de tanques, é criar um novo grupo de estratégias, crie uma interface de estratégias 
     * de tanque(interface), assim como as classes concretas de estratégia.
     */
    public static void main(String[] args) {
        Soldado mySoldado, enemySoldado;
        String resp;
        int num;
        Scanner sc = new Scanner(System.in);
        
        Map<String,Strategy> estrategias = new HashMap<String,Strategy>();
        estrategias.put("AGRESSIVO", new AgressivoStrategy());
        estrategias.put("NORMAL", new NormalStrategy());

        boolean fim = false;
        while(!fim) {
            mySoldado = new Soldado();
            enemySoldado = new Soldado();
            //Mudar Estratégia
            System.out.println("\n\nVocê vaga sem rumo, procurar um inimigo de forma AGRESSIVO ou NORMAL ? ");
            resp = sc.nextLine().toUpperCase();
            Strategy escolha = estrategias.get(resp);
            while(escolha == null) {
                System.out.println("Escolha uma opção válida, " + escolha + " não é válido.");
                System.out.println("\n\nVocê vaga sem rumo, procurar um inimigo de forma AGRESSIVO ou NORMAL ? ");
                resp = sc.nextLine().toUpperCase();
                escolha = estrategias.get(resp);
            }
            mySoldado.setEstrategia(escolha);
            
            num = new Random().nextInt(2);
            switch (num) {
                case 0:
                    enemySoldado.setEstrategia(estrategias.get("AGRESSIVO"));
                    break;
                case 1:
                    enemySoldado.setEstrategia(estrategias.get("NORMAL"));
                    break;
                default:
                    break;
            }

            //Procurando
            boolean achou = false;
            achou = mySoldado.observar();
            //Encontrado
            boolean encontrado = false;
            encontrado = enemySoldado.observar();

            if(achou) {
                System.out.println("\nVocê avista um soldado inimigo! "); 
                mySoldado.agir();

                //Se achou enquanto agressivo
                if(mySoldado.getEstrategia() instanceof AgressivoStrategy) {
                    System.out.println("\n\nVocê avista primeiro o inimigo e o derrota! Parabens!");  
                }
                //Se achou enquanto normal
                if(mySoldado.getEstrategia() instanceof NormalStrategy) {
                    if (encontrado) {
                        if(enemySoldado.getEstrategia() instanceof NormalStrategy) {
                            System.out.println("\n\nComo ambos se avistaram o combate termina na derrota de ambos!");
                        }
                        System.out.println("\n\nVocê foi avistado primeiro! Você Perdeu!");
                    } else {
                        System.out.println("\n\nVocê avista primeiro o inimigo e o derrota! Parabens!");  
                    }
                }
            } else {
                System.out.println("\nVocê não encontrou ninguem.");
                if(encontrado) {
                    System.out.println("\n\nVocê foi avistado e não vê de onde o inimigo veio! Por isso, você perdeu!");
                } else {
                    System.out.println("\n\nNada aconteceu.");
                }
            }

            System.out.println("\nContinuar? (1) Sim (2) Não");
            num = sc.nextInt();
            if(num == 2) {
                fim = true;
            }
            sc.nextLine();
        }
    }
}
