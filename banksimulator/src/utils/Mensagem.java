package utils;

import view.CoresANSI;

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

    public void entradaInvalidaNum () {
        System.out.println(CoresANSI.RED + "\nOpção inválida. Por favor, digite um número correspondente à operação desejada." + CoresANSI.RESET + "\n");
    }
}
