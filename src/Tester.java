import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

public class Tester {
    public static void main(String[] args) {
//        NumberFormat.getCurrencyInstance(Locale.JAPAN);
//        System.out.println("1234567.777");
//        System.out.println(Locale.getDefault());
        try {
            EmployeeRepo h=new EmployeeRepo();

            //h.getEmployeesInfo();
            h.insertEmployee();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
