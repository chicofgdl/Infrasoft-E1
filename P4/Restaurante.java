package P4;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Restaurante {
    private final int quantidadeLugares;
    private final Semaphore semMesa;
    private final Semaphore semFila = new Semaphore(0);
    private final Semaphore mutex = new Semaphore(1);
    private int clientesNaFila = 0;
    private int clientesJantando = 0;

    public Restaurante(int quantidadeLugares) {
        this.quantidadeLugares = quantidadeLugares;
        this.semMesa = new Semaphore(quantidadeLugares);
    }

    public void entrarRestaurante(String nomeCliente) throws InterruptedException {
        mutex.acquire();
        if (clientesJantando == quantidadeLugares) {
            mutex.release();
            aguardarClientesSairem();
        } else {
            clientesNaFila++;
            if (clientesNaFila > 1) {
                mutex.release();
                semFila.acquire();
            } else {
                mutex.release();
            }

            semMesa.acquire();

            mutex.acquire();
            clientesNaFila--;
            clientesJantando++;
            if (clientesNaFila > 0) {
                semFila.release();
            }
            mutex.release();

            System.out.println(nomeCliente + " está jantando.");
            mutex.acquire();
            int tempoJantar = new Random().nextInt(5000) + 1000; // 1 a 6 seg
            mutex.release();
            Thread.sleep(tempoJantar);

            mutex.acquire();
            clientesJantando--;
            if (clientesJantando == 0 && clientesNaFila > 0) {
                semFila.release();
            }
            mutex.release();

            semMesa.release();
            System.out.println(nomeCliente + " terminou de jantar.");
        }
    }

    private void aguardarClientesSairem() throws InterruptedException {
        mutex.acquire();
        while (clientesJantando > 0) {
            mutex.release();
            Thread.sleep(500);
            mutex.acquire();
        }
        semMesa.acquire(quantidadeLugares);
        mutex.release();
    }
}
