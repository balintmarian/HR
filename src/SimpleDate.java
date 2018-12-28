import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SimpleDate {
    public Scanner sc=new Scanner(System.in);
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public java.util.Date createDate()  {

        //Scanner sc = new Scanner(System.in);
        System.out.println("Example: 2018-12-10");
        String dateString = sc.nextLine();
        java.util.Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //sdf = new SimpleDateFormat("yyyy-MM-dd");

        return date;
    }
    public java.util.Date getLastDate()  {

        //Scanner sc = new Scanner(System.in);
        //System.out.println("Example: 2018-12-10");
        String dateString = "9999-01-01";
        java.util.Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //sdf = new SimpleDateFormat("yyyy-MM-dd");

        return date;
    }
}
