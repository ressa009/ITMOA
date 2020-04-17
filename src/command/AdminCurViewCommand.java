package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminClassDAO;
import com.lec.beans.ClassDAO;
import com.lec.beans.ClassDTO;

public class AdminCurViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int class_uid = Integer.parseInt(request.getParameter("class_uid"));
		
		ClassDTO [] arr = null;
		AdminClassDAO dao = new AdminClassDAO();
		
		if (class_uid > 0) {
			try {
				arr = dao.selectClassByUid(class_uid);
				request.setAttribute("adminCurView", arr);
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}