public class Employee {
    private String firstName;
    private String lastName;
    private Department department;
    private Salary salary;
    private Title title;
    private Manager manager;

    public Employee(String firstName, String lastName, Department department, Salary salary, Title title, Manager manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
        this.title = title;
        this.manager = manager;
    }
}
