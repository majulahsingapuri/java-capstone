package capstone;

import capstone.Enums.AccountType;
import capstone.Objects.Database;
import java.util.Date;
import org.joda.time.DateTime;

public class Manage_SQL_Data {
  public static void main(String[] args) {
    new Database();
    // Convert LocalDate to Date
    Date dob_date = DateTime.now().toDate();

    // Database.createAdmin("admin_1", "123456", "lastname_admin1", "firstname_admin1");
    // Database.createAdmin("admin_2", "123456", "lastname_admin2", "firstname_admin2");
    // Database.createTeller("teller_1", "123456", "firstname_teller1", "lastname_teller1");
    // Database.createTeller("teller_2", "123456", "firstname_teller2", "firstname_teller2");
    Database.createCustomer(
        "customer_1",
        "123456",
        "cus_fn_1",
        "cus_fn_1",
        "cus1",
        "cus1@email.com",
        dob_date,
        "hello road_1",
        "111111");
    Database.createCustomer(
        "customer_2",
        "123456",
        "cus_fn_2",
        "cus_fn_2",
        "cus2",
        "cus2@email.com",
        dob_date,
        "hello road_2",
        "222222");

    Database.createAccount("customer_1", AccountType.CURRENT);
    Database.createAccount("customer_1", AccountType.CURRENT); // 2nd account for same username
    Database.createAccount("customer_1", AccountType.SAVINGS); // 2nd account for same username

    Database.createAccount("customer_2", AccountType.CURRENT); // 2nd account for same username
    // Database.createAccount("bhargav", AccountType.SAVINGS);

    // Database.getCustomer("SuperiorMonLBJ");
    Database.close();
  }
}
