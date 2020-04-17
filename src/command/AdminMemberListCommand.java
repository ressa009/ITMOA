package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminMbDAO;
import com.lec.beans.MbDTO;

public class AdminMemberListCommand implements Command {
	
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) {
	  
	   	System.out.println(request.getParameter("option_mb_1"));
		System.out.println(request.getParameter("option_mb_2"));
		System.out.println(request.getParameter("option_mb_3"));
		String str_option_mb_1 = request.getParameter("option_mb_1");
		String str_option_mb_2 = request.getParameter("option_mb_2");
		String option_mb_3 = request.getParameter("option_mb_3");
		int option_mb_1 = 0;
		int option_mb_2 = 0;
		if (str_option_mb_1 == null || str_option_mb_2 == null || option_mb_3 == null) {
			option_mb_1 = 1;
			option_mb_2 = 5;
			option_mb_3 = "all";   
		} else {
			option_mb_1 = Integer.parseInt(request.getParameter("option_mb_1"));
			option_mb_2 = Integer.parseInt(request.getParameter("option_mb_2"));
		}
		
		System.out.println(option_mb_1 + "," + option_mb_2 + "," + option_mb_3);
	   
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

		AdminMbDAO dao = new AdminMbDAO();
		MbDTO [] arr = null;
		
		
			try {
				// 글 전체 개수 구하기
				
				cnt = dao.countAll();
				
				// 총 몇페이지 분량인가?
				totalPage = (int)Math.ceil(cnt / (double)pageRows);
				
				// 몇번재 row 부터?
				int fromRow = (page - 1) * pageRows;  // MySQL 은 0 부터 시작 !
				
				arr = dao.selectFromRow(option_mb_1, option_mb_2, option_mb_3, fromRow, pageRows);
				
				
				request.setAttribute("adminMemberAllCnt", cnt);
				request.setAttribute("adminMemberCnt", arr.length);
				request.setAttribute("adminMemberList", arr);
				request.setAttribute("page", page);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("writePages", writePages);
				request.setAttribute("pageRows", pageRows);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	  
		
//      System.out.println(request.getParameter("option_mb_1"));
//      System.out.println(request.getParameter("option_mb_2"));
//      System.out.println(request.getParameter("option_mb_3"));
//      String str_option_mb_1 = request.getParameter("option_mb_1");
//      String str_option_mb_2 = request.getParameter("option_mb_2");
//      String option_mb_3 = request.getParameter("option_mb_3");
//      int option_mb_1 = 0;
//      int option_mb_2 = 0;
//      if (str_option_mb_1 == null || str_option_mb_2 == null) {
//         option_mb_1 = 1;
//         option_mb_2 = 5;
//         option_mb_3 = "a";
//      } else {
//         option_mb_1 = Integer.parseInt(request.getParameter("option_mb_1"));
//         option_mb_2 = Integer.parseInt(request.getParameter("option_mb_2"));
//      }
//      AdminMbDAO dao = new AdminMbDAO();
//      MbDTO [] arr = null;
//      
//      System.out.println(option_mb_1 + "," + option_mb_2 + "," + option_mb_3);
//      
//      if (option_mb_1 > 0 
//      && option_mb_2 > 0 
//      && option_mb_3.length() > 0 && !option_mb_3.trim().equals("") && option_mb_3 != null) {
//         try {
//            arr = dao.selectMb(option_mb_1, option_mb_2, option_mb_3);
//            request.setAttribute("adminMemberList", arr);
//         } catch (SQLException e) {
//            e.printStackTrace();
//         } catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//      }
   

}
