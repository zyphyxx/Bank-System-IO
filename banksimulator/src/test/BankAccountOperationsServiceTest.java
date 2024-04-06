package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import services.BankAccountOperationsService;

import java.math.BigDecimal;

public class BankAccountOperationsServiceTest {

    private BankAccountOperationsService bankService;

    @BeforeEach
    public void setUp() {
        bankService = BankAccountOperationsService.getInstance();
    }

    @Test
    public void testDeposit() {
        bankService.deposit(new BigDecimal(-1000));
        Assertions.assertEquals(new BigDecimal(0), bankService.balance());
    }

    @Test
    public void testWithdraw() {
        bankService.withdraw(new BigDecimal(-1500));
        Assertions.assertEquals(new BigDecimal(1000), bankService.balance());

    }
}
