package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.MbDAO;

public class RegistOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mb_name = request.getParameter("mb_name");
		String mb_id = request.getParameter("mb_id");
		String mb_pw = request.getParameter("mb_pw");
		String mb_email = request.getParameter("mb_email");
		int mb_zip = Integer.parseInt(request.getParameter("mb_zip"));
		String mb_add1 = request.getParameter("mb_add1");
		String mb_add2 = request.getParameter("mb_add2");

		int cnt = 0;
		MbDAO dao = new MbDAO();
		
		if (mb_name != null && mb_name.length() > 0 && !mb_name.trim().equals("")
		&& mb_id != null && mb_id.length() > 0 && !mb_id.trim().equals("")
		&& mb_pw != null && mb_pw.length() > 0 && !mb_pw.trim().equals("")
		&& mb_email != null && mb_email.length() > 0 && !mb_email.trim().equals("")
		&& mb_zip > 0
		&& mb_add1 != null && mb_add1.length() > 0 && !mb_add1.trim().equals("")
		&& mb_add2 != null && mb_add2.length() > 0 && !mb_add2.trim().equals("")) {
			try {
				cnt = dao.join(mb_name, mb_id, mb_pw, mb_email, mb_zip, mb_add1, mb_add2);
				request.setAttribute("registOk", cnt);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}		
	}

}