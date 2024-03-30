import model.Conta;
import model.Operacoes;

import java.util.Scanner;

public class BankSimulatorApp {

    public static void main(String[] args) {

        MenuBanco.iniciarBanco();

    }

    static class MenuBanco {

        static Scanner scanner = new Scanner(System.in);
        static Conta conta = new Conta();

        public static void iniciarBanco () {

            System.out.println("Digite seu nome:");
            conta.setNomeDoTitular(scanner.nextLine());

            System.out.println("Digite o numero da sua conta:");
            conta.setNumeroDaConta(scanner.nextInt());

            statusCliente();

        }

        public static void statusCliente () {

            System.out.println("Bem vindo(a): "+conta.getNomeDoTitular());
            System.out.println("Numero do cartão: "+conta.getNumeroDaConta());
            System.out.println("Seu saldo é: "+conta.getSaldo());

            menuSelecao();
        }

        public static void menuSelecao () {

            int valor;

            do {

                System.out.println("==== OPERAÇOES ====");
                System.out.println("1 - Depositar");
                System.out.println("2 - Sacar");
                System.out.println("3 - Transferir");
                System.out.println("4 - Sair");

                valor = scanner.nextInt();

                switch (valor){
                    case 1: System.out.println("Depositandoo");
                    break;
                    case 2: System.out.println("Sacandoo");
                    break;
                    case 3: System.out.println("Transferindoo");
                    break;
                    case 4: System.out.println("Saindoo");
                    break;
                    default: System.out.println("Digite um numero valido");
                    break;
                }

            } while (valor != 4);

        }
    }
}
