import java.sql.SQLException;

public class Tester {
    public static void main(String[] args) {
        try {
            EmployeeRepo h=new EmployeeRepo();
            h.getEmployeesInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
