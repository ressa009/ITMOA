package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminClassDAO;

public class AdminClassDeleteOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		AdminClassDAO dao = new AdminClassDAO();
		
		//입력한 값을 받아오기
		int class_uid = Integer.parseInt(request.getParameter("class_uid"));
		
		try {			
			cnt = dao.deleteClassByUid(class_uid);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("adminClassDeleteOk", cnt);

	}

}