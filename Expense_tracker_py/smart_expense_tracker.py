import csv
import os
from datetime import datetime
import matplotlib.pyplot as plt

# File to store expenses
EXPENSE_FILE = 'expenses.csv'

# Categories for expenses
CATEGORIES = ['Food', 'Travel', 'Bills', 'Shopping', 'Entertainment', 'Healthcare', 'Other']


def create_file_if_not_exists():
    """Create CSV file with headers if it doesn't exist"""
    if not os.path.exists(EXPENSE_FILE):
        with open(EXPENSE_FILE, 'w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(['Date', 'Category', 'Amount', 'Description'])
        print("New expense file created!")


def add_expense():
    """Add a new expense entry"""
    print("\n--- Add New Expense ---")
    
    # Get date
    date_input = input("Enter date (YYYY-MM-DD) or press Enter for today: ")
    if date_input == '':
        date = datetime.now().strftime('%Y-%m-%d')
    else:
        date = date_input
    
    # Show categories
    print("\nCategories:")
    for i, cat in enumerate(CATEGORIES, 1):
        print(f"{i}. {cat}")
    
    # Get category
    cat_choice = int(input("Select category (1-7): "))
    category = CATEGORIES[cat_choice - 1]
    
    # Get amount
    amount = float(input("Enter amount: "))
    
    # Get description
    description = input("Enter description: ")
    
    # Write to CSV
    with open(EXPENSE_FILE, 'a', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([date, category, amount, description])
    
    print(f"\nExpense added: {amount} for {category}")


def read_all_expenses():
    """Read all expenses from CSV file"""
    expenses = []
    
    with open(EXPENSE_FILE, 'r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            expenses.append(row)
    
    return expenses


def view_all_expenses():
    """Display all expenses"""
    expenses = read_all_expenses()
    
    if len(expenses) == 0:
        print("\nNo expenses recorded yet!")
        return
    
    print("\n--- All Expenses ---")
    print(f"{'Date':<12} {'Category':<15} {'Amount':<10} {'Description'}")
    print("-" * 60)
    
    for expense in expenses:
        print(f"{expense['Date']:<12} {expense['Category']:<15} {expense['Amount']:<10} {expense['Description']}")


def get_expenses_for_month(month, year):
    """Get expenses for a specific month"""
    all_expenses = read_all_expenses()
    monthly_expenses = []
    
    for expense in all_expenses:
        expense_date = datetime.strptime(expense['Date'], '%Y-%m-%d')
        if expense_date.month == month and expense_date.year == year:
            monthly_expenses.append(expense)
    
    return monthly_expenses


def calculate_category_totals(expenses):
    """Calculate total spending per category"""
    category_totals = {}
    
    for expense in expenses:
        category = expense['Category']
        amount = float(expense['Amount'])
        
        if category in category_totals:
            category_totals[category] = category_totals[category] + amount
        else:
            category_totals[category] = amount
    
    return category_totals


def find_highest_spending_category(category_totals):
    """Find the category with highest spending"""
    highest_category = ''
    highest_amount = 0
    
    for category, amount in category_totals.items():
        if amount > highest_amount:
            highest_amount = amount
            highest_category = category
    
    return highest_category, highest_amount


def monthly_summary():
    """Show monthly expense summary"""
    print("\n--- Monthly Summary ---")
    
    # Get month and year
    month = int(input("Enter month (1-12): "))
    year = int(input("Enter year (YYYY): "))
    
    # Get expenses for this month
    monthly_expenses = get_expenses_for_month(month, year)
    
    if len(monthly_expenses) == 0:
        print(f"\nNo expenses found for {month}/{year}")
        return
    
    # Calculate totals
    total_spent = 0
    for expense in monthly_expenses:
        total_spent = total_spent + float(expense['Amount'])
    
    # Category breakdown
    category_totals = calculate_category_totals(monthly_expenses)
    
    # Display summary
    print(f"\nMonth: {month}/{year}")
    print(f"Total Expenses: {len(monthly_expenses)}")
    print(f"Total Amount: ₹{total_spent:.2f}")
    
    print("\n--- Category Breakdown ---")
    for category, amount in category_totals.items():
        percentage = (amount / total_spent) * 100
        print(f"{category:<15}: ₹{amount:<10.2f} ({percentage:.1f}%)")
    
    # Find highest spending
    highest_cat, highest_amt = find_highest_spending_category(category_totals)
    print(f"\nHighest Spending: {highest_cat} (₹{highest_amt:.2f})")
    
    # Ask if user wants to see chart
    show_chart = input("\nShow pie chart? (y/n): ")
    if show_chart.lower() == 'y':
        create_pie_chart(category_totals, month, year)


def create_pie_chart(category_totals, month, year):
    """Create a pie chart for category spending"""
    categories = list(category_totals.keys())
    amounts = list(category_totals.values())
    
    # Create pie chart
    plt.figure(figsize=(10, 7))
    plt.pie(amounts, labels=categories, autopct='%1.1f%%', startangle=90)
    plt.title(f'Expense Breakdown - {month}/{year}')
    plt.axis('equal')
    
    # Save and show
    chart_filename = f'expenses_{month}_{year}.png'
    plt.savefig(chart_filename)
    print(f"\nChart saved as {chart_filename}")
    plt.show()


def show_menu():
    """Display main menu"""
    print("\n=== Expense Tracker ===")
    print("1. Add Expense")
    print("2. View All Expenses")
    print("3. Monthly Summary")
    print("4. Exit")


def main():
    """Main program function"""
    # Create file if needed
    create_file_if_not_exists()
    
    # Main loop
    while True:
        show_menu()
        choice = input("\nEnter choice (1-4): ")
        
        if choice == '1':
            add_expense()
        elif choice == '2':
            view_all_expenses()
        elif choice == '3':
            monthly_summary()
        elif choice == '4':
            print("\nGoodbye!")
            break
        else:
            print("\nInvalid choice! Try again.")


# Run the program
if __name__ == "__main__":
    main()