package P1E2;

public class PonteSemControle {
    public void atravessar(String direcao, String nomeCarro) {
        System.out.println(nomeCarro + " está atravessando a ponte na direção " + direcao);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nomeCarro + " saiu da ponte.");
    }
}
