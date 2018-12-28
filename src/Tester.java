import java.sql.SQLException;
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
//        NumberFormat.getCurrencyInstance(Locale.JAPAN);
//        System.out.println("1234567.777");
//        System.out.println(Locale.getDefault());

        try {
            EmployeeRepo employeeRepository = new EmployeeRepo();
            employeeRepository.getEmployeesInfo();
            showMenu(employeeRepository);

            //h.getEmployeesInfo();
            // h.insertEmployee();
            // h.printEmployeeInfo();
            // h.deleteEmployee();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void showMenu(EmployeeRepo employeeRepository) {
        System.out.println(" -----------------------------------------------");
        System.out.println("|          Welcome to the DataBase HR   |");
        System.out.println(" -----------------------------------------------");
        System.out.println();
        System.out.println("                    MAIN MENU                   ");
        System.out.println("1. Add employee");
        System.out.println("2. Show List Employees");
        System.out.println("3. Remove employee");
        //System.out.println("4. Show History Employee");
        System.out.println("4. Exit");
        showOptionsMenu(employeeRepository);
    }

    public static void showOptionsMenu(EmployeeRepo employeeRepository) {

        Scanner sc = new Scanner(System.in);


        System.out.println("Select an action from below:");
        int number = sc.nextInt();

        switch (number) {
            case 1:
                employeeRepository.insertEmployee();
                break;
            case 2:
                employeeRepository.printEmployeeInfo();
                //employeeRepository.showListAllEmployee(employeeRepository.getAllEmployees());
                break;
            case 3:
                employeeRepository.deleteEmployee();
                break;
//                case 4:
//                    employeeRepository.showHistoryEmployee(employeeRepository.createEmployee());
//                    break;
            case 4:
                System.exit(0);
                break;
        }
        showMenu(employeeRepository);
    }
}
