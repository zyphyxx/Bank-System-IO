package services.interfaces;

import java.math.BigDecimal;

public interface OperacoesInter {

   BigDecimal depositar(BigDecimal valorDeposito);

   BigDecimal sacar (BigDecimal valor);

   void transferir (int numDaConta, BigDecimal valor);

   void historico ();
}
