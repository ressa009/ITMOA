package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminClassDAO;
import com.lec.beans.ClassDTO;

public class AdminCurListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		AdminClassDAO dao = new AdminClassDAO();
		int ins_uid = Integer.parseInt(request.getParameter("ins_uid"));
		ClassDTO[] curArr1 = null;
		ClassDTO[] curArr2 = null;
		
		if(ins_uid > 0) {
			try {
			
				curArr1 = dao.selectClassByUid(ins_uid);
				curArr2 = dao.selectInsByUidForClassList(ins_uid);
				
				request.setAttribute("adminCurList", curArr1);
				request.setAttribute("adminCurListInsInfo", curArr2);


			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("데이터 불러오기 실패");
		}
		
	}
}
