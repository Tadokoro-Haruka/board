package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.beans.User;
import user.service.UserService;


@WebServlet(urlPatterns = { "/usersetting" })
public class UserSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException {

		String user_id = request.getParameter("id");
		int id = Integer.parseInt(user_id);
		User editUser = new UserService().getUser(id);
		request.setAttribute("editUser", editUser);

		request.getRequestDispatcher("usersetting.jsp").forward(request, response);


//		//System.out.println(request.getParameter("id"));
//		HttpSession session = request.getSession();
//		User loginUser = (User) session.getAttribute("loginUser");
//
//		if (session.getAttribute("editUser") == null) {
//			User editUser = new UserService().getUsers(loginUser.getId());
//			session.setAttribute("editUser", editUser);
//			response.sendRedirect("./");
//			request.getRequestDispatcher("usersetting.jsp").forward(request, response);
//		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		User editUser = (User) session.getAttribute("editUser");

//		request.setId(editUser.getId());
//
//		User users = new User();
//		request.getRequestDispatcher("id");

	}


}
