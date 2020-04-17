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

public class ReviewDAO {
      
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
   
   // 학원후기
   // 1. 학원후기 목록 or 검색 reviewDTO ->  Array로 변경 
   public ReviewDTO [] createReviewListArray(ResultSet rs) throws SQLException {
      
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
   
   // 1-1. 학원후기 목록 or 검색
   public ReviewDTO[] selectReviewList(int option_review, String keyword, int fromRow, int pageRows) throws SQLException, NamingException {
      
      
	    ReviewDTO [] arr = null;
		String selectReview = D.SQL_SELECT_REVIEW;
		
		// 리뷰 검색 조건 (1)회원ID  (2)리뷰제목   (3)리뷰내용


		int setStr1 = 0;
		
		switch(option_review) {
			case 1: 
				selectReview += D.SQL_SELECT_REVIEW_BRD_WHERE_USER_ID;
				keyword = "%" + keyword + "%";
				setStr1 = 1;
				break;
			case 2:
				selectReview += D.SQL_SELECT_REVIEW_BRD_WHERE_REVIEW_TITLE;
				keyword = "%" + keyword + "%";
				setStr1 = 1;
				break;
			case 3:
				selectReview += D.SQL_SELECT_REVIEW_BRD_WHERE_REVIEW_CONTENT;
				keyword = "%" + keyword + "%";
				setStr1 = 1;
				break;
			case 4:
				break;
		}
		
		selectReview += (D.SQL_ORDER_REVIEW + D.SQL_SELECT_FROM_ROW_REVIEW_BRD);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(selectReview);
			if (setStr1 == 1) pstmt.setString(setStr1, keyword);
			pstmt.setInt(setStr1 + 1, fromRow); 
			pstmt.setInt(setStr1 + 2, pageRows);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			arr = createReviewListArray(rs);
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
   
   // 2. 특정 학원후기 불러오기 reviewDTO ->  Array로 변경 
   public ReviewDTO [] createReviewContentArray(ResultSet rs) throws SQLException {
      
      ArrayList<ReviewDTO> list = new ArrayList<ReviewDTO>();
      
      
      while(rs.next()){

         int review_brd_uid = rs.getInt("review_brd_uid");
         String mb_id = rs.getString("mb_id");
         int mb_uid = rs.getInt("mb_uid");
         String ins_name = rs.getString("ins_name");
         String review_brd_regdate = rs.getString("review_brd_regdate");
         String review_brd_title = rs.getString("review_brd_title");
         String review_brd_content =  rs.getString("review_brd_content");
         int review_brd_viewcnt = rs.getInt("review_brd_viewcnt");
         
         ReviewDTO dto = new ReviewDTO(review_brd_uid, mb_id, mb_uid, ins_name, review_brd_regdate, review_brd_viewcnt, review_brd_title, review_brd_content);
         list.add(dto);
      }
      
      int size = list.size();
      ReviewDTO [] arr = new ReviewDTO[size];
      list.toArray(arr);
      
      return arr;
   }
   
   // 2-1. 특정 학원후기 불러오기 (조회수 증가o)review_uid로 review
   public ReviewDTO[] selectReviewByUid(int review_brd_uid) throws SQLException, NamingException{
      ReviewDTO[] arr = null;
      
      
      try {
    	 conn = getConnection();
         conn.setAutoCommit(false);
         
         pstmt = conn.prepareStatement(D.SQL_UPDATE_REVIEW_INC_VIEWCNT);
         pstmt.setInt(1, review_brd_uid);
         pstmt.executeUpdate();
		 pstmt.close();
         
         pstmt = conn.prepareStatement(D.SQL_SELECT_REVIEW_CONTENT);
         pstmt.setInt(1, review_brd_uid);
		 pstmt.close();
         
         rs = pstmt.executeQuery();
         arr = createReviewContentArray(rs);
         
         conn.commit();
      } catch(SQLException e) {
         conn.rollback();   // 처리할 것 처리하고 throw
         throw e;
      } finally {
         close();
      }
      
      return arr;
   }
   
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
   // 2-2. 특정 학원후기 불러오기 (조회수 증가x) review_uid로 review
   public ReviewDTO [] createInsNameArray(ResultSet rs) throws SQLException {
      
      ArrayList<ReviewDTO> list = new ArrayList<ReviewDTO>();
      while(rs.next()){

         int class_uid = rs.getInt("class_uid");
         String ins_name = rs.getString("ins_name");
         String cur_name = rs.getString("cur_name");
         
         ReviewDTO dto = new ReviewDTO(class_uid, ins_name, cur_name);
         list.add(dto);
      }
      
      int size = list.size();
      ReviewDTO [] arr = new ReviewDTO[size];
      list.toArray(arr);
      
      return arr;
   }
   // 학원 목록 불러오기   
   public ReviewDTO[] selectInsByName(String ins_name) throws SQLException, NamingException{    
      ReviewDTO[] arr = null;
      
      try {
    	 conn = getConnection();
         pstmt = conn.prepareStatement(D.SQL_SELECT_CUR_NAME);
         pstmt.setString(1, ins_name);
         System.out.println(pstmt);
         rs = pstmt.executeQuery();
         arr = createInsNameArray(rs);
   
      } finally {
         close();
      }      
      
      return arr;
   }
   
   // 3. 학원후기 수정
   public int updateReviewByUid(int review_brd_uid, String review_brd_title, String review_brd_content) throws SQLException, NamingException{
      int cnt = 0;
      
      try {
    	 conn = getConnection();
         pstmt = conn.prepareStatement(D.SQL_UPDATE_REVIEW_BY_UID);
         pstmt.setString(1, review_brd_title);
         pstmt.setString(2, review_brd_content);
         pstmt.setInt(3, review_brd_uid);
         cnt = pstmt.executeUpdate();
         
      } finally {
         close();
      }
      
      return cnt;
   }
   
   // 4. 학원후기 삭제
   public int deleteReviewByUid(int review_brd_uid) throws SQLException, NamingException{
      int cnt = 0;
      
      try {
    	 conn = getConnection();
         pstmt = conn.prepareStatement(D.SQL_DELETE_REVIEW_BY_UID);
         pstmt.setInt(1, review_brd_uid);
         cnt = pstmt.executeUpdate();
         
      } finally {
         close();
      }
      
      return cnt;
   }
   

   // 학원후기 삽입
   // 1
   public int insertReview(int mb_uid, int class_uid, String review_brd_title, String review_brd_content) throws SQLException, NamingException{

      int cnt = 0;
      
      try {
    	 conn = getConnection();
         pstmt = conn.prepareStatement(D.SQL_INSERT_REVIEW);
         pstmt.setInt(1, mb_uid);
         pstmt.setInt(2, class_uid);
         pstmt.setString(3, review_brd_title);
         pstmt.setString(4, review_brd_content);
         cnt = pstmt.executeUpdate();
      } finally {
         close();
      }
      
      return cnt;
   }
   

   // 댓글
   // 1. 댓글 삽입
   public int insertRep(int mb_uid, int review_brd_uid, String rep_content) throws SQLException, NamingException{
      int cnt = 0;
      
      try {
    	 conn = getConnection();
         pstmt = conn.prepareStatement(D.SQL_INSERT_REP);
         pstmt.setInt(1, mb_uid);
         pstmt.setInt(2, review_brd_uid);
         pstmt.setString(3, rep_content);
         cnt = pstmt.executeUpdate();
      } finally {
         close();
      }
      
      return cnt;
   }
   
   // 2. 댓글 수정
   public int updateRepByUid(int rep_uid, String rep_content) throws SQLException, NamingException{
      int cnt = 0;
      
      try {
    	 conn = getConnection();
         pstmt = conn.prepareStatement(D.SQL_UPDATE_REP_BY_UID);
         pstmt.setString(1, rep_content);
         pstmt.setInt(2, rep_uid);
         cnt = pstmt.executeUpdate();
         
      } finally {
         close();
      }
      
      return cnt;
   }
   
   // 3. 댓글 삭제
   public int deleteRepByUid(int rep_uid) throws SQLException, NamingException{
      int cnt = 0;
      
      try {
    	 conn = getConnection();
         pstmt = conn.prepareStatement(D.SQL_DELETE_REP_BY_UID);
         pstmt.setInt(1, rep_uid);
         cnt = pstmt.executeUpdate();
         
      } finally {
         close();
      }
      
      return cnt;
   }

   // 4. 댓글 불러오기 reviewDTO ->  Array로 변경 (댓글 내용) 
   public ReviewDTO [] createRepArray(ResultSet rs) throws SQLException {
      
      ArrayList<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
      
      while(rs.next()){

         int review_brd_uid = rs.getInt("review_brd_uid");
         int rep_uid = rs.getInt("rep_uid");
         String mb_id = rs.getString("mb_id");
         int mb_uid = rs.getInt("mb_uid");
         String mb_img = rs.getString("mb_img");
         String rep_content = rs.getString("rep_content");
	     Date d = rs.getDate("rep_regdate");
	     Time t = rs.getTime("rep_regdate");
	     String rep_regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " | " + new SimpleDateFormat("hh:mm:ss").format(t);
         
         ReviewDTO dto = new ReviewDTO(review_brd_uid, rep_uid, mb_id, mb_uid, mb_img, rep_content, rep_regdate);
         reviewList.add(dto);         
      }
      
      int size = reviewList.size();
      ReviewDTO [] arr = new ReviewDTO[size];
      
      reviewList.toArray(arr);
      
      return arr;
   }
      
   // 4-1. review_uid로 댓글가져오기
   public ReviewDTO[] selectRepByUid(int review_brd_uid) throws SQLException, NamingException{
      
      ReviewDTO[] arr = null;
      
      try {
    	 conn = getConnection();
		 conn.setAutoCommit(false);
		 
         pstmt = conn.prepareStatement(D.SQL_SELECT_REP_BY_UID);
         pstmt.setInt(1, review_brd_uid);
         rs = pstmt.executeQuery();
         arr = createRepArray(rs);
		 conn.commit();
      } finally {
         close();
      }      
      
      return arr;
   }
   
}