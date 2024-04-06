package view;

import entities.Account;
import services.BankAccountOperationsService;

import java.util.Scanner;

public class Rodando {

    private Account account = new Account();
    private Scanner sc = new Scanner(System.in);
    private BankAccountOperationsService bs = BankAccountOperationsService.getInstance();

    public static void main(String[] args) {

        Rodando bk = new Rodando();

        System.out.println("digite seu nome");
        bk.account.setName(bk.sc.nextLine());

        System.out.println("Digite o deposito:");
        bk.bs.deposit(bk.sc.nextBigDecimal());

        System.out.println("digite o valor de saque:");
        bk.bs.withdraw(bk.sc.nextBigDecimal());


        System.out.println(bk.account.getName());
        System.out.println(bk.bs.balance());

    }
}
