package Servlet;

import dao.articleDao;
import exception.BusinessException;
import frank.JSONUtil;
import model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        Article article= JSONUtil.deserialize(req.getInputStream(),Article.class);
        if(!articleDao.update(article)){
            throw new BusinessException("aupdate001","文章修改执行数量为0");
        }
        return null;
    }
}
