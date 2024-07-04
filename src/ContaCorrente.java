public class ContaCorrente extends Conta {

    public ContaCorrente(int numero, Cliente dono, double saldo, double limite) {
        super(numero, dono, saldo, limite);
    }

    @Override
    public boolean setLimite(double limite){
        if (limite < -100) {
            this.limite = 0;
            System.out.println("<Limite inválido>");
            return false;
        } else {
            this.limite = limite;
            return true;
        }
    }

    @Override
    public double calculaTaxas() {
        if(getDono() instanceof PessoaFisica) {
            return 10;
        } else {
            return 20;
        }
    }
}
