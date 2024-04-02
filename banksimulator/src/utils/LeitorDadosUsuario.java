package utils;

import java.util.Scanner;

import static view.MenuBanco.conta;

public class LeitorDadosUsuario {

    private final Scanner scanner;


    public static LeitorDadosUsuario iniciarLeitor (){
        return new LeitorDadosUsuario();
    }

   public LeitorDadosUsuario () {
   this.scanner = new Scanner(System.in);
   }

   public String lerString () {
       return scanner.nextLine();
   }

   public int lerInteiro () {
       return scanner.nextInt();

   }

   public double lerDouble () {
       return scanner.nextDouble();
   }

   public void lerUsuario (){
       // ENTRADA DO NOME
       System.out.println("Digite seu nome:");
       String nomeUsuario = lerString();
       conta.setNomeDoTitular(nomeUsuario);
   }

    // ARRUMAR O ERRO QUANDO O USUARIO ENTRA COM LETRAS
   public void lerConta () {
       // ENTRADA DO NUMERO DA CONTA
       System.out.println("Digite o número da sua conta:");
       int numConta = Integer.parseInt(lerString());
       conta.setNumeroDaConta(numConta);
   }
}
