package utils;

import java.util.Scanner;

public class LeitorDadosUsuario {

    private final Scanner scanner;

    public static LeitorDadosUsuario iniciarLeitor (){
        LeitorDadosUsuario leitorDadosUsuario = new LeitorDadosUsuario();
        return leitorDadosUsuario;
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
}
