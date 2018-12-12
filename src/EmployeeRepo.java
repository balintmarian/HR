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
            // very long SELECT -->
            ResultSet rs = stm.executeQuery("SELECT e.first_name,e.last_name,s.salary ,s.from_date as s_from_date,s.to_date as s_to_date,d.dept_name as d_dept_name,de.from_date as de_from_date,de.to_date as de_to_date,t.title,t.from_date as t_from_date,t.to_date as t_to_date " +
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

            while (rs.next()) {
                employeeList.add(new Employee(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        new Department(rs.getString("d_dept_name"),
                                rs.getString("de_from_date"),
                                rs.getString("de_to_date")),
                        new Salary(rs.getLong("salary"),
                                rs.getString("s_from_date"),
                                rs.getString("s_to_date")),
                        new Title(rs.getString("title"),
                                rs.getString("t_from_date"),
                                rs.getString("t_to_date"))));
            }
            for (Employee e : employeeList) {
                System.out.println(e.toString());
            }
//            while (rs.next()) {
//                System.out.println(rs.getString("first_name") + " - " +
//                        rs.getString("last_name") + " - " + "recent salary: " +
//                        rs.getString("salary") + " - " +
//                        rs.getString("dept_name") + " - " +
//                        rs.getString("title"));
//            }
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
