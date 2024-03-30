package model;

import model.interfaces.OperacoesInter;

import java.util.Scanner;

public class Operacoes implements OperacoesInter {

    Scanner sc = new Scanner(System.in);

    private Conta conta;

    public Operacoes(Conta conta) {
        super();
        this.conta = conta;
    }

    public Operacoes() {
        super();
    }

    @Override
    public double depositar(double valor) {

        double saldo = conta.getSaldo();
        conta.setSaldo(saldo + valor);

        System.out.println("Deposito no valor de: " + valor + " R$.\n" +
                "Efetuado com sucesso ;D");
        sc.nextLine();

        return saldo;
    }

    @Override
    public double sacar(double valor) {

        double saldo = conta.getSaldo();

        if (valor > saldo) {
            System.out.println("Voce não possui saldo para efetuar o saque\n");
            sc.nextLine();
        } else {
            conta.setSaldo(saldo - valor);

            System.out.println("Saque no valor de: " + valor + " R$.\n" +
                    "Efetuado com sucesso ;D");
            sc.nextLine();

        }

        return saldo;
    }

    @Override
    public void transfererir(int numDaConta, double valor) {
        double saldo = conta.getSaldo();

       if (valor > saldo){
           System.out.println("Voce não possui saldo para efetuar a trasnferencia\n");
           sc.nextLine();
       } else {
           conta.setSaldo(saldo - valor);

           System.out.println("Transferencia no valor de: " + valor + " R$.\n" +
                   "Para o usuario da conta: "+ numDaConta +
                   "\nEfetuado com sucesso ;D");
           sc.nextLine();

       }

    }
}
