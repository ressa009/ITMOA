package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminMbDAO;
import com.lec.beans.MbDTO;

public class AdminMemberUpdateViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AdminMbDAO dao = new AdminMbDAO();
		MbDTO[] arr = null;
		System.out.println(Integer.parseInt(request.getParameter("mb_uid")));
		int mb_uid = Integer.parseInt(request.getParameter("mb_uid"));
		     
		if (mb_uid > 0) {
			try {
				arr = dao.selectMbByUid(mb_uid);
				request.setAttribute("adminMemberUpdateView", arr);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
