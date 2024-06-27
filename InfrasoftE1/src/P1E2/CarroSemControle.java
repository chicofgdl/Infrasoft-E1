package P1E2;

public class CarroSemControle implements Runnable {
    private final PonteSemControle ponte;
    private final String direcao;
    private final String nomeCarro;

    public CarroSemControle(PonteSemControle ponte, String direcao, String nomeCarro) {
        this.ponte = ponte;
        this.direcao = direcao;
        this.nomeCarro = nomeCarro;
    }

    @Override
    public void run() {
        ponte.atravessar(direcao, nomeCarro);
    }
}
