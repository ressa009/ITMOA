package command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.beans.MbDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MyPageUpdateOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		MbDAO dao = new MbDAO();
		int cnt = 0;
		ServletContext context = request.getServletContext();
		String contextRootPath = context.getRealPath("user/mypage/img"); 
		System.out.println(contextRootPath);
		HttpSession httpSession = request.getSession();
		
		try {
			
			MultipartRequest multi = new MultipartRequest(request, contextRootPath, 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());			
			String mb_img = multi.getFilesystemName("mb_img");
			System.out.println(multi.getParameter("mb_img"));
			String mb_pw = multi.getParameter("mb_pw");
			String mb_email = multi.getParameter("mb_email");
			int mb_zip = Integer.parseInt(multi.getParameter("mb_zip"));
			String mb_add1 = multi.getParameter("mb_add1");
			String mb_add2 = multi.getParameter("mb_add2");
			int mb_uid = Integer.parseInt(multi.getParameter("mb_uid"));
			String older_mb_img = multi.getParameter("older_mb_img");

			String regex_email = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+";
			String regex_pw = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,20}";

			boolean emailMatches = Pattern.matches(regex_email, mb_email);
			boolean pwMatches = Pattern.matches(regex_pw, mb_pw);

			System.out.printf(mb_img + mb_pw +  mb_email +  mb_zip +  mb_add1 +  mb_add2 +  mb_uid);

			if (mb_img == null)
				mb_img = older_mb_img;

			if (mb_pw != null && mb_pw.length() > 0
					&& !mb_pw.trim().equals("") && mb_email != null && mb_email.length() > 0 && !mb_email.trim().equals("")
					&& mb_zip > 0 && mb_add1 != null && mb_add1.length() > 0 && !mb_add1.trim().equals("")
					&& mb_add2 != null && mb_add2.length() > 0 && !mb_add2.trim().equals("") && mb_uid > 0) {
				if (pwMatches && emailMatches) {
					try {
						cnt = dao.update(mb_img, mb_pw, mb_email, mb_zip, mb_add1, mb_add2, mb_uid);
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (NamingException e) {
						e.printStackTrace();
					} 
				} else if (!pwMatches) {
					cnt = 2;
				} else if (!emailMatches) {
					cnt = 3;
				}
			}
			
			if (cnt == 1) {
				httpSession.removeAttribute("loginImg");
				httpSession.setAttribute("loginImg", mb_img);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		request.setAttribute("myPageUpdateOk", cnt);

		
	}

}
