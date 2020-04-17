package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.beans.ClassDAO;

public class ReserveOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int mb_uid = Integer.parseInt(request.getParameter("mb_uid"));
		
		ClassDAO dao = new ClassDAO();
		int cnt = 0;
		HttpSession httpSession = request.getSession(true);
		httpSession.removeAttribute("loginLevel");
		if (mb_uid > 0) {
			try {
				cnt = dao.updateMemberByUid(mb_uid);
				if(cnt > 0) {
				httpSession.setAttribute("loginLevel", 2);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			request.setAttribute("reserveOk", cnt);
	}

}