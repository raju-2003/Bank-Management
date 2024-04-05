import java.util.Scanner;
import java.util.ArrayList;

class Account {
    private int balance;
    private String name;
    private String accNo;
    private String type;
    private boolean status;
    private ArrayList<Transaction> transactionHistory;

    public Account(String name, String accNo, String type) {
        this.name = name;
        this.accNo = accNo;
        this.type = type;
        balance = 0;
        transactionHistory = new ArrayList<>();
    }
    public void deposit(int amount){
        balance += amount;
        System.out.println();
        System.out.println("Amount deposited successfully");
    }
    
    public void withdraw(int amount){
        if(amount > balance){
            System.out.println();
            System.out.println("Insufficient balance");
            status = false;
        } else {
            balance -= amount;
            System.out.println();
            System.out.println("Amount withdrawn successfully");
            status = true;
        }
    }
    
    public void balance(){
        System.out.println();
        System.out.println("Balance: " + balance);
    }
    
    public String getaccNo(){
        return accNo;
    }
    
    public boolean getstatus(){
        return status;
    }

    public void addTransaction(Transaction t) {
        transactionHistory.add(t);
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

class Transaction {
    private String accNo;
    private String type;
    private int amount;
    private boolean status;

    public Transaction(String accNo, String type, int amount, boolean status) {
        this.accNo = accNo;
        this.type = type;
        this.amount = amount;
        this.status = status;
    }

    public void display() {
        System.out.println();
        System.out.println("Transaction history");
        //give as a table format 
        System.out.println("Account No: " + accNo + " | Type: " + type + " | Amount: " + amount + " | Status: " + status);
        

    }
}

public class Banking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("------------------");
        System.out.println("1. Create account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Balance");
        System.out.println("5. Transaction history");
        System.out.println("6. Exit");

        Account acc = null;

        while (true) {
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.print("Enter name: ");
                    String name = sc.next();
                    System.out.print("Enter account no: ");
                    String accNo = sc.next();
                    System.out.print("Enter account type: ");
                    String type = sc.next();
                    acc = new Account(name, accNo, type);
                    break;

                case 2:
                    System.out.println();
                    System.out.print("Enter amount to deposit: ");
                    int amount = sc.nextInt();
                    acc.deposit(amount);
                    Transaction depositTransaction = new Transaction(acc.getaccNo(), "Deposit", amount, acc.getstatus());
                    acc.addTransaction(depositTransaction);
                    break;

                case 3:
                    System.out.println();
                    System.out.print("Enter amount to withdraw: ");
                    amount = sc.nextInt();
                    acc.withdraw(amount);
                    Transaction withdrawTransaction = new Transaction(acc.getaccNo(), "Withdraw", amount, acc.getstatus());
                    acc.addTransaction(withdrawTransaction);
                    break;

                case 4:
                    acc.balance();
                    break;

                case 5:
                    ArrayList<Transaction> history = acc.getTransactionHistory();
                    for (Transaction t : history) {
                        t.display();
                    }
                    break;

                case 6:
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid choice");
            }
        }
    }
}
