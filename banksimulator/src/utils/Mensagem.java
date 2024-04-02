package utils;

import view.CoresANSI;

import java.math.BigDecimal;

import static view.MenuBanco.conta;

public class Mensagem {



    public static Mensagem iniciarMSG () {
        return new Mensagem();
    }

    public void bemVindo () {
        System.out.println(CoresANSI.YELLOW + "=== BEM-VINDO AO BANCO ===" + CoresANSI.RESET + "\n");
    }

    public void sair () {
        System.out.println("\n" + CoresANSI.GREEN + conta.getNomeDoTitular() + ", você está saindo... " +
                "\nAté a próxima ;D\n" + CoresANSI.RESET);
        System.exit(0);
    }

    public void status () {
        System.out.println("\n" + CoresANSI.CYAN + "=================================" + CoresANSI.RESET);
        System.out.println("Bem-vindo(a): " + CoresANSI.GREEN + conta.getNomeDoTitular() + CoresANSI.RESET);
        System.out.println("Número do conta: " + CoresANSI.PURPLE + conta.getNumeroDaConta() + CoresANSI.RESET);
        System.out.println("Saldo atual: " + CoresANSI.YELLOW + "R$" + conta.getSaldo() + CoresANSI.RESET);
        System.out.println(CoresANSI.CYAN + "=================================" + CoresANSI.RESET + "\n");
    }

    public void menu () {
        System.out.println(CoresANSI.GREEN + "==== OPERAÇÕES ====" + CoresANSI.RESET);
        System.out.println("1 - " + CoresANSI.CYAN + "Depositar" + CoresANSI.RESET);
        System.out.println("2 - " + CoresANSI.CYAN + "Sacar" + CoresANSI.RESET);
        System.out.println("3 - " + CoresANSI.CYAN + "Transferir" + CoresANSI.RESET);
        System.out.println("4 - " + CoresANSI.CYAN + "Historico" + CoresANSI.RESET);
        System.out.println("5 - " + CoresANSI.RED + "Sair" + CoresANSI.RESET);
        System.out.println("Digite sua opção:");
    }

    public void depositar () {
        System.out.println("\n" + CoresANSI.YELLOW + "=== DEPOSITAR ===" + CoresANSI.RESET);
        System.out.println("\nDigite o valor a ser depositado:");
    }

    public void depositarStatus (BigDecimal valorDeposito) {
        System.out.println(CoresANSI.GREEN + "Depósito de " + valorDeposito + " R$ realizado com sucesso." + CoresANSI.RESET);
        System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
    }

    public void sacar () {
        System.out.println("\n" + CoresANSI.YELLOW + "=== SACAR ===" + CoresANSI.RESET);
        System.out.println("Digite o valor a ser sacado:");
    }

    public void transferir () {
        System.out.println("\n" + CoresANSI.YELLOW + "=== TRANSFERIR ===" + CoresANSI.RESET);
        System.out.println("\nDigite o número da conta de destino:");
    }

    public void transferirValor () {
        System.out.println("Digite o valor a ser transferido:");
    }

    public void entradaInvalidaNum () {
        System.out.println(CoresANSI.RED + "\nOpção inválida. Por favor, digite um número correspondente à operação desejada." + CoresANSI.RESET + "\n");
        //System.out.println(CoresANSI.RED + "\nOpção inválida. Por favor, escolha uma das opções disponíveis." + CoresANSI.RESET + "\n");
    }

}
