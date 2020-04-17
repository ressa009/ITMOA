package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.ClassDAO;

public class ZZimOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		boolean ifZZim = Boolean.parseBoolean(request.getParameter("ifZZim"));
		int mb_uid = Integer.parseInt(request.getParameter("mb_uid"));
		int goBack = Integer.parseInt(request.getParameter("goBack"));
		
		ClassDAO dao = new ClassDAO();
		int cnt = 0;
		
		if(mb_uid > 0) {			
			if (ifZZim == true) {
				try {
					int zzim_uid = Integer.parseInt(request.getParameter("zzim_uid"));
					cnt = dao.deleteZZim(zzim_uid);
					if (cnt == 1) cnt = 1;
					if (cnt == 0) cnt = 0;
					request.setAttribute("zzimOk", cnt);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			} else if (ifZZim == false) {
				try {
					int class_uid = Integer.parseInt(request.getParameter("class_uid"));
					cnt = dao.insertZZim(mb_uid, class_uid);
					if (cnt == 0) cnt = 2;
					if (cnt == 1) cnt = 3;
					request.setAttribute("zzimOk", cnt);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("goBack", goBack);
	}

}