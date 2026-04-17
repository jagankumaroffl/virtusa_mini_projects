public class CurrentAccount extends Account {
    private double overdraftLimit;
    
    // Constructor
    public CurrentAccount(String accountNumber, String accountHolderName, 
                         String username, String password) {
        super(accountNumber, accountHolderName, username, password, "Current");
        this.overdraftLimit = 5000.0; // Can overdraft up to Rs.5000
    }
    
    // Override withdraw to allow overdraft
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        
        double availableBalance = getBalance() + overdraftLimit;
        
        if (amount > availableBalance) {
            System.out.println("Withdrawal amount exceeds overdraft limit!");
            return;
        }
        
        super.withdraw(amount);
    }
    
    // Get overdraft limit
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}