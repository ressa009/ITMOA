package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.beans.ClassDAO;
import com.lec.beans.ClassDTO;

public class CurViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
	
		int class_uid = Integer.parseInt(request.getParameter("class_uid"));
		String mb_uid = request.getParameter("mb_uid");
		System.out.println(mb_uid);
		
		ClassDAO dao = new ClassDAO();
		ClassDTO [] arr = null;
		ClassDTO [] arr2 = null;
		
		try {
			if ( mb_uid == null) {
				arr = dao.selectClassByUid(class_uid);
				request.setAttribute("classView", arr);
			} else {
				arr2 = arr = dao.selectClassByUid(class_uid);
				arr = dao.selectZZimByUid(class_uid, Integer.parseInt(mb_uid));
				request.setAttribute("zzimView", arr);
				request.setAttribute("classView", arr2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	
	}

}
