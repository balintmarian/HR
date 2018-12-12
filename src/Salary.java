public class Salary {
    private long salary;
    private String fromDate;
    private String toDate;

    public Salary(long salary, String fromDate, String toDate) {
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salary=" + salary +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
