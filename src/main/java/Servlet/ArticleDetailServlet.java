package Servlet;

import dao.articleDao;
import model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        Integer id=Integer.parseInt(req.getParameter("id"));
        Article article= articleDao.queryByArticleId(id);
        return article;
    }
}
