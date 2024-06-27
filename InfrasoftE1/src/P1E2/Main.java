package P1E2;

public class Main {
    public static void main(String[] args) {
        Ponte ponte = new Ponte();

        Thread carro1 = new Thread(new Carro(ponte, "esquerda", "Carro 1"));
        Thread carro2 = new Thread(new Carro(ponte, "direita", "Carro 2"));
        Thread carro3 = new Thread(new Carro(ponte, "esquerda", "Carro 3"));
        Thread carro4 = new Thread(new Carro(ponte, "direita", "Carro 4"));

        carro1.start();
        carro2.start();
        carro3.start();
        carro4.start();

        try {
            carro1.join();
            carro2.join();
            carro3.join();
            carro4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
