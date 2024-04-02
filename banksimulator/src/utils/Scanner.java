package utils;

import static view.MenuBanco.conta;

public class Scanner {

    private final java.util.Scanner scanner;


    public static Scanner iniciarLeitor (){
        return new Scanner();
    }

   public Scanner() {
   this.scanner = new java.util.Scanner(System.in);
   }

   public String string () {
       return scanner.nextLine();
   }

   public int inteiro () {
       return scanner.nextInt();

   }

    public void lerUsuario (){
        // ENTRADA DO NOME
        System.out.println("Digite seu nome:");
        String nomeUsuario = string();
        conta.setNomeDoTitular(nomeUsuario);
    }

    // ARRUMAR O ERRO QUANDO O USUARIO ENTRA COM LETRAS
    public void lerConta () {
        // ENTRADA DO NUMERO DA CONTA
        System.out.println("Digite o número da sua conta:");
        int numConta = Integer.parseInt(string());
        conta.setNumeroDaConta(numConta);
    }
}
