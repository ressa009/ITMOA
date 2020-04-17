package com.lec.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import common.D;

public class MbDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Connection 도 생성된다!
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

	// 로그인
	public MbDTO[] createLoginArr(ResultSet rs) throws SQLException {	// 데이터 우리가 쓸 수 있는 값으로 바꿔오기
		ArrayList<MbDTO> list = new ArrayList<MbDTO>();

		while(rs.next()){
			int mb_uid = rs.getInt("mb_uid");
			String mb_id = rs.getString("mb_id");
			String mb_pw = rs.getString("mb_pw");
			int mb_level = rs.getInt("mb_level");
			String mb_img = rs.getString("mb_img");
			
			MbDTO dto = new MbDTO(mb_uid, mb_id, mb_pw, mb_level, mb_img);
			list.add(dto);			
		}
		
		int size = list.size();
		MbDTO [] arr = new MbDTO[size];
		list.toArray(arr);
		return arr;
	}
	
	public MbDTO[] login(String mb_id, String mb_pw) throws SQLException, NamingException {
		MbDTO[] arr = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_SELECT_LOGIN);	// 검증 어떤식으로? 이미지는 마이페이지에서, 레벨은 게시글 쓸떄필요한게 아닌가?
			pstmt.setString(1, mb_id);
			pstmt.setString(2, mb_pw);
			rs = pstmt.executeQuery();
			arr = createLoginArr(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return arr; 
	}
	
