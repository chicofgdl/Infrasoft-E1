package P6;

public class Pessoa extends Thread {
    private final Banheiro banheiro;
    private final String genero;

    public Pessoa(Banheiro banheiro, String genero, String nome) {
        super(nome); // nome da thread
        this.banheiro = banheiro;
        this.genero = genero;
    }

    @Override
    public void run() {
        try {
            banheiro.entrar(genero);
            Thread.sleep((int) (Math.random() * 1000)); // tempo dentro do banheiro
            banheiro.sair(genero);
        } catch (InterruptedException e) {
            e.printStackTrace(); // exceção para caso tenha interrupção
        }
    }
}
