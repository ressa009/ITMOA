package command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminClassDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class AdminInsUpdateOkCommand implements Command {

	String ins_name;
	String ins_tel;
	String ins_branch;
	String ins_location;
	String ins_add1;
	String ins_add2;
	String ins_img;
	double ins_x;
	double ins_y;
	int ins_zip;
	int ins_uid;
	
	String fileType;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		AdminClassDAO dao = new AdminClassDAO();
		int cnt = 0;
		
		//ServletContext context = this.getServletContext();	// 서블릿 객체에서 ServletContext 객체 불러옴
		ServletContext context = request.getServletContext();
		String contextRootPath = context.getRealPath("admin/ins/img"); 
		
		
		
		// 최대 5M byte
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
				case "ins_name":
					this.ins_name = value;
					break;
				case "ins_tel":
					this.ins_tel = value;
					break;
				case "ins_branch":
					this.ins_branch = value;
					break;
				case "ins_location":
					this.ins_location = value;
					break;
				case "ins_add1":
					this.ins_add1 = value;
					break;
				case "ins_add2":
					this.ins_add2 = value;
					break;
				case "ins_zip":
					this.ins_zip = Integer.parseInt(value);
					break;
				case "ins_x":
					this.ins_x = Double.parseDouble(value);
					break;
				case "ins_y":
					this.ins_y = Double.parseDouble(value);
					break;
				case "ins_uid":
					this.ins_uid = Integer.parseInt(value);
					break;
				case "ins_img":
					this.ins_img = value;
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
				ins_img = fileSystemName;
				
				// 업로딩 된 파일의 타입 : MIME 타입(ex: text/html)
				fileType = multi.getContentType(name2);
				System.out.println("파일타입 : " + fileType + "<br>");
				
				// File 객체 추출 가능
//				File file = multi.getFile(name2);
//				if(file != null){
//					long fileSize = file.length();
//					System.out.println("파일크기  : " + fileSize + "bytes<br>");
//					
//					// 이미지 파일 다루기
//					BufferedImage bi = ImageIO.read(file);	// 이미지 만을 위한 객체. 이미지 파일 아니면 null 리턴
//					if(bi != null){
//						// 규격(사이즈) 체크
//						int width = bi.getWidth(); // 정수값 리턴
//						int height = bi.getHeight();
//						System.out.println("이미지 파일 WxH : " + width + "x" + height + "<br>");
//					} else {
//						System.out.println("이미지 파일이 아닙니다<br>");
//					}
//				}
				
			}
			
			if(fileType == null) {
				cnt = 3;
				request.setAttribute("adminInsUpdateOk", cnt);
			} else if(fileType.contains("image")) {
				//ins_img = saveDirectory + "\\" + ins_img;
			    ins_img = "/Project_itmoa/admin/ins/img/" + ins_img;
				cnt = dao.updateInsByUid(ins_name, ins_tel, ins_zip, ins_add1, ins_add2,
						 ins_location, ins_branch, ins_img, ins_x, ins_y, ins_uid);
				if(cnt > 0) request.setAttribute("adminInsUpdateOk", cnt);
			} else if(!fileType.contains("image")) {
				cnt = 2;
				request.setAttribute("adminInsUpdateOk", cnt);
			} else {
				request.setAttribute("adminInsUpdateOk", cnt);
			}
			
		} catch(NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		

}
