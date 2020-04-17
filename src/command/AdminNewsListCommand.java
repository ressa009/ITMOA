package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.AdminNewsDAO;
import com.lec.beans.NewsDTO;

public class AdminNewsListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String opn1 = request.getParameter("option_news_1");
		String opn2 = request.getParameter("option_news_2");
		String option_news_3 = request.getParameter("option_news_3");
		
		int option_news_1 = 0;
		int option_news_2 = 0;
		
		if (opn1 == null || opn2 == null) {
			option_news_1 = 1;
			option_news_2 = 4;
			option_news_3 = "all";
		} else {
			option_news_1 = Integer.parseInt(request.getParameter("option_news_1"));
			option_news_2 = Integer.parseInt(request.getParameter("option_news_2"));
		}
		
		// 페이징 관련 세팅 값들
		int page = 1;  // 현재 페이지 (디폴트는 1 page)
		int writePages = 9;    // 한 [페이징] 에 몇개의 '페이지'를 표현할 것인가?
		int pageRows = 10;   // 한 '페이지'에 몇개의 글을 리스트 할것인가? 
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
		
		AdminNewsDAO dao = new AdminNewsDAO();
		NewsDTO [] arr = null;
		
		if (option_news_1 > 0
		&& option_news_2 > 0
		&& option_news_3 != null && !option_news_3.trim().equals("")) {
			try {
				// 글 전체 개수 구하기
				cnt = dao.countAll();
				
				// 총 몇페이지 분량인가?
				totalPage = (int)Math.ceil(cnt / (double)pageRows);
				
				// 몇번재 row 부터?
				int fromRow = (page - 1) * pageRows;  // MySQL 은 0 부터 시작 !
				
				arr = dao.selectNews(option_news_1, option_news_2, option_news_3, fromRow, pageRows);
				request.setAttribute("adminNewsListAllCnt", cnt);
				request.setAttribute("adminNewsListCnt", arr.length);
				request.setAttribute("adminNewsList", arr);
				request.setAttribute("page", page);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("writePages", writePages);
				request.setAttribute("pageRows", pageRows);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

}
