package dao;

import exception.SystemException;
import frank.Constant;
import frank.DButil;
import model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class articleDao {
    //插入
    public static boolean insert(Article article){
        Connection connection=null;
        PreparedStatement statement=null;
        try{
            connection= DButil.getConnection();
            String sql="insert into article(title,content,user_id,create_time)"+
                    " values (?,?,?,?)";
            statement=connection.prepareStatement(sql);
            statement.setString(1,article.getTitle());
            statement.setString(2,article.getContent());
            statement.setInt(3,article.getUserId());
            statement.setTimestamp(4,new Timestamp(new Date().getTime()));
            int num =statement.executeUpdate();
            return num>=1;
        }catch (Exception e){
            throw new SystemException("插入文章错误",e,Constant.INSERT_ARTICLE_ERROR_CODE);
        }finally {
            DButil.close(connection,statement);
        }
    }
//通过用户id查询
    public static List<Article> queryByUserId(Integer id) {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Article> articles=new ArrayList<>();
        try{
            connection= DButil.getConnection();
            String sql="select id,title,content,user_id,create_time"+
                    " from article where user_id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            //处理结果集
            while(resultSet.next()){
                Article article=new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setUserId(id);
                article.setCreateTime(new java.util.Date
                        (resultSet.getTimestamp("create_time").getTime()));
                articles.add(article);
            }
            return articles;
        }catch (Exception e){
            throw new SystemException("查询文章列表错误",e,"aquery001");
        }finally {
            DButil.close(connection,statement,resultSet);
        }
    }
//通过文章id查询
    public static Article queryByArticleId(Integer id) {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            connection= DButil.getConnection();
            String sql="select id,title,content,user_id,create_time"+
                    " from article where id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            Article article=new Article();
            //处理结果集
            while(resultSet.next()){
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setUserId(id);
                article.setCreateTime(new java.util.Date
                        (resultSet.getTimestamp("create_time").getTime()));
            }
            return article;
        }catch (Exception e){
            throw new SystemException("查询文章详情出错",e,"aquery002");
        }finally {
            DButil.close(connection,statement,resultSet);
        }
    }
//修改
    public static boolean update(Article article) {
        Connection connection=null;
        PreparedStatement statement=null;
        try{
            connection= DButil.getConnection();
            String sql="update article set title=?,content=?"+
                    " where id=?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,article.getTitle());
            statement.setString(2,article.getContent());
            statement.setInt(3,article.getId());
            int num =statement.executeUpdate();
            return num>=1;
        }catch (Exception e){
            throw new SystemException("修改文章出错",e,"aupdate002");
        }finally {
            DButil.close(connection,statement);
        }
    }
    //删除
    public static boolean delete(int[] ids) {
        Connection connection=null;
        PreparedStatement statement=null;
        try{
            connection= DButil.getConnection();
            String sql="delete from article"+
                    " where id in(";
            for(int i=0;i<ids.length;i++){
                if(i==0){
                    sql+="?";
                }else{
                    sql+=",?";
                }
            }
            sql+=")";
            statement=connection.prepareStatement(sql);
            for(int j=0;j<ids.length;j++){
                statement.setInt(j+1,ids[j]);
            }
            int num =statement.executeUpdate();
            return num>=1;
        }catch (Exception e){
            throw new SystemException("删除文章出错",e,"adelete002");
        }finally {
            DButil.close(connection,statement);
        }
    }
}
