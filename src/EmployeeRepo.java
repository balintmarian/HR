import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeRepo {
    private SimpleDate d = new SimpleDate();
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private Connection con = DBConnection.getConnection();
    private Statement stm = con.createStatement();
    private PreparedStatement prepStmInser = con.prepareStatement("INSERT INTO employees (emp_no,birth_date,first_name,last_name,gender,hire_date)" +
            "VALUES(?,?,?,?,?,?)");
    private PreparedStatement pstm = con.prepareStatement("select dept_no as deptId from departments where dept_name=?");
    private PreparedStatement pstm1 = con.prepareStatement("insert into departments values (?,?)");
    private PreparedStatement pstm2 = con.prepareStatement("insert into dept_emp values(?,?,?,?)");
    private PreparedStatement pstmDelete = con.prepareStatement("delete from employees where first_name=? and last_name=?");
    private Scanner sc = new Scanner(System.in);

    public EmployeeRepo() throws SQLException {
    }

//    public SimpleDate getDate()  {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        //Scanner sc = new Scanner(System.in);
//        System.out.println("Example: 2018-12-10");
//        String str = sc.nextLine();
//        SimpleDate date = null;
//        try {
//            date = sdf.parse(str);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        return date;
//    }

    public void printEmployeeInfo() {
        //ResultSet rs = stm.executeQuery("");
        for (Employee e : employeeList) {
            System.out.println(e.toString());
        }
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
//            for (Employee e : employeeList) {
//                System.out.println(e.toString());
//            }
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
        //System.out.print("employ id: ");
        // int empId = sc.nextInt();
        //System.out.print("employ birth date: ");
        //SimpleDate birthDate = SimpleDate.valueOf(sc.nextLine());
        System.out.print("birth SimpleDate: ");
        String birthDate = sc.next();
        System.out.print("first name: ");
        String firstName = sc.next();
        System.out.print("last name: ");
        String lastName = sc.next();
        System.out.print("gender: ");
        String gender = sc.next();
        System.out.print("hire date: ");
        String hireDate = sc.next();

        int empId = -1;
        try {
            empId = generateEmployId();
            prepStmInser.setInt(1, empId);
            //prepStmInser.setDate(1, birthDate);
            prepStmInser.setString(2, birthDate);
            prepStmInser.setString(3, firstName);
            prepStmInser.setString(4, lastName);//11201
            prepStmInser.setString(5, gender);
            prepStmInser.setString(6, hireDate);

            prepStmInser.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Does this employee work in a department? Yes/No");
        String answer = sc.nextLine();
        if (answer.toLowerCase().contains("no")) {
            System.out.println("This employee doesn't work");
        } else {
            if (empId != -1) {
                setEmployeeDepartment(empId);
            }
        }
    }

    public void setEmployeeDepartment(int empId) {

        //if i find the deprtment for this employee ill add him to it, if not i create the department

        String deptId=null ;
        try {
            String department = sc.next();
            pstm.setString(1, department);
            pstm.execute();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()) {
                deptId = rs.getString("deptId");// get the dep id
                //
            } else {
                ///create
                ResultSet rsLastDeptId = stm.executeQuery("select max(dept_no) as deptId from departments");
                rsLastDeptId.next();
                //stm.executeQuery("")
                deptId = generateDeptId(rsLastDeptId.getString(1));
                pstm1.setString(1, deptId);
                pstm1.setString(2, department);
                pstm1.execute();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (empId != -1 || deptId != null) {
            try {
                pstm2.setInt(1, empId);
                pstm2.setString(2, deptId);
                System.out.println("Enter hire date: ");
                pstm2.setDate(3, new java.sql.Date(d.createDate().getTime()));
                pstm2.setDate(4, new java.sql.Date(d.getLastDate().getTime()));//date: 9999-01-01
                pstm2.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int generateEmployId() {
        ResultSet rs1;
        int empId = 0;
        try {
            rs1 = stm.executeQuery("select max(emp_no) as emp_no from employees");

            //System.out.println());
            rs1.next();
            empId = rs1.getInt("emp_no");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empId + 1;
    }

    public String generateDeptId(String lastDeptId) {
        String deptIdSubString = lastDeptId.substring(1);
        int deptIdNumber = Integer.parseInt(deptIdSubString);
        //deptIdNumber++;
        String deptIdNew = "d" + (deptIdNumber + 1);
        return deptIdNew;
    }

    public void updateEmployee() {
//ResultSet rs = stm.executeQuery("");

    }

    public void deleteEmployee() {
//ResultSet rs = stm.executeQuery("");
        System.out.print("Enter the first name of the employee you want to delete: ");

        String firstName = sc.nextLine();
        System.out.print("Enter the last name of the employee you want to delete: ");

        String lastName = sc.nextLine();

        try {
            pstmDelete.setString(1, firstName);
            pstmDelete.setString(2, lastName);
            pstmDelete.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
