import java.util.Scanner;

// BankAccount class to manage the user's account balance
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}

// ATM class to handle user interaction
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n====== ATM Menu ======");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number (1-4): ");
                scanner.next(); // discard invalid input
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleWithdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 4.");
            }
        } while (choice != 4);
    }

    private void handleDeposit() {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        if (account.deposit(amount)) {
            System.out.printf("Successfully deposited $%.2f%n", amount);
        } else {
            System.out.println("Deposit failed. Amount must be positive.");
        }
    }

    private void handleWithdraw() {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.printf("Successfully withdrew $%.2f%n", amount);
        } else {
            System.out.println("Withdrawal failed. Check the amount and your balance.");
        }
    }
}

// Main class to run the ATM application
public class AtmInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Initial balance: $1000
        ATM atm = new ATM(userAccount);
        atm.displayMenu();
    }
}
