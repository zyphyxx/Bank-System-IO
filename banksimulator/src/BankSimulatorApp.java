import model.Conta;
import model.Operacoes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankSimulatorApp {

    public static void main(String[] args) {

        MenuBanco.iniciarBanco();

    }

    static class MenuBanco {

        static Scanner scanner = new Scanner(System.in);
        static Conta conta = new Conta();
        static Operacoes operacoes = new Operacoes(conta);

        public static void iniciarBanco() {

            System.out.println("Digite seu nome:");
            conta.setNomeDoTitular(scanner.nextLine());

            System.out.println("Digite o numero da sua conta:");
            conta.setNumeroDaConta(scanner.nextInt());

            statusCliente();

        }

        public static void sairBanco() {

            System.out.println("\n" + conta.getNomeDoTitular() + " você esta saindo... " +
                    "\nAté a proxima ;D");

            System.exit(0);
        }

        public static void statusCliente() {

            System.out.println("\n---------------------------------------");
            System.out.println("Bem vindo(a): " + conta.getNomeDoTitular());
            System.out.println("Numero do cartão: " + conta.getNumeroDaConta());
            System.out.println("Seu saldo é: " + conta.getSaldo());
            System.out.println("---------------------------------------\n");
            menuSelecao();
        }

        public static void menuSelecao() {

            int valor = 0;

            do {

                try {

                    System.out.println("==== OPERAÇOES ====\n");
                    System.out.println("1 - Depositar");
                    System.out.println("2 - Sacar");
                    System.out.println("3 - Transferir");
                    System.out.println("4 - Sair");

                    valor = scanner.nextInt();
                    scanner.nextLine();

                    switch (valor) {
                        case 1:
                            MenuBanco.depositarBanco();
                            break;
                        case 2:
                            MenuBanco.sacarBanco();

                            break;
                        case 3:
                            System.out.println("Transferindoo");
                            break;
                        case 4:
                            MenuBanco.sairBanco();
                            break;
                        default:
                            System.out.println("Digite um numero valido");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nDIGITE UM NUMERO VALIDO\n");
                    scanner.nextLine();
                }

            } while (valor != 4);

        }

        public static void depositarBanco() {

            System.out.println("\n=== DEPOSITAR ===\n");
            System.out.println("Digite o valor: ");
            String valorString = scanner.nextLine();

            try {

                double valorDeposito = Double.parseDouble(valorString);

                operacoes.depositar(valorDeposito);
                MenuBanco.statusCliente();

            } catch (NumberFormatException e) {
                System.out.println("\nVocê não digitou um número. Por favor, insira um valor numérico válido.\n");
            }
        }

        public static void sacarBanco() {

            System.out.println("\nDigite o valor do saque: ");
            String valorString = scanner.nextLine();

            try {

                double valorSaque = Double.parseDouble(valorString);

                operacoes.sacar(valorSaque);
                MenuBanco.statusCliente();

            } catch (NumberFormatException e) {
                System.out.println("\nVocê não digitou um número. Por favor, insira um valor numérico válido.\n");

            }
        }
    }
}
