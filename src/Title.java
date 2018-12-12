public class Title {
    private String titleName;
    private String fromDate;
    private String toDate;

    public Title(String titleName, String fromDate, String toDate) {
        this.titleName = titleName;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "Title{" +
                "titleName='" + titleName + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
