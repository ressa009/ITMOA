package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminReviewDAO;
import com.lec.beans.ReviewDTO;

public class ReviewUpdateViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int review_brd_uid = Integer.parseInt(request.getParameter("review_brd_uid"));
		String review_brd_title = request.getParameter("review_brd_title");
		String review_brd_content = request.getParameter("review_brd_content");
		
		AdminReviewDAO dao = new AdminReviewDAO();
		ReviewDTO [] arr = null;
		
		if (review_brd_uid > 0) {
			try {
				arr = dao.readReviewByUid(review_brd_uid);
				request.setAttribute("reviewUpdateView", arr);
				request.setAttribute("reviewBrdTitle", review_brd_title);
				request.setAttribute("reviewBrdContent", review_brd_content);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}