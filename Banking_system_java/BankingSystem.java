import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystem {
    // Store all accounts
    private static ArrayList<Account> accounts = new ArrayList<Account>();
    private static Scanner scanner = new Scanner(System.in);
    private static int accountCounter = 1000; // Start account numbers from 1001
    
    // Main method
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("   WELCOME TO BANKING SYSTEM    ");
        System.out.println("=================================");
        
        boolean running = true;
        
        while (running) {
            showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            if (choice == 1) {
                createAccount();
            } else if (choice == 2) {
                login();
            } else if (choice == 3) {
                System.out.println("\nThank you for using our banking system!");
                running = false;
            } else {
                System.out.println("\nInvalid choice! Try again.");
            }
        }
        
        scanner.close();
    }
    
    // Show main menu
    public static void showMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Create New Account");
        System.out.println("2. Login to Existing Account");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
    }
    
    // Create new account
    public static void createAccount() {
        System.out.println("\n--- Create New Account ---");
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Create username: ");
        String username = scanner.nextLine();
        
        // Check if username already exists
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(username)) {
                System.out.println("Username already exists! Try another.");
                return;
            }
        }
        
        System.out.print("Create password: ");
        String password = scanner.nextLine();
        
        System.out.println("\nSelect Account Type:");
        System.out.println("1. Savings Account (Min Balance: Rs.500)");
        System.out.println("2. Current Account (Overdraft: Rs.5000)");
        System.out.print("Enter choice: ");
        int accountType = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        // Generate account number
        accountCounter++;
        String accountNumber = "ACC" + accountCounter;
        
        // Create account based on type
        Account newAccount = null;
        
        if (accountType == 1) {
            newAccount = new SavingsAccount(accountNumber, name, username, password);
            System.out.println("\nSavings Account created successfully!");
        } else if (accountType == 2) {
            newAccount = new CurrentAccount(accountNumber, name, username, password);
            System.out.println("\nCurrent Account created successfully!");
        } else {
            System.out.println("Invalid account type!");
            return;
        }
        
        accounts.add(newAccount);
        System.out.println("Your Account Number: " + accountNumber);
        System.out.println("Username: " + username);
    }
    
    // Login to account
    public static void login() {
        System.out.println("\n--- Login ---");
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Find account
        Account userAccount = null;
        
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getUsername().equals(username)) {
                if (acc.checkPassword(password)) {
                    userAccount = acc;
                    break;
                } else {
                    System.out.println("\nIncorrect password!");
                    return;
                }
            }
        }
        
        if (userAccount == null) {
            System.out.println("\nAccount not found!");
            return;
        }
        
        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + userAccount.getAccountHolderName() + "!");
        
        // Show account menu
        accountMenu(userAccount);
    }
    
    // Account menu after login
    public static void accountMenu(Account account) {
        boolean loggedIn = true;
        
        while (loggedIn) {
            System.out.println("\n--- Account Menu ---");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Transfer Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            if (choice == 1) {
                depositMoney(account);
            } else if (choice == 2) {
                withdrawMoney(account);
            } else if (choice == 3) {
                transferMoney(account);
            } else if (choice == 4) {
                account.showBalance();
            } else if (choice == 5) {
                account.showTransactionHistory();
            } else if (choice == 6) {
                System.out.println("\nLogged out successfully!");
                loggedIn = false;
            } else {
                System.out.println("\nInvalid choice!");
            }
        }
    }
    
    // Deposit money
    public static void depositMoney(Account account) {
        System.out.println("\n--- Deposit Money ---");
        System.out.print("Enter amount to deposit: Rs.");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer
        
        account.deposit(amount);
    }
    
    // Withdraw money
    public static void withdrawMoney(Account account) {
        System.out.println("\n--- Withdraw Money ---");
        System.out.print("Enter amount to withdraw: Rs.");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer
        
        account.withdraw(amount);
    }
    
    // Transfer money
    public static void transferMoney(Account account) {
        System.out.println("\n--- Transfer Money ---");
        System.out.print("Enter recipient account number: ");
        String recipientAccNum = scanner.nextLine();
        
        // Find recipient account
        Account recipientAccount = null;
        
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber().equals(recipientAccNum)) {
                recipientAccount = accounts.get(i);
                break;
            }
        }
        
        if (recipientAccount == null) {
            System.out.println("Recipient account not found!");
            return;
        }
        
        if (recipientAccount == account) {
            System.out.println("Cannot transfer to same account!");
            return;
        }
        
        System.out.println("Recipient: " + recipientAccount.getAccountHolderName());
        System.out.print("Enter amount to transfer: Rs.");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer
        
        account.transfer(recipientAccount, amount);
    }
}