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
    ArrayList<BigDecimal> listaHistorico = new ArrayList<>();


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
                listaHistorico.add(valorDeposito);
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

            System.out.println(Collor.RED + "Saldo insuficiente para sacar " + valorSaque + " R$. Operação cancelada." + Collor.RESET);
        } else if (valorSaque.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("valor nao pode ser negativo");
        } else {

            try {
                // FAZ A SUBTRAÇÃO DO SALDO
                conta.setSaldo(conta.getSaldo().subtract(valorSaque));
                // ADICIONA NO HISTORICO
                listaHistorico.add(valorSaque);
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
    public void transferir(int numDaConta, BigDecimal valorTransferencia) {
        BigDecimal saldo = conta.getSaldo();

        // VERIFICANDO SE A ENTRADA FOR NULA OU SE FOR NEGATIVO E SE O TEM SALDO PARA TRANSFERENCIA
        if (valorTransferencia == null) {
            System.out.println("Valor do transferencia não pode ser nulo.");
        } else if (valorTransferencia.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println(Collor.RED + "Saldo insuficiente para transferencia " + valorTransferencia+ " R$. Operação cancelada." + Collor.RESET);
        } else if (saldo.compareTo(valorTransferencia) < 0) {
            System.out.println(Collor.RED + "Saldo insuficiente para transferir " + valorTransferencia + " R$ para a conta " + numDaConta + "." + Collor.RESET);

            scanner.string();
        } else {
            try {
                // FAZ A SUBTRAÇÃO DO SALDO
                conta.setSaldo(saldo.subtract(valorTransferencia));
                // ADICIONA NO HISTORICO
                listaHistorico.add(valorTransferencia);
                // MENSAGEM
                System.out.println(Collor.GREEN + "Transferência de " + valorTransferencia + " R$ para a conta " + numDaConta + " realizada com sucesso." + Collor.RESET);
                System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
                // LIMPA O BUFFER
                scanner.string();
            } catch (Exception e){
                throw new RuntimeException("Erro ao transferir");
            }

        }
    }

    @Override
    public void historico() {

        System.out.println("\n=== HISTÓRICO BANCÁRIO ===");
        for (int i = 0; i < listaHistorico.size(); i++){
            System.out.println(listaHistorico.get(i));
        }
        scanner.string();

    }
}
