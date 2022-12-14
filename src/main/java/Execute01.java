import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechProEd","postgres","227672");
        Statement st = con.createStatement();

        /*
            execute() methodu DDL(create, drop, alter table) ve DQL(select) için kullanılabilir.
            1) Eğer execute() methodu DDL için kullanılırsa "false" return eder.
            2) Eğer execute() methodu DQL için kullanılırsa, ResultSet alındığında "true", aksi halde "false" return eder.
        */

        //1.Örnek: "workers" adında bir table oluşturup "worker_id, worker_name, worker_salary" sütunlarını ekleyin.
        boolean sql1 = st.execute("create table workers(worker_id varchar(20), worker_name varchar(20), worker_salary int)");
        System.out.println(sql1);//false return eder, çünkü data çağırmıyoruz.
        st.execute("insert into workers values('007','Gürkay Birinci', 50000)");

        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        String sql2 = "alter table workers add worker_address varchar(80)";
        boolean sql2b = st.execute(sql2);
        System.out.println(sql2b);

        //3.Örnek: workers tablosunu silin
        String sql3 = "drop table workers";
        st.execute(sql3);

        //Bağlantı ve Statement'i kapat!
        con.close();
        st.close();








    }
}
