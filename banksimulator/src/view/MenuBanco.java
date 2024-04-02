package view;

import entities.Conta;
import services.OperacoesService;
import utils.LeitorDadosUsuario;
import utils.Mensagem;

import java.math.BigDecimal;
import java.util.InputMismatchException;


public class MenuBanco {

    public LeitorDadosUsuario leitorDadosUsuario = new LeitorDadosUsuario();


    public static Conta conta = new Conta();
    static OperacoesService operacoes = new OperacoesService(conta);

    public Mensagem mensagem = Mensagem.iniciarMSG(); // iniciando a mensagem

    // método factory
    public static MenuBanco iniciar() {
        MenuBanco menuBanco = new MenuBanco();
        menuBanco.iniciarBanco();
        return menuBanco;
    }

    public void iniciarBanco() {

        mensagem.bemVindo();

        try {

            leitorDadosUsuario.lerUsuario();
            leitorDadosUsuario.lerConta();

            statusCliente();

        } catch (NumberFormatException e) {
            mensagem.entradaInvalidaNum();
        }


    }

    public void sairBanco() {
        mensagem.sair();
    }

    public void statusCliente() {

       mensagem.status();
       menuSelecao();

    }

    public void menuSelecao() {

        int valor = 0;

        do {
            try {

                mensagem.menu();

                valor = leitorDadosUsuario.lerInteiro();
                leitorDadosUsuario.lerString(); // consumir o enter

                switch (valor) {
                    case 1:
                        operacaoDepositar();
                        break;
                    case 2:
                        operacaoSacar();
                        break;
                    case 3:
                        transferirBanco();
                        break;
                    case 4:
                        historicoBanco();
                        break;
                    case 5:
                        sairBanco();
                    default:
                        mensagem.entradaInvalidaNum();
                        break;
                }
            } catch (InputMismatchException e) {

                mensagem.entradaInvalidaNum();
                leitorDadosUsuario.lerString();
            }

        } while (valor != 5);

    }

    public void operacaoDepositar() {

        // A OPERAÇÃO DEPOSITAR ESTA ACEITANDO NUMEROS NEGATIVOS
        // CORRIGIR ESSE ERRO
        try {

            mensagem.depositar();
            BigDecimal valorDeposito = new BigDecimal(leitorDadosUsuario.lerString());
            operacoes.depositar(valorDeposito);

            mensagem.depositarStatus(valorDeposito);
            leitorDadosUsuario.lerString();

            statusCliente();

        } catch (NumberFormatException e) {
            mensagem.entradaInvalidaNum();
        }


    }

    public void operacaoSacar() {

        // A OPERAÇÃO SACAR ESTA SACANDO MAIS DOQUE A QUANTIDADE DE SALDO
        // ESTA ACEITANDO OPERADORES PRIMITIVOS (+ E - )
        // CORRIGIR ESSE ERRO

        try {
            mensagem.sacar();
            BigDecimal valorSaque = new BigDecimal(leitorDadosUsuario.lerString());
            operacoes.sacar(valorSaque);
            statusCliente();
        } catch (NumberFormatException e) {
            mensagem.entradaInvalidaNum();
        }
    }

    public void transferirBanco() {

        // ESTA ACEITANDO OPERADORES PRIMITIVOS (+ E - ) ADICINANDO SALDO QUE NAO EXISTE
        // CORRIGIR ESSE ERRO

        try {
            mensagem.transferir();
            int numConta = leitorDadosUsuario.lerInteiro();
            leitorDadosUsuario.lerString(); // consumir o enter

            mensagem.transferirValor();
            BigDecimal valorTransferencia = new BigDecimal(leitorDadosUsuario.lerString());
            leitorDadosUsuario.lerString(); // consumir o enter

            operacoes.transferir(numConta, valorTransferencia);

        } catch (NumberFormatException e) {
           mensagem.entradaInvalidaNum();
        }
    }


    public void historicoBanco() {
        operacoes.historico();
    }
}
