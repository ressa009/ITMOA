package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.AdminClassDeleteOkCommand;
import command.AdminCurListCommand;
import command.AdminCurOkCommand;
import command.AdminCurUpdateCommand;
import command.AdminCurUpdateOkCommand;
import command.AdminCurViewCommand;
import command.AdminInsDeleteOkCommand;
import command.AdminInsListCommand;
import command.AdminInsRegistCommand;
import command.AdminInsUpdateOkCommand;
import command.AdminInsUpdateViewCommand;
import command.AdminMemberDeleteOkCommand;
import command.AdminMemberListCommand;
import command.AdminMemberUpdateOkCommand;
import command.AdminMemberUpdateViewCommand;
import command.AdminNewsDeleteOkCommand;
import command.AdminNewsFileUploadCommand;
import command.AdminNewsListCommand;
import command.AdminNewsUpdateOkCommand;
import command.AdminNewsViewCommand;
import command.AdminReviewDeleteOkCommand;
import command.AdminReviewListCommand;
import command.Command;
import command.CurListCommand;
import command.CurViewCommand;
import command.FindIdOkCommand;
import command.FindPwOkCommand;
import command.JoinOkCommand;
import command.LoginOkCommand;
import command.MyPageCommand;
import command.MyPageUpdateOkCommand;
import command.NewsListCommand;
import command.NewsViewCommand;
import command.RepDeleteOkCommand;
import command.RepUpdateOkCommand;
import command.ReserveOkCommand;
import command.ReviewDeleteOkCommand;
import command.ReviewFileUploadCommand;
import command.ReviewListCommand;
import command.ReviewUpdateOkCommand;
import command.ReviewUpdateViewCommand;
import command.ReviewViewCommand;
import command.ReviewWriteCommand;
import command.ZZimOkCommand;



