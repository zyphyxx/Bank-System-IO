package services;

import entities.Conta;
import services.interfaces.OperacoesInter;
import view.CoresANSI;

import java.util.ArrayList;
import java.util.Scanner;

public class OperacoesService implements OperacoesInter {

    Scanner sc = new Scanner(System.in);

    // LISTA PARA HISTORICO DE TRANSFERENCIA
    ArrayList<Double> historicoDeposito = new ArrayList<>();
    ArrayList<Double> historicoSaques = new ArrayList<>();
    ArrayList<Double> historicoTransferencia = new ArrayList<>();

    ArrayList<String> historicoSaqueRecusado = new ArrayList<>();
    ArrayList<String> historicoTransferenciaRecusado = new ArrayList<>();


    private Conta conta;

    public OperacoesService(Conta conta) {
        super();
        this.conta = conta;
    }

    public OperacoesService() {
        super();
    }

    @Override
    public double depositar(double valor) {
        double saldo = conta.getSaldo();
        conta.setSaldo(saldo + valor);

        System.out.println(CoresANSI.GREEN + "Depósito de " + valor + " R$ realizado com sucesso." + CoresANSI.RESET);
        System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
        sc.nextLine();

        historicoDeposito.add(valor);
        return saldo;
    }

    @Override
    public double sacar(double valor) {
        double saldo = conta.getSaldo();

        if (valor > saldo) {
            System.out.println(CoresANSI.RED + "Saldo insuficiente para sacar " + valor + " R$. Operação cancelada." + CoresANSI.RESET);
            historicoSaqueRecusado.add(" R$ -- RECUSADO FALTA DE SALDO");
            sc.nextLine();
        } else {
            conta.setSaldo(saldo - valor);
            System.out.println(CoresANSI.GREEN + "Saque de " + valor + " R$ realizado com sucesso." + CoresANSI.RESET);
            System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
            historicoSaqueRecusado.add(" R$ -- APROVADO");
            sc.nextLine();
        }

        historicoSaques.add(valor);
        return saldo;
    }

    @Override
    public void transferir(int numDaConta, double valor) {
        double saldo = conta.getSaldo();

        if (valor > saldo) {
            System.out.println(CoresANSI.RED + "Saldo insuficiente para transferir " + valor + " R$ para a conta " + numDaConta + "." + CoresANSI.RESET);
            historicoTransferenciaRecusado.add(" R$ -- RECUSADO");
            sc.nextLine();
        } else {
            conta.setSaldo(saldo - valor);
            System.out.println(CoresANSI.GREEN + "Transferência de " + valor + " R$ para a conta " + numDaConta + " realizada com sucesso." + CoresANSI.RESET);
            System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
            historicoTransferencia.add(valor);
            historicoTransferenciaRecusado.add(" R$ -- APROVADO");
            sc.nextLine();
        }
    }

    @Override
    public void historico() {

        System.out.println("\n=== HISTÓRICO BANCÁRIO ===");

        System.out.println("\n" + CoresANSI.GREEN + "Histórico de depósitos:" + CoresANSI.RESET);
        for (int i = 0; i < historicoDeposito.size(); i++) {
            System.out.println((i + 1) + " - " + historicoDeposito.get(i) + " R$");
        }

        System.out.println("\n" + CoresANSI.RED + "Histórico de saques:" + CoresANSI.RESET);
        for (int i = 0; i < (historicoSaques.size() & historicoSaqueRecusado.size()); i++) {

                System.out.println((i + 1) + " - " + historicoSaques.get(i)+ " " + historicoSaqueRecusado.get(i));

        }

        System.out.println("\n" + CoresANSI.BLUE + "Histórico de transferências:" + CoresANSI.RESET);

        for (int i = 0; i < (historicoTransferencia.size() & historicoTransferenciaRecusado.size()); i++) {

            System.out.println((i + 1) + " - " + historicoTransferencia.get(i) + " " + historicoTransferenciaRecusado.get(i));

        }

        sc.nextLine();

    }
}
