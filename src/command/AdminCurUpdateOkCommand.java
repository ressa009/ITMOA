package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminClassDAO;
import com.lec.beans.ClassDTO;

public class AdminCurUpdateOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("AdminCurUpdateOkCommand 접근");

		int cnt = 0;
		AdminClassDAO dao = new AdminClassDAO();

		int cur_hours = Integer.parseInt(request.getParameter("cur_hours"));
		int cur_months = Integer.parseInt(request.getParameter("cur_months"));
		String cur_name = request.getParameter("cur_name");
		String cur_month1 = request.getParameter("cur_month1");
		String cur_month2 = request.getParameter("cur_month2");
		String cur_month3 = request.getParameter("cur_month3");
		String cur_month4 = request.getParameter("cur_month4");
		String cur_month5 = request.getParameter("cur_month5");
		String cur_month6 = request.getParameter("cur_month6");
		int cur_uid = Integer.parseInt(request.getParameter("cur_uid"));
		
		try {
				cnt = dao.updateClass( cur_uid,  cur_name,  cur_hours, cur_months,  cur_month1,  cur_month2,  cur_month3, 
					cur_month4,  cur_month5,  cur_month6);
				System.out.println(cur_uid);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("curSelect", cnt);

	}

}
