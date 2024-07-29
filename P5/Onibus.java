package P5;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Onibus extends Thread {
    private final Semaphore semaforoVagasDisponiveis; // semaforo para ditar as vagas do ônibus
    private final Semaphore semaforoOnibusNaParada = new Semaphore(0); // semaforo binário que permite ou não os passageiros entrarem no ônibus
    private final int capacidade;
    private final Random random = new Random(); // gera numero aleatório

    public Onibus(int capacidade) {
        this.capacidade = capacidade;
        this.semaforoVagasDisponiveis = new Semaphore(capacidade);
    }

    public void pararNaParada() throws InterruptedException {
        System.out.println("O ônibus chegou na parada.");
        semaforoOnibusNaParada.release(capacidade);
        Thread.sleep((random.nextInt(3) + 1) * 1000); // o tempo que o ônibus fica na parada é entre 1 a 3 segundos
        System.out.println("Ônibus saiu da parada com " + ( capacidade - semaforoVagasDisponiveis.availablePermits()) + " passageiros." );
        semaforoOnibusNaParada.drainPermits(); // esvazia o semáforo para sinalizar que o ônibus partiu
        semaforoVagasDisponiveis.release(capacidade - semaforoVagasDisponiveis.availablePermits()); // reseta os assentos disponíveis
    }

    public void entrar() throws InterruptedException {
        semaforoOnibusNaParada.acquire();
        if (semaforoVagasDisponiveis.tryAcquire()) {
            System.out.println(Thread.currentThread().getName() + " entrou no ônibus.");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                pararNaParada();
                Thread.sleep(5000); // tempo entre a saída e a chegada do próximo ônibus
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); // exceção para caso tenha interrupção na thread
        }
    }
}
