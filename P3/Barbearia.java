package P3;

import java.util.concurrent.Semaphore;

public class Barbearia {
    private final int quantidadeCadeiras;
    private final Semaphore semaforoClientes;
    private final Semaphore semaforoBarbeiro = new Semaphore(1);
    private final Semaphore mutex = new Semaphore(1);
    private final Barbeiro barbeiro;

    public Barbearia(int quantidadeCadeiras) {
        this.quantidadeCadeiras = quantidadeCadeiras;
        this.semaforoClientes = new Semaphore(quantidadeCadeiras);
        this.barbeiro = new Barbeiro(this);
    }

    public void cortarCabelo(String nome) throws InterruptedException {
        if (!barbeiro.isCortando()) {
            barbeiro.acordarBarbeiro();
        }
        System.out.println(nome + " está cortando o cabelo!");
        Thread.sleep(3000);
        System.out.println("->>>> " + nome + " terminou de cortar o cabelo!");

        mutex.acquire();
        if (semaforoClientes.availablePermits() == quantidadeCadeiras) {
            barbeiro.cochilarBarbeiro();
        }
        mutex.release();
        semaforoBarbeiro.release();
    }

    public void entrarBarbearia(String nome) {
        try {
            if (semaforoClientes.tryAcquire()) {
                System.out.println(nome + " entrou na barbearia e está esperando.");

                semaforoBarbeiro.acquire();
                cortarCabelo(nome);

                semaforoClientes.release();
            } else {
                System.out.println(nome + " foi embora porque a barbearia estava cheia!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
