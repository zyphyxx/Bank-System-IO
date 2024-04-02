package view;

import entities.Conta;
import services.OperacoesService;
import utils.Mensagem;
import utils.Scanner;

import java.math.BigDecimal;


public class MenuBanco {

    public Scanner scanner = new Scanner();
    public static Conta conta = new Conta();
    static OperacoesService operacoes = new OperacoesService(conta);
    public Mensagem mensagem = Mensagem.iniciarMSG();

    // método factory
    public static MenuBanco iniciar() {
        MenuBanco menuBanco = new MenuBanco();
        menuBanco.iniciarBanco();
        return menuBanco;
    }

    public void iniciarBanco() {
        // METODO DE INICIO E CRIAÇÃO DO CLIENTE
        mensagem.bemVindo();
        operacoes.nomeUsuario();
        operacoes.numeroConta();
        // STATUS
        statusCliente();
    }

    public void operacaoSair() {
        // METODO PARA SAIR
        mensagem.sair();
    }

    public void statusCliente() {
        // METODO DE STATUS DE CLIENTE
        mensagem.status();
        menuSelecao();

    }

    public void menuSelecao() {
        // VAR
        int valor;

        do {
            // MENSAGEM
            mensagem.menu();
            valor = scanner.inteiro();
            // LIMPAR O BUFFER
            scanner.string();

            switch (valor) {
                case 1:
                    operacaoDepositar();
                    break;
                case 2:
                    operacaoSacar();
                    break;
                case 3:
                    operacaoTransferir();
                    break;
                case 4:
                    operacaoHistorico();
                    break;
                case 5:
                    operacaoSair();
                default:
                    mensagem.entradaInvalidaNum();
                    break;
            }

        } while (valor != 5);

    }

    public void operacaoDepositar() {
        // MENSAGEM
        mensagem.depositar();
        // VARIAVEL PARA DEPOSITO
        BigDecimal valorDeposito = new BigDecimal(scanner.string());
        // CHAMA O METODO DEPOSITAR PASSANDO A VARIAVEL DO TIPO BIGDECIMAL
        operacoes.depositar(valorDeposito);
        // LIMPAR O BUFFER DO SCANNER
        scanner.string();
        // CHAMA O METODO DE STATUS
        statusCliente();
    }

    public void operacaoSacar() {
        // MENSAGEM
        mensagem.sacar();
        // VARIAVEL PARA SAQUE
        BigDecimal valorSaque = new BigDecimal(scanner.string());
        // CHAMA O METODO SACAR ADICIONANDO O VALOR
        operacoes.sacar(valorSaque);
        // CHAMA O METODO DE STATUS
        statusCliente();

    }

    public void operacaoTransferir() {
        // MENSAGEM
        mensagem.transferir();
        // VARIAVEL COM NUMERO DA CONTA
        int numConta = scanner.inteiro();
        // LIMPA O BUFFER
        scanner.string();
        // MENSAGEM
        mensagem.transferirValor();
        // VARIAVEL COM VALOR DE TRANSFERENCIA
        BigDecimal valorTransferencia = new BigDecimal(scanner.string());
        // LIMPA O BUFFER
        scanner.string();
        // CHAMA O METODO DE TRANSFERIR
        operacoes.transferir(numConta, valorTransferencia);

    }

    public void operacaoHistorico() {
        // MENSAGEM
        mensagem.historico();
        // CHAMA OS METODOS DE HISTORICOS
        operacoes.depositoHistorico();
        operacoes.saqueHistorico();
        operacoes.transfHistorico();
        // LIMPA O BUFFER
        scanner.string();
    }
}
