import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeRepo {
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private Connection con = DBConnection.getConnection();
    private Statement stm = con.createStatement();

    public EmployeeRepo() throws SQLException {
    }

    public void printEmployeeInfo() {
        //ResultSet rs = stm.executeQuery("");
    }

    public void getEmployeesInfo() {
        try {
            // Connection con = connect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT e.first_name,e.last_name,s.salary ,d.dept_name,t.title " +
                    "FROM employees e " +
                    "INNER JOIN salaries s " +
                    "ON e.emp_no = s.emp_no " +
                    "INNER JOIN dept_emp de " +
                    "ON e.emp_no=de.emp_no " +
                    "INNER JOIN departments d " +
                    "ON de.dept_no=d.dept_no " +
                    "INNER JOIN titles t " +
                    "ON e.emp_no=t.emp_no ");
            //"WHERE s.to_date='9999-01-01'"

//            while (rs.next()) {
//
//            }
            while (rs.next()) {
                System.out.println(rs.getString("first_name") + " - " +
                        rs.getString("last_name") + " - " + "recent salary: " +
                        rs.getString("salary") + " - " +
                        rs.getString("dept_name") + " - " +
                        rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEmployee() {
//ResultSet rs = stm.executeQuery("");
    }

    public void updateEmployee() {
//ResultSet rs = stm.executeQuery("");
    }

    public void deleteEmployee() {
//ResultSet rs = stm.executeQuery("");
    }
}
