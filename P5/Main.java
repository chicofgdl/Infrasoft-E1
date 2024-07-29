package P5;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int capacidadeOnibus = 50; // quantidade de threads que vão poder ocupar o ônibus
        final int numeroPassageiros = 100; // quantidade de threads ao todo

        Onibus onibus = new Onibus(capacidadeOnibus); // cria o recurso que vai ser compartilhado pelas Threads
        onibus.start(); // chama o metodo run

        for (int i = 0; i < numeroPassageiros; i++) {
            // cria as Threads e nomeia de "Passageiro 1" até "Passageiro 100"
            new Passageiro(onibus, "Passageiro " + (i + 1)).start();
            try {
                Thread.sleep(new Random().nextInt(100)); // intervalo aleatorio de 0 a 499ms para a chegada dos passageiros na parada
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
