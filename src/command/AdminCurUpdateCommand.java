package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminClassDAO;
import com.lec.beans.ClassDTO;

public class AdminCurUpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
			System.out.println("AdminCurUpdateCommand 접근");
		
			String cur_uid = request.getParameter("cur_uid");
			ClassDTO [] curArr = null;
			AdminClassDAO dao = new AdminClassDAO();
			System.out.println(request.getParameter("cur_uid"));
			
			try {
				if( !cur_uid.equals("") && cur_uid != null ) {
					curArr = dao.selectCurByUid(Integer.parseInt(cur_uid));
					System.out.println(curArr[0].getCur_month1());
				} else { 
					System.out.println("cur_uid 파라미터값을 불러오지 못했음" + cur_uid);
				}
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("Curselect", curArr);
	}
}
