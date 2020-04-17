package com.lec.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import common.D;

public class AdminReviewDAO {

	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	
	
	public static Connection getConnection() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/testIt");
		return ds.getConnection();
	}
	
	
	// DB 자원 반납 메소드
	public void close() throws SQLException {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	}
	
	// ResultSet --> DTO 배열로 변환 리턴
	public ReviewDTO [] createReviewArray(ResultSet rs) throws SQLException {
		
		ArrayList<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
		
		while(rs.next()){
			int review_brd_uid = rs.getInt("review_brd_uid");
			String mb_id = rs.getString("mb_id");
			String ins_name = rs.getString("ins_name");
			String review_brd_title = rs.getString("review_brd_title");
			Date d = rs.getDate("review_brd_regdate");
			Time t = rs.getTime("review_brd_regdate");
			String review_brd_regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " | " + new SimpleDateFormat("hh:mm:ss").format(t);
			int review_brd_viewcnt = rs.getInt("review_brd_viewcnt");
			ReviewDTO dto = new ReviewDTO(review_brd_uid, mb_id, ins_name, review_brd_regdate, review_brd_title, review_brd_viewcnt);
			reviewList.add(dto);
		}
		
		int size = reviewList.size();
		ReviewDTO [] arr = new ReviewDTO[size];
		
		reviewList.toArray(arr);
		
		return arr;
	}
		
		public ReviewDTO [] createReviewContentArray(ResultSet rs) throws SQLException {
	      
	      ArrayList<ReviewDTO> list = new ArrayList<ReviewDTO>();
	      
	      
	      while(rs.next()){

	         int review_brd_uid = rs.getInt("review_brd_uid");
	         String mb_id = rs.getString("mb_id");
	         String ins_name = rs.getString("ins_name");
	         String review_brd_regdate = rs.getString("review_brd_regdate");
	         String review_brd_title = rs.getString("review_brd_title");
	         String review_brd_content =  rs.getString("review_brd_content");
	         int review_brd_viewcnt = rs.getInt("review_brd_viewcnt");
	         
	         ReviewDTO dto = new ReviewDTO(review_brd_uid, mb_id, ins_name, review_brd_regdate, review_brd_title, review_brd_viewcnt, review_brd_content);
	         list.add(dto);
	      }
	      
	      int size = list.size();
	      ReviewDTO [] arr = new ReviewDTO[size];
	      list.toArray(arr);
	      
	      return arr;
	   }
	
	   // 2-2. 특정 학원후기 불러오기 (조회수 증가x) review_uid로 review
	   public ReviewDTO[] readReviewByUid(int review_brd_uid) throws SQLException, NamingException{
	      
	      ReviewDTO[] arr = null;
	      
	      
	      try {
	    	 conn = getConnection();
	         pstmt = conn.prepareStatement(D.SQL_SELECT_REVIEW_CONTENT);
	         pstmt.setInt(1, review_brd_uid);
	         
	         rs = pstmt.executeQuery();
	         arr = createReviewContentArray(rs);
	   
	      } finally {
	         close();
	      }      
	      
	      return arr;
	   }
	
	
	// 리뷰 목록 
	public ReviewDTO[] selectReviewList(int option_review, String keyword, int fromRow, int pageRows) throws SQLException, NamingException {
		
		ReviewDTO [] arr = null;
		String selectReview = D.SQL_SELECT_REVIEW;
		
		// 리뷰 검색 조건 (1)회원ID  (2)리뷰제목   (3)리뷰내용

		int setStr1 = 0;
		
		switch(option_review) {
			case 1: 
				selectReview += (D.SQL_SELECT_REVIEW_BRD_WHERE_USER_ID + D.SQL_ORDER_REVIEW + D.SQL_SELECT_FROM_ROW_REVIEW_BRD);
				keyword = "%" + keyword + "%";
				setStr1 = 1;
				break;
			case 2:
				selectReview += (D.SQL_SELECT_REVIEW_BRD_WHERE_REVIEW_TITLE + D.SQL_ORDER_REVIEW + D.SQL_SELECT_FROM_ROW_REVIEW_BRD);
				keyword = "%" + keyword + "%";
				setStr1 = 1;
				break;
			case 3:
				selectReview += (D.SQL_SELECT_REVIEW_BRD_WHERE_REVIEW_CONTENT + D.SQL_ORDER_REVIEW + D.SQL_SELECT_FROM_ROW_REVIEW_BRD);
				keyword = "%" + keyword + "%";
				setStr1 = 1;
				break;
			case 4:
				selectReview += D.SQL_SELECT_FROM_ROW_REVIEW_BRD;
				break;
		}
		
		try {
			// keyword가 있을 경우 쿼리문에 키워드 넘겨주기
			if(keyword != null && !keyword.equals("")) {
				conn = getConnection();
				pstmt = conn.prepareStatement(selectReview);
				if (setStr1 == 1) pstmt.setString(setStr1, keyword); 
				pstmt.setInt(setStr1 + 1, fromRow); 
				pstmt.setInt(setStr1 + 2, pageRows);
			} else { 
				conn = getConnection();
				pstmt = conn.prepareStatement(selectReview);
			}

			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			arr = createReviewArray(rs);
		} finally {
			close();
		}		
		
		return arr;
	}
	
	// 총 몇 개의 글이 있는지 계산
	public int countAll() throws SQLException, NamingException{
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_COUNT_ALL_REVIEW_BRD);
			rs = pstmt.executeQuery();
			rs.next();
			rs.getInt(1);	// 첫번째 행의 
			cnt = rs.getInt(1);	// 첫번재 컬럼
		} finally {
			close();
		}
		
		return cnt;
	}	
	
	// 리뷰 삭제
	public int deleteReview(int review_uid) throws SQLException, NamingException {
		
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_DELETE_REVIEW_BY_UID);
			System.out.println(pstmt);
			pstmt.setInt(1, review_uid);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
	
	
	
}
