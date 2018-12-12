public class Employee {
    private String firstName;
    private String lastName;
    private Department department;//must be list
    private Salary salary;//must be list
    private Title title;
    //private Manager manager;

    public Employee(String firstName, String lastName, Department department, Salary salary, Title title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
        this.title = title;
        //this.manager = manager;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department.toString() +
                ", salary=" + salary.toString() +
                ", title=" + title.toString() +
                '}';
    }
}
