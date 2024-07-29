package P6;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int capacidadeBanheiro = 3; // quantidade de threads maximas compartilhando o recurso
        final int numeroPessoas = 100; // quantidade de threads ao todo

        Banheiro banheiro = new Banheiro(capacidadeBanheiro); // cria instancia do banheiro

        String[] generos = {"Homem", "Mulher"};
        Random random = new Random();

        for (int i = 0; i < numeroPessoas; i++) {
            String genero = generos[random.nextInt(generos.length)];
            new Pessoa(banheiro, genero, genero + " " + (i + 1)).start();
            try {
                Thread.sleep(random.nextInt(1000)); // intervalo da chegada das pessoas
            } catch (InterruptedException e) {
                e.printStackTrace(); // exceção de interrupção
            }
        }
    }
}
