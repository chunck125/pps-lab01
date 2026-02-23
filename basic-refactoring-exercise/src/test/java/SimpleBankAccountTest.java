import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private BankAccount bankAccount;
    private final int DEPOSIT_AMOUNT = 100;
    private final int NON_EXISTENT_ACCOUNT_ID = 2;
    private final int WITHDRAW_AMOUNT = 70;


    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        final int wrongDepositAmount = 50;
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        bankAccount.deposit(NON_EXISTENT_ACCOUNT_ID, wrongDepositAmount);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        final int difference = 30;
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), WITHDRAW_AMOUNT);
        assertEquals(difference, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(NON_EXISTENT_ACCOUNT_ID, WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
