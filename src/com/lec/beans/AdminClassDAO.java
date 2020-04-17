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

public class AdminClassDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	
	
	
	public AdminClassDAO() {
		super();
		System.out.println("AdminClassDAO 객체 생성");
	}

	public static Connection getConnection() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/testIt");
		
		return ds.getConnection();
	}

	
	// DB 자원 반납 메소드
	public void close() throws SQLException {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
	}

	//관리자  AdminClass 배열 생성
	public ClassDTO[] createCurArray(ResultSet rs) throws SQLException {

		ArrayList<ClassDTO> list = new ArrayList<ClassDTO>();

		while (rs.next()) {

			int ins_uid = rs.getInt("ins_uid");
			String ins_name = rs.getString("ins_name");
			String ins_add1 = rs.getString("ins_add1");
			String ins_tel = rs.getString("ins_tel");

			ClassDTO dto = new ClassDTO(ins_uid, ins_name, ins_add1, ins_tel);
			list.add(dto);
		}

		int size = list.size();
		ClassDTO[] arr = new ClassDTO[size];
		list.toArray(arr);
		
		return arr;
	}

	
	//관리자  AdminInsUpdateView 배열 생성
	public ClassDTO[] createInsArray(ResultSet rs) throws SQLException {

		ArrayList<ClassDTO> list = new ArrayList<ClassDTO>();

		while (rs.next()) {

			String ins_name = rs.getString("ins_name");
			String ins_add1 = rs.getString("ins_add1");
			String ins_add2 = rs.getString("ins_add2");
			int ins_zip = rs.getInt("ins_zip");
			String ins_tel = rs.getString("ins_tel");
			String ins_location = rs.getString("ins_location");
			String ins_branch = rs.getString("ins_branch");
			String ins_img = rs.getString("ins_img");
			double ins_x = rs.getDouble("ins_x");
			double ins_y = rs.getDouble("ins_y");
			int ins_uid = rs.getInt("ins_uid");
			ClassDTO dto = new ClassDTO(ins_name,  ins_zip,  ins_add1,  ins_add2,  ins_tel,  ins_img, ins_branch, ins_location, ins_x, ins_y, ins_uid);
			list.add(dto);
		}

		int size = list.size();
		ClassDTO[] arr = new ClassDTO[size];
		list.toArray(arr);
		
		return arr;
	}
	
	
