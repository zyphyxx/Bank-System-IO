package model;

import model.interfaces.OperacoesInter;

public class Operacoes implements OperacoesInter {

    private Conta conta;

    public Operacoes (Conta conta) {
        super();
        this.conta = conta;
    }
    public Operacoes(){
        super();
    }

    @Override
    public double depositar(double valor) {

        double saldo = conta.getSaldo();
        conta.setSaldo(saldo + valor);
        return conta.getSaldo();
    }

    @Override
    public void sacar() {

    }

    @Override
    public void transfererir() {

    }
}
