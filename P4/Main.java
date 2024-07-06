package P4;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante(5);

        for (int i = 0; i < 100; i++) {
            String nomeCliente = "Cliente " + (i + 1);
            new ClienteRestaurante(restaurante, nomeCliente).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
