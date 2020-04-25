package Servlet;

import dao.UserDao;
import dao.articleDao;
import exception.BusinessException;
import frank.Constant;
import frank.JSONUtil;
import model.Article;
import model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        //从输入流获取json数据
        InputStream inputStream=req.getInputStream();
        Article article=JSONUtil.deserialize(inputStream,Article.class);
        //根据传入数据userAccout
        User user= UserDao.queryByName(article.getUserAccout());
        if(user==null){
            throw new BusinessException("该用户不存在,无法发表", Constant.QUERY_NULL_ERROR_CODE);
        }
        //如果用户存在,就插入文章数据
        article.setUserId(user.getId());
        if(!articleDao.insert(article)){
            throw new BusinessException("插入0条数据",Constant.INSERT_ARTICLE_ERROR_CODE);
        }
        return null;
    }
}
