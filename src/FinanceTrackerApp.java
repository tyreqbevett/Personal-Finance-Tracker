import java.util.Scanner;

public class FinanceTrackerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceManager manager = new FinanceManager();

        manager.loadTransactions();

        while (true) {
            System.out.println("\n📊 Personal Finance Tracker");
            System.out.println("1️⃣ Add Income");
            System.out.println("2️⃣ Add Expense");
            System.out.println("3️⃣ Show Summary");
            System.out.println("4️⃣ Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1 || choice == 2) {
                System.out.print("Enter category: ");
                String category = scanner.nextLine();
                System.out.print("Enter amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                String type = (choice == 1) ? "Income" : "Expense";
                manager.addTransaction(type, category, amount);
                System.out.println("✅ Transaction was added successfully!");

            } else if (choice == 3) {
                manager.showSummary();
            } else if (choice == 4) {
                System.out.println("📌 Exiting... Have a nice day!");
                break;
            } else {
                System.out.println("❌ Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}
