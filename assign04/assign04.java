import java.io.*;
import java.util.*;

// User-defined Exceptions
class MinimumBalanceException extends Exception {
    MinimumBalanceException(String msg) {
        super(msg);
    }
}

class InvalidCustomerIDException extends Exception {
    InvalidCustomerIDException(String msg) {
        super(msg);
    }
}

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String msg) {
        super(msg);
    }
}

class NegativeAmountException extends Exception {
    NegativeAmountException(String msg) {
        super(msg);
    }
}

// Customer Class
class Customer {
    int cid;
    String cname;
    double amount;

    Customer(int cid, String cname, double amount) {
        this.cid = cid;
        this.cname = cname;
        this.amount = amount;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Withdraw Amount");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            try {
                switch (choice) {

                    case 1:
                        createAccount();
                        break;

                    case 2:
                        withdrawAmount();
                        break;

                    case 3:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 3);
    }

    // Create Account
    static void createAccount() throws Exception {
        System.out.print("Enter Customer ID (1-20): ");
        int cid = sc.nextInt();

        if (cid < 1 || cid > 20)
            throw new InvalidCustomerIDException("CID must be between 1 and 20");

        System.out.print("Enter Customer Name: ");
        String cname = sc.next();

        System.out.print("Enter Initial Amount: ");
        double amount = sc.nextDouble();

        if (amount < 0)
            throw new NegativeAmountException("Amount must be positive");

        if (amount < 1000)
            throw new MinimumBalanceException("Minimum balance is Rs. 1000");

        Customer c = new Customer(cid, cname, amount);

        // Write to file
        FileWriter fw = new FileWriter("customers.txt", true);
        fw.write(c.cid + " " + c.cname + " " + c.amount + "\n");
        fw.close();

        System.out.println("Account created and saved to file.");
    }

    // Withdraw Amount
    static void withdrawAmount() throws Exception {
        System.out.print("Enter Current Balance: ");
        double total = sc.nextDouble();

        System.out.print("Enter Withdrawal Amount: ");
        double wth_amt = sc.nextDouble();

        if (wth_amt < 0)
            throw new NegativeAmountException("Amount must be positive");

        if (wth_amt > total)
            throw new InsufficientBalanceException("Insufficient balance");

        total = total - wth_amt;
        System.out.println("Withdrawal successful. Remaining balance: " + total);
    }
}