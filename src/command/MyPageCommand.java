package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.MbDAO;
import com.lec.beans.MbDTO;

public class MyPageCommand implements Command {
	   
	   @Override
	   public void execute(HttpServletRequest request, HttpServletResponse response) {


	      MbDAO dao = new MbDAO();
	      MbDTO [] arr = null;
	      MbDTO [] arr1 = null;
	            
	      int uid = Integer.parseInt(request.getParameter("mb_uid"));
	      
//	      System.out.println(uid);
	      
	      if(uid > 0) {// 매개변수 유효성 검증
	         try {
	            
	            arr = dao.myPage(uid);  // 회원정보 가져오기
	            arr1 = dao.myPageZzim(uid);  // 회원정보 가져오기
	           
	           
	            request.setAttribute("myPage", arr);
	            request.setAttribute("myPageZzim", arr1);
	      
	         } catch (SQLException e) {  
	            e.printStackTrace();
	         } catch (NamingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }else {
	            
	      }
	   
	   }

}
