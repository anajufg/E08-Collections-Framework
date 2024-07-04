public class ContaUniversitaria extends Conta {
    public ContaUniversitaria(int numero, Cliente dono, double saldo, double limite) {
        super(numero, dono, saldo, limite);
    }

    @Override
    public boolean setLimite(double limite){
        if (limite < 0 || limite > 500) {
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
        return 0;
    }
}
