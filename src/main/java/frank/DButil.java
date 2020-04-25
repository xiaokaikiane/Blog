package frank;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import exception.SystemException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DButil {
    private static volatile DataSource DATA_SOURCE;
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static final String URL="jdbc:mysql://localhost:3306/blogdemo";
    private DButil(){

    }
    private static DataSource getDataSource(){
        if(DATA_SOURCE==null){
            synchronized (DButil.class){
                if(DATA_SOURCE==null){
                    DATA_SOURCE=new MysqlDataSource();
                    ((MysqlDataSource) DATA_SOURCE).setUrl(URL);
                    ((MysqlDataSource) DATA_SOURCE).setUser(USERNAME);
                    ((MysqlDataSource) DATA_SOURCE).setPassword(PASSWORD);
                }
            }
        }
        return DATA_SOURCE;
    }
    public static java.sql.Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new SystemException("获取数据库连接失败",e,Constant.DB_ERROR_CODE);
        }
    }
    public static void close(Connection connection, Statement statement,
                             ResultSet resultSet){
        try {
            if(resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
           throw new SystemException("释放数据库资源失败",e,Constant.DB_ERROR_CODE);
        }
    }
    public static void close(Connection connection, Statement statement){
        close(connection,statement,null);
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
