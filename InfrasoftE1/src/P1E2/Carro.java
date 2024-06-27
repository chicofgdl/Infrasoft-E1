package P1E2;

public class Carro implements Runnable {
    private final Ponte ponte;
    private final String direcao;
    private final String nomeCarro;

    public Carro(Ponte ponte, String direcao, String nomeCarro) {
        this.ponte = ponte;
        this.direcao = direcao;
        this.nomeCarro = nomeCarro;
    }

    @Override
    public void run() {
        ponte.atravessar(direcao, nomeCarro);
    }
}
