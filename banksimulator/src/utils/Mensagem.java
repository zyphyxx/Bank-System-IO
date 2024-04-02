package utils;

import java.math.BigDecimal;

import static view.MenuBanco.conta;

public class Mensagem {



    public static Mensagem iniciarMSG () {
        return new Mensagem();
    }

    public void bemVindo () {
        System.out.println(Collor.YELLOW + "=== BEM-VINDO AO BANCO ===" + Collor.RESET + "\n");
    }

    public void sair () {
        System.out.println("\n" + Collor.GREEN + conta.getNomeDoTitular() + ", você está saindo... " +
                "\nAté a próxima ;D\n" + Collor.RESET);
        System.exit(0);
    }

    public void status () {
        System.out.println("\n" + Collor.CYAN + "=================================" + Collor.RESET);
        System.out.println("Bem-vindo(a): " + Collor.GREEN + conta.getNomeDoTitular() + Collor.RESET);
        System.out.println("Número do conta: " + Collor.PURPLE + conta.getNumeroDaConta() + Collor.RESET);
        System.out.println("Saldo atual: " + Collor.YELLOW + "R$" + conta.getSaldo() + Collor.RESET);
        System.out.println(Collor.CYAN + "=================================" + Collor.RESET + "\n");
    }

    public void menu () {
        System.out.println(Collor.GREEN + "==== OPERAÇÕES ====" + Collor.RESET);
        System.out.println("1 - " + Collor.CYAN + "Depositar" + Collor.RESET);
        System.out.println("2 - " + Collor.CYAN + "Sacar" + Collor.RESET);
        System.out.println("3 - " + Collor.CYAN + "Transferir" + Collor.RESET);
        System.out.println("4 - " + Collor.CYAN + "Historico" + Collor.RESET);
        System.out.println("5 - " + Collor.RED + "Sair" + Collor.RESET);
        System.out.println("Digite sua opção:");
    }

    public void depositar () {
        System.out.println("\n" + Collor.YELLOW + "=== DEPOSITAR ===" + Collor.RESET);
        System.out.println("\nDigite o valor a ser depositado:");
    }

    public void depositarStatus (BigDecimal valorDeposito) {
        System.out.println(Collor.GREEN + "Depósito de " + valorDeposito + " R$ realizado com sucesso." + Collor.RESET);
        System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
    }

    public void sacar () {
        System.out.println("\n" + Collor.YELLOW + "=== SACAR ===" + Collor.RESET);
        System.out.println("Digite o valor a ser sacado:");
    }

    public void transferir () {
        System.out.println("\n" + Collor.YELLOW + "=== TRANSFERIR ===" + Collor.RESET);
        System.out.println("\nDigite o número da conta de destino:");
    }

    public void transferirValor () {
        System.out.println("Digite o valor a ser transferido:");
    }

    public void entradaInvalidaNum () {
        System.out.println(Collor.RED + "\nOpção inválida. Por favor, digite um número correspondente à operação desejada." + Collor.RESET + "\n");
        //System.out.println(CoresANSI.RED + "\nOpção inválida. Por favor, escolha uma das opções disponíveis." + CoresANSI.RESET + "\n");
    }

}
