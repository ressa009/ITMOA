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

public class NewsDAO {
	    
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
	
	// 뉴스 검색 및 불러오기
	// 1-1.
	public NewsDTO [] createNewsArray(ResultSet rs) throws SQLException {
		ArrayList<NewsDTO> newsList = new ArrayList<NewsDTO>();
		
		while(rs.next()){
     
			int news_brd_uid = rs.getInt("news_brd_uid");
			String news_brd_title = rs.getString("news_brd_title");
			String news_brd_img = rs.getString("news_brd_img");
			int news_brd_viewcnt = rs.getInt("news_brd_viewcnt");
			
			NewsDTO dto = new NewsDTO(news_brd_uid, news_brd_title, news_brd_img, news_brd_viewcnt);
			newsList.add(dto);			
		}
		
		int size = newsList.size();
		NewsDTO [] arr = new NewsDTO[size];
		
		newsList.toArray(arr);
		
		return arr;
	}
	
	// 1-2.
	public NewsDTO[] selectNewsList(String option_news, String keyword, int fromRow, int pageRows) throws SQLException, NamingException {
		
		NewsDTO [] arr = null;
		String selectNews = D.SQL_SELECT_NEWS_BRD;
		
		if (option_news != null && !option_news.trim().equals("")
		&& keyword != null && !keyword.trim().equals("")) {
			try {
				switch(option_news) {
					case "1": 
						selectNews += D.SQL_SELECT_NEWS_BRD_WHERE_TITLE;
						selectNews += D.SQL_ORDER_BY_NEWS_BRD_UID_DESC;
						selectNews += D.SQL_SELECT_FROM_ROW_NEWS_BRD;
						keyword = "%" + keyword + "%";
						conn = getConnection();
						pstmt = conn.prepareStatement(selectNews);
						pstmt.setString(1, keyword);
			            pstmt.setInt(2, fromRow);
						pstmt.setInt(3, pageRows);
						break;
						
					case "2":
						selectNews += D.SQL_SELECT_NEWS_BRD_WHERE_CONTENT;
						selectNews += D.SQL_ORDER_BY_NEWS_BRD_UID_DESC;
						selectNews += D.SQL_SELECT_FROM_ROW_NEWS_BRD;
						keyword = "%" + keyword + "%";
						conn = getConnection();
						pstmt = conn.prepareStatement(selectNews);
						pstmt.setString(1, keyword);
			            pstmt.setInt(2, fromRow);
						pstmt.setInt(3, pageRows);
						break;
					case "3":
						selectNews += D.SQL_ORDER_BY_NEWS_BRD_UID_DESC;
						selectNews += D.SQL_SELECT_FROM_ROW_NEWS_BRD;
						conn = getConnection();
						pstmt = conn.prepareStatement(selectNews);
			            pstmt.setInt(1, fromRow);
						pstmt.setInt(2, pageRows);
						break;
				}
				rs = pstmt.executeQuery();
				arr = createNewsArray(rs);
			} finally {
				close();
			}

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
	
	// 2. 특정 뉴스 불러오기 NewsDTO ->  Array로 변경 
	public NewsDTO [] createNewsContentArray(ResultSet rs) throws SQLException {
		
		ArrayList<NewsDTO> list = new ArrayList<NewsDTO>();
		
		while(rs.next()){

			int news_brd_uid = rs.getInt("news_brd_uid");
			String news_brd_title = rs.getString("news_brd_title");
			String news_brd_content =  rs.getString("news_brd_content");
			String news_brd_img =  rs.getString("news_brd_img");
			int news_brd_viewcnt = rs.getInt("news_brd_viewcnt");
			
			NewsDTO dto = new NewsDTO(news_brd_uid, news_brd_title, news_brd_content, news_brd_img, news_brd_viewcnt);
			list.add(dto);
		}
		
		int size = list.size();
		NewsDTO [] arr = new NewsDTO[size];
		list.toArray(arr);
		
		return arr;
	}
	
	// 2-1. 특정 학원후기 불러오기 (조회수 증가o) news_brd_uid로 review
	public NewsDTO[] selectNewsByUid(int news_brd_uid) throws SQLException, NamingException{
		int cnt = 0;
		NewsDTO[] arr = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(D.SQL_UPDATE_NEWS_BRD_INC_VIEWCNT);
			pstmt.setInt(1, news_brd_uid);
			cnt = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(D.SQL_SELECT_NEWS_BRD_CONTENT);
			pstmt.setInt(1, news_brd_uid);

			rs = pstmt.executeQuery();
			arr = createNewsContentArray(rs);
	
			conn.commit();
		} catch(SQLException e) {
			conn.rollback();	// 처리할 것 처리하고 throw
			throw e;
		} finally {
			close();
		}

		
		return arr;
	}
	
	// 2-2. 특정 학원후기 불러오기 (조회수 증가x)
	public NewsDTO[] readNewsByUid(int news_brd_uid) throws SQLException, NamingException {
		NewsDTO [] arr = null;
		
		try {
			// 트랜잭션 처리
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_SELECT_NEWS_BRD_CONTENT);
			pstmt.setInt(1, news_brd_uid);
			rs = pstmt.executeQuery();
			
			arr = createNewsContentArray(rs);
			
		} finally {
			close();
		}
		
		return arr;
	}
}
