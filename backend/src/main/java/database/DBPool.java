package database;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBPool {
    static DruidDataSource dataSource;

    static {
        Properties prop = new Properties();
        try{
            prop.load(DBPool.class.getClassLoader().getResourceAsStream("configs.properties"));
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(prop);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon(){
        try{
            return dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
