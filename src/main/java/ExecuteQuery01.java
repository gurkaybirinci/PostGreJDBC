import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechProEd","postgres","227672");
        Statement st = con.createStatement();

        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.
        String sql1 = "select country_name from countries where region_id = '1'";
        boolean r1 = st.execute(sql1);
        System.out.println(r1);

        //Recordları görmek için ExecuteQuery() methodunu kullanmalıyız.
        ResultSet resultSet1 = st.executeQuery(sql1);
        while(resultSet1.next()){
            System.out.println(resultSet1.getString(1));// 1 değeri sütun numarasını ifade eder.
        }

        System.out.println("--------------------------");

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2 = "select country_id, country_name from countries where region_id > '2'";
        boolean r2 = st.execute(sql2);
        System.out.println(r2);
        ResultSet resultSet2 = st.executeQuery(sql2);
        while(resultSet2.next()){
            System.out.println(resultSet2.getString(1) + " " +resultSet2.getString(2));
        }

        System.out.println("--------------------------");

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        st.execute(sql3);
        ResultSet resultSet3 = st.executeQuery(sql3);
        while(resultSet3.next()){
            System.out.println(resultSet3.getInt(1) + " " +resultSet3.getString(2)+ " "+ resultSet3.getInt(3));
        }

        con.close();
        st.close();
    }
}