import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {

        //1.Adım: Driver'a kaydol
        //2.Adım: Database'e bağlan
        Connection connection = JdbcUtils.connectToDatabase("localhost", "TechProEd", "postgres", "227672");
        //3.Adım: Statement oluştur
        Statement statement = JdbcUtils.createStatement();
        //4.Adım: Query oluştur
        /*JdbcUtils.execute("CREATE TABLE students\n" +
                "(\n" +
                "  name varchar(20),\n" +
                "  id int,\n" +
                "  address varchar(80)\n" +
                ")");*/
//        JdbcUtils.createTable("Schools", "classes varchar(20)", "teacher_name varchar(20)", "id int");
//        JdbcUtils.executeQuery("country_name", "countries");
//        JdbcUtils.executeUpdate("countries", "region_id", "2", "region_id", "5");
//        JdbcUtils.insertTable("110","VESTEL","15000");
        JdbcUtils.assertToList("country_id", "countries");

        //5.Adım: Bağlantı ve Statement'i kapat.
        JdbcUtils.closeConnectionAndStatement();



    }
}