//	//관리자페이지 학원 수정할 경우 화면에 띄워주기 -> 사용안해서 주석처리
//	public ClassDTO[] insView() throws SQLException, NamingException {
//		
//		ClassDTO [] insArr = null ;
//		
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(D.SQL_SELECT_INS);
//			rs = pstmt.executeQuery();
//			insArr = createInsArray(rs);
//			
//		} finally {
//			close();
//		}
//		
//		
//		return insArr;
//		
//		
//	}
	
	
	
	// 관리자페이지 학원검색 전체 
	public ClassDTO[] selectInsList() throws SQLException, NamingException {
		ClassDTO [] arr = null;
		
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(D.SQL_SELECT_INS);
				rs = pstmt.executeQuery();
				arr = createCurArray(rs);
		
		} finally {
			close();
		}		
		
		return arr;
	}
	
	
	// 관리자페이지 학원검색 전체 + 조건

	public ClassDTO[] selectInsListByOption(int option, String keyword) throws SQLException, NamingException {

		ClassDTO [] arr = null;
		String selectIns = D.SQL_SELECT_INS;
		
		try {
			
			switch(option) {

			case 1 :	
				selectIns += D.SQL_INS_WHERE_NAME;
				keyword = "%" + keyword + "%";
				conn = getConnection();
				pstmt = conn.prepareStatement(selectIns);
				pstmt.setString(1, keyword);
				break;
				
			case 2 :
				selectIns += D.SQL_INS_WHERE_UID;
				int k1 = Integer.parseInt(keyword);
				conn = getConnection();
				pstmt = conn.prepareStatement(selectIns);
				pstmt.setInt(1, k1);
				break;

			default :
				conn = getConnection();
				pstmt = conn.prepareStatement(selectIns);
				break;
			
			}
			
			rs = pstmt.executeQuery();
			
			arr = createCurArray(rs);
		
		} finally {
			close();
		}		

		return arr;
	}
	
	
	// 페이징
	// 몇 번째 from 부터 몇 개 rows 를 SELECT
	public ClassDTO[] selectInsFromRow(int from, int rows) throws SQLException, NamingException {
		ClassDTO [] arr = null;
		
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(D.SQL_SELECT_INS + D.SQL_SELECT_FROM_ROW_INS);
				pstmt.setInt(1, from);
				pstmt.setInt(2, rows);
				rs = pstmt.executeQuery();
				arr = createCurArray(rs);
		
		} finally {
			close();
		}		
		
		return arr;
	}
	
	public ClassDTO[] selectInsFromRow2(int option, String keyword, int from, int rows) throws SQLException, NamingException {

		ClassDTO [] arr = null;
		String selectIns = D.SQL_SELECT_INS;
		
		try {
			int cnt = 0;
			switch(option) {

			case 1 :	
				selectIns += D.SQL_INS_WHERE_NAME;
				selectIns += D.SQL_SELECT_FROM_ROW_INS;
				keyword = "%" + keyword + "%";
				conn = getConnection();
				pstmt = conn.prepareStatement(selectIns);
				pstmt.setString(1, keyword);
				cnt++;
				break;
				
			case 2 :
				selectIns += D.SQL_INS_WHERE_UID;
				selectIns += D.SQL_SELECT_FROM_ROW_INS;
				int k1 = Integer.parseInt(keyword);
				conn = getConnection();
				pstmt = conn.prepareStatement(selectIns);
				pstmt.setInt(1, k1);
				cnt++;
				break;

			default :
				conn = getConnection();
				selectIns += D.SQL_SELECT_FROM_ROW_INS;
				pstmt = conn.prepareStatement(selectIns);
				break;
			
			}
			
			if(cnt == 1) {
				pstmt.setInt(2, from);
				pstmt.setInt(3, rows);
			} else {
				pstmt.setInt(1, from);
				pstmt.setInt(2, rows);
			}
			
			rs = pstmt.executeQuery();
			arr = createCurArray(rs);
		
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
			pstmt = conn.prepareStatement(D.SQL_COUNT_ALL_INS);
			rs = pstmt.executeQuery();
			rs.next();
			rs.getInt(1);	// 첫번째 행의 
			cnt = rs.getInt(1);	// 첫번재 컬럼
		} finally {
			close();
		}
		
		return cnt;
	}
	
	// 관리자페이지 학원등록
	public int insertIns(ClassDTO dto) throws SQLException, NamingException{
		
		String ins_name = dto.getIns_name();
		String ins_tel =dto.getIns_tel();
		int ins_zip = dto.getIns_zip();
		String ins_add1 = dto.getIns_add1();
		String ins_add2 = dto.getIns_add2();
		String ins_location = dto.getIns_location();
		String ins_branch = dto.getIns_branch();
		Double ins_x = dto.getIns_x();
		Double ins_y = dto.getIns_y();
		String ins_img = dto.getIns_img();
    
		return this.insertIns(ins_name, ins_zip, ins_add1, ins_add2, ins_tel, ins_img, ins_branch, ins_location, ins_x, ins_y);
	}
	
	
	
	public int insertIns(String ins_name, int ins_zip, String ins_add1, String ins_add2, String ins_tel, String ins_img,
			String ins_branch, String ins_location, double ins_x, double ins_y) throws SQLException, NamingException{

		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_INSERT_INS);
			pstmt.setString(1, ins_name);
			pstmt.setString(2, ins_tel);
			pstmt.setInt(3, ins_zip);
			pstmt.setString(4, ins_add1);
			pstmt.setString(5, ins_add2);
			pstmt.setString(6, ins_location);
			pstmt.setString(7, ins_branch);
			pstmt.setString(8, ins_img);	
			pstmt.setDouble(9, ins_x);
			pstmt.setDouble(10, ins_y);
			
			cnt = pstmt.executeUpdate();
	
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
	
	// 수정할 학원정보 불러오기
	public ClassDTO[] selectInsByUid(int ins_uid) throws SQLException, NamingException{

		ClassDTO [] arr = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_SELECT_INS_BY_UID_FOR_UPDATE);
			pstmt.setInt(1, ins_uid);
			
			rs = pstmt.executeQuery();
			
			arr = createInsArray(rs);
			
		} finally {
			close();
		}
		
		return arr;
	}
	

	// 관리자페이지 학원 수정
	public int updateInsByUid(String ins_name, String ins_tel, int ins_zip, String ins_add1, String ins_add2,
			String ins_location, String ins_branch, String ins_img, double ins_x, double ins_y, int ins_uid) throws SQLException, NamingException{
		    
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_UPDATE_INS);
			pstmt.setString(1, ins_name);
			pstmt.setString(2, ins_tel);
			pstmt.setInt(3, ins_zip);
			pstmt.setString(4, ins_add1);
			pstmt.setString(5, ins_add1);
			pstmt.setString(6, ins_location);
			pstmt.setString(7, ins_branch);
			pstmt.setString(8, ins_img);
			pstmt.setDouble(9, ins_x);
			pstmt.setDouble(10, ins_y);
			pstmt.setInt(11, ins_uid);
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
	
	
	// 학원 이미지 삭제
	public int deleteInsImgByUid(String ins_img, int ins_uid) throws SQLException{
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_DELETE_INS_IMG);
			pstmt.setString(1, ins_img);
			pstmt.setInt(2, ins_uid);
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	public int deleteInsByUid(int ins_uid) throws SQLException, NamingException{
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_DELETE_INS);
			pstmt.setInt(1, ins_uid);
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
	
	
	
	// 학원 과정 관리 
	// 1. 과정 추가 
	public int insertCur(int ins_uid, String cur_name, int cur_hours, int cur_months, String cur_month1, String cur_month2, String cur_month3, 
			String cur_month4, String cur_month5, String cur_month6) throws SQLException, NamingException{
		
		int cnt = 0;
		int cur_uid = 0;
		
		try {
			
			String [] generetedCols = {"cur_uid"};
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_INSERT_CUR, generetedCols);   
			pstmt.setString(1, cur_name);
			pstmt.setInt(2, cur_hours);
			pstmt.setInt(3, cur_months);
			pstmt.setString(4, cur_month1);
			pstmt.setString(5, cur_month2);
			pstmt.setString(6, cur_month3);
			pstmt.setString(7, cur_month4);
			pstmt.setString(8, cur_month5);
			pstmt.setString(9, cur_month6);
					
			cnt = pstmt.executeUpdate();
			
			// auto-generated keys 값 뽑아오기
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				cur_uid = rs.getInt(1);
			}
			
			pstmt.close();
			
			// 클래스에 저장하기
			pstmt = conn.prepareStatement(D.SQL_INSERT_CLASS);
				pstmt.setInt(1, ins_uid);
				pstmt.setInt(2, cur_uid);
				pstmt.executeUpdate();
	
		} finally {
			close();
		}
		return cnt;
	}
	
	

	// 2. 과정 출력
		public ClassDTO[] createClassArrayByUid(ResultSet rs) throws SQLException {

			ArrayList<ClassDTO> list = new ArrayList<ClassDTO>();

			while (rs.next()) {
				String cur_name = rs.getString("cur_name");
				int cur_hours = rs.getInt("cur_hours");
				int cur_uid = rs.getInt("cur_uid");
				int class_uid = rs.getInt("class_uid");

				ClassDTO dto = new ClassDTO(cur_name, cur_hours, class_uid, cur_uid);
				list.add(dto);
			}

			int size = list.size();
			ClassDTO[] arr = new ClassDTO[size];
			list.toArray(arr);
			return arr;
		}

		
		// 특정학원의 수정할 class정보 불러오기
		public ClassDTO[] selectClassByUid(int ins_uid) throws SQLException, NamingException {
			ClassDTO[] arr = null ;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(D.SQL_SELECT_CLASS_BY_INS_UID);
				pstmt.setInt(1, ins_uid);
				rs = pstmt.executeQuery();
				arr = createClassArrayByUid(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			return arr;
		}
		
		
		
		// 2. 과정 출력
		public ClassDTO[] createCurArrayByUid(ResultSet rs) throws SQLException {

			ArrayList<ClassDTO> list = new ArrayList<ClassDTO>();

			while (rs.next()) {
				int cur_uid = rs.getInt("cur_uid");
				String cur_name = rs.getString("cur_name");
				int cur_hours = rs.getInt("cur_hours");
				int cur_months = rs.getInt("cur_months");
				String cur_month1 = rs.getString("cur_month1");
				String cur_month2 = rs.getString("cur_month2");
				String cur_month3 = rs.getString("cur_month3");
				String cur_month4 = rs.getString("cur_month4");
				String cur_month5 = rs.getString("cur_month5");
				String cur_month6 = rs.getString("cur_month6");

				
				ClassDTO dto = new ClassDTO(cur_uid, cur_name, cur_hours, cur_months, cur_month1, cur_month2, cur_month3, cur_month4, cur_month5, cur_month6);
				list.add(dto);
			}

			int size = list.size();
			ClassDTO[] arr = new ClassDTO[size];
			list.toArray(arr);
			return arr;
		}

		
		
		// 특정학원의 수정할 class정보 불러오기
		public ClassDTO[] selectCurByUid(int ins_uid) throws SQLException, NamingException {
			ClassDTO[] arr = null ;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(D.SQL_SELECT_CUR_BY_INS_UID);
				pstmt.setInt(1, ins_uid);
				rs = pstmt.executeQuery();
				arr = createCurArrayByUid(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			return arr;
		}

	


	// 클래스 정보 수정
	public int updateClass(int cur_uid, String cur_name, int cur_hours, int cur_months, String cur_month1, String cur_month2, String cur_month3, 
			String cur_month4, String cur_month5, String cur_month6) throws SQLException, NamingException{ // 선택된 클래스가 가지고 있는 cur_uid를 인자로 줄것
		
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_UPDATE_CUR);
			pstmt.setString(1, cur_name);
			pstmt.setInt(2, cur_hours);
			pstmt.setInt(3, cur_months);
			pstmt.setString(4, cur_month1);
			pstmt.setString(5, cur_month2);
			pstmt.setString(6, cur_month3);
			pstmt.setString(7, cur_month4);
			pstmt.setString(8, cur_month5);
			pstmt.setString(9, cur_month6);
			pstmt.setInt(10, cur_uid);
			
			cnt = pstmt.executeUpdate();

			
		} finally {
			close();
		}
		
		return cnt; 
		
	}
	
	
	
	// 3. 과정 삭제
	public int deleteClassByUid(int class_uid) throws SQLException, NamingException{
		
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_DELETE_CLASS);
			pstmt.setInt(1, class_uid);
		
			cnt = pstmt.executeUpdate();
			pstmt.close();

			
		} finally {
			close();
		}
		
		
		return cnt; 
		
	}
	
	
	// 과정목록에서 쓰는 학원정보 출력용
	
	public ClassDTO[] createInsArrayForClassList(ResultSet rs) throws SQLException {

		ArrayList<ClassDTO> list = new ArrayList<ClassDTO>();

		while (rs.next()) {

			int ins_uid = rs.getInt("ins_uid");
			String ins_name = rs.getString("ins_name");
			
			ClassDTO dto = new ClassDTO(ins_uid, ins_name);
			list.add(dto);
		}

		int size = list.size();
		ClassDTO[] arr = new ClassDTO[size];
		list.toArray(arr);
		
		return arr;
	}
	
	
	
	
	public ClassDTO[] selectInsByUidForClassList(int ins_uid) throws NamingException, SQLException {
		
		ClassDTO[] arr = null ;

		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_SELECT_INS_FOR_CLASS);
			pstmt.setInt(1, ins_uid);
			rs = pstmt.executeQuery();
			arr = createInsArrayForClassList(rs);
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return arr;
	}
	
	
	
}