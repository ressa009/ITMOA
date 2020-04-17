package command;

import java.sql.SQLException;
import java.util.Enumeration;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminMbDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class AdminMemberUpdateOkCommand implements Command {
	
	int mb_level;
	String mb_img;
	String mb_email;
	String mb_add1;
	String mb_add2;
	int mb_zip;
	int mb_uid;
	
	String fileType;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		AdminMbDAO dao = new AdminMbDAO();
		int cnt = 0;
		
		// 최대 5M byte
		ServletContext context = request.getServletContext();
		String contextRootPath = context.getRealPath("user/mypage/img");
		int maxPostSize = 5 * 1024 * 1024;	// 최대 5M byte POST 받기
		String encoding = "utf-8";	// 인코딩
		FileRenamePolicy policy = new DefaultFileRenamePolicy();	// 업로딩 파일 이름 중복 정책
		
		MultipartRequest multi = null;
		// MultipartRequest 객체생성. 생성한것만으로도 서버에 파일 저장되어있음
		// 파일 저장까지 마무리
		try{
			multi = new MultipartRequest(
					request,
					contextRootPath,
					maxPostSize,
					encoding,
					policy
					);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 예외 처리 발생");
		}
		
		try {
			Enumeration names = null;
			names = multi.getParameterNames();	// form 의 name 들 추출
			while(names.hasMoreElements()){
				String name = (String)names.nextElement();	// name
				String value = multi.getParameter(name);
				switch(name) {
				case "mb_level":
					this.mb_level = Integer.parseInt(value);
					break;
				case "mb_add1":
					this.mb_add1 = value;
					break;
				case "mb_add2":
					this.mb_add2 = value;
					break;
				case "mb_zip":
					this.mb_zip = Integer.parseInt(value);
					break;
				case "mb_uid":
					this.mb_uid = Integer.parseInt(value);
					break;
				case "mb_img":
					this.mb_img = value;
					break;
				case "mb_email":
					this.mb_email = value;
					break;
				}
				
				System.out.println(name + " : " + value);
			}
			// 2. File 들 추출
			names = multi.getFileNames();	// type="file" 요소의 name 들 추출
			while(names.hasMoreElements()){
				String name2 = (String)names.nextElement();
				System.out.println("input name : " + name2 + "<br>");
				
				// 위 name 에는 폼 요소의 name 이 담겨 있다.
				// 그 name 을 사용하여 원래 파일(업로드 한 파일) 정보를 가져온다.
				
				String originalFileName = multi.getOriginalFileName(name2);
				System.out.println("원본 파일 이름 : " + originalFileName + "<br>");
				System.out.println("<input type='hidden' value='" + originalFileName + "' name='originalFileName'/>");
				
				// 업로드할 폴더에 동일이름의 파일이 있으면 현재 업로드하는 파일이름은 바뀌어서 저장됨.
				// 물리적으로 저장된 파일 이름
				String fileSystemName = multi.getFilesystemName(name2);
				System.out.println("파일시스템 이름 : " + fileSystemName + "<br>");
				System.out.println("<input type='hidden' value='" + fileSystemName + "' name='fileSystemName'/>");
				mb_img = fileSystemName;
				
				// 업로딩 된 파일의 타입 : MIME 타입(ex: text/html)
				fileType = multi.getContentType(name2);
				System.out.println("파일타입 : " + fileType + "<br>");
				
			}
			    
			if(fileType == null) {
				cnt = 3;
				request.setAttribute("adminMemberUpdateOk", cnt);
			} else if(fileType.contains("image")) {
				cnt = dao.updateMbByUid(mb_level, mb_email, mb_zip, mb_add1, mb_add2, mb_img, mb_uid);
				if(cnt > 0) request.setAttribute("adminInsUpdateOk", cnt);
			} else if(!fileType.contains("image")) {
				cnt = 2;
				request.setAttribute("adminMemberUpdateOk", cnt);
			} else {
				request.setAttribute("adminMemberUpdateOk", cnt);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("adminMemberUpdateOk", cnt);
	}

}