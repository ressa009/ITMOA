package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminClassDAO;
import com.lec.beans.ClassDTO;

public class AdminInsUpdateViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		ClassDTO [] insArr = null; 
		AdminClassDAO dao = new AdminClassDAO();

		int ins_uid = Integer.parseInt(request.getParameter("ins_uid"));
		
		try {
			insArr = dao.selectInsByUid(ins_uid);
			if (insArr.length == 1) {
				request.setAttribute("adminInsUpdateView", insArr);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}	

}



