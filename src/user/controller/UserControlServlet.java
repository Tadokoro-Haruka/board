package user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.beans.User;
import user.service.UserService;

@WebServlet(urlPatterns = { "/usercontrol" })
public class UserControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

@Override
protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws IOException, ServletException {

	List<User> users = new UserService().getUsers();

	request.setAttribute("users", users);

	//System.out.println(users);
	request.getRequestDispatcher("usercontrol.jsp").forward(request, response);


	}
}