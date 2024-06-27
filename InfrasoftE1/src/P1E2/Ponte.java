package P1E2;

import java.util.concurrent.locks.ReentrantLock;

public class Ponte {
    private final ReentrantLock lock = new ReentrantLock();

    public void atravessar(String direcao, String nomeCarro) {
        lock.lock();
        try {
            System.out.println(nomeCarro + " está atravessando a ponte na direção " + direcao);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(nomeCarro + " saiu da ponte.");
            lock.unlock();
        }
    }
}
