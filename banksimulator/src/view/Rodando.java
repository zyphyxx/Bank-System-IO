package view;

import entities.Account;
import services.BankAccountOperationsService;

import java.util.Scanner;

public class Rodando {

    public static void main(String[] args) {

        Account account = new Account();
        Scanner sc = new Scanner(System.in);
        BankAccountOperationsService bs = BankAccountOperationsService.getInstance();

        System.out.println("digite seu nome");
        account.setName(sc.nextLine());

        System.out.println("Digite o deposito:");



        System.out.println(account.getName());
    }
}
