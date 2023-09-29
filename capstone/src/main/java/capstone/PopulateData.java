package capstone;

import capstone.Enums.AccountType;
import capstone.Enums.TransactionType;
import capstone.Objects.Account;
import capstone.Objects.Customer;
import capstone.Objects.Database;
import capstone.Objects.Transaction;
import java.sql.Date;

public class PopulateData {
  public static void main(String[] args) {
    // transactions

    /*** Initialize the Database */
    new Database();

    /***
     * Creates and Insert Admins into the Database
     */
    Database.createAdmin("admin_1", "password", "Suk", "Madeeq");
    Database.createAdmin("admin_2", "password", "Moe", "Lester");

    /***
     * Creates and Insert Tellers into the Database
     */
    for (int i = 0; i < 5; i++) {
      Database.createTeller("teller_" + i, "password", "Ben_" + i, "Dover_" + i);
    }

    /*** Creates customer_1 and store into the database */
    Customer customer_1 =
        Database.createCustomer(
                "customer_1",
                "password",
                "Customer",
                "1",
                "S99000001",
                "customer_1@cust.com",
                Date.valueOf("2023-09-11"),
                "Sembcorp Singapore",
                "99999901")
            .get();
    /*** Creates customer_2 and store into the database */
    Customer customer_2 =
        Database.createCustomer(
                "customer_2",
                "password",
                "Customer",
                "2",
                "S99000002",
                "customer_2@cust.com",
                Date.valueOf("2023-09-12"),
                "Orchard Road",
                "99999902")
            .get();
    /*** Creates customer_3 and store into the database */
    Customer customer_3 =
        Database.createCustomer(
                "customer_3",
                "password",
                "Customer",
                "3",
                "S99000003",
                "customer_3@cust.com",
                Date.valueOf("2023-09-13"),
                "Singapore Flyer",
                "99999903")
            .get();
    /*** Creates customer_4 and store into the database */
    Customer customer_4 =
        Database.createCustomer(
                "customer_4",
                "password",
                "Customer",
                "4",
                "S99000004",
                "customer_4@cust.com",
                Date.valueOf("2023-09-14"),
                "Shipyard",
                "99999904")
            .get();
    /*** Creates customer_5 and store into the database */
    Customer customer_5 =
        Database.createCustomer(
                "customer_5",
                "password",
                "Customer",
                "5",
                "S99000005",
                "customer_5@cust.com",
                Date.valueOf("2023-09-15"),
                "Yacht",
                "99999905")
            .get();

    /*** Creates an account for customer_1 and store into the database */
    Account cust_acc_1 = Database.createAccount(customer_1, AccountType.CURRENT).get();
    /*** Creates an account for customer_2 and store into the database */
    Account cust_acc_2 = Database.createAccount(customer_2, AccountType.SAVINGS).get();
    /*** Creates an account for customer_3 and store into the database */
    Account cust_acc_3 = Database.createAccount(customer_3, AccountType.CURRENT).get();
    /*** Creates an account for customer_4 and store into the database */
    Account cust_acc_4 = Database.createAccount(customer_4, AccountType.SAVINGS).get();
    /*** Creates an account for customer_5 and store into the database */
    Account cust_acc_5 = Database.createAccount(customer_5, AccountType.CURRENT).get();

    /*** Creates a transaction and credits into customer_1 account */
    Transaction transaction_1 =
        Database.createTransaction(customer_1, cust_acc_1, TransactionType.CREDIT, 500).get();
    Database.updateBalance(cust_acc_1, (cust_acc_1.getBalance() + transaction_1.getAmount()));

    /*** Creates a 'debit' transaction from the customer_1 account */
    Transaction transaction_2 =
        Database.createTransaction(customer_1, cust_acc_1, TransactionType.DEBIT, 300).get();
    Database.updateBalance(cust_acc_1, (cust_acc_1.getBalance() - transaction_2.getAmount()));

    /*** Creates a transaction and credits into customer_2 account */
    Transaction transaction_3 =
        Database.createTransaction(customer_2, cust_acc_2, TransactionType.CREDIT, 1000).get();
    Database.updateBalance(cust_acc_2, (cust_acc_2.getBalance() + transaction_3.getAmount()));

    /*** Creates a transaction and credits into customer_3 account */
    Transaction transaction_4 =
        Database.createTransaction(customer_3, cust_acc_3, TransactionType.CREDIT, 2020).get();
    Database.updateBalance(cust_acc_3, (cust_acc_3.getBalance() + transaction_4.getAmount()));

    /*** Creates a transaction and credits into customer_4 account */
    Transaction transaction_5 =
        Database.createTransaction(customer_4, cust_acc_4, TransactionType.CREDIT, 6969).get();
    Database.updateBalance(cust_acc_4, (cust_acc_4.getBalance() + transaction_5.getAmount()));

    /*** Creates a transaction and credits into customer_5 account */
    Transaction transaction_6 =
        Database.createTransaction(customer_5, cust_acc_5, TransactionType.CREDIT, 10).get();
    Database.updateBalance(cust_acc_5, (cust_acc_5.getBalance() + transaction_6.getAmount()));

    System.out.println("Database Updated! :)");

    /*** Closes the Database */
    Database.close();
  }
}