@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public controller() {
        super();
    }  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		Command command = null;
		String viewPage = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		System.out.println(uri + "\n" + conPath + "\n" + com);
		
		switch (com) {
		
//		사용자
//		- 메인페이지
		case "/user/index.do":  // 메인페이지
			viewPage = "index.jsp";
			break;
		case "/user/loginIndex.do":  // 메인페이지
			viewPage = "loginIndex.jsp";
			break;
		case "/user/logoutIndex.do":  // 메인페이지
			viewPage = "logoutIndex.jsp";
			break;

//		- 로그인
		case "/user/login.do":  // 로그인 페이지
			viewPage = "login.jsp";
			break;
		case "/user/loginOk.do":  // 로그인 (체크)
			command = new LoginOkCommand();
			command.execute(request, response);
			viewPage = "loginOk.jsp";
			break;
		case "/user/logoutOk.do":  // 로그아웃
			viewPage = "logoutOk.jsp";
			break;

//		- 회원가입
		case "/user/join.do":  // 회원가입 페이지
			viewPage = "join.jsp";
			break;
		case "/user/joinOk.do":  // 회원가입 (체크)
			System.out.println("ddd");
			command = new JoinOkCommand();
			command.execute(request, response);
			viewPage = "joinOk.jsp";
			break;
	
//		- 아이디 비밀번호 찾기
		case "/user/find_id_pw.do":  // 아이디 찾기 페이지
			viewPage = "find_id_pw.jsp";
			break;
//		case "/user/findIdOk.do":  // 아이디 찾기 (체크) 및 아이디 출력. 혹시 몰라 남겨둠
//			command = new FindIdOkCommand();
//			command.execute(request, response);
//			viewPage = "findIdOk.jsp";
//			break;
		case "/user/findIdView.do":  // 아이디 찾기 결과창   
			command = new FindIdOkCommand();
			command.execute(request, response);
			viewPage = "findIdView.jsp";
			break;
		case "/user/findPwOk.do":  // 비밀번호 찾기 (체크) 및 이메일로 발송
			command = new FindPwOkCommand();
			command.execute(request, response);
			viewPage = "findPwOk.jsp";
			break;
		case "/user/findPwView.do":  // 비밀번호 찾기 결과창
			viewPage = "findPwView.jsp";
			break;
			

//		- 마이페이지
		case "/user/myPage.do":  // 마이페이지 출력
			command = new MyPageCommand();
			command.execute(request, response);
			viewPage = "myPage.jsp";
			break;
			
		case "/user/myPageUpdateOk.do":  // 마이페이지 수정 (체크)
			command = new MyPageUpdateOkCommand();
			command.execute(request, response);
			viewPage = "myPageUpdateOk.jsp";
			break;
			
		case "/user/myPageAction.do":  // 마이페이지 출력
			viewPage = "myPageAction.jsp";
			break;
			
		case "/user/zzimDeleteOk.do":
			command = new ZZimOkCommand();
			command.execute(request, response);
			viewPage = "zzimDeleteOk.jsp";
			break;
		
	
			
//		- 학원
		case "/user/classList.do":  // 학원 리스트 출력
			command = new CurListCommand();
			command.execute(request, response);
			viewPage = "classList.jsp";
			break;
			
			
//		case "/user/zzimCheck.do":  // 찜체크
//			command = new ZzimCheckCommand();
//			command.execute(request, response);
//			break;
//			

				
//		검색 조건 추가는 curList.do 뒤에 쿼리 추가해서 다시 request
		case "/user/classView.do":  // 학원 상세페이지 출력
			command = new CurViewCommand();
			command.execute(request, response);
			viewPage = "classView.jsp";
			break;
//		* 찜 삭제는 커맨드에서 if문으로 거르기
		case "/user/zzimOk.do":  // 찜 추가
			command = new ZZimOkCommand();
			command.execute(request, response);
			viewPage = "zzimOk.jsp";
			break;
		case "/user/reserveOk.do":  // 상담 예약 (mb_level = 2)
			command = new ReserveOkCommand();
			command.execute(request, response);
			viewPage = "reserveOk.jsp";
			break;
			
//		- 리뷰
		case "/user/reviewList.do":  // 리뷰 리스트 출력 (댓글도)
			command = new ReviewListCommand();
			command.execute(request, response);
			viewPage = "reviewList.jsp";
			break;
		case "/user/reviewView.do":  // 리뷰 상세페이지 출력
			command = new ReviewViewCommand();
			command.execute(request, response);
			viewPage = "reviewView.jsp";
			break;
		case "/user/reviewWrite.do":  // 리뷰 작성 페이지 이동
			command = new ReviewWriteCommand();
			command.execute(request, response);
			viewPage = "reviewWrite.jsp";
			break;
		case "/user/reviewUpdateView.do":  // 리뷰 수정 페이지 이동
			command = new ReviewUpdateViewCommand();
			command.execute(request, response);
			viewPage = "reviewUpdateView.jsp";
			break;
		case "/user/reviewUpdateOk.do":  // 리뷰 수정 (체크)
			command = new ReviewUpdateOkCommand();
			command.execute(request, response);
			viewPage = "reviewUpdateOk.jsp";
			break;
		case "/user/reviewFileUplaod.do":
			command = new ReviewFileUploadCommand();
			command.execute(request, response);
			break;
		case "/user/reviewDeleteOk.do":  // 리뷰 삭제 (체크)
			command = new ReviewDeleteOkCommand();
			command.execute(request, response);
			viewPage = "reviewDeleteOk.jsp";
			break;

//		- 댓글
// 		* 댓글 불러오기는 jQuery .click() 함수로 대체
		case "/user/repUpdateOk.do":  // 댓글 수정 (체크)
			command = new RepUpdateOkCommand();
			command.execute(request, response);
			viewPage = "repUpdateOk.jsp";
			break;
		case "/user/repDeleteOk.do":  // 댓글 삭제 (체크)
			command = new RepDeleteOkCommand();
			command.execute(request, response);
			viewPage = "repDeleteOk.jsp";
			break;

//		- 뉴스
		case "/user/newsList.do":  // 뉴스 리스트 출력
			command = new NewsListCommand();
			command.execute(request, response);
			viewPage = "newsList.jsp";
			break;
		case "/user/newsView.do":  // 뉴스 상세페이지 출력
			command = new NewsViewCommand();
			command.execute(request, response);
			viewPage = "newsView.jsp";
			break;
			
//		관리자
//		- 회원관리
		case "/admin/adminMemberList.do":  // 관리자 회원 리스트 출력
			command = new AdminMemberListCommand();
			command.execute(request, response);
			viewPage = "adminMemberList.jsp";
			break;
//		* 검색 조건 추가는 adminMemberList.do 뒤에 쿼리 추가해서 다시 request
		case "/admin/adminMemberUpdateView.do":  // 관리자 회원 상세 정보 출력
			command = new AdminMemberUpdateViewCommand();
			command.execute(request, response);
			viewPage = "adminMemberUpdateView.jsp";
			break;
		case "/admin/adminMemberUpdateOk.do":  // 관리자 회원 상세 정보 수정 (체크)
			command = new AdminMemberUpdateOkCommand();
			command.execute(request, response);
			viewPage = "adminMemberUpdateOk.jsp";
			break;
		case "/admin/adminMemberDeleteOk.do":
			command = new AdminMemberDeleteOkCommand();
			command.execute(request, response);
			viewPage = "adminMemberDeleteOk.jsp";
			break;
//		- 학원관리
		case "/admin/adminInsList.do":  // 관리자 학원 리스트 출력. 관리자페이지 초기화면
			command = new AdminInsListCommand();
			command.execute(request, response);
			viewPage = "adminInsList.jsp";
			break;
			
		case "/admin/adminInsRegist.do":  // 관리자 학원등록
			viewPage = "adminInsRegist.jsp";
			break;
		
		case "/admin/adminInsRegistOk.do":  
			command = new AdminInsRegistCommand();
			command.execute(request, response);
			viewPage = "adminInsRegistOk.jsp";
			break;
			    
//		* 검색 조건 추가는 adminInsList.do 뒤에 쿼리 추가해서 다시 request
		case "/admin/adminInsUpdateView.do":  // 관리자 학원 상세 정보 출력. 학원 수정할 때
			command = new AdminInsUpdateViewCommand();
			command.execute(request, response);
			viewPage = "adminInsUpdateView.jsp";
			break;  
		case "/admin/adminInsDeleteOk.do":  // 관리자 학원 삭제
			command = new AdminInsDeleteOkCommand();
			command.execute(request, response);
			viewPage = "adminInsDeleteOk.jsp";
			break;
		case "/admin/adminInsUpdateOk.do":  // 관리자 학원 상세 정보 수정 (체크)
			command = new AdminInsUpdateOkCommand();
			command.execute(request, response);
			viewPage = "adminInsUpdateOk.jsp";
			break;
//		* insert 같이 사용
		case "/admin/adminClassList.do":  // 관리자 Class 출력. 과정 관리 화면
			command = new AdminCurListCommand();
			command.execute(request, response);
			viewPage = "adminClassList.jsp";
			break;
		case "/admin/adminCurWrite.do":  // 관리자 Cur 작성
			viewPage = "adminCurWrite.jsp";
			break;
		case "/admin/adminCurView.do":  // 관리자 Cur 출력. 과정 수정할 때
			command = new AdminCurViewCommand();
			command.execute(request, response);
			viewPage = "adminCurView.jsp";
			break;
		case "/admin/adminCurOk.do":  // 관리자 Cur 추가
			command = new AdminCurOkCommand();
			command.execute(request, response);
			viewPage = "adminCurOk.jsp";
			break;
		case "/admin/adminCurUpdate.do":  // 관리자 Cur 추가
			command = new AdminCurUpdateCommand();
			command.execute(request, response);
			viewPage = "adminCurUpdate.jsp";
			break;
		case "/admin/adminCurUpdateOk.do":  // 관리자 Cur 추가
			command = new AdminCurUpdateOkCommand();
			command.execute(request, response);
			viewPage = "adminCurUpdateOk.jsp";
			break;
		case "/admin/adminClassDeleteOk.do":  // 관리자 Class 삭제
			command = new AdminClassDeleteOkCommand();
			command.execute(request, response);
			viewPage = "adminClassDeleteOk.jsp";
			break;
			
//		- 리뷰관리
		case "/admin/adminReviewList.do":  // 관리자 리뷰 리스트 출력
			command = new AdminReviewListCommand();
			command.execute(request, response);
			viewPage = "adminReviewList.jsp";
			break;
//		* 검색 조건 추가는 adminReviewList.do 뒤에 쿼리 추가해서 다시 request
		case "/admin/adminReviewDeleteOk.do":  // 관리자 리뷰 삭제 (체크)
			command = new AdminReviewDeleteOkCommand();
			command.execute(request, response);
			viewPage = "adminReviewDeleteOk.jsp";
			break;
				
//		- IT뉴스 관리
		case "/admin/adminNewsList.do":  // 관리자 뉴스 리스트 출력
			command = new AdminNewsListCommand();
			command.execute(request, response);
			viewPage = "adminNewsList.jsp";
			break;
//		* 검색 조건 추가는 adminNewsList.do 뒤에 쿼리 추가해서 다시 request
		case "/admin/adminNewsView.do":  // 관리자 뉴스 상세 내용 출력
			command = new AdminNewsViewCommand();
			command.execute(request, response);
			viewPage = "adminNewsView.jsp";
			break;
		case "/admin/adminNewsWrite.do":  // 관리자 뉴스 상세 내용 수정 (체크)
			viewPage = "adminNewsWrite.jsp";
			break;
		case "/admin/adminNewsUpdateView.do":  // 관리자 뉴스 상세 내용 수정 (체크)
			command = new AdminNewsViewCommand();
			command.execute(request, response);
			viewPage = "adminNewsUpdateView.jsp";
			break;
		case "/admin/adminNewsUpdateOk.do":  // 관리자 뉴스 상세 내용 수정 (체크)
			command = new AdminNewsUpdateOkCommand();
			command.execute(request, response);
			viewPage = "adminNewsUpdateOk.jsp";
			break;
//		* insert도 같이 사용
		case "/admin/adminNewsDeleteOk.do":  // 관리자 뉴스 상세 내용 수정 (체크)
			command = new AdminNewsDeleteOkCommand();
			command.execute(request, response);
			viewPage = "adminNewsDeleteOk.jsp";
			break;
		case "/admin/adminNewsFileUplaod.do":
			command = new AdminNewsFileUploadCommand();
			command.execute(request, response);
			break;
		}
		
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}