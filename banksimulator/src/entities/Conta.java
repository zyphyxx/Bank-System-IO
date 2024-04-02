package entities;

import services.OperacoesService;

import java.math.BigDecimal;

public class Conta extends OperacoesService {

    private String nomeDoTitular;
    private int numeroDaConta;
    private BigDecimal saldo;

    public Conta(){
        super();
        this.saldo = BigDecimal.ZERO;
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
