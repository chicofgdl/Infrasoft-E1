package P4;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Restaurante {
    private final int quantidadeLugares;
    private final Semaphore semaforoMesa;
    private final Lock lock = new ReentrantLock();
    private final Condition restauranteCheio = lock.newCondition();
    private int clientesJantando = 0;

    public Restaurante(int quantidadeLugares) {
        this.quantidadeLugares = quantidadeLugares;
        this.semaforoMesa = new Semaphore(quantidadeLugares);
    }

    public void entrarRestaurante(String nomeCliente) throws InterruptedException {
        System.out.println(nomeCliente + " entrou na fila do restaurante");

        lock.lock();
        try {
            while (clientesJantando == quantidadeLugares) { // todas as mesas ocupadas aguarda um sinal
                restauranteCheio.await();
            }

            semaforoMesa.acquire();
            clientesJantando++;
            System.out.println(nomeCliente + " entrou para jantar.");
            System.out.println(clientesJantando + " clientes estão jantando.");
        } finally {
            lock.unlock();
        }

        int tempoJantar = new Random().nextInt(5000) + 1000; // 1 a 6 segundos
        Thread.sleep(tempoJantar);

        lock.lock();
        try {
            clientesJantando--;
            semaforoMesa.release();
            System.out.println(nomeCliente + " terminou de jantar.");

            if (clientesJantando == 0) {
                restauranteCheio.signalAll(); // libera quando não tem mais clientes jantando
                System.out.println("Todas as mesas estão livres.");
            }
        } finally {
            lock.unlock();
        }
    }
}
