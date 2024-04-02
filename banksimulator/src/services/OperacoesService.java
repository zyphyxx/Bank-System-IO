package services;

import entities.Conta;
import services.interfaces.OperacoesInter;
import utils.Collor;
import utils.Mensagem;
import utils.Scanner;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OperacoesService implements OperacoesInter {


    Scanner scanner = Scanner.iniciarLeitor();
    Mensagem mensagem = Mensagem.iniciarMSG();


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
    public void depositar(BigDecimal valorDeposito) {
        // VERIFICANDO SE A ENTRADA FOR NULA OU SE FOR NEGATIVO LANÇAR UMA EXCEPTION
        if (valorDeposito == null) {
            System.out.println("Valor do depósito não pode ser nulo.");

        } else if (valorDeposito.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("\"Número negativo não é permitido.\"");
        } else {

            try {
                // ADICIONA O VALOR NA CONTA
                conta.setSaldo(conta.getSaldo().add(valorDeposito));
                // ADICIONA NO HISTORICO
                historicoDeposito.add(valorDeposito);
                // MENSAGEM
                mensagem.depositarStatus(valorDeposito);

            } catch (Exception e) {
                throw new RuntimeException("Erro ao depositar valor: " + valorDeposito);
            }
        }

    }

    @Override
    public void sacar(BigDecimal valorSaque) {
        // VERIFICANDO SE A ENTRADA FOR NULA OU SE FOR NEGATIVO E SE O TEM SALDO PARA SAQUE
        if (valorSaque == null) {
            System.out.println("Valor do saque não pode ser nulo.");
        } else if (conta.getSaldo().compareTo(valorSaque) < 0) {
            historicoSaqueRecusado.add(" - Recusado");
            System.out.println(Collor.RED + "Saldo insuficiente para sacar " + valorSaque + " R$. Operação cancelada." + Collor.RESET);
        } else if (valorSaque.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("valor nao pode ser negativo");
        } else {

            try {
                // FAZ A SUBTRAÇÃO DO SALDO
                conta.setSaldo(conta.getSaldo().subtract(valorSaque));
                // ADICIONA NO HISTORICO
                historicoSaqueRecusado.add(" - Aprovado");
                // MENSAGEM
                System.out.println(Collor.GREEN + "Saque de " + valorSaque + " R$ realizado com sucesso." + Collor.RESET);
                System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
                // LIMPA O BUFFER
                scanner.string();

            } catch (Exception e) {
                throw new RuntimeException("Erro ao efetuar o saque");
            }
        }
    }

    @Override
    public void transferir(int numDaConta, BigDecimal valor) {
        BigDecimal saldo = conta.getSaldo();

        if (saldo.compareTo(valor) < 0) {
            System.out.println(Collor.RED + "Saldo insuficiente para transferir " + valor + " R$ para a conta " + numDaConta + "." + Collor.RESET);
            historicoTransferenciaRecusado.add(" R$ -- RECUSADO");
            scanner.string();
        } else {
            conta.setSaldo(saldo.subtract(valor));
            System.out.println(Collor.GREEN + "Transferência de " + valor + " R$ para a conta " + numDaConta + " realizada com sucesso." + Collor.RESET);
            System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
            historicoTransferencia.add(valor);
            historicoTransferenciaRecusado.add(" R$ -- APROVADO");
            scanner.string();
        }
    }

    @Override
    public void historico() {

        System.out.println("\n=== HISTÓRICO BANCÁRIO ===");

        System.out.println("\n" + Collor.GREEN + "Histórico de depósitos:" + Collor.RESET);
        for (int i = 0; i < historicoDeposito.size(); i++) {
            System.out.println((i + 1) + " - " + historicoDeposito.get(i) + " R$");
        }

        System.out.println("\n" + Collor.RED + "Histórico de saques:" + Collor.RESET);
        for (int i = 0; i < (historicoSaques.size() & historicoSaqueRecusado.size()); i++) {

            System.out.println((i + 1) + " - " + historicoSaques.get(i) + " " + historicoSaqueRecusado.get(i));

        }

        System.out.println("\n" + Collor.BLUE + "Histórico de transferências:" + Collor.RESET);

        for (int i = 0; i < (historicoTransferencia.size() & historicoTransferenciaRecusado.size()); i++) {

            System.out.println((i + 1) + " - " + historicoTransferencia.get(i) + " " + historicoTransferenciaRecusado.get(i));

        }

        scanner.string();

    }
}
