package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminReviewDAO;
import com.lec.beans.ReviewDAO;
import com.lec.beans.ReviewDTO;

public class ReviewWriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String ins_name = request.getParameter("ins_name");
		String review_brd_title = request.getParameter("review_brd_title");
		String review_brd_content = request.getParameter("review_brd_content");
		
		ReviewDTO [] arr = null;
		ReviewDAO dao = new ReviewDAO();
		
		if (ins_name != null) {
			try {
				ins_name = "%" + ins_name + "%";
				arr = dao.selectInsByName(ins_name);
				request.setAttribute("reviewWrite", arr);
				request.setAttribute("reviewBrdTitle", review_brd_title);
				request.setAttribute("reviewBrdContent", review_brd_content);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
	}

}