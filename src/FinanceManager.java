import java.io.*;
import java.util.*;

public class FinanceManager {
    private List<Transaction> transactions = new ArrayList<>();
    private final String filename = "src/transactions.txt";

    // Load transactions from file
    public void loadTransactions() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    transactions.add(new Transaction(parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }

    // Add a transaction and save it to file
    public void addTransaction(String type, String category, double amount) {
        Transaction newTransaction = new Transaction(type, category, amount);
        transactions.add(newTransaction);
        saveToFile(newTransaction);
    }

    // Save a new transaction to file
    private void saveToFile(Transaction transaction) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(transaction.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Display transaction summary
    public void showSummary() {
        double totalIncome = 0, totalExpense = 0;
        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("Income")) {
                totalIncome += t.getAmount();
            } else {
                totalExpense += t.getAmount();
            }
        }
        System.out.println("\n📊 Financial Summary:");
        System.out.println("💰 Your Total Income Is: $" + totalIncome);
        System.out.println("💸 Your Total Expenses Are: $" + totalExpense);
        System.out.println("📈 Your Net Balance Is: $" + (totalIncome - totalExpense));
    }
}
