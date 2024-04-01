package services;

import entities.Conta;
import services.interfaces.OperacoesInter;
import utils.LeitorDadosUsuario;
import view.CoresANSI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class OperacoesService implements OperacoesInter {


    LeitorDadosUsuario leitorDadosUsuario = LeitorDadosUsuario.iniciarLeitor();

    // LISTA PARA HISTORICO DE TRANSFERENCIA
    ArrayList<BigDecimal> historicoDeposito = new ArrayList<>();
    ArrayList<BigDecimal> historicoSaques = new ArrayList<>();
    ArrayList<BigDecimal> historicoTransferencia = new ArrayList<>();

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
    public BigDecimal depositar(BigDecimal valorDeposito) {

        if (valorDeposito.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("O valor é negativo.");

        }
        if (valorDeposito == null) {
            throw new IllegalArgumentException("Valor do depósito não pode ser nulo.");
        }
        try {
            conta.setSaldo(conta.getSaldo().add(valorDeposito));
            historicoDeposito.add(valorDeposito);
        } catch (Exception e) {
            System.out.println("Erro ao depositar valor: " + valorDeposito);
            throw e;
        }

        return conta.getSaldo();
    }

    @Override
    public BigDecimal sacar(BigDecimal valor) {
        BigDecimal saldo = conta.getSaldo();

        if (saldo.compareTo(valor) < 0) {
            System.out.println(CoresANSI.RED + "Saldo insuficiente para sacar " + valor + " R$. Operação cancelada." + CoresANSI.RESET);
            historicoSaqueRecusado.add(" R$ -- RECUSADO FALTA DE SALDO");
            leitorDadosUsuario.lerString();
        }
        if (valor == null) {
            throw new IllegalArgumentException("Valor do depósito não pode ser nulo.");
        }

            try {
                conta.setSaldo(saldo.subtract(valor));
                System.out.println(CoresANSI.GREEN + "Saque de " + valor + " R$ realizado com sucesso." + CoresANSI.RESET);
                System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
                historicoSaqueRecusado.add(" R$ -- APROVADO");
                historicoSaques.add(valor);
                leitorDadosUsuario.lerString();
            } catch (Exception e) {
                System.out.println("Erro para sacar");
                throw e;
            }

            return saldo;
    }

    @Override
    public void transferir(int numDaConta, BigDecimal valor) {
        BigDecimal saldo = conta.getSaldo();

        if ( saldo.compareTo(valor) < 0 ) {
            System.out.println(CoresANSI.RED + "Saldo insuficiente para transferir " + valor + " R$ para a conta " + numDaConta + "." + CoresANSI.RESET);
            historicoTransferenciaRecusado.add(" R$ -- RECUSADO");
            leitorDadosUsuario.lerString();
        } else {
            conta.setSaldo(saldo.subtract(valor));
            System.out.println(CoresANSI.GREEN + "Transferência de " + valor + " R$ para a conta " + numDaConta + " realizada com sucesso." + CoresANSI.RESET);
            System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
            historicoTransferencia.add(valor);
            historicoTransferenciaRecusado.add(" R$ -- APROVADO");
            leitorDadosUsuario.lerString();
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

        leitorDadosUsuario.lerString();

    }
}
