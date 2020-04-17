package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminClassDAO;
import com.lec.beans.ClassDTO;

public class AdminInsListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String option = request.getParameter("option");
		String keyword = request.getParameter("keyword");

		System.out.println(option + " " + keyword );
		
		// 페이징 관련 세팅 값들
		int page = 1;  // 현재 페이지 (디폴트는 1 page)
		int writePages = 10;    // 한 [페이징] 에 몇개의 '페이지'를 표현할 것인가?
		int pageRows = 8;   // 한 '페이지'에 몇개의 글을 리스트 할것인가? 
		int totalPage = 0; // 총 몇 '페이지' 분량인가? 
		int cnt = 0;  // 글은 총 몇개인가?
		
		String param;
		
		// 현재 몇 페이지 ?
		param = request.getParameter("page");
		if(param != null && !param.trim().equals("")){
			try{
				page = Integer.parseInt(param);
			} catch (NumberFormatException e){
				// page parameter 오류는 별도의 exception 처리는 안함
			}
		} // end if
		
		// 한 '페이지'에 몇개의 글을 리스트 할것인가?
		param = request.getParameter("pageRows");
		if(param != null && !param.trim().equals("")){
			try{
				pageRows = Integer.parseInt(param);
			} catch (NumberFormatException e){
				// page parameter 오류는 별도의 exception 처리는 안함
			}
		}

		
		AdminClassDAO dao = new AdminClassDAO();
		ClassDTO [] arr = null;

		try {

			if(option == null || option.equals("") || keyword == null || keyword.equals("")){
				
				// 글 전체 개수 구하기
				cnt = dao.countAll();
				
				//총 몇 페이지 분량인가?
				totalPage = (int)Math.ceil(cnt / (double)pageRows); 
				
				// 몇번째 row 부터?
				int fromRow = (page - 1) * pageRows;  // MySQL 은 0부터, ORACLE 은 1부터 시작	
				arr = dao.selectInsFromRow(fromRow, pageRows);

				request.setAttribute("adminInsAllCnt", cnt);
				request.setAttribute("adminInsCnt", arr.length);
				request.setAttribute("adminInsList", arr);
				request.setAttribute("page", page);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("writePages", writePages);
				request.setAttribute("pageRows", pageRows);

			}else {

//			    option 1: 학원명
//			           2: 학원코드
				
				// 글 전체 개수 구하기
				cnt = dao.countAll();
				
				//총 몇 페이지 분량인가?
				totalPage = (int)Math.ceil(cnt / (double)pageRows); 
				
				// 몇번째 row 부터?
				int fromRow = (page - 1) * pageRows;  // MySQL 은 0부터, ORACLE 은 1부터 시작	
				
				arr = dao.selectInsFromRow2(Integer.parseInt(option), keyword, fromRow, pageRows);
				
				request.setAttribute("adminInsAllCnt", cnt);
				request.setAttribute("adminInsCnt", arr.length);
				request.setAttribute("adminInsList", arr);
				request.setAttribute("page", page);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("writePages", writePages);
				request.setAttribute("pageRows", pageRows);
				System.out.println(arr[0].getIns_uid());
			}

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
