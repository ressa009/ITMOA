package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.NewsDAO;
import com.lec.beans.NewsDTO;

public class NewsViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		NewsDAO dao = new NewsDAO();
		NewsDTO [] arr = null;
		
		int news_brd_uid = Integer.parseInt(request.getParameter("news_brd_uid"));
		
		if (news_brd_uid > 0) {
			try {
				arr = dao.selectNewsByUid(news_brd_uid);
				request.setAttribute("newsView", arr);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
