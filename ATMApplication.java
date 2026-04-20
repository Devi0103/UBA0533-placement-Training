import java.util.Scanner;

// ATM Class (Encapsulation)
class ATM {
    private double balance;
    private int pin;

    // Constructor
    public ATM(double balance, int pin) {
        this.balance = balance;
        this.pin = pin;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Validate PIN
    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    // Withdraw Money
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful!");
        }
    }

    // Deposit Money
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else {
            balance += amount;
            System.out.println("Deposit successful!");
        }
    }

    // Change PIN
    public void changePin(int oldPin, int newPin) {
        if (this.pin == oldPin) {
            this.pin = newPin;
            System.out.println("PIN changed successfully!");
        } else {
            System.out.println("Incorrect old PIN!");
        }
    }
}

// Main Class
public class ATMApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ATM user = new ATM(5000.0, 1234); // Initial balance & PIN

        System.out.println("===== Welcome to ATM =====");

        // PIN Authentication
        System.out.print("Enter your PIN: ");
        int inputPin = sc.nextInt();

        if (!user.validatePin(inputPin)) {
            System.out.println("Wrong PIN! Access Denied.");
            return;
        }

        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your Balance: ₹" + user.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = sc.nextDouble();
                    user.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = sc.nextDouble();
                    user.deposit(depositAmount);
                    break;

                case 4:
                    System.out.print("Enter old PIN: ");
                    int oldPin = sc.nextInt();
                    System.out.print("Enter new PIN: ");
                    int newPin = sc.nextInt();
                    user.changePin(oldPin, newPin);
                    break;

                case 5:
                    System.out.println("Thank you! Visit again.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}