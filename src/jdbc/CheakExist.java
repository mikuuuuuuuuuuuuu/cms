package jdbc;
import gui.*;
import java.sql.*;
//@author 数科21-1 陈巴楚 2110181194
public class CheakExist {

    ////学号查找账号密码的函数
    public int CheakExcuteQuery(String text1, String text2) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student?useSSL=false", "root", "cbc195");
        //创建向数据库发送sql语句的对象
        Statement statement = connection.createStatement();
        String sql = "SELECT *FROM biao";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            //账号存在
            if (rs.getString("id").equals(text1) == true) {

                if (rs.getString("password").equals(text2) == false) {//密码不存在返回2
                    return 2;
                }
                else {//密码存在返回3
                    return 3;
                }
            }
        }
        rs.close();
        statement.close();
        connection.close();
        return 1;//账号密码都没有
    }


    //注册添加函数
    public void AddRegister() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student?useSSL=false", "root", "cbc195");

        String v1 = Login.jtext3.getText();
        String v2 = Login.jtext4.getText();
        String v3 = Login.jtext5.getText();
        String v4 = Login.jtext6.getText();
        String v5 = Login.jtext7.getText();
        String v6 = Login.jtext8.getText();

        String sql = "INSERT INTO `student`.`biao` (`id`, `password`, `name`, `class`, `email`, `dorm`) VALUES ('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "','" + v5 + "','" + v6 + "');";
        //创建向数据库发送sql语句的对象,preparedstatement.
        Statement st = connection.createStatement();
        int count = st.executeUpdate(sql);
        st.close();
        connection.close();
    }


    //主界面查找学号宿舍函数是否存在和注册界面学号是否存在函数
    public int ExcuteQuery(String text1, String text2) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student?useSSL=false", "root", "cbc195");
        //创建向数据库发送sql语句的对象
        Statement statement = connection.createStatement();
        String sql = "SELECT *FROM biao";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            //学号存在
            if (rs.getString("id").equals(text1) == true && rs.getString("dorm").equals(text2) == true) {//返回3是查出账号和宿舍
                return 3;
            }
            else if (rs.getString("id").equals(text1) == true) {//返回1是只查出账号
                return 1;
            }
            else if (rs.getString("dorm").equals(text2) == true) {//返回2是只查出宿舍
                return 2;
            }
        }
        rs.close();
        statement.close();
        connection.close();
        return 4;//学号没有
    }

    //主界面查找账号，把所查结果显示到文本域
    public void ExcuteQueryTest1(String text1) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student?useSSL=false", "root", "cbc195");
        //创建向数据库发送sql语句的对象
        Statement statement = connection.createStatement();
        String sql = "SELECT *FROM biao";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            //学号存在
            if (rs.getString("id").equals(text1) == true) {
                Login.j1.setText("学号：" + rs.getString("id") + "," + " 姓名：" + rs.getString("name") + "," + " 班级：" + rs.getString("class") + "," + " 邮箱：" + rs.getString("email") + "," + " 宿舍：" + rs.getString("dorm"));
            }
        }
        rs.close();
        statement.close();
        connection.close();
    }

    //主界面查找宿舍，把所查结果显示到文本域
    public void ExcuteQueryTest2(String text2) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student?useSSL=false", "root", "cbc195");
        //创建向数据库发送sql语句的对象
        Statement statement = connection.createStatement();
        String sql = "SELECT *FROM biao";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            //宿舍存在
            if (rs.getString("dorm").equals(text2) == true) {
                Login.j1.append("学号：" + rs.getString("id") + "," + " 姓名：" + rs.getString("name") + "," + " 班级：" + rs.getString("class") + "," + " 邮箱：" + rs.getString("email") + "," + " 宿舍：" + rs.getString("dorm") + "\n");
            }
        }
        rs.close();
        statement.close();
        connection.close();
    }

    //主界面查找账号宿舍，把所查结果显示到文本域
    public void ExcuteQueryTest3(String text1, String text2) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student?useSSL=false", "root", "cbc195");
        //创建向数据库发送sql语句的对象
        Statement statement = connection.createStatement();
        String sql = "SELECT *FROM biao";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            //学号存在
            if (rs.getString("id").equals(text1) == true && rs.getString("dorm").equals(text2) == true) {
                Login.j1.setText("学号：" + rs.getString("id") + "," + " 姓名：" + rs.getString("name") + "," + " 班级：" + rs.getString("class") + "," + " 邮箱：" + rs.getString("email") + "," + " 宿舍：" + rs.getString("dorm"));
            }
        }
        rs.close();
        statement.close();
        connection.close();
    }
}