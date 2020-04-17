package com.lec.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import common.D;

public class AdminMbDAO {

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

   
   // 관리자페이지 회원정보검색
   // 1.
   public MbDTO[] createMbArr(ResultSet rs) throws SQLException {   // 데이터 우리가 쓸 수 있는 값으로 바꿔오기
      ArrayList<MbDTO> list = new ArrayList<MbDTO>();

      while(rs.next()){
         int mb_uid = rs.getInt("mb_uid");
         String mb_name = rs.getString("mb_name");
         String mb_id = rs.getString("mb_id");
         String mb_email = rs.getString("mb_email");
         String mb_add1 = rs.getString("mb_add1");
         String mb_add2 = rs.getString("mb_add2");
         Date d = rs.getDate("mb_regdate");
         Time t = rs.getTime("mb_regdate");
         String mb_regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " " 
                     + new SimpleDateFormat("hh:mm:ss").format(t);
         
         
         MbDTO dto = new MbDTO(mb_uid, mb_name, mb_id, mb_email, mb_add1, mb_add2, mb_regdate);
         list.add(dto);
      }
      
      int size = list.size();
      MbDTO [] arr = new MbDTO[size];
      list.toArray(arr);
      return arr;
   }
   
   // 2.
   public MbDTO[] selectMb(int option_mb_1, int option_mb_2, String option_mb_3) throws SQLException, NamingException {
      // option_mb_1 은 회원구분
      // option_mb_2 는 검색조건
      // option_mb_3 은 검색키워드
      MbDTO[] arr = null;
      String SELECT_MB = D.SQL_SELECT_USER;
      SELECT_MB += D.SQL_SELECT_USER_WHERE_LEVEL;
      
      int chkUid = 0;
      // 검색조건 들어갈 switch문
      switch(option_mb_2) {
      case 1:
         SELECT_MB += D.SQL_SELECT_USER_WHERE_ID;
         break;
      case 2:
         SELECT_MB += D.SQL_SELECT_USER_WHERE_NAME;
         option_mb_3 = "%" + option_mb_3 + "%";
         break;
      case 3:
         SELECT_MB += D.SQL_SELECT_USER_WHERE_UID;
         chkUid = 4;
         break;
      case 4:
         SELECT_MB += D.SQL_SELECT_USER_WHERE_EMAIL;
         break;
      case 5:
         break;
      }
      
      SELECT_MB += D.SQL_USER_ORDER_BY;
      
      try {
    	 conn = getConnection();
         pstmt = conn.prepareStatement(SELECT_MB);
         
         // ? 값들어갈 switch문
         switch(option_mb_1) {
         case 1:   // 전체회원
            pstmt.setInt(1, 1);
            System.out.println("o");
            pstmt.setInt(2, 2);               
            System.out.println("o");
            pstmt.setInt(3, 3);   
            System.out.println("o");
            break;
         case 2: // 일반회원일 때
            pstmt.setInt(1, 1);   // 쿼리문이 IN이기 때문에 포함만 되면 됨. 따라서 첫번째 값에 걍 레벨값 넣어줌       
            pstmt.setInt(2, 0);               
            pstmt.setInt(3, 0);      
            break;
         case 3: // 슈퍼회원일 때
            pstmt.setInt(1, 2);               
            pstmt.setInt(2, 0);               
            pstmt.setInt(3, 0);   
            break;
         case 4: // 관리자일 때
            pstmt.setInt(1, 3);               
            pstmt.setInt(2, 0);               
            pstmt.setInt(3, 0);      
            break;
         }
         
         if(chkUid == 4) {
            pstmt.setInt(chkUid, Integer.parseInt(option_mb_3));
            rs = pstmt.executeQuery();
            arr = createMbArr(rs);
            System.out.println(arr.length);
         } else if(!option_mb_3.equals("a")) {
            pstmt.setString(4, option_mb_3);
            rs = pstmt.executeQuery();
            arr = createMbArr(rs);
            System.out.println(arr.length);
         } else {
         rs = pstmt.executeQuery();
         arr = createMbArr(rs);
         System.out.println(arr.length);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      
      return arr; 
   }
   
   // 페이징
	
	// 몇 번째 from 부터 몇 개 rows 를 SELECT
	public MbDTO[] selectFromRow(int option_mb_1, int option_mb_2, String option_mb_3, int fromRow, int pageRows) throws SQLException, NamingException{
		MbDTO[] arr = null;
	      String SELECT_MB = D.SQL_SELECT_USER;
	      SELECT_MB += D.SQL_SELECT_USER_WHERE_LEVEL;
	      
	      int chkUid = 0;
	      // 검색조건 들어갈 switch문
	      switch(option_mb_2) {
	      case 1:
	    	 if(option_mb_3.equals("") || option_mb_3 == null) {
	    		 break;
	    	 } else {
	    		 SELECT_MB += D.SQL_SELECT_USER_WHERE_ID;
	    		 break;
	    	 }
	      case 2:
	         if(option_mb_3.equals("") || option_mb_3 == null) {
	    		 break;
	    	 } else {
	    		 SELECT_MB += D.SQL_SELECT_USER_WHERE_NAME;
	    		 option_mb_3 = "%" + option_mb_3 + "%";
	    		 break;
	    	 }
	      case 3:
	    	  if(option_mb_3.equals("") || option_mb_3 == null) {
	    		  break;
	    	  } else {
	    		  chkUid = 4;
	    		  SELECT_MB += D.SQL_SELECT_USER_WHERE_UID;
	    		  break;
	    	  }
	      case 4:
	    	  if(option_mb_3.equals("") || option_mb_3 == null) {
	    		  break;
	    	  } else {
	    		  SELECT_MB += D.SQL_SELECT_USER_WHERE_EMAIL;
	    		  break;
	    	  }
	      case 5:
	         break;
	      }
	      
	      SELECT_MB += D.SQL_USER_ORDER_BY + D.SQL_SELECT_FROM_ROW_USER;
	      try {
	    	 conn = getConnection();
	    	 pstmt = conn.prepareStatement(SELECT_MB);
	    	 
	         // ? 값들어갈 switch문
	         switch(option_mb_1) {
	         case 1:   // 전체회원
	            pstmt.setInt(1, 1);
	            System.out.println("o");
	            pstmt.setInt(2, 2);               
	            System.out.println("o");
	            pstmt.setInt(3, 3);   
	            System.out.println("o");
	            break;
	         case 2: // 일반회원일 때
	            pstmt.setInt(1, 1);   // 쿼리문이 IN이기 때문에 포함만 되면 됨. 따라서 첫번째 값에 걍 레벨값 넣어줌       
	            pstmt.setInt(2, 0);               
	            pstmt.setInt(3, 0);      
	            break;
	         case 3: // 슈퍼회원일 때
	            pstmt.setInt(1, 2);               
	            pstmt.setInt(2, 0);               
	            pstmt.setInt(3, 0);   
	            break;
	         case 4: // 관리자일 때
	            pstmt.setInt(1, 3);               
	            pstmt.setInt(2, 0);               
	            pstmt.setInt(3, 0);      
	            break;
	         }
	         
	         if(chkUid == 4) {
	            pstmt.setInt(chkUid, Integer.parseInt(option_mb_3));
	            pstmt.setInt(5, fromRow);
				pstmt.setInt(6, pageRows);
				System.out.println(pstmt);
	            rs = pstmt.executeQuery();
	            arr = createMbArr(rs);   
	            System.out.println(arr.length);
	         } else if(!option_mb_3.equals("all") && option_mb_3.equals("") && option_mb_3 == null) {
	            pstmt.setInt(4, fromRow);
	            pstmt.setInt(5, pageRows);
	            System.out.println(pstmt);
	            rs = pstmt.executeQuery();
	            arr = createMbArr(rs);
	            System.out.println(arr.length);
	         } else if(!option_mb_3.equals("all") && !option_mb_3.equals("") && option_mb_3 != null) {
	        	 pstmt.setString(4, option_mb_3);
	        	 pstmt.setInt(5, fromRow);
	        	 pstmt.setInt(6, pageRows);
	        	 System.out.println(pstmt);
	        	 rs = pstmt.executeQuery();
	        	 arr = createMbArr(rs);
	        	 System.out.println(arr.length);
	         } else {
	        	pstmt.setInt(4, fromRow);
	        	pstmt.setInt(5, pageRows);
	        	System.out.println(pstmt);
	        	rs = pstmt.executeQuery();
	        	arr = createMbArr(rs);
	        	System.out.println(arr.length);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
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
			pstmt = conn.prepareStatement(D.SQL_COUNT_ALL_USER);
			rs = pstmt.executeQuery();
			rs.next();
			rs.getInt(1);	// 첫번째 행의 
			cnt = rs.getInt(1);	// 첫번재 컬럼
		} finally {
			close();
		}
		
		return cnt;
	}
   
   // 관리자페이지 회원상세정보(수정) 가져오기
   // 1.
   public MbDTO[] createMbByUidArr(ResultSet rs) throws SQLException {   // 데이터 우리가 쓸 수 있는 값으로 바꿔오기
      ArrayList<MbDTO> list = new ArrayList<MbDTO>();

      while(rs.next()){
			int mb_uid = rs.getInt("mb_uid");
			String mb_name = rs.getString("mb_name");
			String mb_id = rs.getString("mb_id");
			String mb_pw = rs.getString("mb_pw");
			String mb_email = rs.getString("mb_email");
			int mb_level = rs.getInt("mb_level");
			int mb_zip = rs.getInt("mb_zip");
			String mb_add1 = rs.getString("mb_add1");
			String mb_add2 = rs.getString("mb_add2");
			String mb_img = rs.getString("mb_img");
			
			MbDTO dto = new MbDTO(mb_uid, mb_id, mb_pw, mb_name, mb_email, mb_level, mb_zip, mb_add1, mb_add2, mb_img);
			list.add(dto);
		}
		
		int size = list.size();
		MbDTO [] arr = new MbDTO[size];
		list.toArray(arr);
		return arr;
	}
	
	
	// 2.
	public MbDTO[] selectMbByUid(int mb_uid) throws SQLException, NamingException {
		MbDTO[] arr = null;
		String SELECT_MB = D.SQL_SELECT_USER + D.SQL_SELECT_USER_WHERE_UID2;
		System.out.println(mb_uid);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SELECT_MB);
			pstmt.setInt(1, mb_uid);
			rs = pstmt.executeQuery();
			arr = createMbByUidArr(rs);
			System.out.println(mb_uid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return arr; 
	}

	// 관리자페이지 회원정보수정
	public int updateMbByUid(int mb_level, String mb_email, int mb_zip, String mb_add1, String mb_add2, 
			String mb_img,int mb_uid) throws SQLException, NamingException{
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_UPDATE_USER);
			pstmt.setInt(1, mb_level);
			pstmt.setString(2, mb_email);
			pstmt.setInt(3, mb_zip);
			pstmt.setString(4, mb_add1);
			pstmt.setString(5, mb_add2);
			pstmt.setString(6, mb_img);
			pstmt.setInt(7, mb_uid);
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}

	// 관리자페이지 회원정보삭제
	public int deleteMbByUid(int mb_uid) throws SQLException, NamingException{
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_DELETE_USER_BY_UID);
			pstmt.setInt(1, mb_uid);
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
	
}