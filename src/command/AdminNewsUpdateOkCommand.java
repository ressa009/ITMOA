package command;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminNewsDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminNewsUpdateOkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		final String SAVE_URL = "admin/news/img";
		
		// 실제 저장되는 물리적인 경로 확인하기
		ServletContext context = request.getServletContext();
		String saveDirectory = context.getRealPath(SAVE_URL);
		System.out.println("업로드 경로: " + saveDirectory);
		
		try {
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
			//MultipartRequest multi = new MultipartRequest(request, "C:\\tomcat\\itmoa\\wtpwebapps\\Project_itmoa\\admin\\news\\main", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
			boolean ifNew = Boolean.parseBoolean(multi.getParameter("ifNew"));
			String news_brd_title = multi.getParameter("news_brd_title");
			String news_brd_content = multi.getParameter("news_brd_content");
			String news_brd_img = multi.getFilesystemName("news_brd_img");
			String contentType = multi.getContentType("news_brd_img");
			
			AdminNewsDAO dao = new AdminNewsDAO();
			int cnt = 0;
			
			if (ifNew) {
				if (news_brd_img == null) {
					if (news_brd_title != null && !news_brd_title.trim().equals("")
						&& news_brd_content != null && !news_brd_content.trim().equals("")) {
						try {
							System.out.println("??");
							cnt = dao.insertNewsWithoutMainImg(news_brd_title, news_brd_content);
							if (cnt == 1) cnt = 1;
							if (cnt == 0) cnt = 0;
							request.setAttribute("adminNewsUpdateOk", cnt);
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (NamingException e) {
							e.printStackTrace();
						}
					} else {
						cnt = 0;
						request.setAttribute("adminNewsUpdateOk", cnt);
					}
				} else {
					if (news_brd_title != null && !news_brd_title.trim().equals("")
						&& news_brd_content != null && !news_brd_content.trim().equals("")) {
						try {
							news_brd_img = "/Project_itmoa/admin/news/img/" + news_brd_img;
							cnt = dao.insertNews(news_brd_title, news_brd_img, news_brd_content);
							if (cnt == 1) cnt = 1;
							if (cnt == 0) cnt = 0;
							request.setAttribute("adminNewsUpdateOk", cnt);
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (NamingException e) {
							e.printStackTrace();
						}
					} else {
						cnt = 0;
						request.setAttribute("adminNewsUpdateOk", cnt);
					}
				}
			} else if (!ifNew) {
				int news_brd_uid = Integer.parseInt(multi.getParameter("news_brd_uid"));
				if (news_brd_img == null) {
					if (news_brd_title != null && !news_brd_title.trim().equals("")
						&& news_brd_content != null && !news_brd_content.trim().equals("")) {
						System.out.println(news_brd_img);
						try {
							cnt = dao.updateNewsByUidWithoutImg(news_brd_uid, news_brd_title, news_brd_content);
							if (cnt == 1) cnt = 1;
							if (cnt == 0) cnt = 0;
							request.setAttribute("adminNewsUpdateOk", cnt);
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (NamingException e) {
							e.printStackTrace();
						}
					} else {
						cnt = 0;
						request.setAttribute("adminNewsUpdateOk", cnt);
					}
				} else {
					if (news_brd_title != null && !news_brd_title.trim().equals("")
						&& news_brd_content != null && !news_brd_content.trim().equals("")) {
						try {
							news_brd_img = "/Project_itmoa/newsMainImg/" + news_brd_img;
							cnt = dao.updateNewsByUid(news_brd_uid, news_brd_title, news_brd_content, news_brd_img);
							if (cnt == 1) cnt = 1;
							if (cnt == 0) cnt = 0;
							request.setAttribute("adminNewsUpdateOk", cnt);
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (NamingException e) {
							e.printStackTrace();
						}
					} else {
						cnt = 0;
						request.setAttribute("adminNewsUpdateOk", cnt);
					}
				}
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}