package user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import user.beans.Message;
import user.beans.User;
import user.service.MessageService;

@WebServlet(urlPatterns = { "/newmessage" })
public class NewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("newmessage.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException {

			Message message = new Message();
			HttpSession session = request.getSession();

			User user = (User) session.getAttribute("loginUser");

			//変数messageでrequest領域にあるsubjectの項目をmessageクラスのsetSubjectに入れる
			message.setSubject(request.getParameter("subject"));
			message.setText(request.getParameter("text"));
			message.setCategory(request.getParameter("category"));
			message.setUserId(user.getId());

			List<String> messages = new ArrayList<String>();
			if (isValid(request, messages) == true) {
				new MessageService().register(message);
				response.sendRedirect("./");
			} else {
				session.setAttribute("errorMessages", messages);
				request.setAttribute("message", message);
				request.getRequestDispatcher("newmessage.jsp").forward(request, response);
			}
		}


	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String message = request.getParameter("text");
		HttpSession session = request.getSession();
		session.getAttribute("loginUser");

		if (StringUtils.isEmpty(message) == true) {
			messages.add("投稿内容を入力してください");
		}
		if (1000 < message.length()) {
			messages.add("1000文字以下で入力してください");
			request.setAttribute("text", message);
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
