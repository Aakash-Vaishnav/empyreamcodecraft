import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private int transactionId;
    private String description;
    private double amount;

    public Transaction(int transactionId, String description, double amount) {
        this.transactionId = transactionId;
        this.description = description;
        this.amount = amount;
    }

    public String toString() {
        return "Transaction ID: " + transactionId + "\nDescription: " + description + "\nAmount: $" + amount;
    }
}

class Account {
    private int accountId;
    private double balance;
    private List<Transaction> transactions;

    public Account(int accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction(transactions.size() + 1, "Deposit", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction(transactions.size() + 1, "Withdraw", -amount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

class User {
    private int userId;
    private int pin;
    private Account account;

    public User(int userId, int pin) {
        this.userId = userId;
        this.pin = pin;
        this.account = new Account(userId);
    }

    public int getUserId() {
        return userId;
    }

    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }

    public Account getAccount() {
        return account;
    }
}

class ATM {
    private List<User> users;
    private User currentUser;

    public ATM() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean login(int userId, int pin) {
        for (User user : users) {
            if (user.getUserId() == userId && user.verifyPin(pin)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public double getCurrentBalance() {
        return currentUser.getAccount().getBalance();
    }

    public void displayMenu() {
        System.out.println("\nOPERATIONS\n");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit\n");
    }

    public void performTransactions() {
        try (Scanner scanner = new Scanner(System.in)) {
			int choice;

			do {
			    displayMenu();
			    System.out.print("Enter your choice: ");
			    choice = scanner.nextInt();

			    switch (choice) {
			        case 1:
			            displayTransactionHistory();
			            break;
			        case 2:
			            System.out.print("\nEnter the amount to withdraw: $");
			            double withdrawAmount = scanner.nextDouble();
			            currentUser.getAccount().withdraw(withdrawAmount);
			            System.out.println("\nWithdrawal successful");
			            System.out.println("\nCurrent Balance: $" + getCurrentBalance());
			            break;
			        case 3:
			            System.out.print("\nEnter the amount to deposit: $");
			            double depositAmount = scanner.nextDouble();
			            currentUser.getAccount().deposit(depositAmount);
			            System.out.println("\nDeposit successful" );
			            System.out.println("\nCurrent Balance: $" + getCurrentBalance());
			            break;
			        case 4:
			            System.out.print("\nEnter the target User ID for transfer: ");
			            int targetUserId = scanner.nextInt();
			            System.out.print("\nEnter the amount to transfer: $");
			            double transferAmount = scanner.nextDouble();
			            transfer(targetUserId, transferAmount);
			            System.out.println("\nTransfer successful");
			            break;
			        case 5:
			            System.out.println("\nThank you for using the ATM!");
			            break;
			        default:
			            System.out.println("\nInvalid choice. Please try again.");
			    }
			} while (choice != 5);
		}
    }

    public void displayTransactionHistory() {
        List<Transaction> transactions = currentUser.getAccount().getTransactions();
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
            System.out.println("/n---------------");
        }
    }

    public void transfer(int targetUserId, double amount) {
        // ... (previous code for the transfer operation)
    }
}

public class AtmInterface  {
    public static void main(String[] args) {
        ATM atm = new ATM();
        User user1 = new User(12345, 1234); // User ID and PIN
        User user2 = new User(54321, 4321); // User ID and PIN
        atm.addUser(user1);
        atm.addUser(user2);

        try (Scanner scanner = new Scanner(System.in)) {
        	System.out.println("PLEASE LOGIN TO PROCEED\n");
			System.out.print("Enter your User ID: ");
			int userId = scanner.nextInt();
			System.out.print("\nEnter your PIN: ");
			int pin = scanner.nextInt();

			if (atm.login(userId, pin)) {
			    atm.performTransactions();
			} else {
			    System.out.println("Login failed. Please check your User ID and PIN.\n");
			}
		}
    }
}
