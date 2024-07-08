package P3;

public class Main {
    public static void main(String[] args) {
        Barbearia barbearia = new Barbearia(5);

        for (int i = 1; i <= 100; i++) {
            String nomeCliente = "Cliente " + i;
            new Cliente(barbearia, nomeCliente).start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}