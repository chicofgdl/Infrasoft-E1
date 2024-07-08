package P3;

public class Barbeiro {
    private final Barbearia barbearia;
    private boolean cortando = false;

    public Barbeiro(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    public void acordarBarbeiro() {
        System.out.println("O barbeiro acordou");
        cortando = true;
    }

    public void cochilarBarbeiro() {
        System.out.println("O barbeiro está dormindo");
        cortando = false;
    }

    public boolean isCortando() {
        return cortando;
    }
}