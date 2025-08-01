package service;

import model.EqualSplit;
import model.ExactSplit;
import model.Split;
import model.User;

import java.util.*;

public class ExpenseManager {
    private Map<String, User> userMap; // Stores userId to User object
    private Map<String, Map<String, Double>> balances; // Balance mapping

    public ExpenseManager() {
        userMap = new HashMap<>();
        balances = new HashMap<>();
    }

    // Add a new user
    public void addUser(User user) {
        userMap.put(user.getUserId(), user);
        balances.put(user.getUserId(), new HashMap<>());
    }

    // Add an expense
    public void addExpense(double amount, User paidBy, List<Split> splits) {
        for (Split split : splits) {
            User user = split.getUser();
            if (!balances.containsKey(user.getUserId())) {
                balances.put(user.getUserId(), new HashMap<>());
            }

            double splitAmount = (split instanceof EqualSplit)
                    ? amount / splits.size()
                    : ((ExactSplit) split).getAmount();

            // Update balances
            balances.get(user.getUserId()).put(
                    paidBy.getUserId(),
                    balances.get(user.getUserId()).getOrDefault(paidBy.getUserId(), 0.0) + splitAmount
            );

            balances.get(paidBy.getUserId()).put(
                    user.getUserId(),
                    balances.get(paidBy.getUserId()).getOrDefault(user.getUserId(), 0.0) - splitAmount
            );
        }
    }

    // Show balances
    public void showBalances() {
        for (String userId : balances.keySet()) {
            for (Map.Entry<String, Double> entry : balances.get(userId).entrySet()) {
                if (entry.getValue() != 0) {
                    System.out.println(userMap.get(userId).getName() + " owes " +
                            userMap.get(entry.getKey()).getName() + ": " + entry.getValue());
                }
            }
        }
    }

    // Show balance for a specific user
    public void showBalanceForUser(String userId) {
        for (Map.Entry<String, Double> entry : balances.get(userId).entrySet()) {
            if (entry.getValue() != 0) {
                System.out.println(userMap.get(userId).getName() + " owes " +
                        userMap.get(entry.getKey()).getName() + ": " + entry.getValue());
            }
        }
    }
}
