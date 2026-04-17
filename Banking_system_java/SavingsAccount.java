public class SavingsAccount extends Account {
    private double interestRate;
    private double minimumBalance;
    
    // Constructor
    public SavingsAccount(String accountNumber, String accountHolderName, 
                         String username, String password) {
        super(accountNumber, accountHolderName, username, password, "Savings");
        this.interestRate = 4.0; // 4% interest rate
        this.minimumBalance = 500.0;
    }
    
    // Override withdraw to check minimum balance
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        
        double newBalance = getBalance() - amount;
        
        if (newBalance < minimumBalance) {
            System.out.println("Cannot withdraw! Minimum balance of Rs." + minimumBalance + " required!");
            return;
        }
        
        super.withdraw(amount);
    }
    
    // Calculate and add interest
    public void addInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
        System.out.println("Interest of Rs." + interest + " added to account!");
    }
}