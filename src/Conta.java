import java.util.LinkedList;

abstract class Conta implements ITaxas{

    private int numero;

    private Cliente dono;

    private double saldo;

    protected double limite;

    private LinkedList<Operacao> operacoes;

    private int numOperacoes;

    private static int totalContas = 0;

    public Conta(int numero, Cliente dono, double saldo, double limite) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;
        this.limite = limite;

        this.operacoes = new LinkedList<Operacao>();
        this.numOperacoes = 0;

        Conta.totalContas++;
    }

    public boolean depositar(double valor) {
        if (this.numOperacoes < 1000) {

            this.operacoes.add(numOperacoes, new OperacaoDeposito(valor));
            this.numOperacoes++;
            this.saldo += valor;
            return true;

        } else {
            System.out.println("<Numero máximo de operações atingido>");
            return false;
        }
    }

    public boolean sacar(double valor) {
        if (this.numOperacoes < 1000) {

            if (valor > 0.0 && valor <= this.limite) {
                this.operacoes.add(numOperacoes, new OperacaoSaque(valor));
                this.numOperacoes++;
                this.saldo -= valor;
                return true;
            } else {
                return false;
            }

        } else {
            System.out.println("<Numero máximo de operações atingido>");
            return false;
        }
    }

    public boolean transferir(Conta contaDestino, double valor) {
        boolean saqueRealizado = this.sacar(valor);

        if (saqueRealizado) {
            boolean depositoRealizado = contaDestino.depositar(valor);
            if (depositoRealizado) {
                return true;
            } else {
                System.out.print("<Não foi possível realizar a transferência>");
                this.depositar(valor);
                return false;
            }
        } else {
            System.out.print("<Não foi possível realizar a transferência>");
            return false;
        }
    }

    public String toString() {
        String str = "=========== Conta " + this.numero + " ===========\n" +
                this.dono.toString() + "\n" +
                "Saldo: " + this.saldo + "\n" +
                "Limite: " + this.limite + "\n" +
                "===================================\n";
        return str;
    }

    public boolean equals(Object obj) {
        if(obj instanceof Conta) {
            Conta objConta = (Conta) obj;

            if(this.numero == objConta.numero) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void imprimirExtrato() {
        System.out.println("=========== Extrato Conta " + this.numero + " ===========");
        for(Operacao atual : this.operacoes) {
            if (atual != null) {
                System.out.println(atual.toString());
            }
        }
        System.out.println("==========================================\n");
    }

    public void imprimirExtratoTaxas() {
        double total = 0;

        System.out.printf("====== Extrato de Taxas Conta " + this.numero + " ======\n" +
                           "Manutenção da conta: %.2f \n\n" +
                           "OPERAÇÕES\n", calculaTaxas());
        total += calculaTaxas();
        for(Operacao atual : this.operacoes) {
            if (atual != null) {
                if(atual instanceof OperacaoSaque) {
                    System.out.printf("Saque: %.2f\n", atual.calculaTaxas());
                    total += atual.calculaTaxas();
                }
            }
        }
        System.out.printf("\nTotal: %.2f \n==========================================\n", total);
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public static int getTotalContas() {
        return Conta.totalContas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    // Set limite
    public abstract boolean setLimite(double limite);
}