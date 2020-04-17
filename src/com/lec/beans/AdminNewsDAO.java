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

public class AdminNewsDAO {
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
	
	// 관리자페이지 뉴스 검색
	// 1.
	public NewsDTO[] createNewsArr(ResultSet rs) throws SQLException {	// 데이터 우리가 쓸 수 있는 값으로 바꿔오기
		ArrayList<NewsDTO> list = new ArrayList<NewsDTO>();

		while(rs.next()){
			int news_brd_uid = rs.getInt("news_brd_uid");
			String news_brd_title = rs.getString("news_brd_title");
			int news_brd_viewcnt = rs.getInt("news_brd_viewcnt");
			
			NewsDTO dto = new NewsDTO(news_brd_uid, news_brd_title, news_brd_viewcnt);
			list.add(dto);
		}
		
		int size = list.size();
		NewsDTO [] arr = new NewsDTO[size];
		list.toArray(arr);
		return arr;
	}
	
	// 2.
	public NewsDTO[] selectNews(int option_news_1, int option_news_2, String option_news_3, int fromRow, int pageRows) throws SQLException, NamingException {
		// option_news_1 은 검색조건
		// option_news_2 는 검색키워드
		NewsDTO[] arr = null;
		String SELECT_NEWS_BRD = D.SQL_SELECT_NEWS_BRD;
		
		int setStr1 = 1;
		
		switch(option_news_2) {
		case 1:
			SELECT_NEWS_BRD += D.SQL_SELECT_NEWS_BRD_WHERE_UID;
			break;
		case 2:
			SELECT_NEWS_BRD += D.SQL_SELECT_NEWS_BRD_WHERE_TITLE;
			break;
		case 3:
			SELECT_NEWS_BRD += D.SQL_SELECT_NEWS_BRD_WHERE_CONTENT;
			break;
		case 4:
			setStr1 = 0;
			break;
		}
		
		try {
			conn = getConnection();
			option_news_3 = "%" + option_news_3 + "%";
			switch(option_news_1) {
			case 1: // 최근순
				SELECT_NEWS_BRD += D.SQL_ORDER_BY_NEWS_BRD_UID_DESC;
				break;
			case 2: // 오래된 순
				SELECT_NEWS_BRD += D.SQL_ORDER_BY_NEWS_BRD_UID_ASC;
				break;
			case 3: // 조회수 순
				SELECT_NEWS_BRD += D.SQL_ORDER_BY_NEWS_BRD_VIEWCNT_DESC;
				break;
			}
			
			SELECT_NEWS_BRD += D.SQL_SELECT_FROM_ROW_NEWS_BRD;
			pstmt = conn.prepareStatement(SELECT_NEWS_BRD);
			if (setStr1 == 1) pstmt.setString(setStr1, option_news_3);
			pstmt.setInt(setStr1 + 1, fromRow); 
			pstmt.setInt(setStr1 + 2, pageRows);
			rs = pstmt.executeQuery();
			arr = createNewsArr(rs);
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
			pstmt = conn.prepareStatement(D.SQL_COUNT_ALL_NEWS_BRD);
			rs = pstmt.executeQuery();
			rs.next();
			rs.getInt(1);	// 첫번째 행의 
			cnt = rs.getInt(1);	// 첫번재 컬럼
		} finally {
			close();
		}
		
		return cnt;
	}	
	
	public NewsDTO[] createNewsArrByUid(ResultSet rs) throws SQLException {	// 데이터 우리가 쓸 수 있는 값으로 바꿔오기
		ArrayList<NewsDTO> list = new ArrayList<NewsDTO>();

		while(rs.next()){
			int news_brd_uid = rs.getInt("news_brd_uid");
			String news_brd_title = rs.getString("news_brd_title");
			String news_brd_img = rs.getString("news_brd_img");
			String news_brd_content = rs.getString("news_brd_content");
			int news_brd_viewcnt = rs.getInt("news_brd_viewcnt");
			
			NewsDTO dto = new NewsDTO(news_brd_uid, news_brd_title, news_brd_content, news_brd_img, news_brd_viewcnt);
			list.add(dto);
		}
		
		int size = list.size();
		NewsDTO [] arr = new NewsDTO[size];
		list.toArray(arr);
		return arr;
	}
	
	// 2.
	public NewsDTO[] selectNewsByUid(int news_brd_uid) throws SQLException, NamingException {
		NewsDTO[] arr = null;
		String SELECT_NEWS_BRD_BY_UID = D.SQL_SELECT_NEWS_BRD_CONTENT;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SELECT_NEWS_BRD_BY_UID);
			pstmt.setInt(1, news_brd_uid);
			
			rs = pstmt.executeQuery();
			arr = createNewsArrByUid(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return arr; 
	}
	
	// 관리자페이지 뉴스 수정
	public int updateNewsByUid(int news_brd_uid, String news_brd_title, String news_brd_content, String news_brd_img) throws SQLException, NamingException{
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_UPDATE_NEWS_BRD_BY_UID);
			pstmt.setString(1, news_brd_title);
			pstmt.setString(2, news_brd_content);
			pstmt.setString(3, news_brd_img);
			pstmt.setInt(4, news_brd_uid);
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	public int updateNewsByUidWithoutImg(int news_brd_uid, String news_brd_title, String news_brd_content) throws SQLException, NamingException{
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_UPDATE_NEWS_BRD_BY_UID_WITHOUT_IMG);
			pstmt.setString(1, news_brd_title);
			pstmt.setString(2, news_brd_content);
			pstmt.setInt(3, news_brd_uid);
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	// 관리자페이지 뉴스 삭제
	public int deleteNewsByUid(int mb_uid) throws SQLException, NamingException{
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_DELETE_NEWS_BRD_BY_UID);
			pstmt.setInt(1, mb_uid);
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	// 관리자페이지 뉴스 삽입
	// 1.
	public int insertNews(String news_brd_title, String news_brd_img, String news_brd_content) throws SQLException, NamingException{
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_INSERT_NEWS_BRD);
			pstmt.setString(1, news_brd_title);
			pstmt.setString(2, news_brd_img);
			pstmt.setString(3, news_brd_content);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return cnt;
	}
	
	public int insertNewsWithoutMainImg(String news_brd_title, String news_brd_content) throws SQLException, NamingException{
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_INSERT_NEWS_BRD_WITHOUT_IMG);
			pstmt.setString(1, news_brd_title);
			pstmt.setString(2, news_brd_content);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return cnt;
	}
	
}