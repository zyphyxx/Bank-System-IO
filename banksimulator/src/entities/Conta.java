package entities;

import services.OperacoesService;

import java.math.BigDecimal;

public class Conta extends OperacoesService {

    private String nomeDoTitular;
    private int numeroDaConta;
    private BigDecimal saldo = BigDecimal.ZERO;

    public Conta (String nomeDoTitular, int numeroDaConta, BigDecimal saldo) {
        super();
       this.nomeDoTitular = nomeDoTitular;
       this.numeroDaConta = numeroDaConta;
       this.saldo = BigDecimal.ZERO;
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
