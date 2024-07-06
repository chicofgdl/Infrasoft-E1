package P4;

class ClienteRestaurante extends Thread {
    private final Restaurante restaurante;
    private final String nome;

    public ClienteRestaurante(Restaurante restaurante, String nome) {
        this.restaurante = restaurante;
        this.nome = nome;
    }

    @Override
    public void run() {
        try {
            restaurante.entrarRestaurante(nome);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
