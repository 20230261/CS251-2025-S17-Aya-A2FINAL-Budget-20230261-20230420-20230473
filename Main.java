

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

class User {
    static User currentUser = null;
    static Scanner input = new Scanner(System.in);

    int userId;
    String name;
    String email;
    String password;

    void logout() {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }

    void signup() {
        System.out.print("Enter Email: ");
        email = input.nextLine();
        if (!email.contains("@")) {
            System.out.println("Invalid email.");
            return;
        }

        System.out.print("Enter Username: ");
        name = input.nextLine();

        System.out.print("Enter Password: ");
        password = input.nextLine();
        if (password.length() < 6) {
            System.out.println("Password too short.");
            return;
        }

        userId = (int)(Math.random() * 10000);

        List<String> userData = Arrays.asList(
            "UserID: " + userId,
            "Username: " + name,
            "Email: " + email,
            "Password: " + password,
            "---------------"
        );

        try {
            Path path = Paths.get("users.txt");
            Files.write(path, userData, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Sign-Up Successful!");
        } catch (IOException e) {
            System.out.println("Error saving user data.");
        }
    }

    void login() {
        System.out.print("Enter Email: ");
        String enteredEmail = input.nextLine();
        System.out.print("Enter Password: ");
        String enteredPassword = input.nextLine();

        try {
            Path path = Paths.get("users.txt");
            List<String> lines = Files.readAllLines(path);
            boolean found = false;

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith("Email: ")) {
                    String savedEmail = lines.get(i).substring(7).trim();
                    String savedPassword = lines.get(i + 1).substring(9).trim();
                    String savedName = lines.get(i - 1).substring(9).trim();
                    int savedUserId = Integer.parseInt(lines.get(i - 2).substring(8).trim());

                    if (savedEmail.equals(enteredEmail) && savedPassword.equals(enteredPassword)) {
                        currentUser = new User();
                        currentUser.email = savedEmail;
                        currentUser.password = savedPassword;
                        currentUser.name = savedName;
                        currentUser.userId = savedUserId;
                        found = true;
                        break;
                    }
                }
            }

            if (found) System.out.println("Login successful!");
            else System.out.println("Invalid email or password.");
        } catch (IOException e) {
            System.out.println("Error reading user data.");
        }
    }
}

class Income {
    double amount;
    boolean isRecurring;

    void addIncome(User user) {
        if (user == null) return;

        System.out.print("Enter income amount: ");
        while (!User.input.hasNextDouble()) {
            System.out.println("Invalid input.");
            User.input.next();
        }
        amount = User.input.nextDouble();
        User.input.nextLine();

        System.out.print("Is it recurring? (yes/no): ");
        String response = User.input.nextLine().toLowerCase();
        isRecurring = response.equals("yes");

        String filename = "income_" + user.userId + ".txt";

        List<String> incomeData = Arrays.asList(
            "Amount: " + amount,
            "IsRecurring: " + isRecurring,
            "---------------"
        );



try {
            Files.write(Paths.get(filename), incomeData, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Income added.");
        } catch (IOException e) {
            System.out.println("Error saving income.");
        }
    }
}

class Expense {
    double amount;
    String category;
    boolean isRecurring;

    void addExpense(User user) {
        if (user == null) return;

        System.out.print("Enter expense amount: ");
        while (!User.input.hasNextDouble()) {
            System.out.println("Invalid input.");
            User.input.next();
        }
        amount = User.input.nextDouble();
        User.input.nextLine();

        System.out.print("Enter category: ");
        category = User.input.nextLine();

        System.out.print("Is it recurring? (yes/no): ");
        String response = User.input.nextLine().toLowerCase();
        isRecurring = response.equals("yes");

        String filename = "expense_" + user.userId + ".txt";

        List<String> expenseData = Arrays.asList(
            "Amount: " + amount,
            "Category: " + category,
            "IsRecurring: " + isRecurring,
            "---------------"
        );

        try {
            Files.write(Paths.get(filename), expenseData, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Expense added.");
        } catch (IOException e) {
            System.out.println("Error saving expense.");
        }
    }
}

class Budget {
    double amount;
    String category;

