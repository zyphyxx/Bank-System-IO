package utils;

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
}
