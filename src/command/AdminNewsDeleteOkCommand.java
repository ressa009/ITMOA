package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminNewsDAO;
import com.lec.beans.AdminReviewDAO;

public class AdminNewsDeleteOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int news_brd_uid = Integer.parseInt(request.getParameter("news_brd_uid"));
		
		AdminNewsDAO dao = new AdminNewsDAO();
		int cnt = 0;
		
		if (news_brd_uid > 0) {
			try {
				cnt = dao.deleteNewsByUid(news_brd_uid);
				request.setAttribute("adminNewsDeleteOk", cnt);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}