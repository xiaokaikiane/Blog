package Servlet;

import dao.articleDao;
import model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/articleList")
public class articleListServlet extends BaseServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        //解析请求数据id=1
        Integer id=Integer.parseInt(req.getParameter("id"));
        //数据库查询id=1的用户,该用户发表的文章数据,返回给客户端
        List<Article> articles= articleDao.queryByUserId(id);
        return articles;
    }
}
