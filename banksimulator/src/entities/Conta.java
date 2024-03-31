package entities;

import services.OperacoesService;

public class Conta extends OperacoesService {

    private String nomeDoTitular;
    private int numeroDaConta;
    private double saldo;

    public Conta (String nomeDoTitular, int numeroDaConta, double saldo) {
        super();
       this.nomeDoTitular = nomeDoTitular;
       this.numeroDaConta = numeroDaConta;
       this.saldo = saldo;
    }
    public Conta(){
        super();
    }

    public String getNomeDoTitular() {
        return nomeDoTitular;
    }

    public void setNomeDoTitular(String nomeDoTitular) {
        this.nomeDoTitular = nomeDoTitular;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
