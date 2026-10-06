package com.example.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Automated test suite for BankAccount validating deposit, withdrawal,
 * interest calculation, boundary limits, and input validations.
 */
class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("SB-789012", 1000.0);
    }

    @Test
    @DisplayName("Verify successful deposit increases balance")
    void testDepositSuccess() {
        double updated = account.deposit(500.0);
        assertEquals(1500.0, updated, 0.001);
        assertEquals(1500.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Verify successful withdrawal decreases balance")
    void testWithdrawSuccess() {
        double updated = account.withdraw(400.0);
        assertEquals(600.0, updated, 0.001);
        assertEquals(600.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Verify exact balance withdrawal leaves zero balance")
    void testWithdrawExactBoundary() {
        double updated = account.withdraw(1000.0);
        assertEquals(0.0, updated, 0.001);
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Verify rejection when withdrawal exceeds available balance")
    void testWithdrawOverdraftRejection() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(1000.01)
        );
        assertTrue(exception.getMessage().contains("Insufficient funds"));
    }

    @Test
    @DisplayName("Verify rejection of zero and negative deposit amounts")
    void testDepositInvalidAmounts() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0.0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100.0));
    }

    @Test
    @DisplayName("Verify rejection of zero and negative withdrawal amounts")
    void testWithdrawInvalidAmounts() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0.0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50.0));
    }

    @Test
    @DisplayName("Verify annual compound interest calculation")
    void testCompoundInterestCalculation() {
        // P=1000, r=0.05, t=2 -> A = 1000 * (1.05)^2 = 1102.50
        double projected = account.calculateCompoundInterest(0.05, 2);
        assertEquals(1102.50, projected, 0.001);
    }

    @Test
    @DisplayName("Verify zero interest rate and zero duration projections")
    void testCompoundInterestZeroBoundary() {
        assertEquals(1000.0, account.calculateCompoundInterest(0.0, 5), 0.001);
        assertEquals(1000.0, account.calculateCompoundInterest(0.08, 0), 0.001);
    }

    @Test
    @DisplayName("Verify constructor rejects invalid account numbers")
    void testInvalidAccountNumber() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(null, 500.0));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("   ", 500.0));
    }

    @Test
    @DisplayName("Verify constructor rejects negative initial balance")
    void testNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("SB-1111", -50.0));
    }
}
