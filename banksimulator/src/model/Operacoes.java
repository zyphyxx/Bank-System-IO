package model;

import model.interfaces.OperacoesInter;
import view.CoresANSI;

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

        System.out.println(CoresANSI.GREEN + "Depósito de " + valor + " R$ realizado com sucesso." + CoresANSI.RESET);
        System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
        sc.nextLine();

        return saldo;
    }

    @Override
    public double sacar(double valor) {
        double saldo = conta.getSaldo();

        if (valor > saldo) {
            System.out.println(CoresANSI.RED + "Saldo insuficiente para sacar " + valor + " R$. Operação cancelada." + CoresANSI.RESET);
            sc.nextLine();
        } else {
            conta.setSaldo(saldo - valor);
            System.out.println(CoresANSI.GREEN + "Saque de " + valor + " R$ realizado com sucesso." + CoresANSI.RESET);
            System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
            sc.nextLine();
        }

        return saldo;
    }

    @Override
    public void transferir(int numDaConta, double valor) {
        double saldo = conta.getSaldo();

        if (valor > saldo) {
            System.out.println(CoresANSI.RED + "Saldo insuficiente para transferir " + valor + " R$ para a conta " + numDaConta + "." + CoresANSI.RESET);
            sc.nextLine();
        } else {
            conta.setSaldo(saldo - valor);
            System.out.println(CoresANSI.GREEN + "Transferência de " + valor + " R$ para a conta " + numDaConta + " realizada com sucesso." + CoresANSI.RESET);
            System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
            sc.nextLine();
        }
    }
}
