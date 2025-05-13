This Java program is designed to manage a personal budget. It allows users to sign up and 
log in, then track their incomes, expenses, budgets, and set reminders for important 
financial activities. The system uses file storage to save user information and their financial 
records. The application is written using object-oriented programming principles, with each 
feature implemented in a separate class to organize the code and responsibilities clearly. 
The core structure of the program consists of several main classes: User, Income, Expense, 
Budget, Reminder, and Main. Each class serves a specific purpose and contains the methods 
required to handle its functionality. Below is a detailed explanation of each class and its 
methods. 
1.The first class is the User class. It is responsible for user registration, login, and logout. It 
includes the necessary fields to store user data such as user ID, name, email, and password. 
There is also a static field called currentUser, which keeps track of the currently logged-in 
user across the application. 
The signup method is the first function in the User class. This method allows a new user to 
create an account. It asks the user to input an email address and checks whether it contains 
the "@" symbol, which is a simple validation for correct email format. Then it asks for the 
user’s name and password. If the password is shorter than six characters, it displays an error 
and stops the registration process. If all inputs are valid, the system generates a random 
user ID and saves all user data to a text file named "users.txt". The data is stored line by line, 
with a separator line to distinguish between users. 
The login method allows a user to log in to their account. It takes the entered email and 
password, reads the "users.txt" file, and searches for a match. If a match is found, the 
method reads the related user data, creates a new User object, and sets it as the current 
user. If the login credentials are incorrect or not found, an error message is displayed. This 
method also handles exceptions such as file not found or other input/output errors. 
The logout method is very simple. It resets the currentUser to null and displays a message 
confirming that the user has logged out successfully. 
2.The second class is the Income class. This class manages income entries for the logged-in 
user. It contains two attributes: amount and a boolean value indicating whether the income 
is recurring. The addIncome method allows users to add a new income. First, it checks if a 
user is logged in. If not, it displays a message asking the user to log in first. If a user is logged 
in, the method asks the user to input the amount and whether the income is recurring or 
not. The data is then saved to a file named "incomes.txt", along with the user's ID for 
tracking. 
3.Next is the Expense class. It is very similar to the Income class but handles spending 
instead. It also has two attributes: amount and category. The addExpense method begins by 
checking if a user is logged in. If the user is not logged in, a message is shown. Otherwise, 
the method prompts the user to enter the amount and the category of the expense. It then 
saves the data to a file called "expenses.txt", also storing the user ID to link the expense to 
the correct user. 
4.The Budget class allows users to set a target for how much they want to spend in a 
specific category. It contains three attributes: the category, the amount, and the month for 
which the budget is being set. The addBudget method checks if a user is logged in. If not, 
the user is prompted to log in. Otherwise, it collects the necessary data and saves the 
budget to a file named "budgets.txt". This way, users can plan their spending and track how 
much they intend to spend in different areas. 
5.The Reminder class helps users remember upcoming financial events, such as bill 
payments or income dates. It contains two fields: description and date. The addReminder 
method works like the other methods. It first checks if a user is logged in, then collects the 
description and the date of the reminder. The data is saved to a file named "reminders.txt" 
and linked to the current user's ID. 
6.The final class is the Main class. This is where the application starts. It includes a loop that 
repeatedly shows the user a menu and waits for input. Depending on what the user selects, 
the program will perform the corresponding action. The user can choose to sign up, log in, 
log out, add income, add expenses, set a budget, add a reminder, or exit the application. 
The loop continues until the user chooses to exit. 
In conclusion, this Java program is a simple but complete system for managing personal 
finances. It includes user account management, income tracking, expense recording, 
budgeting, and reminders. It uses basic file handling to store and retrieve data, and all user
related actions are protected by login checks to ensure data security. The program structure 
follows a modular design, making it easy to maintain or expand in the future.
