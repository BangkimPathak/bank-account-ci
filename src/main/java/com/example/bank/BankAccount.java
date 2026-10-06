package com.example.bank;

/**
 * BankAccount represents a high-integrity bank account supporting deposits,
 * withdrawals with strict overdraft controls, and compound interest calculations.
 */
public class BankAccount {
    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Deposits a positive monetary amount into the account.
     *
     * @param amount the deposit amount
     * @return updated balance after deposit
     */
    public double deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be strictly greater than zero.");
        }
        this.balance += amount;
        return this.balance;
    }

    /**
     * Withdraws funds up to the available balance. Rejects overdrafts.
     *
     * @param amount the withdrawal amount
     * @return updated balance after withdrawal
     */
    public double withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be strictly greater than zero.");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds: withdrawal amount exceeds available balance.");
        }
        this.balance -= amount;
        return this.balance;
    }

    /**
     * Calculates future balance with annual compound interest.
     * Formula: A = P * (1 + r/n)^(n*t)
     * For annual compounding (n=1): A = P * (1 + r)^t
     *
     * @param annualRate annual interest rate in decimal form (e.g., 0.05 for 5%)
     * @param years duration in years
     * @return computed projected balance
     */
    public double calculateCompoundInterest(double annualRate, int years) {
        if (annualRate < 0) {
            throw new IllegalArgumentException("Annual interest rate cannot be negative.");
        }
        if (years < 0) {
            throw new IllegalArgumentException("Duration in years cannot be negative.");
        }
        return this.balance * Math.pow(1.0 + annualRate, years);
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("     BANK ACCOUNT OPERATIONS MANAGEMENT (CI)      ");
        System.out.println("==================================================");

        BankAccount account = new BankAccount("ACC-IND-10024", 5000.0);
        System.out.println("Account Number   : " + account.getAccountNumber());
        System.out.println("Initial Balance  : INR " + account.getBalance());

        double afterDeposit = account.deposit(2500.0);
        System.out.println("After Deposit    : INR " + afterDeposit);

        double afterWithdraw = account.withdraw(1500.0);
        System.out.println("After Withdrawal : INR " + afterWithdraw);

        double projected = account.calculateCompoundInterest(0.06, 3);
        System.out.printf("Projected (3y 6%%): INR %.2f%n", projected);
        System.out.println("==================================================");
    }
}
