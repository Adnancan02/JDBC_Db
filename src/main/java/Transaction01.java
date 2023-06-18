import java.sql.*;


/*
Transaction:DB deki parçalanamaz(atomik) en küçük işlem
Birden fazla işlem için tek bir transaction oluşturabiliriz.
Bu işlemlerin tamamı başarılı bir şekilde gerçekleşirse Transaction commit edilir.
Bu işlemlerin en az birinde problem olursa rollback transaction içindeki işlemler iptal edilebilir.
 */
public class Transaction01 {
    public static void main(String[] args) throws Exception {
        Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");
        connection.setAutoCommit(false);

        try {
        //hesap no:1234 ten hesap no:5678 e 1000$ para transferi olsun.
       // String query="UPDATE hesaplar SET bakiye =bakiye-1000 WHERE hesap_no=1234";
        String query="UPDATE hesaplar SET bakiye =bakiye+? WHERE hesap_no=?";
        PreparedStatement preparedst= connection.prepareStatement(query);
        preparedst.setDouble(1,-1000);
        preparedst.setInt(2,1234);
        preparedst.executeUpdate();

        //sistemsel hata olustu
        if (false){
            throw new Exception();//uygulama burda duracak
        }
        preparedst.setDouble(1,1000);
        preparedst.setInt(2,5678);
        preparedst.executeUpdate();


        //islemler basarili ise
        connection.commit();
        preparedst.close();
        connection.close();
        }catch (Exception e){
           connection.rollback();
        }

        //commit ten sonra rollback kullanilmaz

    }
}
