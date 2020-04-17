package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminMbDAO;
import com.lec.beans.MbDTO;

public class AdminMemberDeleteOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		  System.out.println(Integer.parseInt(request.getParameter("mb_uid")));
	      int mb_uid = Integer.parseInt(request.getParameter("mb_uid"));
	      int cnt = 0;
	      AdminMbDAO dao = new AdminMbDAO();
	      
         try {
            cnt = dao.deleteMbByUid(mb_uid);
            System.out.println(cnt);
            request.setAttribute("adminMemberDeleteOk", cnt);
            System.out.println("ddddd");
         } catch (SQLException e) {
            e.printStackTrace();
         } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
