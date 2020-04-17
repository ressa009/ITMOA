package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.ReviewDAO;

public class RepUpdateOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		boolean ifNew = Boolean.parseBoolean(request.getParameter("ifNew"));
		int review_brd_uid = Integer.parseInt(request.getParameter("review_brd_uid"));
		String rep_content = request.getParameter("rep_content");
		
		ReviewDAO dao = new ReviewDAO();
		int cnt = 0;
		
		if (ifNew) {
			if (rep_content != null && rep_content.length() > 0 && !rep_content.trim().equals("")) {
				try {
					int mb_uid = Integer.parseInt(request.getParameter("mb_uid"));
					cnt = dao.insertRep(mb_uid, review_brd_uid, rep_content);
					if (cnt == 1) cnt = 1;
					if (cnt == 0) cnt = 0;
					request.setAttribute("repUpdateOk", cnt);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		} else if (!ifNew) {
			try {
				int rep_uid = Integer.parseInt(request.getParameter("rep_uid"));
				cnt = dao.updateRepByUid(rep_uid, rep_content);
				if (cnt == 1) cnt = 3;
				if (cnt == 0) cnt = 2;
				request.setAttribute("repUpdateOk", cnt);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("location", review_brd_uid);
	}

}