package entities;

import java.math.BigDecimal;

public interface BankAccountOperations {

    void deposit (BigDecimal amount);

    void withdraw (BigDecimal amount);

    void transfer (BigDecimal amount);

}
