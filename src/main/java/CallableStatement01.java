import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechProEd", "postgres", "227672");
        Statement st = con.createStatement();

        /*
            Java'da methodlar return type sahibi olsa da olmasa da method olarak adlandırılırlar.
            SQL'de ise data return ediyorsa "function" denir. Return yapmıyorsa "procedure" olarak adlandırılır.
        */

        //CallableStatement ile function çağırmayı parametrelendireceğiz.
        //1.Adım: Function kodunu yaz.
        String sql1 = "create or replace function toplamaF (x numeric, y numeric)\n" +
                "returns numeric \n" +
                "language plpgsql\n" +
                "as\n" +
                "$$\n" +
                "begin\n" +
                "\n" +
                "return x + y;\n" +
                "\n" +
                "end\n" +
                "$$";

        //2.Adım: Function'u çalıştır.
        st.execute(sql1);

        //3.Adım: Function'u çağır.
        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?, ?)}"); // İlk parametre function'ın return type'ı

        //4.Adım: Return için registerOurParameter() methodunu, parametreler için ise set() vb. methodları uygula.
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2, 6);
        cst1.setInt(3, 4);

        //5.Adım: Execute methodu ile CallableStatement'i çalıştır.
        cst1.execute();

        //6.Adım: Sonucu çağırmak için return data type tipine göre
        System.out.println(cst1.getBigDecimal(1));



        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        String sql2 = "create or replace function konininHacmi (r numeric, h numeric)\n" +
                "returns numeric \n" +
                "language plpgsql\n" +
                "as\n" +
                "$$\n" +
                "begin\n" +
                "\n" +
                "return 3.14*r*r*h/3;\n" +
                "\n" +
                "end\n" +
                "$$";
        st.execute(sql2);
        CallableStatement cst2 = con.prepareCall("{? = call konininHacmi(?, ?)}");
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2, 1);
        cst2.setInt(3, 6);
        cst2.execute();
        System.out.printf("%.2f", cst2.getBigDecimal(1));







    }
}
