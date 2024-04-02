package services.interfaces;

import java.math.BigDecimal;

public interface OperacoesInter {

   void depositar(BigDecimal valorDeposito);

   void sacar (BigDecimal valor);

   void transferir (int numDaConta, BigDecimal valor);

   void historico ();
}
