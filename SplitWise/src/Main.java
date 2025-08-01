import model.EqualSplit;
import model.ExactSplit;
import model.User;
import service.ExpenseManager;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();

        // Adding users
        User user1 = new User("u1", "User1", "user1@example.com");
        User user2 = new User("u2", "User2", "user2@example.com");
        User user3 = new User("u3", "User3", "user3@example.com");

        expenseManager.addUser(user1);
        expenseManager.addUser(user2);
        expenseManager.addUser(user3);

        // Create an expense: User1 paid 1200, split equally between User1, User2, User3
        expenseManager.addExpense(1200, user1, Arrays.asList(
                new EqualSplit(user1),
                new EqualSplit(user2),
                new EqualSplit(user3)
        ));

        // Show all balances
        System.out.println("Balances after first expense:");
        expenseManager.showBalances();

        // Create another expense: User2 paid 1500, split exactly (User1 owes 600, User3 owes 900)
        expenseManager.addExpense(1500, user2, Arrays.asList(
                new ExactSplit(user1, 600),
                new ExactSplit(user3, 900)
        ));

        // Show all balances again
        System.out.println("Balances after second expense:");
        expenseManager.showBalances();
    }
}