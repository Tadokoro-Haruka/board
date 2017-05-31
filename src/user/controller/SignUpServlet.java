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

import user.beans.User;
import user.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User user = new User();
		user.setLogin_id(request.getParameter("login_id"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setBranch_id(request.getParameter("branch_id"));
		user.setDepartment_id(request.getParameter("department_id"));


		if (isValid(request, messages) == true) {

			new UserService().register(user);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);

			request.setAttribute("user", user);
			request.getRequestDispatcher("signup.jsp").forward(request, response);

		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String branch_id = request.getParameter("branch_id");
		String department_id = request.getParameter("department_id");

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (login_id.length() < 6 || 20 < login_id.length()) {
			messages.add("ログインIDは6文字以上20文字以下で入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (password.length() < 6 || 255 < password.length()) {
			messages.add("パスワードは6文字以上255文字以下で入力してください");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		if (10 < name.length()) {
			messages.add("名前は10文字以下で入力してください");
		}
		if (StringUtils.isEmpty(branch_id) == true) {
			messages.add("支店を選択してください");
		}
		if (StringUtils.isEmpty(department_id) == true) {
			messages.add("部署・役職を選択してください");
		}


		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
