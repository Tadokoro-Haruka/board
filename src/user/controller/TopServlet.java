package user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.beans.Message;
import user.service.MessageService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
	ServletException {
		//User user = (User) 
				request.getSession().getAttribute("loginUser");

		List<Message> messages = new MessageService().getMessage();
		//System.out.println(messages.get(0).getText());
		request.setAttribute("messages", messages);

		//↓の一文で、「top.jspでrequest領域に入ってるものを表示してください」ってことになる
		request.getRequestDispatcher("top.jsp").forward(request, response);
	}
}
