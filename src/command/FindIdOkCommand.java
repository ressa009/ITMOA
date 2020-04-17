package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.MbDAO;
import com.lec.beans.MbDTO;

public class FindIdOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MbDTO[] arr = null;
		MbDAO dao = new MbDAO();
		
		String mb_name = request.getParameter("mb_name");
		String mb_email = request.getParameter("mb_email");
		System.out.println(mb_name + " " + mb_email);
		
		try {
			arr = dao.selectId(mb_name, mb_email);
			System.out.println(arr.length);
			if(arr.length == 1 && arr[0].getMb_name().trim().equals(mb_name.trim()) && arr[0].getMb_email().trim().equals(mb_email.trim())) {
				request.setAttribute("findIdOk", arr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
