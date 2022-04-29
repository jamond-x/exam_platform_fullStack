package database;
import java.sql.*;
import java.io.*;
import java.util.Properties;

public class DBInit {
    public Connection connection;
    private Statement sm;
//    private static String DRIVER;
//    static {
//        Properties ppt = new Properties();
//        try{
//            InputStream in = DBInit.class.getClassLoader().getResourceAsStream("resources/configs");
//            ppt.load(in);
//            DRIVER = ppt.getProperty("driverClassName");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }
//    }
    public DBInit(String db, String user, String pw){
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db,user,pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public DBInit(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"testing","root","wenrutaishan259");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() { return this.connection; }

    public ResultSet querySql(String sql) throws SQLException {
        sm = connection.createStatement();
        return sm.executeQuery(sql);
    }
    public int executeUpdate(String sql) throws SQLException {
        return 	sm.executeUpdate(sql);
    }
    public void close() throws SQLException {
        sm.close();
        connection.close();
    }
}

