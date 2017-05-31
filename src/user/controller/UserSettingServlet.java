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
		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User editUser = new User();
		editUser.setId(Integer.parseInt(request.getParameter("id")));
		editUser.setLogin_id(request.getParameter("login_id"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		editUser.setBranch_id(request.getParameter("branch_id"));
		editUser.setDepartment_id(request.getParameter("department_id"));


		if (isValid(request, messages) == true) {

			new UserService().update(editUser);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);

			request.setAttribute("editUser", editUser);
			request.getRequestDispatcher("usersetting.jsp").forward(request, response);
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
