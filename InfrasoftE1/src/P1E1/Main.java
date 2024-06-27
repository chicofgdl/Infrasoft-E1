package P1E1;

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(1000);

        Thread cliente1 = new Thread(new Cliente(conta, true, 200), "Cliente 1");
        Thread cliente2 = new Thread(new Cliente(conta, false, 150), "Cliente 2");
        Thread cliente3 = new Thread(new Cliente(conta, false, 100), "Cliente 3");
        Thread cliente4 = new Thread(new Cliente(conta, true, 50), "Cliente 4");

        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();

        try {
            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldo final: " + conta.getSaldo());
    }
}
