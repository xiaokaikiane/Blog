package dao;

import exception.SystemException;
import frank.Constant;
import frank.DButil;
import model.User;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public static User queryByName(String name){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            connection=DButil.getConnection();
            String sql="select id,name,create_time from user where name=?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,name);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                User user=new User();
                user.setId(resultSet.getInt("id"));
                user.setName(name);
                user.setCreateTime(
                        new Date(resultSet.getTimestamp("create_time").getTime()));
                return user;
            }
            return null;
        }catch (Exception e){
            throw new SystemException("查询用户错误",e, Constant.QUERY_USER_ERROR_CODE);
        }finally {
            DButil.close(connection,statement,resultSet);
        }
    }
}
