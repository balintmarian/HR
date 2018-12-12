public class Department {
    private String departmentName;
    private String fromDate;
    private String toDate;

    public Department(String departmentName, String fromDate, String toDate) {
        this.departmentName = departmentName;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