    void createBudget(User user) {
        if (user == null) return;

        System.out.print("Enter budget category: ");
        category = User.input.nextLine();

        System.out.print("Enter budget amount: ");
        while (!User.input.hasNextDouble()) {
            System.out.println("Invalid input.");
            User.input.next();
        }
        amount = User.input.nextDouble();
        User.input.nextLine();

        String filename = "budget_" + user.userId + ".txt";

        List<String> budgetData = Arrays.asList(
            "Category: " + category,
            "Amount: " + amount,
            "---------------"
        );

        try {
            Files.write(Paths.get(filename), budgetData, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Budget created.");
        } catch (IOException e) {
            System.out.println("Error saving budget.");
        }
    }

    void viewBudgets(User user) {
        if (user == null) return;

        String filename = "budget_" + user.userId + ".txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            if (lines.isEmpty()) {
                System.out.println("No budgets created yet.");
            } else {
                for (String line : lines) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading budget data.");
        }
    }

    void analyzeSpending(User user) {
        if (user == null) return;

        String budgetFile = "budget_" + user.userId + ".txt";
        String expenseFile = "expense_" + user.userId + ".txt";

        try {
            List<String> budgetLines = Files.readAllLines(Paths.get(budgetFile));
            List<String> expenseLines = Files.readAllLines(Paths.get(expenseFile));

            double totalBudget = 0;
            double totalSpent = 0;

            for (String line : budgetLines) {
                if (line.startsWith("Amount: ")) {
                    totalBudget += Double.parseDouble(line.substring(8).trim());
                }
            }

            for (String line : expenseLines) {
                if (line.startsWith("Amount: ")) {
                    totalSpent += Double.parseDouble(line.substring(8).trim());
                }
            }

            System.out.println("Total Budget: " + totalBudget);
            System.out.println("Total Spent: " + totalSpent);


if (totalSpent > totalBudget) {
                System.out.println(" You are over budget!");
            } else {
                System.out.println("You are within budget.");
            }

        } catch (IOException e) {
            System.out.println("Error analyzing spending.");
        }
    }
}

class Transaction {
    String description;
    String date;
    String time;

    void createTransaction(User user) {
        if (user == null) return;

        System.out.print("Enter description: ");
        description = User.input.nextLine();

        System.out.print("Enter due date (YYYY-MM-DD): ");
        date = User.input.nextLine();

        System.out.print("Enter due time (HH:MM): ");
        time = User.input.nextLine();

        String filename = "transaction_" + user.userId + ".txt";

        List<String> transactionData = Arrays.asList(
            "Description: " + description,
            "Date: " + date,
            "Time: " + time,
            "---------------"
        );

        try {
            Files.write(Paths.get(filename), transactionData, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Transaction added.");
        } catch (IOException e) {
            System.out.println("Error saving transaction.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Income income = new Income();
        Expense expense = new Expense();
        Budget budget = new Budget();
        Transaction transaction = new Transaction();

        while (true) {
            System.out.println("1. Sign up\n2. Login");
            String option = User.input.nextLine();

            if (option.equals("1")) {
                user.signup();
                user.login();
            } else if (option.equals("2")) {
                user.login();
            } else {
                System.out.println("Invalid choice.");
                continue;
            }

            if (User.currentUser != null) {
                while (true) {
                    System.out.println("\n--- Main Menu ---");
                    System.out.println("1. Add Income");
                    System.out.println("2. Add Expense");
                    System.out.println("3. Add Budget");
                    System.out.println("4. View Budgets");
                    System.out.println("5. Analyze Spending");
                    System.out.println("6. Add Reminder");
                    System.out.println("7. Logout");
                    System.out.print("Choose option: ");
                    String choice = User.input.nextLine();

                    switch (choice) {
                        case "1":
                            income.addIncome(User.currentUser);
                            break;
                        case "2":
                            expense.addExpense(User.currentUser);
                            break;
                        case "3":
                            budget.createBudget(User.currentUser);
                            break;
                        case "4":
                            budget.viewBudgets(User.currentUser);
                            break;
                        case "5":
                            budget.analyzeSpending(User.currentUser);
                            break;
                        case "6":
                            transaction.createTransaction(User.currentUser);
                            break;
                        case "7":
                            user.logout();
                            return;
                        default:
                            System.out.println("Invalid choice.");
                    }
                }
            }
        }
    }
}
