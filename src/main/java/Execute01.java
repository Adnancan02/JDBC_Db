import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1-Adim: driveri kaydet
        Class.forName("org.postgresql.Driver");

        //2-Adim: Database e baglanma
       Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");

        //3-Adim: Statement olusturulmasi: sorgularin DB ye iletilmesi ve calistirilmasi icin
        Statement st=connection.createStatement();
        System.out.println("Connection success");

        //4-Sorgu olusturma/calistirma
        //ÖRNEK 1:"workers" adında bir tablo oluşturup "worker_id,worker_name,salary" sütunlarını ekleyiniz.

        boolean sql1=st.execute("CREATE TABLE IF NOT EXISTS workers(worker_id INT, worker_name VARCHAR(50), salary REAL)");
        System.out.println("sql1:"+sql1);

        //execute
        //DQL için kullanılırsa: ResultSet alır, geriye TRUE olarak döner
        //DDL için kullanılırsa: ResultSet almadığı için FALSE olarak döner.

        //ÖRNEK 2:"workers" tablosuna VARCHAR(20) tipinde "city" sütununu ekleyiniz.
       boolean sql2=st.execute("ALTER TABLE workers ADD COLUMN city VARCHAR(20)");
       System.out.println("sql2:"+sql2);
        //ÖRNEK 3:"workers" tablosunu SCHEMAdan siliniz.
        String query="DROP TABLE IF EXISTS workers";
        st.execute(query);

        //5-Adim: Baglantiyi ve statement kapatma
        st.close();
        connection.close();;

    }
}
