package P5;

public class Passageiro extends Thread {
    private final Onibus onibus;
    // O passageiro vai estar associado a um ônibus e também vai receber um nome
    public Passageiro(Onibus onibus, String nome) {
        super(nome); // associa o nome à thread
        this.onibus = onibus; // faz referencia ao onibus
    }

    @Override
    public void run() {
        try {
            onibus.entrar(); // metodo para o passageiro tentar entrar no onibus
        } catch (InterruptedException e) {
            e.printStackTrace(); // exceção para caso tenha interrupção na thread
        }
    }
}
