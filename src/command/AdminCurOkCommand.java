package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminClassDAO;

public class AdminCurOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int ins_uid = Integer.parseInt(request.getParameter("ins_uid"));
		String cur_name = request.getParameter("cur_name");
		int cur_hours = Integer.parseInt(request.getParameter("cur_hours"));
		int cur_months = Integer.parseInt(request.getParameter("cur_months"));
		String cur_month1 = request.getParameter("cur_month1");
		String cur_month2 = request.getParameter("cur_month2");
		String cur_month3 = request.getParameter("cur_month3");
		String cur_month4 = request.getParameter("cur_month4");
		String cur_month5 = request.getParameter("cur_month5");
		String cur_month6 = request.getParameter("cur_month6");
		
		
		int cnt = 0;
		AdminClassDAO dao = new AdminClassDAO();
		
		if (ins_uid >= 0
		&& cur_name != null && cur_name.length() > 0 && !cur_name.trim().equals("")
		&& cur_hours > 0
		&& cur_months > 0) {
			try {
				cnt = dao.insertCur(ins_uid, cur_name, cur_hours, cur_months, cur_month1, cur_month2, cur_month3, cur_month4, cur_month5, cur_month6);
				System.out.println(cnt);
				request.setAttribute("adminCurOk", cnt);
			}  catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}

}