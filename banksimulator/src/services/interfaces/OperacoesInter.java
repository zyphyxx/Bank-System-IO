package services.interfaces;

public interface OperacoesInter {

   double depositar(double valor);

   double sacar (double valor);

   void transferir (int numDaConta, double valor);

   void historico ();
}
