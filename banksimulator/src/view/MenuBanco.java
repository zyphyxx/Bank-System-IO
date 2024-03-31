package view;

import entities.Conta;
import services.OperacoesService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuBanco {

    static Scanner scanner = new Scanner(System.in);
    static Conta conta = new Conta();
    static OperacoesService operacoes = new OperacoesService(conta);

    public static void iniciarBanco() {
        System.out.println(CoresANSI.YELLOW + "=== BEM-VINDO AO BANCO ===" + CoresANSI.RESET + "\n");

        System.out.println("Digite seu nome:");
        conta.setNomeDoTitular(scanner.nextLine());

        System.out.println("Digite o número da sua conta:");
        conta.setNumeroDaConta(scanner.nextInt());

        statusCliente();
    }

    public static void sairBanco() {
        System.out.println("\n" +CoresANSI.GREEN + conta.getNomeDoTitular() + ", você está saindo... " +
                "\nAté a próxima ;D\n"+ CoresANSI.RESET);
        System.exit(0);
    }

    public static void statusCliente() {
        System.out.println("\n" + CoresANSI.CYAN + "=================================" + CoresANSI.RESET);
        System.out.println("Bem-vindo(a): " + CoresANSI.GREEN + conta.getNomeDoTitular() + CoresANSI.RESET);
        System.out.println("Número do conta: " + CoresANSI.PURPLE + conta.getNumeroDaConta() + CoresANSI.RESET);
        System.out.println("Saldo atual: " + CoresANSI.YELLOW + "R$" + conta.getSaldo() + CoresANSI.RESET);
        System.out.println(CoresANSI.CYAN + "=================================" + CoresANSI.RESET + "\n");

        menuSelecao();
    }

    public static void menuSelecao() {
        int valor = 0;

        do {
            try {
                System.out.println(CoresANSI.GREEN + "==== OPERAÇÕES ====" + CoresANSI.RESET);
                System.out.println("1 - " + CoresANSI.CYAN + "Depositar" + CoresANSI.RESET);
                System.out.println("2 - " + CoresANSI.CYAN + "Sacar" + CoresANSI.RESET);
                System.out.println("3 - " + CoresANSI.CYAN + "Transferir" + CoresANSI.RESET);
                System.out.println("4 - " + CoresANSI.CYAN + "Historico" + CoresANSI.RESET);
                System.out.println("5 - " + CoresANSI.RED + "Sair" + CoresANSI.RESET);
                System.out.println("Digite sua opção:");

                valor = scanner.nextInt();
                scanner.nextLine();

                switch (valor) {
                    case 1:
                        depositarBanco();
                        break;
                    case 2:
                        sacarBanco();
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
                        System.out.println(CoresANSI.RED + "\nOpção inválida. Por favor, escolha uma das opções disponíveis." + CoresANSI.RESET + "\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(CoresANSI.RED + "\nOpção inválida. Por favor, digite um número correspondente à operação desejada." + CoresANSI.RESET + "\n");
                scanner.nextLine();
            }

        } while (valor != 5);
    }

    public static void depositarBanco() {

        System.out.println("\n" + CoresANSI.YELLOW + "=== DEPOSITAR ===" + CoresANSI.RESET);
        System.out.println("Digite o valor a ser depositado:");

        try {
            double valorDeposito = Double.parseDouble(scanner.nextLine());
            operacoes.depositar(valorDeposito);
            statusCliente();
        } catch (NumberFormatException e) {
            System.out.println(CoresANSI.RED + "\nValor inválido. Por favor, insira um valor numérico válido." + CoresANSI.RESET + "\n");
        }


    }

    public static void sacarBanco() {
        System.out.println("\n" + CoresANSI.YELLOW + "=== SACAR ===" + CoresANSI.RESET);
        System.out.println("Digite o valor a ser sacado:");

        try {
            double valorSaque = Double.parseDouble(scanner.nextLine());
            operacoes.sacar(valorSaque);
            statusCliente();
        } catch (NumberFormatException e) {
            System.out.println(CoresANSI.RED + "\nValor inválido. Por favor, insira um valor numérico válido." + CoresANSI.RESET + "\n");
        }
    }

    public static void transferirBanco() {
        System.out.println("\n" + CoresANSI.YELLOW + "=== TRANSFERIR ===" + CoresANSI.RESET);

        try {
            System.out.println("Digite o número da conta de destino:");
            int numConta = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Digite o valor a ser transferido:");
            double valorTransferencia = scanner.nextDouble();
            scanner.nextLine();

            operacoes.transferir(numConta, valorTransferencia);

        } catch (NumberFormatException e) {
            System.out.println(CoresANSI.RED + "\nValor inválido. Por favor, insira um número de conta e um valor numérico válido." + CoresANSI.RESET + "\n");
        }
    }


    public static void historicoBanco() {
        operacoes.historico();
    }
}