//	public int loginCheck(String mb_id, String mb_pw) {
//		String dbID = "";	// db 에서 꺼낸 아이디 담을 변수
//		String dbPW = "";	// db 에서 꺼낸 비밀번호 담을 변수
//		int x = -1;
//		try {
//			pstmt = conn.prepareStatement(D.SQL_SELECT_LOGINOK);
//			pstmt.setString(1, mb_id);
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) // 입려된 아이디에 해당하는 비번 있을경우
//            {
//				dbID = rs.getString("mb_id");
//                dbPW = rs.getString("mb_pw"); // 비번을 변수에 넣는다.
// 
//                if (dbPW.equals(mb_pw) && dbID.equals(mb_id)) 
//                    x = 1; // 넘겨받은 비번과 꺼내온 배번 비교. 같으면 인증성공
//                else                  
//                    x = 0; // DB의 비밀번호와 입력받은 비밀번호 다름, 인증실패
//                
//            } else {
//                x = -1; // 해당 아이디가 없을 경우
//            }
// 
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return x;
//	}
	
	// 회원가입
	public int join(String mb_name, String mb_id, String mb_pw, String mb_email, int mb_zip, String mb_add1, String mb_add2) throws SQLException, NamingException {
		int cnt = 0;
		String defaultImg = "default_Img.png"; 
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_INSERT_JOIN);
			pstmt.setString(1, mb_name);
			pstmt.setString(2, mb_id);
			pstmt.setString(3, mb_pw);
			pstmt.setString(4, mb_email);
			pstmt.setInt(5, mb_zip);
			pstmt.setString(6, mb_add1);
			pstmt.setString(7, mb_add2);
			pstmt.setString(8, defaultImg);
			System.out.println(pstmt);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return cnt;
	}
	
	// 마이페이지
	// 1. 회원정보 가져오기
	public MbDTO[] createMypageArr(ResultSet rs) throws SQLException {	// 데이터 우리가 쓸 수 있는 값으로 바꿔오기
		ArrayList<MbDTO> list = new ArrayList<MbDTO>();

		while(rs.next()){
			String mb_name = rs.getString("mb_name");
			int mb_uid = rs.getInt("mb_uid");
			String mb_id = rs.getString("mb_id");
			String mb_pw = rs.getString("mb_pw");
			String mb_email = rs.getString("mb_email");
			int mb_zip = rs.getInt("mb_zip");
			String mb_add1 = rs.getString("mb_add1");
			String mb_add2 = rs.getString("mb_add2");
			String mb_img = rs.getString("mb_img");
			
					
			MbDTO dto = new MbDTO(mb_name, mb_uid, mb_id, mb_pw, mb_email, mb_zip, mb_add1, mb_add2, mb_img);
			list.add(dto);
		}
		
		int size = list.size();
		MbDTO [] arr = new MbDTO[size];
		list.toArray(arr);
		
		return arr;
	}
	
	// 1-1
	public MbDTO[] myPage(int mb_uid) throws SQLException, NamingException {
		MbDTO[] arr = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_SELECT_MYPAGE);
			pstmt.setInt(1, mb_uid);
			rs = pstmt.executeQuery();
			arr = createMypageArr(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return arr; 
	}
	
	
	// 2-1 찜정보 가져오기
		public MbDTO[] createMypageZzimArr(ResultSet rs) throws SQLException {	// 데이터 우리가 쓸 수 있는 값으로 바꿔오기
			ArrayList<MbDTO> list = new ArrayList<MbDTO>();

			while(rs.next()){
				
				int zzim_uid = rs.getInt("zzim_uid");
				String ins_name = rs.getString("ins_name");
				String cur_name = rs.getString("cur_name");
				
				
				MbDTO dto = new MbDTO(zzim_uid,ins_name, cur_name);
				list.add(dto);
			}
			
			int size = list.size();
			MbDTO [] arr = new MbDTO[size];
			list.toArray(arr);
			
			return arr;
		}
	
	
	
	// 2-2
		public MbDTO[] myPageZzim(int mb_uid) throws SQLException, NamingException {
			MbDTO[] arr = null;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(D.SQL_SELECT_MYPAGE_ZZIM);
				pstmt.setInt(1, mb_uid);
				rs = pstmt.executeQuery();
				arr = createMypageZzimArr(rs);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			return arr; 
		}
		
		
		
		
	
	// 회원정보 수정
	public int update(String mb_img, String mb_pw, String mb_email, int mb_zip, String mb_add1, String mb_add2, int mb_uid) throws SQLException, NamingException{
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_UPDATE_MYPAGE);
			pstmt.setString(1, mb_img);
			pstmt.setString(2, mb_pw);
			pstmt.setString(3, mb_email);
			pstmt.setInt(4, mb_zip);
			pstmt.setString(5, mb_add1);
			pstmt.setString(6, mb_add2);
			pstmt.setInt(7, mb_uid);
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
	
	
	
	
	
	
	
	// 회원정보 찾기 - ID
	// 1.
	public MbDTO[] createSelectIdArray(ResultSet rs) throws SQLException {
		ArrayList<MbDTO> list = new ArrayList<MbDTO>();

		while(rs.next()){
			String mb_id = rs.getString("mb_id");
			String mb_name = rs.getString("mb_name");
			String mb_email = rs.getString("mb_email");
			
			MbDTO dto = new MbDTO(mb_id, mb_name, mb_email);
			list.add(dto);
		}
		
		int size = list.size();
		MbDTO [] arr = new MbDTO[size];
		list.toArray(arr);
		return arr;
	}
	
	// 1-1.
	public MbDTO[] selectId(String mb_name, String mb_email) throws SQLException, NamingException {
		
		MbDTO[] arr = null;
		
        try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_SELET_FIND_ACCOUNT_ID);
			pstmt.setString(1, mb_name);
			pstmt.setString(2, mb_email);
			rs = pstmt.executeQuery();
			arr = createSelectIdArray(rs);
			System.out.println(arr.length);
	        
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
        
        
		
		return arr;
	}
	
	// 회원정보 찾기 - PW
	// 1.
	public MbDTO[] createSelectPwArray(ResultSet rs) throws SQLException {
		ArrayList<MbDTO> list = new ArrayList<MbDTO>();

		while(rs.next()){
			String mb_id = rs.getString("mb_id");
			String mb_name = rs.getString("mb_name");
			String mb_email = rs.getString("mb_email");
			String mb_pw = rs.getString("mb_pw");
			
			MbDTO dto = new MbDTO(mb_id, mb_name, mb_email, mb_pw);
			list.add(dto);
		}
		
		int size = list.size();
		MbDTO [] arr = new MbDTO[size];
		list.toArray(arr);
		return arr;
	}
	
	public MbDTO[] selectPw(String mb_id, String mb_name, String mb_email) throws SQLException, NamingException {
		
		MbDTO[] arr = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_SELECT_FIND_ACCOUNT_PWD);
			pstmt.setString(1, mb_id);
			pstmt.setString(2, mb_name);
			pstmt.setString(3, mb_email);
			rs = pstmt.executeQuery();
			arr = createSelectPwArray(rs);
			System.out.println(arr.length);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return arr;
	}
	
}