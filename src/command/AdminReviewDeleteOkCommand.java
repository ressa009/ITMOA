package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminReviewDAO;

public class AdminReviewDeleteOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int review_brd_uid = Integer.parseInt(request.getParameter("review_brd_uid"));
		
		AdminReviewDAO dao = new AdminReviewDAO();
		int cnt = 0;
		
		if (review_brd_uid > 0) {
			try {
				cnt = dao.deleteReview(review_brd_uid);
				System.out.println(cnt);
				request.setAttribute("adminReviewDeleteOk", cnt);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}