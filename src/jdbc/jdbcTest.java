package jdbc;
//@author 数科21-1 陈巴楚 2110181194
import javax.xml.transform.stream.StreamSource;
import java.sql.*;

public class jdbcTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ExcuteQuery();
        insert();
    }

    private static void insert(){
        Connection connection=null;
        Statement statement=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //连接
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student", "root", "cbc195");
            //创建向数据库发送sql语句的对象
            statement = connection.createStatement();
            String sql="SELECT *FROM biao";
            rs= statement.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt("id")+","+rs.getString("sex"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private static void ExcuteQuery() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student", "root", "cbc195");
        //创建向数据库发送sql语句的对象
        Statement statement = connection.createStatement();
        String sql="SELECT *FROM biao";
        ResultSet rs= statement.executeQuery(sql);
        while(rs.next()){
            System.out.println(rs.getInt("id")+","+rs.getString("name"));
        }
        rs.close();
        statement.close();
        connection.close();
    }

}
