package P6;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banheiro {
    private final Semaphore semaforoCapacidadeBanheiro; // controla as vagas do banheiro
    private final Lock lock = new ReentrantLock(); // controla o acesso ao banheiro
    private int homensNoBanheiro = 0;
    private int mulheresNoBanheiro = 0;
    private String generoAtual = ""; // gênero atual que está no banheiro

    public Banheiro(int capacidade) {
        this.semaforoCapacidadeBanheiro = new Semaphore(capacidade);
    }

    public void entrar(String genero) throws InterruptedException {
        lock.lock(); // para garantir que só entre uma pessoa por vez no banheiro (regiao critica)
        try {
            // aguarda enquanto o banheiro tá cheio ou se o gênero atual não é o da pessoa
            while ((!generoAtual.equals("") && (!generoAtual.equals(genero))) || (semaforoCapacidadeBanheiro.availablePermits() == 0)) {
                lock.unlock();
                Thread.sleep(100); // aguarda pra tentar entrar de novo
                lock.lock();
            }

            if (generoAtual.equals("")) {
                generoAtual = genero;
            }

            semaforoCapacidadeBanheiro.acquire();
            if (genero.equals("Homem")) {
                homensNoBanheiro++;
            } else {
                mulheresNoBanheiro++;
            }
            // texto de entrada no banheiro que usa ternario para imprimir a quantidade de homens ou mulheres no banheiro
            System.out.println(Thread.currentThread().getName() + " entrou no banheiro. (Pessoas no banheiro: " + (genero.equals("Homem") ? homensNoBanheiro : mulheresNoBanheiro) + ")");
        } finally {
            lock.unlock();
        }
    }

    public void sair(String genero) {
        lock.lock();
        try {
            semaforoCapacidadeBanheiro.release(); // quando sair do banheiro libera uma vaga do semaforo
            if (genero.equals("Homem")) {
                homensNoBanheiro--;
                if (homensNoBanheiro == 0) {
                    generoAtual = ""; // reseta o gênero do banheiro se não tiver ninguém
                }
            } else {
                mulheresNoBanheiro--;
                if (mulheresNoBanheiro == 0) {
                    generoAtual = ""; // reseta o gênero do banheiro se não tiver ninguém
                }
            }
            System.out.println(Thread.currentThread().getName() + " saiu do banheiro. (Pessoas no banheiro: " + (genero.equals("Homem") ? homensNoBanheiro : mulheresNoBanheiro) + ")");
        } finally {
            lock.unlock();
        }
    }
}
