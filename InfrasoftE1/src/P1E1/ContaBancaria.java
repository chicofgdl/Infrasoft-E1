package P1E1;

import java.util.concurrent.locks.ReentrantLock;

public class ContaBancaria {
    private double saldo;
    private ReentrantLock lock = new ReentrantLock();

    public ContaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        lock.lock();
        try {
            saldo += valor;
            System.out.println(Thread.currentThread().getName() + " depositou " + valor + ". Saldo atual: " + saldo);
        } finally {
            lock.unlock();
        }
    }

    public void sacar(double valor) {
        lock.lock();
        try {
            if (saldo >= valor) {
                saldo -= valor;
                System.out.println(Thread.currentThread().getName() + " sacou " + valor + ". Saldo atual: " + saldo);
            } else {
                System.out.println(Thread.currentThread().getName() + " tentou sacar " + valor + " mas não tinha saldo suficiente. Saldo atual: " + saldo);
            }
        } finally {
            lock.unlock();
        }
    }

    public double getSaldo() {
        return saldo;
    }
}
