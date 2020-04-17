package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.ClassDAO;
import com.lec.beans.ClassDTO;

public class CurListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String uid = request.getParameter("mb_uid");
		String option_location = request.getParameter("option_location");
		String option_branch = request.getParameter("option_branch");
		String option_curName = request.getParameter("option_curName");
		
		
		ClassDAO dao = new ClassDAO();
		ClassDTO [] arr = null;
		ClassDTO [] zzimArr = null;
		
		
// 로그인 사용자일때
		if(uid != null && !uid.equals("")) {
			
			int mb_uid = Integer.parseInt(uid);
		
			// 검색조건 없을 때
			if(option_location == null ||  option_branch == null || option_curName == null) {
				try {
					
					arr = dao.selectClassList();	
					request.setAttribute("classList", arr);
					
					zzimArr = dao.selectZzimByMbUid(mb_uid);
					request.setAttribute("zzimList", zzimArr);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}		
				
			}

			
			// 검색조건 있을 때
			else if(!option_location.equals("") && !option_branch.equals("") && !option_curName.equals("") 
					&& option_location != null  && option_branch != null && option_curName != null ) {
				try {
					
					arr = dao.selectClassList(option_location, option_branch, option_curName);	
					request.setAttribute("classList", arr);
					
					zzimArr = dao.selectZzimByMbUid(mb_uid);
					request.setAttribute("zzimList", zzimArr);
					
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			
			}

		}else { //로그인 사용자가 아닐떄..?
			
			// 검색조건 없을 때
			if(option_location == null ||  option_branch == null || option_curName == null) {
				try {
					
					arr = dao.selectClassList();	
					request.setAttribute("classList", arr);
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}		
				
			}
							
			// 검색조건 있을 때
			else if(!option_location.equals("") && !option_branch.equals("") && !option_curName.equals("") 
					&& option_location != null  && option_branch != null && option_curName != null ) {
				try {
					
					arr = dao.selectClassList(option_location, option_branch, option_curName);	
					request.setAttribute("classList", arr);

				} catch (SQLException e) {
					e.printStackTrace();
				} 
			
			}
			
		}
		

	}
	

}