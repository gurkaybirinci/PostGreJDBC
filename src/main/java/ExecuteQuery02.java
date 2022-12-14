import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechProEd", "postgres", "227672");
        Statement st = con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

        //1.YOL: OFFSET ve FETCH NEXT kullanarak
        String sql1 = "select company, number_of_employees \n" +
                "from companies\n" +
                "order by number_of_employees desc\n" +
                "offset 1 row \n" +
                "fetch next 1 row only";
        ResultSet resultSet1 = st.executeQuery(sql1);
        while(resultSet1.next()){
            System.out.println(resultSet1.getString(1) + " " +resultSet1.getInt(2));
        }

        //2.YOL SubQuery kullanarak
        String sql2 = "select company, number_of_employees from companies\n" +
                "where number_of_employees = (select max(number_of_employees) from companies\n" +
                "where number_of_employees < (select max(number_of_employees) from companies));";
        ResultSet resultSet2 = st.executeQuery(sql2);
        while(resultSet2.next()){
            System.out.println(resultSet2.getString(1) + " " +resultSet2.getInt(2));
        }

        con.close();
        st.close();
        resultSet1.close();
        resultSet2.close();

    }
}
