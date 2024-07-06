package P3;

import java.util.concurrent.Semaphore;

public class Barbearia {
    private final int quantidadeCadeiras;
    private int clientesEsperando = 0;
    private final Semaphore semaforoClientes = new Semaphore(0);
    private final Semaphore semaforoBarbeiro = new Semaphore(1);
    private final Semaphore mutex = new Semaphore(1);
    private final Barbeiro barbeiro;

    public Barbearia(int quantidadeCadeiras) {
        this.quantidadeCadeiras = quantidadeCadeiras;
        this.barbeiro = new Barbeiro(this);
    }

    public void cortarCabelo(String nome) throws InterruptedException {
        if (!barbeiro.isCortando()){
            barbeiro.acordarBarbeiro();}
        System.out.println(nome + " está cortando o cabelo!");
        Thread.sleep(3000);
        System.out.println("->>>> " + nome + " terminou de cortar o cabelo!");

        mutex.acquire();
        if (clientesEsperando == 0) {
            barbeiro.cochilarBarbeiro();
        }
        mutex.release();
        semaforoBarbeiro.release();
    }

    public void entrarBarbearia(String nome) {
        try {
            mutex.acquire();

            if (clientesEsperando < quantidadeCadeiras) {
                clientesEsperando++;
                System.out.println(nome + " entrou na barbearia e está esperando.");
                if (clientesEsperando == 1) {
                    System.out.println("Há " + clientesEsperando + " cliente esperando.");
                } else {
                    System.out.println("Há " + clientesEsperando + " clientes esperando.");
                }
                semaforoClientes.release();
                mutex.release();
                semaforoBarbeiro.acquire();
                mutex.acquire();
                clientesEsperando--;
                mutex.release();
                cortarCabelo(nome);
            } else {
                System.out.println(nome + " foi embora porque a barbearia estava cheia!");
                mutex.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
