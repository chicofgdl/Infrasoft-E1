package P1E1;

public class Cliente implements Runnable {
    private ContaBancaria conta;
    private boolean isDeposit;
    private double valor;

    public Cliente(ContaBancaria conta, boolean isDeposit, double valor) {
        this.conta = conta;
        this.isDeposit = isDeposit;
        this.valor = valor;
    }

    @Override
    public void run() {
        if (isDeposit) {
            conta.depositar(valor);
        } else {
            conta.sacar(valor);
        }
    }
}
