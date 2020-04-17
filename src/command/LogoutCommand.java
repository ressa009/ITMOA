package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
				HttpSession httpSession = request.getSession();
				if (request.getSession() != null) {
					httpSession.removeAttribute("loginUid");
					httpSession.removeAttribute("loginId");
					httpSession.removeAttribute("loginLevel");
					httpSession.removeAttribute("loginImg");
			}
	}
}
