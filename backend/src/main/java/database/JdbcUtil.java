package database;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

// JdbcUtil obj = new JdbcUtil();  obj.getCon()
// JdbcUtil obj = new JdbcUtil();  obj.createStatement();
// JdbcUtil.getCon();
public class JdbcUtil {

    final String URL = "jdbc:mysql://localhost:3306/del?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    final String USERNAME = "root";
    final String PASSWORD = "admin";
    PreparedStatement ps = null;
    Connection con = null;

    //-------------通过全局作用域对象得到Connetion-----------start
    public Connection getCon(HttpServletRequest request) {

        //1.通过请求对象，得到全局作用域对象
        ServletContext application = request.getServletContext();
        //2.从全局作用域对象得到map
        Map map = (Map) application.getAttribute("key1");
        //3.从map得到一个处于空闲状态Connection(拖鞋)
        Iterator it = map.keySet().iterator();//map.keySet()得到所有 的key
        while (it.hasNext()) {
            con = (Connection) it.next();
            boolean flag = (boolean) map.get(con);
            if (flag == true) {
                map.put(con, false);
                System.out.println("从连接池拿连接----getcon-="+con);
                break;
            }
        }
        return con;
    }

    public PreparedStatement createStatement(String sql, HttpServletRequest request) {

        try {
            ps = getCon(request).prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void close(HttpServletRequest request) {


        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ServletContext application = request.getServletContext();
        Map map = (Map) application.getAttribute("key1");
        map.put(con, true);
        System.out.println("用完放回连接池--------getcon-----"+con);

    }
    //-------------通过全局作用域对象得到Connetion-----------end

    //将jar包中driver实现类加载到JVM中
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //封装连接通道创建细节
    public Connection getCon() {
        //每次新建连接
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("创新建连接从= "+con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //封装交通工具创建细节
    public PreparedStatement createStatement(String sql) {

        try {
            ps = getCon().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    // ps与con销毁细节 insert,update,delete
    public void close() {

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (con != null) {
            try {
                System.out.println("关闭连接"+con);

                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //select ps,con,rs
    public void close(ResultSet rs) {
        System.out.println("00rsrsrsrsrsrsrsrsrsrs000000");
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();
    }
}
