import java.util.ArrayList;

public class Account {
    // Account details
    private String accountNumber;
    private String accountHolderName;
    private String username;
    private String password;
    private double balance;
    private String accountType;
    
    // Transaction history
    private ArrayList<String> transactionHistory;
    
    // Constructor
    public Account(String accountNumber, String accountHolderName, String username, 
                   String password, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.accountType = accountType;
        this.transactionHistory = new ArrayList<String>();
    }
    
    // Get account number
    public String getAccountNumber() {
        return accountNumber;
    }
    
    // Get account holder name
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    // Get username
    public String getUsername() {
        return username;
    }
    
    // Get account type
    public String getAccountType() {
        return accountType;
    }
    
    // Get balance
    public double getBalance() {
        return balance;
    }
    
    // Check password
    public boolean checkPassword(String inputPassword) {
        if (password.equals(inputPassword)) {
            return true;
        } else {
            return false;
        }
    }
    
    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            String record = "Deposited: Rs." + amount + " | New Balance: Rs." + balance;
            transactionHistory.add(record);
            System.out.println("Deposit successful!");
            System.out.println("New Balance: Rs." + balance);
        } else {
            System.out.println("Invalid amount!");
        }
    }
    
    // Withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        
        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return;
        }
        
        balance = balance - amount;
        String record = "Withdrawn: Rs." + amount + " | New Balance: Rs." + balance;
        transactionHistory.add(record);
        System.out.println("Withdrawal successful!");
        System.out.println("New Balance: Rs." + balance);
    }
    
    // Transfer money to another account
    public boolean transfer(Account targetAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return false;
        }
        
        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return false;
        }
        
        balance = balance - amount;
        targetAccount.receiveTransfer(amount);
        
        String record = "Transferred: Rs." + amount + " to " + targetAccount.getAccountNumber() 
                       + " | New Balance: Rs." + balance;
        transactionHistory.add(record);
        
        System.out.println("Transfer successful!");
        System.out.println("New Balance: Rs." + balance);
        return true;
    }
    
    // Receive transfer
    public void receiveTransfer(double amount) {
        balance = balance + amount;
        String record = "Received: Rs." + amount + " | New Balance: Rs." + balance;
        transactionHistory.add(record);
    }
    
    // Show balance
    public void showBalance() {
        System.out.println("\n--- Balance Inquiry ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Type: " + accountType);
        System.out.println("Current Balance: Rs." + balance);
    }
    
    // Show transaction history
    public void showTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        System.out.println("Account: " + accountNumber);
        
        if (transactionHistory.size() == 0) {
            System.out.println("No transactions yet!");
            return;
        }
        
        for (int i = 0; i < transactionHistory.size(); i++) {
            System.out.println((i + 1) + ". " + transactionHistory.get(i));
        }
    }
}