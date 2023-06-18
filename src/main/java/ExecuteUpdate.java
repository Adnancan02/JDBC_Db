import java.sql.*;

public class ExecuteUpdate {
    public static void main(String[] args) throws SQLException {
        //1-Adim: driveri kaydet
       // Class.forName("org.postgresql.Driver");

        //2-Adim: Database e baglanma
        Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");

        //3-Adim: Statement olusturulmasi: sorgularin DB ye iletilmesi ve calistirilmasi icin
        Statement st=connection.createStatement();

         //ExecuteUpdate: DML icin kullanılır: Datalari INSERT, UPDATE, DELETE
         //return: sorgudan etkilenen kayıt sayisini dondurur

        //ÖRNEK1:developers tablosunda maaşı ortalama maaştan az olanların maaşını 5111 olarak güncelleyiniz
      /*  String query="UPDATE developers SET salary=5111 WHERE salary<(SELECT AVG(salary) FROM developers)";
        int updated=st.executeUpdate(query);
        System.out.println("güncellenen kayıt sayısı:"+updated);*/

        ResultSet resultSet=st.executeQuery("SELECT name,salary FROM developers");

        while (resultSet.next()){
            System.out.println(resultSet.getString("name")+"---"+resultSet.getDouble("salary"));
        }
        System.out.println("-------------ÖRNEK 2-------------------");
        //ÖRNEK2:developers tablosuna yeni bir developer ekleyiniz.
        String query2="INSERT INTO developers(name,salary,prog_lang) VALUES('Aynur',7000,'Python')";
        int inserted=st.executeUpdate(query2);
        System.out.println("eklenen kayıt sayısı = " + inserted);

        //tüm kayıtları görelim
        ResultSet resultSet2=st.executeQuery("SELECT name,salary FROM developers");

        while (resultSet2.next()){
            System.out.println(resultSet2.getString("name")+"---"+resultSet2.getDouble("salary"));
        }

        //ÖRNEK3:developers tablosundan id'si 11 olanı siliniz.

        String query3="DELETE FROM developers WHERE id=11";
        int deleted=st.executeUpdate(query3);
        System.out.println("silinen kayıt sayısı = " + deleted);
        st.close();;
        connection.close();
















    }
}
