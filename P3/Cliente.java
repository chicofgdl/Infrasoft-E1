package P3;

public class Cliente extends Thread {
    private final Barbearia barbearia;
    private final String nome;

    public Cliente(Barbearia barbearia, String nome) {
        this.barbearia = barbearia;
        this.nome = nome;
    }

    @Override
    public void run() {
        barbearia.entrarBarbearia(nome);
    }
}
