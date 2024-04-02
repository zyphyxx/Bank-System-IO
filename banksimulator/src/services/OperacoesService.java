package services;

import entities.Conta;
import services.interfaces.OperacoesInter;
import utils.Collor;
import utils.Mensagem;
import utils.Scanner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static view.MenuBanco.conta;

public class OperacoesService implements OperacoesInter {

    // INSTANCIANDO OS METODOS SCANNER E MENSAGEM
    Scanner scanner = Scanner.iniciarLeitor();
    Mensagem mensagem = Mensagem.iniciarMSG();
    // LISTA PARA HISTORICO DE TRANSFERENCIA
    private final ArrayList<BigDecimal> depositoHistorico = new ArrayList<>();
    private final ArrayList<BigDecimal> saqueHistorico = new ArrayList<>();
    private final ArrayList<BigDecimal> transfHistorico = new ArrayList<>();
    // VAR
    private Conta conta;
    // CONSTRUTOR
    public OperacoesService(Conta conta) {
        this.conta = conta;
    }
    public OperacoesService() {
        super();
    }

    public void nomeUsuario (){
        // MENSAGEM
        mensagem.nome();
        // ENTRADA DO NOME
        String nomeUsuario = scanner.string();
        conta.setNomeDoTitular(nomeUsuario);
    }

    // ARRUMAR O ERRO QUANDO O USUARIO ENTRA COM LETRAS
    public void numeroConta () {
        // MENSAGEM
        mensagem.conta();
        // ENTRADA DO NUMERO DA CONTA
        int numeroConta = Integer.parseInt(scanner.string());
        conta.setNumeroDaConta(numeroConta);
    }

    @Override
    public void depositar(BigDecimal valorDeposito) {
        // VERIFICANDO SE A ENTRADA FOR NULA OU SE FOR NEGATIVO LANÇAR UMA EXCEPTION
        if (valorDeposito == null) {
                mensagem.numeroNulo();
        } else if (valorDeposito.compareTo(BigDecimal.ZERO) < 0) {
            mensagem.numeroNegativo();
        } else {

            try {
                // ADICIONA O VALOR NA CONTA
                conta.setSaldo(conta.getSaldo().add(valorDeposito));
                // ADICIONA NO HISTORICO
                depositoHistorico.add(valorDeposito);
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
            mensagem.numeroNulo();
        } else if (conta.getSaldo().compareTo(valorSaque) < 0) {
            mensagem.saldoInsuficiente(valorSaque);
        } else if (valorSaque.compareTo(BigDecimal.ZERO) < 0) {
            mensagem.numeroNegativo();
        } else {

            try {
                // FAZ A SUBTRAÇÃO DO SALDO
                conta.setSaldo(conta.getSaldo().subtract(valorSaque));
                // ADICIONA NO HISTORICO
                saqueHistorico.add(valorSaque);
                // MENSAGEM
                mensagem.saqueRealizado(valorSaque);
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
            mensagem.numeroNulo();
        } else if (valorTransferencia.compareTo(BigDecimal.ZERO) < 0) {
            mensagem.transfInsuficiente(valorTransferencia,numDaConta);
        } else if (saldo.compareTo(valorTransferencia) < 0) {
            mensagem.transfInsuficiente(valorTransferencia,numDaConta);
            scanner.string();
        } else {
            try {
                // FAZ A SUBTRAÇÃO DO SALDO
                conta.setSaldo(saldo.subtract(valorTransferencia));
                // ADICIONA NO HISTORICO
                transfHistorico.add(valorTransferencia);
                // MENSAGEM
                mensagem.transferenciaRealizada(valorTransferencia,numDaConta);
                // LIMPA O BUFFER
                scanner.string();
            } catch (Exception e) {
                throw new RuntimeException("Erro ao transferir");
            }

        }
    }

    public BigDecimal depositoHistorico() {
        int i;
        for (i = 0; i < depositoHistorico.size(); i++) {
            mensagem.depositoHistorico(depositoHistorico,i);
        }
        return conta.getSaldo();
    }

    public BigDecimal saqueHistorico() {
        int i;
        for ( i = 0; i < saqueHistorico.size(); i++) {
            mensagem.saqueHistorico(saqueHistorico,i);
        }
        return conta.getSaldo();
    }

    public BigDecimal transfHistorico() {
        int i;
        for (i = 0; i < transfHistorico.size(); i++) {
            mensagem.transfHistorico(transfHistorico,i);
        }
        return conta.getSaldo();
    }

}
