package command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.MbDAO;
import com.lec.beans.MbDTO;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FindPwOkCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		MbDTO[] arr = null;
		MbDAO dao = new MbDAO();
		int cnt = 0;
		
		String mb_id = request.getParameter("mb_id");
		String mb_name = request.getParameter("mb_name");
		String mb_email = request.getParameter("mb_email");    

		String SMTP_USERNAME = "itmoa-test@naver.com";
		String SMTP_PASSWORD = "wkaldls_93";
		String HOST = "smtp.naver.com";
		//int PORT = 587;

		Properties props = new Properties();   
		props.put("mail.smtp.host", HOST); 
		props.put("mail.smtp.auth", "true");
		
		 Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
			    return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
			   }
		 });
		
		try {
			arr = dao.selectPw(mb_id, mb_name, mb_email);
			if(arr.length == 1 && arr[0].getMb_id().trim().equals(mb_id.trim()) && arr[0].getMb_name().trim().equals(mb_name.trim()) && arr[0].getMb_email().trim().equals(mb_email.trim())) {
				cnt++;
				// 메일 보내기
				String TO = arr[0].getMb_email();
				String FROM = "itmoa@itmoa.com";
				
			   MimeMessage message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(SMTP_USERNAME));
			   message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

			   
			   // 메일 제목
			   message.setSubject("[아이티모아] 비밀번호 안내입니다.");
			   
			   // 메일 내용
			   message.setText("고객님의 비밀번호는 " + arr[0].getMb_pw() + "입니다.");

			   System.out.println("Sending...");
			   
			   // send the message
			   Transport.send(message);
			   System.out.println("message sent successfully...");
				
	           System.out.println("Email sent!");
		       
	           request.setAttribute("findPwOk", cnt);
			} else {
				request.setAttribute("findPwOk", cnt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
        }
	}
	

}
