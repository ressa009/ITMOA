package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.ReviewDAO;

public class ReviewDeleteOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int review_brd_uid = Integer.parseInt(request.getParameter("review_brd_uid"));
		
		ReviewDAO dao = new ReviewDAO();
		int cnt = 0;
		
		if (review_brd_uid > 0) {
			try {
				cnt = dao.deleteReviewByUid(review_brd_uid);
				request.setAttribute("reviewDeleteOk", cnt);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

}