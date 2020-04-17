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

public class ClassDAO {

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

	
	public ClassDTO[] createClassArray(ResultSet rs) throws SQLException {

		ArrayList<ClassDTO> list = new ArrayList<ClassDTO>();

		while (rs.next()) {

			String ins_img = rs.getString("ins_img");
			int class_zzimcnt = rs.getInt("class_zzimcnt");
			String ins_name = rs.getString("ins_name");
			String cur_name = rs.getString("cur_name");
			String ins_branch = rs.getString("ins_branch");
			int class_uid = rs.getInt("class_uid");

			ClassDTO dto = new ClassDTO(ins_name, ins_branch,cur_name, class_zzimcnt, ins_img, class_uid);
			list.add(dto);
		}

		int size = list.size();
		ClassDTO[] arr = new ClassDTO[size];
		list.toArray(arr);
		return arr;
	}

	
	
	
	
	public ClassDTO[] selectCurList() throws SQLException {

		ClassDTO [] arr = null;
		String selectCur = D.SQL_SELECT_CLASS;

		// 정렬
		selectCur += D.SQL_ORDER_CLASS_UID;

		try {
			// keyword가 있을 경우 쿼리문에 키워드 넘겨주기
			conn = getConnection();
			pstmt = conn.prepareStatement(selectCur);
			rs = pstmt.executeQuery();
			arr = createClassArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return arr;
	}
	
	

	public ClassDTO[] createBranchArray(ResultSet rs) throws SQLException {

		ArrayList<ClassDTO> list = new ArrayList<>();

		while (rs.next()) {
			String ins_branch = rs.getString("ins_branch");
			ClassDTO dto = new ClassDTO(ins_branch);
			list.add(dto);
		}

		int size = list.size();
		ClassDTO[] arr = new ClassDTO[size];
		list.toArray(arr);

		return arr;
	}

	
	
	public ClassDTO[] selectBranchList(int option_cur_1) throws SQLException {

		ClassDTO [] arr = null;
		String selectCur = D.SQL_SELECT_BRANCH;
		selectCur += D.SQL_SELECT_BRANCH_WHERE_LOCATION;

		try {
			// keyword가 있을 경우 쿼리문에 키워드 넘겨주기
			pstmt = conn.prepareStatement(selectCur);
			pstmt.setInt(1, option_cur_1);
			rs = pstmt.executeQuery();
			arr = createBranchArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return arr;
	}


	
	public ClassDTO[] createClassArrayByUid(ResultSet rs) throws SQLException {

		ArrayList<ClassDTO> list = new ArrayList<ClassDTO>();

		while (rs.next()) {
			String cur_name = rs.getString("cur_name");
			int cur_hours = rs.getInt("cur_hours");
			int cur_months = rs.getInt("cur_months");
			String cur_month1 = rs.getString("cur_month1");
			String cur_month2 = rs.getString("cur_month2");
			String cur_month3 = rs.getString("cur_month3");
			String cur_month4 = rs.getString("cur_month4");
			String cur_month5 = rs.getString("cur_month5");
			String cur_month6 = rs.getString("cur_month6");
			String ins_name = rs.getString("ins_name");
			String ins_tel = rs.getString("ins_tel");
			String ins_img = rs.getString("ins_img");
			double ins_x = rs.getDouble("ins_x");
			double ins_y = rs.getDouble("ins_y");
			int class_uid = rs.getInt("class_uid");
			
			ClassDTO dto = new ClassDTO( cur_name,  cur_hours,  cur_months,  cur_month1,  cur_month2,
					 cur_month3,  cur_month4,  cur_month5,  cur_month6,  ins_name,  ins_tel,  ins_img, ins_x, ins_y, class_uid);
			list.add(dto);
		}

		int size = list.size();
		ClassDTO[] arr = new ClassDTO[size];
		list.toArray(arr);
		return arr;
	}

	
	
	public ClassDTO[] selectClassByUid(int class_uid) throws SQLException, NamingException {
		ClassDTO[] arr = null ;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_SELECT_INS_BY_UID);
			pstmt.setInt(1, class_uid);
			rs = pstmt.executeQuery();
			arr = createClassArrayByUid(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return arr;
	}
	
	public ClassDTO[] createZZimArrayByUid(ResultSet rs) throws SQLException {

		ArrayList<ClassDTO> list = new ArrayList<ClassDTO>();

		while (rs.next()) {
			int mb_uid = rs.getInt("mb_uid");
			int zzim_uid = rs.getInt("zzim_uid");
			
			ClassDTO dto = new ClassDTO(mb_uid, zzim_uid);
			list.add(dto);
		}

		int size = list.size();
		ClassDTO[] arr = new ClassDTO[size];
		list.toArray(arr);
		return arr;
	}
	
	
	
	public ClassDTO[] selectZZimByUid(int class_uid, int mb_uid) throws SQLException, NamingException {
		ClassDTO[] arr = null ;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_SELECT_ZZIM_BY_UID);
			pstmt.setInt(1, class_uid);
			pstmt.setInt(2, mb_uid);
			rs = pstmt.executeQuery();
			arr = createZZimArrayByUid(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return arr;
	}
	
	
	public int updateMemberByUid(int mb_uid) throws SQLException, NamingException {
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_UPDATE_MB_LEVEL);
			pstmt.setInt(1, mb_uid);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
	
	public int insertZZim(int mb_uid, int class_uid) throws SQLException, NamingException {
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_INSERT_ZZIM);
			pstmt.setInt(1, mb_uid);
			pstmt.setInt(2, class_uid);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return cnt;
	}
	
	public int deleteZZim(int zzim_uid) throws SQLException, NamingException {
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_DELETE_ZZIM);
			pstmt.setInt(1, zzim_uid);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
	
	//지역별 학원출력 
		public ClassDTO[] selectClassList(String option_location, String option_branch, String option_curName) throws SQLException {
	
			ClassDTO [] arr = null;
			String selectClass = D.SQL_SELECT_CLASS;
			int option = 1; // 조건, 1이 기본
			
			/* ########## 조건 구분 ##########
			 *           
			 * option   지역          지점명         과정명
			 * -----------------------------
			 *   1 :    전체     +  전체     +  X   = 기본
			 *   2 :    전체     +  전체     +  O
			 *  
			 *   3 :    선택    +  전체     +  X
			 *   4 :    선택    +  전체     +  O
			 * 
			 *   5 :    선택    +  선택     +  X
			 *   6 :    선택    +  선택     +  O 
			 *  
			 */
			    
			// 지역 조건이 '전체'가 아니라면, 지점조건을 따질것
			if(!option_location.equals("전체")) {

				// 지점조건이 '전체'가 아니라면! 지점조건을 추가해주고 과정조건을 따지러 가자. 
				if(!option_branch.equals("전체")) {
					
					// 과정명이 입력값을 가졌는가? 값이 있다면 과정명조건 추가
					if(!option_curName.equals("전체과정")) {
						option = 6;
					}else {
						option = 5;
					}
				
				}else {
					// 지점조건이 '전체'라면 바로 과정조건을 따지러가자.
					if(!option_curName.equals("전체과정")) { // 과정조건이 '전체과정'이 아니라면 과정명 조건도 추가 
						option = 4;
					}else {
						option = 3;
					}
				}
			// 지역조건이 '전체'라면 지점은 당연히 전체니까 과정명만 따져주자			
			}else { 
					if(!option_curName.equals("전체과정")) {
						option = 2;
					}else {
						option = 1;
					}
				}

			
			
			try {
				
				switch(option) {
				case 1:
					selectClass += D.SQL_ORDER_CLASS_UID; //정렬
					conn = getConnection();
					pstmt = conn.prepareStatement(selectClass);
					break;
				case 2:
					selectClass += D.SQL_SELECT_CLASS_WHERE_CUR_NAME;
					selectClass += D.SQL_ORDER_CLASS_UID; //정렬
					conn = getConnection();
					pstmt = conn.prepareStatement(selectClass);
					pstmt.setString(1, option_curName);
					break;
				case 3:
					selectClass += D.SQL_SELECT_CLASS_WHERE_INS_LOCATION; 
					selectClass += D.SQL_ORDER_CLASS_UID; //정렬
					conn = getConnection();
					pstmt = conn.prepareStatement(selectClass);
					pstmt.setString(1, option_location);
					break;
				case 4:
					selectClass += D.SQL_SELECT_CLASS_WHERE_INS_LOCATION;
					selectClass += D.SQL_SELECT_CLASS_WHERE_CUR_NAME;
					selectClass += D.SQL_ORDER_CLASS_UID; //정렬
					conn = getConnection();
					pstmt = conn.prepareStatement(selectClass);
					pstmt.setString(1, option_location);
					pstmt.setString(2, option_curName);
					break;
				case 5:
					selectClass += D.SQL_SELECT_CLASS_WHERE_INS_LOCATION;
					selectClass += D.SQL_SELECT_CLASS_WHERE_INS_BRANCH; 
					selectClass += D.SQL_ORDER_CLASS_UID; //정렬
					conn = getConnection();
					pstmt = conn.prepareStatement(selectClass);
					pstmt.setString(1, option_location);
					pstmt.setString(2, option_branch);
					break;
				case 6: 
					selectClass += D.SQL_SELECT_CLASS_WHERE_INS_LOCATION;
					selectClass += D.SQL_SELECT_CLASS_WHERE_INS_BRANCH; 
					selectClass += D.SQL_SELECT_CLASS_WHERE_CUR_NAME;
					selectClass += D.SQL_ORDER_CLASS_UID; //정렬
					conn = getConnection();
					pstmt = conn.prepareStatement(selectClass);
					pstmt.setString(1, option_location);
					pstmt.setString(2, option_branch);
					pstmt.setString(3, option_curName);
					break;
				}

				rs = pstmt.executeQuery();
				arr = createClassArray(rs);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			
			return arr;
		}

		
		
		public ClassDTO[] selectClassList() throws SQLException{

			ClassDTO [] arr = null;
			String selectClass = D.SQL_SELECT_CLASS;
		
			
			try {
				
				selectClass += D.SQL_ORDER_CLASS_UID; //정렬
				conn = getConnection();
				pstmt = conn.prepareStatement(selectClass);
				rs = pstmt.executeQuery();
				arr = createClassArray(rs);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			
			return arr;
		}
		
		
		
		
		
		public ClassDTO[] createZZimArrayByMbUid(ResultSet rs) throws SQLException {

			ArrayList<ClassDTO> list = new ArrayList<ClassDTO>();

			while (rs.next()) {
				int mb_uid = rs.getInt("mb_uid");
				int class_uid = rs.getInt("class_uid");
				
				ClassDTO dto = new ClassDTO(mb_uid, class_uid);
				list.add(dto);
			}

			int size = list.size();
			ClassDTO[] arr = new ClassDTO[size];
			list.toArray(arr);
			
			return arr;
		}
	
		
		public ClassDTO[] selectZzimByMbUid(int mb_uid) throws SQLException{

			ClassDTO [] arr = null;
			
			try {
	
				conn = getConnection();
				pstmt = conn.prepareStatement(D.SQL_SELECT_ZZIM_BY_MB_UID);
				pstmt.setInt(1, mb_uid);
				rs = pstmt.executeQuery();
				arr = createZZimArrayByMbUid(rs);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			
			return arr;
		}
		
		
		
		
		
		
		
	
}