import java.io.*;
import java.util.*;

// Custom Exceptions
class MinimumBalanceException extends Exception {
    MinimumBalanceException(String msg) { super(msg); }
}

class InvalidCustomerIDException extends Exception {
    InvalidCustomerIDException(String msg) { super(msg); }
}

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String msg) { super(msg); }
}

class NegativeAmountException extends Exception {
    NegativeAmountException(String msg) { super(msg); }
}

public class Main {

    static Scanner sc = new Scanner(System.in);
    static final String FILE = "customers.txt";

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Banking System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Withdraw");
            System.out.println("3. Display All");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1: createAccount(); break;
                    case 2: withdraw(); break;
                    case 3: displayAll(); break;
                    case 4: System.out.println("Exit"); break;
                    default: System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 4);
    }

    // ✅ Check if CID already exists
    static boolean cidExists(int cid) throws IOException {
        File file = new File(FILE);
        if (!file.exists()) return false;

        Scanner fileSc = new Scanner(file);
        while (fileSc.hasNext()) {
            int id = fileSc.nextInt();
            String name = fileSc.next();
            double amt = fileSc.nextDouble();

            if (id == cid) {
                fileSc.close();
                return true;
            }
        }
        fileSc.close();
        return false;
    }

    // ✅ Create Account
    static void createAccount() throws Exception {
        System.out.print("Enter CID (1-20): ");
        int cid = sc.nextInt();

        if (cid < 1 || cid > 20)
            throw new InvalidCustomerIDException("CID must be 1–20");

        if (cidExists(cid))
            throw new InvalidCustomerIDException("CID already exists");

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Amount: ");
        double amt = sc.nextDouble();

        if (amt < 0)
            throw new NegativeAmountException("Amount must be positive");

        if (amt < 1000)
            throw new MinimumBalanceException("Minimum balance is 1000");

        FileWriter fw = new FileWriter(FILE, true);
        fw.write(cid + " " + name + " " + amt + "\n");
        fw.close();

        System.out.println("Account Created Successfully");
    }

    // ✅ Get Balance from File
    static double getBalance(int cid) throws Exception {
        File file = new File(FILE);
        if (!file.exists()) throw new Exception("No records found");

        Scanner fileSc = new Scanner(file);

        while (fileSc.hasNext()) {
            int id = fileSc.nextInt();
            String name = fileSc.next();
            double amt = fileSc.nextDouble();

            if (id == cid) {
                fileSc.close();
                return amt;
            }
        }

        fileSc.close();
        throw new Exception("Customer not found");
    }

    // ✅ Withdraw (updates file)
    static void withdraw() throws Exception {
        System.out.print("Enter CID: ");
        int cid = sc.nextInt();

        double balance = getBalance(cid);

        System.out.println("Current Balance: " + balance);

        System.out.print("Enter withdraw amount: ");
        double w = sc.nextDouble();

        if (w < 0)
            throw new NegativeAmountException("Amount must be positive");

        if (w > balance)
            throw new InsufficientBalanceException("Insufficient balance");

        double newBalance = balance - w;

        updateRecord(cid, newBalance);

        System.out.println("Withdraw successful. New Balance: " + newBalance);
    }

    // ✅ Update file after withdrawal
    static void updateRecord(int cid, double newAmt) throws Exception {
        File file = new File(FILE);
        File temp = new File("temp.txt");

        Scanner fileSc = new Scanner(file);
        FileWriter fw = new FileWriter(temp);

        while (fileSc.hasNext()) {
            int id = fileSc.nextInt();
            String name = fileSc.next();
            double amt = fileSc.nextDouble();

            if (id == cid)
                fw.write(id + " " + name + " " + newAmt + "\n");
            else
                fw.write(id + " " + name + " " + amt + "\n");
        }

        fileSc.close();
        fw.close();

        file.delete();
        temp.renameTo(file);
    }

    // ✅ Display all records
    static void displayAll() throws Exception {
        File file = new File(FILE);
        if (!file.exists()) {
            System.out.println("No records found");
            return;
        }

        Scanner fileSc = new Scanner(file);

        System.out.println("\n--- Customer Records ---");
        while (fileSc.hasNext()) {
            System.out.println(
                fileSc.nextInt() + " " +
                fileSc.next() + " " +
                fileSc.nextDouble()
            );
        }

        fileSc.close();
    }
}