package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.beans.MbDAO;
import com.lec.beans.MbDTO;

public class LoginOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MbDAO dao = new MbDAO();
		MbDTO[] arr = null;
		//입력한 값을 받아오기
		String mb_id = request.getParameter("mb_id");
		String mb_pw = request.getParameter("mb_pw");
		String sessionUid = "loginUid";
		String sessionId = "loginId";
		String sessionLevel = "loginLevel";
		String sessionImg = "loginImg";
		
		HttpSession httpSession = request.getSession(true);
		httpSession.removeAttribute(sessionLevel);
		
		try {			
			arr = dao.login(mb_id, mb_pw);
			// arr 이 loginOk 페이지로 넘어감 -> arr.length > 0 성공 -> 성공했을시 메인페이지로 화면전환되고 arr에 있는 이미지를 가져오기 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		if(arr.length > 0) {
			System.out.println(arr[0].getMb_uid());
			 httpSession.setAttribute(sessionUid, arr[0].getMb_uid());
			 System.out.println(httpSession.getAttribute(sessionUid));
			 httpSession.setAttribute(sessionId, arr[0].getMb_id());
			 System.out.println(httpSession.getAttribute(sessionId));
			 httpSession.setAttribute(sessionLevel, arr[0].getMb_level());
			 System.out.println(httpSession.getAttribute(sessionLevel));
			 httpSession.setAttribute(sessionImg, arr[0].getMb_img());
			 System.out.println(httpSession.getAttribute(sessionImg));
		} else {
			httpSession.removeAttribute(sessionUid);
			httpSession.removeAttribute(sessionId);
			httpSession.removeAttribute(sessionImg);
			httpSession.removeAttribute(sessionLevel);
		}
		request.setAttribute("loginOk", arr);
		
	}
}