package command;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.ReviewDAO;

import command.Command;

public class ReviewUpdateOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		boolean ifNew = Boolean.parseBoolean(request.getParameter("ifNew"));
		String review_brd_title = request.getParameter("review_brd_title");
		String review_brd_content = request.getParameter("review_brd_content");
		int mb_uid = Integer.parseInt(request.getParameter("mb_uid"));
		String class_uid = request.getParameter("class_uid");
		
		ReviewDAO dao = new ReviewDAO();
		int cnt = 0;

		if (ifNew) {
			if (ifNew == true) {
				try {
					cnt = dao.insertReview(mb_uid, Integer.parseInt(class_uid), review_brd_title, review_brd_content);
					if (cnt == 1) cnt = 1;
					if (cnt == 0) cnt = 0;
					request.setAttribute("reviewUpdateOk", cnt);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		} else if (!ifNew) {
			int review_brd_uid = Integer.parseInt(request.getParameter("review_brd_uid"));
			if (review_brd_uid > 0
			&& review_brd_title != null && review_brd_title.length() > 0 && !review_brd_title.trim().equals("")
			&& review_brd_content != null && review_brd_content.length() > 0 && !review_brd_content.trim().equals("")) {
				try {
					cnt = dao.updateReviewByUid(review_brd_uid, review_brd_title, review_brd_content);
					if (cnt == 0) cnt = 2;
					if (cnt == 1) cnt = 3;
					request.setAttribute("reviewUpdateOk", cnt);
					request.setAttribute("location", review_brd_uid);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		}
	}

}